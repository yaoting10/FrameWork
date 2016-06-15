package com.huobangzhu.api.v1.cfg;

import com.huobangzhu.AbstractMvcConfig;
import com.huobangzhu.api.v1.controller.RespondableExceptionResolver;
import com.huobangzhu.api.v1.security.SessionTokenFactory;
import com.huobangzhu.api.v1.security.SessionTokenInterceptor;
import com.huobangzhu.api.v1.security.SessionTokenValidator;
import com.huobangzhu.foundation.model.TokenFactory;
import com.google.common.collect.Lists;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import java.util.List;

import static org.springframework.context.annotation.ComponentScan.Filter;

@Configuration
@ComponentScan(basePackages = "com.dsecet.api.v1", includeFilters = @Filter(Controller.class), useDefaultFilters = false)
public class MvcConfig extends AbstractMvcConfig{

    @Override
    protected String resourceContext(){
        return Constants.SERVLET_NAME;
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry){
        SessionTokenInterceptor tokenInterceptor = new SessionTokenInterceptor();
        tokenInterceptor.setSessionTokenValidator(configSessionTokenValidator());
        tokenInterceptor.setTokenFactory(configTokenFactory());

        List<String> interceptorUrls = Lists.newArrayList();
        interceptorUrls.add("/security/**");
        interceptorUrls.add("/user_center/**");
        List<String> excludeUrls = Lists.newArrayList();
        excludeUrls.add("/health/");
        excludeUrls.add("/sms/verify_code");
        excludeUrls.add("/sms/ticket");
        excludeUrls.add("/register");
        excludeUrls.add("/signin");
        excludeUrls.add("/loans");
        excludeUrls.add("/loan/");
        excludeUrls.add("/claims/");
        excludeUrls.add("/repayments/");
        excludeUrls.add("/version/");
        registry.addInterceptor(tokenInterceptor).addPathPatterns(interceptorUrls.stream().toArray(String[]::new)).excludePathPatterns(excludeUrls.stream().toArray(String[]::new));

        super.addInterceptors(registry);
    }

    @Override
    protected void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers){
        RespondableExceptionResolver respondableExceptionResolver = new RespondableExceptionResolver();

        exceptionResolvers.add(respondableExceptionResolver);

        super.configureHandlerExceptionResolvers(exceptionResolvers);
    }

    @Bean
    public MessageSource messageSource(){
        return super.messageSource();
    }

    @Bean(name = "configTokenFactory")
    public TokenFactory<Long> configTokenFactory(){
        return new SessionTokenFactory();
    }


    @Bean
    public SessionTokenValidator configSessionTokenValidator(){
        return new SessionTokenValidator();
    }
}
