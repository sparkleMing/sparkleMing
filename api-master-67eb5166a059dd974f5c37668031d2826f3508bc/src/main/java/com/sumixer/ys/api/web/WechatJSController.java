package com.sumixer.ys.api.web;

import com.sumixer.ys.api.core.Result;
import com.sumixer.ys.api.core.ResultGenerator;
import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : coderWu
 * @date : Created on 16:20 2018/6/27
 */
@RequestMapping("/wechat/jssdk")
@RestController
public class WechatJSController {

    @Autowired
    private WxMpService wxMpService;

    @GetMapping("/signature")
    public Result signature(@RequestParam(value = "url") String url) throws WxErrorException {
        System.out.println(url);
        WxJsapiSignature signature = wxMpService.createJsapiSignature(url);
        return ResultGenerator.success(signature);
    }

}
