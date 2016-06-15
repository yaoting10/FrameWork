package com.huobangzhu.admin.cfg;

import com.huobangzhu.AbstractAnnotationServletInitializer;

public class DispatcherServletInitializer extends AbstractAnnotationServletInitializer{

    @Override
    protected String getServletName(){
        return Constants.SERVLET_NAME;
    }

    @Override
    protected String[] getServletMappings(){
        return new String[]{String.format("/%s/*", Constants.SERVLET_NAME)};
    }

    @Override
    protected Class<?>[] getServletConfigClasses(){
        return new Class<?>[]{MvcConfig.class};
    }

}