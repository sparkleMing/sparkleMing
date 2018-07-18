package com.sumixer.ys.api.web;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.sumixer.ys.api.core.Result;
import com.sumixer.ys.api.core.ResultGenerator;
import com.sumixer.ys.api.service.MessageCodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author : coderWu
 * @date : Created on 16:09 2018/6/13
 */
@RestController
@RequestMapping("/messages")
public class MessageCodeController {

    private final Logger logger = LoggerFactory.getLogger(MessageCodeController.class);

    @Autowired
    private MessageCodeService messageCodeService;

    @PostMapping("/wechat")
    public Result newCode(@RequestParam(value = "phone") String phone) {
        return send(phone, MessageCodeService.CHECK_ID);
    }

    /**
     * 发送函数
     * @param phone 电话
     * @param type 短信模板
     * @return Result
     */
    private Result send(String phone, String type) {
        if (messageCodeService.hasSend(phone)) {
            return ResultGenerator.fail("60秒内不要重复发送");
        }
        try {
            SendSmsResponse response;
            response = messageCodeService.create(phone, type);
            if (response.getCode().equals(MessageCodeService.OK_CODE)){
                return ResultGenerator.success("验证码已发送，有效期一分钟");
            } else if (response.getCode().equals(MessageCodeService.ILLEGAL_PHONE)) {
                return ResultGenerator.fail("非法手机号");
            } else {
                return ResultGenerator.fail("验证码发送失败");
            }
        } catch (ClientException e) {
            this.logger.error("验证码发送失败，电话号码：%s，错误码：%s，错误信息：%s，信息：%s",
                    phone, e.getErrCode(), e.getErrMsg(), e.getMessage());
            return ResultGenerator.fail(e.getErrCode());
        }
    }
}
