package com.sumixer.ys.api.web;

import com.github.binarywang.wxpay.bean.notify.WxPayNotifyResponse;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.notify.WxPayRefundNotifyResult;
import com.github.binarywang.wxpay.bean.result.WxPayOrderQueryResult;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderResult;
import com.github.binarywang.wxpay.bean.result.WxPayRefundResult;
import com.github.binarywang.wxpay.bean.result.WxPayRefundQueryResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.sumixer.ys.api.annotation.LoginUser;
import com.sumixer.ys.api.config.Constants;
import com.sumixer.ys.api.core.Result;
import com.sumixer.ys.api.core.ResultGenerator;
import com.sumixer.ys.api.entity.YsOrder;
import com.sumixer.ys.api.entity.YsStore;
import com.sumixer.ys.api.entity.YsUser;
import com.sumixer.ys.api.service.OrderService;
import com.sumixer.ys.api.service.StoreService;
import com.sumixer.ys.api.service.WepayService;
import com.sumixer.ys.api.service.YlyService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author : coderWu
 * @date : Created on 10:38 2018/6/16
 */
@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private StoreService storeService;
    @Autowired
    private WepayService wepayService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private WxPayService wxPayService;
    @Autowired
    private YlyService ylyService;

    @PostMapping("/orders/wepay")
    public Result wepay(@LoginUser YsUser ysUser,
            @RequestParam("orderId") String orderId, HttpServletRequest request) throws WxPayException {
        YsOrder order = orderService.findNotPayOrder(ysUser.getUserId(), orderId);
        if (order == null) {
            return ResultGenerator.fail("订单不存在");
        }
        WxPayUnifiedOrderResult result = wepayService.createOrder(ysUser, order, request);
        return wepayService.transformResult(result);
    }

    @PostMapping("/orders/refund")
    public Result refund(@LoginUser YsUser ysUser,
                         @RequestParam("orderId") String orderId, HttpServletRequest request) throws WxPayException {
        YsOrder order = orderService.findByUserIdAndOrderId(ysUser.getUserId(),orderId);
        if((order.getPayTime().getTime()-(new Date()).getTime())>2*60*1000)
            return ResultGenerator.fail("商品已送出超过两分钟，订单无法取消，请耐心等待商品送达");
        else{
            order.setPayStatus(Constants.REQUEST_REFUND_ORDER);
            orderService.pay(order);
            YsStore store=storeService.findByStoreId(order.getStoreId());
            String store_phone=store.getContactPhone();
            return ResultGenerator.success("请求已发给商家，等待商家处理");
        }
    }



    @PostMapping("/orders/wepay/fail")
    public Result wepayFail(@LoginUser YsUser ysUser,
                        @RequestParam("orderId") String orderId, HttpServletRequest request) throws WxPayException {
        YsOrder order = orderService.findNotPayOrder(ysUser.getUserId(), orderId);
        if (order == null) {
            return ResultGenerator.fail("订单不存在");
        }
        wepayService.closeOrder(order);
        return ResultGenerator.success("关闭成功");
    }

    @PostMapping("/orders/arrivepay")
    public Result arrivePay(@LoginUser YsUser ysUser,
                            @RequestParam("orderId") String orderId) {
        YsOrder order = orderService.findNotPayOrder(ysUser.getUserId(), orderId);
        if (order == null) {
            return ResultGenerator.fail("订单不存在");
        }
        order.setPayType(Constants.ARRIVED_PAY);
        order.setPayStatus(Constants.ARRIVED_PAY_STATUS);
        order.setPayTime(new Date());
        orderService.pay(order);
        ylyService.print(order);
        return ResultGenerator.success("请货到付款");
    }

    @PostMapping("/weixin/notify")
    public WxPayNotifyResponse wepayNotify(@RequestBody String xmlData) throws WxPayException {
        WxPayOrderNotifyResult payResult = wxPayService.parseOrderNotifyResult(xmlData);
        if (payResult != null && !StringUtils.isEmpty(payResult.getOutTradeNo())) {
            //订单状态确认
            WxPayOrderQueryResult confirmResult = wxPayService.queryOrder(null, payResult.getOutTradeNo());
            if (!StringUtils.isEmpty(confirmResult.getOutTradeNo()) &&
                    confirmResult.getOutTradeNo().equals(payResult.getOutTradeNo())) {
                YsOrder order = orderService.findByOrderId(payResult.getOutTradeNo());
                order.setPayType(Constants.ONLINE_PAY);
                order.setPayStatus(Constants.PAY_ORDER);
                order.setPayTime(new Date());
                orderService.pay(order);
                ylyService.print(order);
            }
        }
        WxPayNotifyResponse response = new WxPayNotifyResponse();
        response.setReturnCode(Constants.WEPAY_NOTIFY_SUCCESS);
        return response;
    }

    @PostMapping("/weixin/refund/notify")
    public WxPayNotifyResponse refundNotify(@RequestBody String xmlData) throws WxPayException {
        WxPayRefundNotifyResult refundResult = wxPayService.parseRefundNotifyResult(xmlData);
        if (refundResult != null && !StringUtils.isEmpty(refundResult.getReqInfo().getOutTradeNo())) {
            //退款状态确认
            WxPayRefundQueryResult confirmResult = wxPayService.refundQuery(refundResult.getReqInfo().getTransactionId(), refundResult.getReqInfo().getOutTradeNo(),refundResult.getReqInfo().getOutRefundNo(),refundResult.getReqInfo().getRefundId());
            if (!StringUtils.isEmpty(confirmResult.getOutTradeNo()) &&
                    confirmResult.getOutTradeNo().equals(refundResult.getReqInfo().getOutTradeNo())) {
                YsOrder order = orderService.findByOrderId(refundResult.getReqInfo().getOutTradeNo());
                order.setPayStatus(Constants.REFUND_ORDER);
                orderService.pay(order);
                ylyService.print(order);
            }
        }
        WxPayNotifyResponse response = new WxPayNotifyResponse();
        response.setReturnCode(Constants.REFUND_NOTIFY_SUCCESS);
        return response;
    }

}
