package com.huobangzhu.admin.aop.advice;

import com.huobangzhu.util.event.aop.advice.EventAdvice;
import com.huobangzhu.util.event.aop.annotation.Event;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * User: liuchang
 * Date: 15/3/30
 */
@Component
@Aspect
public class AdminEventAdvice extends EventAdvice {
    @Around("within(com.dsecet.admin.controller..*) && @annotation(event)")
    public Object around(ProceedingJoinPoint joinPoint, Event event) throws Throwable{
        return doAround(joinPoint, event);
    }
}
