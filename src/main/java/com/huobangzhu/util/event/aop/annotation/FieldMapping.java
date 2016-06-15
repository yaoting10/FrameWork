package com.huobangzhu.util.event.aop.annotation;

/**
 * User: liuchang
 * Date: 15/3/26
 */
public @interface FieldMapping {
    String key();
    String pathOfValue() default "";
    int paramIndex() default 0;
}
