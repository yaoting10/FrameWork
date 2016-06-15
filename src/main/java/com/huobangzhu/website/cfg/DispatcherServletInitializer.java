package com.huobangzhu.website.cfg;

import com.huobangzhu.AbstractAnnotationServletInitializer;
import com.huobangzhu.ApplicationConfig;
import com.huobangzhu.core.cfg.JpaConfig;
import com.huobangzhu.security.MultiHttpSecurityConfig;

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