package com.sumixer.ys.api.service;

import com.github.binarywang.wxpay.bean.request.WxPayRefundRequest;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayOrderCloseResult;
import com.github.binarywang.wxpay.bean.result.WxPayRefundResult;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderResult;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.sumixer.ys.api.config.Constants;
import com.sumixer.ys.api.core.Result;
import com.sumixer.ys.api.core.ResultGenerator;
import com.sumixer.ys.api.dao.YsOrderMapper;
import com.sumixer.ys.api.entity.YsOrder;
import com.sumixer.ys.api.entity.YsOrderExample;
import com.sumixer.ys.api.entity.YsUser;
import com.sumixer.ys.api.utils.IPUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : coderWu
 * @date : Created on 21:59 2018/6/15
 */
@Service
public class WepayService {

    private Logger logger = LoggerFactory.getLogger(WepayService.class);

    @Autowired
    private WxPayService wxPayService;
    @Autowired
    private YsOrderMapper ysOrderMapper;

    public WxPayUnifiedOrderResult createOrder(YsUser ysUser, YsOrder order, HttpServletRequest request) throws WxPayException {
        return createOrder(ysUser, order, "", request);
    }

    public WxPayUnifiedOrderResult createOrder(YsUser ysUser, YsOrder order, String attach, HttpServletRequest request) throws WxPayException {
        LocalDateTime now = LocalDateTime.now();
        WxPayUnifiedOrderRequest payRequest = new WxPayUnifiedOrderRequest();
        payRequest.setDeviceInfo("WEB");
        payRequest.setBody("壹送便利店微信商城订单");
        payRequest.setOutTradeNo(order.getOrderId());
        payRequest.setTotalFee((int) (order.getPayPrice() * 100));
        payRequest.setAttach(attach);
        payRequest.setSpbillCreateIp(IPUtils.getIpAddress(request));
        payRequest.setTimeStart("" + now.getYear() + String.format("%02d", now.getMonth().getValue()) +
                String.format("%02d",now.getDayOfMonth()) + String.format("%02d", now.getHour()) +
                String.format("%02d", now.getMinute()) + String.format("%02d", now.getSecond()));
        LocalDateTime expireTime = now.plusMinutes(10);
        payRequest.setTimeExpire("" + expireTime.getYear() + String.format("%02d", expireTime.getMonth().getValue()) +
                String.format("%02d",expireTime.getDayOfMonth()) + String.format("%02d", expireTime.getHour()) +
                String.format("%02d", expireTime.getMinute()) + String.format("%02d", expireTime.getSecond()));
        payRequest.setNotifyUrl(Constants.DOMAIN + "/payment/weixin/notify");
        payRequest.setTradeType(WxPayConstants.TradeType.JSAPI);
        payRequest.setOpenid(ysUser.getOpenid());
        return wxPayService.unifiedOrder(payRequest);
    }

    public WxPayRefundResult refundOrder(YsUser ysUser, YsOrder order,String refundDesc, HttpServletRequest request) throws WxPayException {
        WxPayRefundRequest refundRequest=new WxPayRefundRequest();
        refundRequest.setDeviceInfo("WEB");
        refundRequest.setOutTradeNo(order.getOrderId());
        refundRequest.setTotalFee((int)(order.getPayPrice() * 100));
        refundRequest.setRefundFee((int)(order.getPayPrice() * 100));
        refundRequest.setOutRefundNo(order.getOrderId());
        refundRequest.setRefundDesc(refundDesc);
        refundRequest.setNotifyUrl(Constants.DOMAIN+"/payment/weixin/refund/notify");
        return wxPayService.refund(refundRequest);
    }

    public void closeOrder(YsOrder order) throws WxPayException {
        this.wxPayService.closeOrder(order.getOrderId());
        order.setPayStatus(Constants.CANCLE_PAY);
        YsOrderExample example = new YsOrderExample();
        YsOrderExample.Criteria criteria = example.createCriteria();
        criteria.andOrderIdEqualTo(order.getOrderId());
        ysOrderMapper.updateByExampleSelective(order, example);
    }

    public Result transformResult(WxPayUnifiedOrderResult result) {
        if (result == null) {
            return ResultGenerator.fail("支付异常");
        }
        if (result.getReturnCode().equals(WxPayConstants.ResultCode.SUCCESS)) {
            Map<String, String> returnResult = new HashMap<>(4);
            returnResult.put("appId", result.getAppid());
            returnResult.put("timeStamp", "" + (System.currentTimeMillis() / 1000));
            returnResult.put("nonceStr", result.getNonceStr());
            returnResult.put("package", "prepay_id=" + result.getPrepayId());
            returnResult.put("paySign", result.getSign());
            return ResultGenerator.success(returnResult);
        } else {
            this.logger.error("微信支付失败错误码：{}，错误描述:{}", result.getErrCode(), result.getErrCodeDes());
            return ResultGenerator.fail("支付异常");
        }
    }


    public Result transformRefundResult(WxPayRefundResult result) {
        if (result == null) {
            return ResultGenerator.fail("退款异常");
        }
        if (result.getReturnCode().equals(WxPayConstants.ResultCode.SUCCESS)) {
            if(result.getResultCode().equals(WxPayConstants.ResultCode.FAIL))
                return ResultGenerator.fail("退款失败");
            Map<String, String> returnResult = new HashMap<>(12);
            returnResult.put("appId", result.getAppid());
            returnResult.put("resultCode",result.getResultCode());
            returnResult.put("mchId",result.getMchId());
            returnResult.put("transactionId",result.getTransactionId());
            returnResult.put("outTradeNo",result.getOutTradeNo());
            returnResult.put("outRefundNo",result.getOutRefundNo());
            returnResult.put("refundId",result.getRefundId());
            returnResult.put("refundFee",result.getRefundFee());
            returnResult.put("totalFee",result.getTotalFee());
            returnResult.put("cashFee",result.getCashFee());
            returnResult.put("nonceStr", result.getNonceStr());
            returnResult.put("paySign", result.getSign());
            return ResultGenerator.success(returnResult);
        } else {
            this.logger.error("微信退款失败错误码：{}，错误描述:{}", result.getErrCode(), result.getErrCodeDes());
            return ResultGenerator.fail("退款异常");
        }
    }
}
