package com.sumixer.ys.api.annotation.support;

import com.sumixer.ys.api.annotation.LoginUser;
import com.sumixer.ys.api.config.Constants;
import com.sumixer.ys.api.entity.YsUser;
import com.sumixer.ys.api.service.UserService;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author : coderWu
 * @date : Created on 9:14 2018/6/13
 */
public class LoginUserHandlerResolver implements HandlerMethodArgumentResolver  {

    private UserService userService;

    public LoginUserHandlerResolver(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().isAssignableFrom(YsUser.class) &&
                methodParameter.hasParameterAnnotation(LoginUser.class);
    }

    @Override
    public YsUser resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        String userid = (String) nativeWebRequest.getAttribute(Constants.INNER_USER_ID, RequestAttributes.SCOPE_REQUEST);
        return userService.findByUserId(userid);
    }
}
