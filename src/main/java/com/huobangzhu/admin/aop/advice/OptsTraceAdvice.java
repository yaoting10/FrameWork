package com.huobangzhu.admin.aop.advice;

import com.huobangzhu.admin.aop.annotation.OptsTrace;
import com.huobangzhu.core.domain.admin.OptsTraceLog;
import com.huobangzhu.core.domain.admin.Staff;
import com.huobangzhu.core.service.admin.OptsTraceLogService;
import com.huobangzhu.core.service.admin.StaffService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


/**
 * Created by penghongqin on 14-8-31.
 */
@Component
@Aspect
public class OptsTraceAdvice {

    @Autowired
    OptsTraceLogService optsTraceLogService;

    @Autowired
    StaffService staffService;

    @Around("within(@org.springframework.stereotype.Controller *) && @annotation(optsTrace)")
    public Object aroundTest(ProceedingJoinPoint proceedingJoinPoint, OptsTrace optsTrace) throws Throwable{
        String operation = optsTrace.value();
        Object[] args = proceedingJoinPoint.getArgs();
        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        Staff staff = staffService.getByUserName(username);
        OptsTraceLog optsTraceLog = new OptsTraceLog();
        optsTraceLog.setUsername(staff.getUsername());
        optsTraceLog.setRealName(staff.getUsername());
        optsTraceLog.setOperation(operation);
        optsTraceLog.setSucceed(true);
        try {
            Object result = proceedingJoinPoint.proceed();
            return result;
        } catch (Exception e) {
            optsTraceLog.setSucceed(false);
            optsTraceLog.setException(e.getMessage());
            throw e;
        } finally {
            optsTraceLogService.save(optsTraceLog);
        }
    }
}
