package com.my.website.cfg;

import com.my.AbstractMvcConfig;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.SortHandlerMethodArgumentResolver;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.ViewResolver;

import java.util.List;

import static org.springframework.context.annotation.ComponentScan.Filter;

@Configuration
@ComponentScan(basePackages = "com.my.website", includeFilters = @Filter(Controller.class), useDefaultFilters = false)
public class MvcConfig extends AbstractMvcConfig{

    @Override
    protected String resourceContext(){
        return Constants.SERVLET_NAME;
    }

    @Bean(name = Constants.SERVLET_NAME + "ViewResolver")
    public ViewResolver jspResolver(){
        return super.jpaResolver();
    }

    @Bean
    public MessageSource messageSource(){
        return super.messageSource();
    }

    @Override
    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(sortResolver());
        argumentResolvers.add(pageableResolver());
    }

    @Bean
    public PageableHandlerMethodArgumentResolver pageableResolver() {
        return new PageableHandlerMethodArgumentResolver(sortResolver());
    }

    @Bean
    public SortHandlerMethodArgumentResolver sortResolver() {
        return new SortHandlerMethodArgumentResolver();
    }

    /**
     * Handles favicon.ico requests assuring no <code>404 Not Found</code> error is returned.
     */
    @Controller
    static class FaviconController{
        @RequestMapping("favicon.ico")
        String favicon(){
            return "forward:/resources/images/favicon.ico";
        }
    }
}
