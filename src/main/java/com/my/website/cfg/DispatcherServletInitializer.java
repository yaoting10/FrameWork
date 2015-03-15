package com.my.website.cfg;

import com.my.AbstractAnnotationServletInitializer;
import com.my.ApplicationConfig;
import com.my.core.cfg.JpaConfig;
import com.my.security.MultiHttpSecurityConfig;

public class DispatcherServletInitializer extends AbstractAnnotationServletInitializer{

    @Override
    protected Class<?>[] getRootConfigClasses(){
        return new Class<?>[]{ApplicationConfig.class, JpaConfig.class, MultiHttpSecurityConfig.class};
    }
    @Override
    protected String getServletName(){
        return Constants.SERVLET_NAME;
    }

    @Override
    protected String[] getServletMappings(){
        return new String[]{"/"};
    }

    @Override
    protected Class<?>[] getServletConfigClasses(){
        return new Class<?>[]{MvcConfig.class};
    }

}