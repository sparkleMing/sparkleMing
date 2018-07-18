package com.sumixer.ys.api.service;

import com.sumixer.ys.api.config.Constants;
import com.sumixer.ys.api.dao.YsOrderItemMapper;
import com.sumixer.ys.api.dao.YsPrintMachineMapper;
import com.sumixer.ys.api.dao.YsUserAddressMapper;
import com.sumixer.ys.api.entity.*;
import com.sumixer.ys.api.sdk.yly.YlyMethods;
import com.sumixer.ys.api.utils.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 易联云打印机
 * @author : coderWu
 * @date : Created on 10:14 2018/6/16
 */
@Service
public class YlyService {

    private final Logger logger = LoggerFactory.getLogger(YlyService.class);

    @Value("${spring.profiles.active}")
    private String env;

    @Autowired
    YsOrderItemMapper ysOrderItemMapper;
    @Autowired
    YsPrintMachineMapper ysPrintMachineMapper;
    @Autowired
    YsUserAddressMapper ysUserAddressMapper;

    public void print(YsOrder order) {
        if (Constants.DEV_ENV.equals(env)) {
            this.logger.info(formatOrder(order));
            return;
        }
        if (order == null) {
            return;
        }
        YsPrintMachineExample machineExample = new YsPrintMachineExample();
        YsPrintMachineExample.Criteria machineCriteria = machineExample.createCriteria();
        machineCriteria.andStoreIdEqualTo(order.getStoreId()).andStatusEqualTo(Constants.ONLINE_PRINT_MACHINE);
        List<YsPrintMachine> machines = ysPrintMachineMapper.selectByExample(machineExample);
        String printContent = formatOrder(order);
        for (YsPrintMachine machine : machines) {
            YlyMethods.getInstance().init(
                    machine.getClientId(), machine.getClientSecret());
            if (machine.getUpdateTime().getTime() / 1000 + machine.getExpiresIn() <
                    (System.currentTimeMillis() / 1000)) {
                YlyMethods.getInstance().getFreedomToken();
                machine.setAccessToken(YlyMethods.token);
                machine.setUpdateTime(new Date());
                machine.setExpiresIn(2592000);
                machineExample = new YsPrintMachineExample();
                machineCriteria = machineExample.createCriteria();
                machineCriteria.andMachineCodeEqualTo(machine.getMachineCode());
                ysPrintMachineMapper.updateByExample(machine, machineExample);
            }

            YlyMethods.token = machine.getAccessToken();
            YlyMethods.getInstance().print(machine.getMachineCode(), printContent, order.getOrderId());
        }
    }

    private String formatOrder(YsOrder order) {
        YsOrderItemExample example = new YsOrderItemExample();
        YsOrderItemExample.Criteria criteria = example.createCriteria();
        criteria.andOrderIdEqualTo(order.getOrderId());
        List<YsOrderItem> items = ysOrderItemMapper.selectByExample(example);

        YsUserAddressExample addressExample = new YsUserAddressExample();
        YsUserAddressExample.Criteria addressCriteria = addressExample.createCriteria();
        addressCriteria.andAddressIdEqualTo(order.getAddressId());
        List<YsUserAddress> addresses = ysUserAddressMapper.selectByExample(addressExample);
        YsUserAddress address = addresses.get(0);

        StringBuilder sb = new StringBuilder();
        sb.append("<MN>2</MN>")
                .append("<audio>您有新的微信订单,7,0</audio>")
                .append("<FB><FS>微信商城订单</FS></FB>").append("\n")
                .append("下单时间:" ).append(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(order.getPayTime())).append("\n")
                .append("订单号:").append(order.getOrderId()).append("\n")
                .append("<center>--------------------------------</center>").append("\n")
                .append("<table><tr><td>商品名称</td><td>数量</td><td>金额</td></tr></table>");
        for (YsOrderItem item : items) {
            sb.append("<table><tr><td>")
            .append(item.getGoodsName()).append("</td><td>")
            .append(item.getCount()).append("</td><td>")
            .append(NumberUtils.formatDouble1(item.getPayPrice() * item.getCount()))
            .append("元</td></tr></table>");
        }
        sb.append("配送费:").append(order.getSendMoney()).append("元").append("\n");
        sb.append("合计:").append(order.getPayPrice()).append("元").append("\n");
        sb.append("付款方式:").append(order.getPayType() == Constants.ARRIVED_PAY ?
                "<FB><FS>货到付款</FS></FB>" : "已支付").append("\n");
        sb.append("<center>--------------------------------</center>").append("\n");


        sb.append("联系人:").append(address.getName()).append("\n")
        .append("联系电话:").append(address.getPhone()).append("\n")
        .append("地址:").append(address.getAddress()).append("\n");
        return sb.toString();
    }

}
