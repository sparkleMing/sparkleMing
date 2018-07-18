package com.sumixer.ys.api.interceptor;

import com.alibaba.fastjson.JSON;
import com.sumixer.ys.api.config.Constants;
import com.sumixer.ys.api.core.Result;
import com.sumixer.ys.api.core.ResultCode;
import com.sumixer.ys.api.service.TokenService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.sumixer.ys.api.core.ResultGenerator.responseResult;
import static com.sumixer.ys.api.utils.IPUtils.getIpAddress;

/**
 * @author : coderWu
 * @date : Created on 8:55 2018/6/13
 */
public class ValidateTokenInterceptor implements HandlerInterceptor {

    private final Logger logger = LoggerFactory.getLogger(ValidateTokenInterceptor.class);

    private TokenService tokenService;

    public ValidateTokenInterceptor(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //验证token
        if ("/users/weixin/".equals(request.getServletPath()) &&
                !"DELETE".equals(request.getMethod())) {
            return true;
        }
        boolean pass = validateToken(request);
        if (pass) {
            return true;
        } else {
            logger.warn("签名认证失败，请求接口：{}，请求IP：{}，请求参数：{}",
                    request.getRequestURI(), getIpAddress(request), JSON.toJSONString(request.getParameterMap()));
            Result result = new Result();
            result.setCode(ResultCode.UNAUTHORIZED).setMessage("token认证失败");
            responseResult(response, result);
            return false;
        }
    }

    /**
     * token认证
     */
    private boolean validateToken(HttpServletRequest request) {
        String tokenValue = request.getHeader(Constants.TOKEN_KEY);
        if (StringUtils.isEmpty(tokenValue)) {
            return false;
        }
        String userid = tokenService.getId(tokenValue);
        if (StringUtils.isEmpty(userid)) {
            return false;
        }
        //将token添加到请求中，使得在LoginUser注解可以获取用户信息
        request.setAttribute(Constants.INNER_USER_ID, userid);
        return true;
    }

}
