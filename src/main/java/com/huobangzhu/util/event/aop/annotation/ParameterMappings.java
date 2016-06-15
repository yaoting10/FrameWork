package com.huobangzhu.util.event.aop.annotation;

/**
 * User: liuchang
 * Date: 15/3/26
 */
public @interface ParameterMappings {
    FieldMapping[] mappings() default {};
}
