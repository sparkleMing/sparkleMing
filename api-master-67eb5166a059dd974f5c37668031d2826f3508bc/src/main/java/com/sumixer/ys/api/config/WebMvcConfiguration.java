package com.sumixer.ys.api.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.sumixer.ys.api.annotation.support.LoginUserHandlerResolver;
import com.sumixer.ys.api.core.Result;
import com.sumixer.ys.api.core.ResultCode;
import com.sumixer.ys.api.core.ServiceException;
import com.sumixer.ys.api.interceptor.ValidateTokenInterceptor;
import com.sumixer.ys.api.service.TokenService;
import com.sumixer.ys.api.service.UserService;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.servlet.ServletException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static com.sumixer.ys.api.core.ResultGenerator.responseResult;

/**
 * @author : coderWu
 * @date : Created on 14:25 2018/6/12
 */
@Configuration
public class WebMvcConfiguration extends WebMvcConfigurationSupport {

    private final Logger logger = LoggerFactory.getLogger(WebMvcConfiguration.class);

    @Autowired
    TokenService tokenService;
    @Autowired
    UserService userService;

    @Value("${spring.profiles.active}")
    private String env;

    /**
     * 使用alibaba fastjson
     * @param converters List<HttpMessageConverter<?>>
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        FastJsonConfig config = new FastJsonConfig();
        //保留空的字段
        config.setSerializerFeatures(SerializerFeature.WriteMapNullValue);

        List<MediaType> supportedMediaTypes = new ArrayList<>();
        supportedMediaTypes.add(MediaType.APPLICATION_JSON);
        supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);

        converter.setSupportedMediaTypes(supportedMediaTypes);
        converter.setDefaultCharset(Charset.forName("UTF-8"));
        converter.setFastJsonConfig(config);
        converters.add(converter);
    }

    /**
     * 统一异常处理
     * @param exceptionResolvers List<HandlerExceptionResolver>
     */
    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        exceptionResolvers.add((request, response, handler, e) -> {
                    Result result = new Result();
                    //业务失败的异常，如“账号或密码错误”
                    if (e instanceof ServiceException) {
                        result.setCode(ResultCode.FAIL).setMessage(e.getMessage());
                        logger.info(e.getMessage());
                    } else if (e instanceof NoHandlerFoundException) {
                        result.setCode(ResultCode.NOT_FOUND).setMessage("接口 [" + request.getRequestURI() + "] 不存在");
                    } else if (e instanceof ServletException) {
                        result.setCode(ResultCode.FAIL).setMessage(e.getMessage());
                    } else if (e instanceof WxErrorException) {
                        result.setCode(ResultCode.FAIL).setMessage("" + ((WxErrorException) e).getError().getErrorMsg());
                    } else if (e instanceof WxPayException) {
                        result.setCode(ResultCode.FAIL).setMessage("微信支付异常");
                        this.logger.error(((WxPayException) e).getErrCodeDes());
                    } else {
                        result.setCode(ResultCode.INTERNAL_SERVER_ERROR).setMessage("接口 [" + request.getRequestURI() + "] 内部错误，请联系管理员");
                        String message;
                        if (handler instanceof HandlerMethod) {
                            HandlerMethod handlerMethod = (HandlerMethod) handler;
                            message = String.format("接口 [%s] 出现异常，方法：%s.%s，异常摘要：%s",
                                    request.getRequestURI(),
                                    handlerMethod.getBean().getClass().getName(),
                                    handlerMethod.getMethod().getName(),
                                    e.getMessage());
                        } else {
                            message = e.getMessage();
                        }
                        logger.error(message, e.getMessage());
                    }
                    responseResult(response, result);
                    return new ModelAndView();
                }
        );
    }



    /**
     * 解决跨域问题
     *
     * @param registry CorsRegistry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                //放行哪些原始域
//                .allowedOrigins("http://wechat.yisongbld.com")
//                //是否发送Cookie信息
//                .allowCredentials(true)
//                //放行哪些原始域(请求方式)
//                .allowedMethods("GET","POST", "PUT")
//                //放行哪些原始域(头部信息)
//                .allowedHeaders("*")
//                //暴露哪些头部信息（因为跨域访问默认不能获取全部头部信息）
//                .exposedHeaders("*");
    }

    /**
     * 添加拦截器
     * token验证
     * @param registry InterceptorRegistry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //接口签名认证拦截器，该签名认证比较简单，实际项目中可以使用Json Web Token或其他更好的方式替代。
        //开发环境忽略签名认证
//        if (!"dev".equals(env)) {
            registry.addInterceptor(new ValidateTokenInterceptor(tokenService))
                    //添加不拦截路径
                    .excludePathPatterns("/error")
                    .excludePathPatterns("/auth/**")
                    .excludePathPatterns("/payments/weixin/notify")
                    .excludePathPatterns("/messages/**")
                    .excludePathPatterns("/stores/**")
                    .excludePathPatterns("/goods/**")
                    .excludePathPatterns("/categories/**")
                    .excludePathPatterns("/wechat/jssdk/**");
//        }
    }

    @Override
    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new LoginUserHandlerResolver(userService));
    }
}
