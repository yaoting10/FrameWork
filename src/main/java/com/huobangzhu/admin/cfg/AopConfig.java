package com.huobangzhu.admin.cfg;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by penghongqin on 14-8-31.
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "com.huobangzhu.admin.aop.advice")
public class AopConfig {
}
