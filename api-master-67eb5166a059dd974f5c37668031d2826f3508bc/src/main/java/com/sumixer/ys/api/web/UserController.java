package com.sumixer.ys.api.web;

import com.sumixer.ys.api.annotation.LoginUser;
import com.sumixer.ys.api.core.Result;
import com.sumixer.ys.api.core.ResultGenerator;
import com.sumixer.ys.api.entity.YsUser;
import com.sumixer.ys.api.entity.YsWxUser;
import com.sumixer.ys.api.service.MessageCodeService;
import com.sumixer.ys.api.service.UserService;
import com.sumixer.ys.api.service.WxUserService;
import com.sumixer.ys.api.utils.IPUtils;
import com.sumixer.ys.api.utils.RandomUtils;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : coderWu
 * @date : Created on 15:27 2018/6/13
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private WxMpService wxMpService;
    @Autowired
    private UserService userService;
    @Autowired
    private WxUserService wxUserService;
    @Autowired
    private MessageCodeService messageCodeService;
    @Value("${spring.profiles.active}")
    private String env;

    /**
     * 微信用户绑定
     * @param phone 手机号
     * @param messageCode 短信验证码
     * @param code 微信登陆返回code
     * @param request HttpServletRequest
     * @return Result
     * @throws WxErrorException WxErrorException
     */
    @PostMapping("/weixin")
    public Result newUser(@RequestParam(value = "phone") String phone,
                          @RequestParam(value = "messageCode") String messageCode,
                          @RequestParam(value = "code") String code, HttpServletRequest request) throws WxErrorException {

        if (!"dev".equals(env) && !messageCodeService.check(phone, messageCode)) {
            return ResultGenerator.fail("验证码错误");
        }

        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        WxMpUser wxMpUser = wxMpService.oauth2getUserInfo(wxMpOAuth2AccessToken, null);
        wxMpUser.setOpenId(wxMpOAuth2AccessToken.getOpenId());
        wxMpUser.setUnionId(wxMpOAuth2AccessToken.getUnionId());
        YsWxUser ysWxUser = new YsWxUser(wxMpUser);
        if (StringUtils.isEmpty(ysWxUser.getOpenid())) {
            return ResultGenerator.fail("微信绑定失败");
        }

        wxUserService.updateOrAdd(ysWxUser);
        YsUser ysUser1 = userService.findByOpenid(ysWxUser.getOpenid());
        if (ysUser1 != null && !ysUser1.getPhone().equals(phone)) {
            return ResultGenerator.fail("该微信号已绑定");
        }
        YsUser ysUser = userService.findByPhone(phone);
        if (ysUser == null) {
            //新用户
            ysUser = new YsUser();
            ysUser.setPhone(phone);
            ysUser.setUserId(RandomUtils.string("userid", ysUser.getNickname()));
            ysUser.setNickname(ysWxUser.getNickname());
            ysUser.setLastLoginTime(new Date());
            ysUser.setLastLoginIp(IPUtils.getIpAddress(request));
            ysUser.setCreateTime(new Date());
            ysUser.setOpenid(ysWxUser.getOpenid());
            userService.save(ysUser);
        } else {
            //老用户
            ysUser.setLastLoginTime(new Date());
            ysUser.setLastLoginIp(IPUtils.getIpAddress(request));
            ysUser.setOpenid(ysWxUser.getOpenid());
            userService.update(ysUser);
        }
        return ResultGenerator.success("绑定成功");
    }

    @DeleteMapping("/weixin")
    public Result weixinLogout(@LoginUser YsUser user) {
        user.setOpenid("useless");
        userService.update(user);
        return ResultGenerator.success("退出成功");
    }

    @GetMapping("/weixin/info")
    public Result weiXinInfo(@LoginUser YsUser user) {
        YsWxUser wxUser = wxUserService.findByOpenid(user.getOpenid());
        Map<String, Object> result = new HashMap<>(2);
        result.put("wxuser", wxUser);
        result.put("user", user);
        return ResultGenerator.success(result);
    }

}
