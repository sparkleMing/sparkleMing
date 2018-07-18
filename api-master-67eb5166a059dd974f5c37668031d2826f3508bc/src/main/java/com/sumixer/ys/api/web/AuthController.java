package com.sumixer.ys.api.web;

import com.sumixer.ys.api.config.Constants;
import com.sumixer.ys.api.core.Result;
import com.sumixer.ys.api.core.ResultGenerator;
import com.sumixer.ys.api.core.Token;
import com.sumixer.ys.api.entity.YsUser;
import com.sumixer.ys.api.service.TokenService;
import com.sumixer.ys.api.service.UserService;
import com.sumixer.ys.api.utils.IPUtils;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;


/**
 * @author : coderWu
 * @date : Created on 18:38 2018/6/12
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    /**
     * 微信登陆认证
     * @param code 微信授权返回code
     * @return Result
     * @throws WxErrorException WxErrorException
     */
    @PostMapping("/weixin")
    public Result weiXin(@RequestParam(value = "code") String code, HttpServletRequest request) throws WxErrorException {
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        String openid = wxMpOAuth2AccessToken.getOpenId();
        if (StringUtils.isEmpty(openid)) {
            return ResultGenerator.fail("微信登陆失败");
        }
        YsUser ysUser = userService.findByOpenid(openid);
        if (ysUser == null) {
            return ResultGenerator.fail("该微信号还未绑定用户");
        }
        if (Constants.YS_USER_FROZEN == ysUser.getScore()) {
            return ResultGenerator.fail("账户已冻结");
        }
        ysUser.setLastLoginTime(new Date());
        ysUser.setLastLoginIp(IPUtils.getIpAddress(request));
        userService.update(ysUser);
        return ResultGenerator.success(tokenService.create(ysUser.getUserId()));
    }

    /**
     * 刷新token
     * @param tokenValue token值
     * @return Result 包含新的token
     */
    @PostMapping("/")
    public Result refresh(@RequestParam(value = "token") String tokenValue) {
        Token token = tokenService.generate(tokenValue);
        if (null == token) {
            return ResultGenerator.fail("token刷新失败");
        } else {
            return ResultGenerator.success(token);
        }
    }


}
