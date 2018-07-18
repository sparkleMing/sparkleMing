package com.sumixer.ys.api.web;

import com.github.binarywang.wxpay.exception.WxPayException;
import com.sumixer.ys.api.annotation.LoginUser;
import com.sumixer.ys.api.config.Constants;
import com.sumixer.ys.api.core.Result;
import com.sumixer.ys.api.core.ResultGenerator;
import com.sumixer.ys.api.entity.*;
import com.sumixer.ys.api.service.*;
import com.sumixer.ys.api.utils.JSONUtils;
import com.sumixer.ys.api.utils.LocationUtils;
import com.sumixer.ys.api.utils.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : coderWu
 * @date : Created on 16:34 2018/6/15
 */
@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private UserAddressService userAddressService;
    @Autowired
    private StoreService storeService;
    @Autowired
    private GoodsService goodsService;


    @PostMapping("/")
    public Result newOrder(@LoginUser YsUser ysUser,
                           @RequestParam(value = "note", required = false) String note,
                           @RequestParam(value = "address_id") String addressId,
                           @RequestParam(value = "store_id") String storeId,
                           @RequestParam(value = "pay_type") Integer payType,
                           @RequestParam(value = "order_items") String itemsList,
                           HttpServletRequest request) throws WxPayException {
        if (note != null && note.length() > 255) {
            return ResultGenerator.fail("备注太长，最多255个字符");
        }
        //支付方式判断
        boolean valuedPayType = payType != null && (payType == Constants.ONLINE_PAY ||
                payType == Constants.ARRIVED_PAY || payType == Constants.WALLET_PAY);
        if (!valuedPayType) {
            return ResultGenerator.fail("未选择支付方式");
        }
        if (payType == Constants.WALLET_PAY) {
            return ResultGenerator.fail("未开通");
        }
        //地址是否存在
        YsUserAddress address = userAddressService.findById(addressId);
        String[] addLocationArray;
        String addressLocation;
        if (address == null || (addressLocation = address.getLocation()) == null ||
                (addLocationArray = addressLocation.split(",")).length != 2 ||
                !ysUser.getUserId().equals(address.getUserId())) {
            return ResultGenerator.fail("地址不存在，或地址信息不完善");
        }
        //门店是否存在
        YsStore store = storeService.findByStoreId(storeId);
        String[] storeLocationArray;
        String storeLocation;
        if (store == null || (storeLocation = store.getLocaltion()) == null ||
                (storeLocationArray = storeLocation.split(",")).length != 2) {
            return ResultGenerator.fail("门店不存在");
        }
        //是否在配送范围
        double distance = LocationUtils.getDistance(
                addLocationArray[0], addLocationArray[1], storeLocationArray[0], storeLocationArray[1]);
        if (distance > store.getPath()) {
            return ResultGenerator.fail("超出配送范围");
        }
        //是否营业
        if (!storeService.isWeekDayOpen(store.getOpenDay(), store.getOpenBeginTime(), store.getOpenEndTime())
                || storeService.isInVacation(storeId)) {
            return ResultGenerator.fail("暂时闭店");
        }
        //物品是否存在
        List<YsOrderItem> items = JSONUtils.readOrderItems(itemsList);
        if (items.size() == 0) {
            return ResultGenerator.fail("空订单");
        }
        YsGoods goods;
        for (YsOrderItem item : items) {
            goods = goodsService.findStoreGoodsByGoodsId(storeId, item.getGoodsId());
            if (goods == null) {
                return ResultGenerator.fail("订单包含不存在商品");
            }
            item.setGoodsId(goods.getGoodsId());
            item.setPrice(goods.getSalePrice());
            item.setOrderItemId(RandomUtils.string("order_item", goods.getGoodsId()));
        }
        //满足所有条件创建订单
        YsOrder order = new YsOrder();
        order.setOrderId(RandomUtils.orderId());
        order.setPayType(payType);
        order.setNote(note);
        order.setCreateTime(new Date());
        order.setPayStatus(Constants.NOT_PAY_ORDER);
        order.setSendMoney(store.getSendMoney() == null ? 0 : store.getSendMoney());
        order.setAddressId(addressId);
        order.setUserId(ysUser.getUserId());
        order.setStoreId(storeId);
        orderService.save(order, items);
//        WxPayUnifiedOrderResult result = wepayService.createOrder(order, store.getStoreName(), request);
//        return wepayService.transforResult(result);
        return ResultGenerator.success(order.getOrderId());
    }

    @GetMapping("/")
    public Result orderList(@LoginUser YsUser ysUser) {
        return ResultGenerator.success(orderService.findAllByUserId(ysUser.getUserId()));
    }

    @GetMapping("/{orderId}")
    public Result order(@LoginUser YsUser ysUser,
                            @PathVariable(value = "orderId") String orderId) {
        Map<String, Object> result = new HashMap<>(2);
        YsOrder order = orderService.findByUserIdAndOrderId(ysUser.getUserId(), orderId);
        List<YsOrderItem> items = null;
        YsUserAddress address = null;
        if (order != null) {
            items = orderService.findAllOrderItems(orderId);
            address = userAddressService.findById(order.getAddressId());
        }
        result.put("order", order);
        result.put("items", items);
        result.put("address", address);
        return ResultGenerator.success(result);
    }

    @PutMapping("/confirmed/{orderId}")
    public Result confirm(@LoginUser YsUser ysUser,
                          @PathVariable(value = "orderId") String orderId) {
        YsOrder order = orderService.findByUserIdAndOrderId(ysUser.getUserId(), orderId);
        orderService.confirm(order);
        return ResultGenerator.success("已确认订单");
    }

    @DeleteMapping("/{orderId}")
    public Result delete(@LoginUser YsUser user,
                         @PathVariable(value = "orderId") String orderId) {
        orderService.delete(user.getUserId(),orderId);
        return ResultGenerator.success("删除成功");
    }

}
