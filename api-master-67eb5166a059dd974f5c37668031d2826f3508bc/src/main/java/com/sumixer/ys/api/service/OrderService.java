package com.sumixer.ys.api.service;

import com.sumixer.ys.api.config.Constants;
import com.sumixer.ys.api.dao.YsOrderItemMapper;
import com.sumixer.ys.api.dao.YsOrderItemSqlProvider;
import com.sumixer.ys.api.dao.YsOrderMapper;
import com.sumixer.ys.api.entity.*;
import com.sumixer.ys.api.utils.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.List;

/**
 * @author : coderWu
 * @date : Created on 20:34 2018/6/15
 */
@Service
public class OrderService {

    @Autowired
    DataSourceTransactionManager transactionManager;
    @Autowired
    YsOrderMapper ysOrderMapper;
    @Autowired
    YsOrderItemMapper ysOrderItemMapper;
    @Autowired
    GoodsService goodsService;

    /**
     * 添加新的订单
     * 启用事务
     * @param order Order
     * @param items List<YsOrderItem>
     */
    @Transactional
    public void save(YsOrder order, List<YsOrderItem> items) {
        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
        definition.setName("new order");
        definition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = transactionManager.getTransaction(definition);
        try {
            YsGoodsDiscount discount;
            double orderPrice = 0;
            double orderDiscount = 0;
            String orderId = order.getOrderId();
            for (YsOrderItem item : items) {
                discount = goodsService.findDiscountByGoodsId(item.getGoodsId());
                item.setDiscount(discount == null ? 0 : discount.getDiscount());
                item.setPayPrice((item.getPrice() - item.getDiscount()) >= 0 ?
                        (item.getPrice() - item.getDiscount()) : 0);
                item.setOrderId(orderId);
                orderPrice += item.getPrice() * item.getCount();
                orderDiscount += item.getCount() * item.getDiscount();
            }
            order.setPrice(NumberUtils.formatDouble1(orderPrice));
            order.setDicount(NumberUtils.formatDouble1(orderDiscount));
            double payPrice = orderPrice + order.getSendMoney() - orderDiscount;
            order.setPayPrice(NumberUtils.formatDouble1(payPrice >= 0 ? payPrice : 0));
            order.setSend(Constants.NOT_CONFIRM_ORDER);
            ysOrderMapper.insert(order);
            ysOrderItemMapper.insertList(items);
            transactionManager.commit(status);
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw e;
        }
    }

    /**
     * 通过orderId 获取订单详情
     * @param orderId String
     * @return YsOrder
     */
    public YsOrder findByOrderId(String orderId) {
        if (StringUtils.isEmpty(orderId)) {
            return null;
        }
        YsOrderExample example = new YsOrderExample();
        YsOrderExample.Criteria criteria = example.createCriteria();
        criteria.andStoreIdEqualTo(orderId);
        List<YsOrder> orders = ysOrderMapper.selectByExample(example);
        return orders.size() > 0 ? orders.get(0) : null;
    }

    /**
     * 查询用户所有信息
     * @param userId String
     * @return List<YsOrder>
     */
    public List<YsOrder> findAllByUserId(String userId) {
        if (StringUtils.isEmpty(userId)) {
            return null;
        }
        YsOrderExample example = new YsOrderExample();
        YsOrderExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        return ysOrderMapper.selectByExample(example);
    }

    /**
     * 根据用户id和订单id查询订单，与订单状态无关
     * @param userId String
     * @param orderId String
     * @return YsOrder
     */
    public YsOrder findByUserIdAndOrderId(String userId, String orderId) {
        if (StringUtils.isAnyEmpty(userId, orderId)) {
            return null;
        }
        YsOrderExample example = new YsOrderExample();
        YsOrderExample.Criteria criteria = example.createCriteria();
        criteria.andOrderIdEqualTo(orderId).andUserIdEqualTo(userId);
        List<YsOrder> orders = ysOrderMapper.selectByExample(example);
        return orders.size() > 0 ? orders.get(0) : null;
    }

    /**
     * 根据用户id订单id查询未支付订单
     * @param userId String
     * @param orderId String
     * @return YsOrder || null
     */
    public YsOrder findNotPayOrder(String userId, String orderId) {
        if (StringUtils.isAnyEmpty(userId, orderId)) {
            return null;
        }
        YsOrderExample example = new YsOrderExample();
        YsOrderExample.Criteria criteria = example.createCriteria();
        criteria.andOrderIdEqualTo(orderId).andUserIdEqualTo(userId)
                .andPayStatusEqualTo(Constants.NOT_PAY_ORDER);
        List<YsOrder> orders = ysOrderMapper.selectByExample(example);
        return orders.size() > 0 ? orders.get(0) : null;
    }

    public List<YsOrderItem> findAllOrderItems(String orderId) {
        if (StringUtils.isEmpty(orderId)) {
            return null;
        }
        YsOrderItemExample example = new YsOrderItemExample();
        YsOrderItemExample.Criteria criteria = example.createCriteria();
        criteria.andOrderIdEqualTo(orderId);
        return ysOrderItemMapper.selectByExample(example);
    }

    /**
     * 支付订单
     * @param order
     */
    public void pay(YsOrder order) {
        if (null != order) {
            order.setSend(Constants.NOT_SEND_ORDER);
            YsOrderExample example = new YsOrderExample();
            YsOrderExample.Criteria criteria = example.createCriteria();
            criteria.andOrderIdEqualTo(order.getOrderId());
            ysOrderMapper.updateByExampleSelective(order, example);
        }
    }

    /**
     * 确认订单
     * @param order
     */
    public void confirm(YsOrder order) {
        if (order != null) {
            order.setSend(Constants.CONFIRMED_ORDER);
            YsOrderExample example = new YsOrderExample();
            YsOrderExample.Criteria criteria = example.createCriteria();
            criteria.andOrderIdEqualTo(order.getOrderId());
            ysOrderMapper.updateByExampleSelective(order, example);
        }
    }

    public void delete(String userId, String orderId) {
        if (StringUtils.isAnyEmpty(userId, orderId)) {
            return;
        }
        YsOrderExample example = new YsOrderExample();
        YsOrderExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId).andOrderIdEqualTo(orderId);
        ysOrderMapper.deleteByExample(example);
    }

}
