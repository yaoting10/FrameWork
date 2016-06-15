package com.huobangzhu.admin.aop.annotation;

/**
 * Created by penghongqin on 14-8-31.
 */
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface OptsTrace {
    String value() default  "";
}