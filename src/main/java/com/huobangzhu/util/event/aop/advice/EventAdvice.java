package com.huobangzhu.util.event.aop.advice;

import com.huobangzhu.core.service.RedisService;
import com.huobangzhu.util.event.Body;
import com.huobangzhu.util.event.BodyHelper;
import com.huobangzhu.util.event.KeyHelper;
import com.huobangzhu.util.event.Payload;
import com.huobangzhu.util.event.aop.annotation.Event;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * User: liuchang
 * Date: 15/3/19
 */
public abstract class EventAdvice {

    @Autowired
    private RedisService redisService;

    @Autowired
    private ObjectMapper objectMapper;

    protected Object doAround(ProceedingJoinPoint joinPoint, Event event) throws Throwable{
        Object proceedResult = joinPoint.proceed();
        Object[] args = joinPoint.getArgs();
        Signature signature = joinPoint.getSignature();
        if (!(signature instanceof MethodSignature)) {
            return proceedResult;
        }
        MethodSignature ms = (MethodSignature) signature;
        Map<Integer, Object> paramValueMapping = new HashMap<>();
        Map<Integer, Class> paramTypeMapping = new HashMap<>();
        Class[] paramTypes = ms.getParameterTypes();
        if (paramTypes.length > 0) {
            for (int i = 0; i < paramTypes.length; i++) {
                paramValueMapping.put(i, args[i]);
                paramTypeMapping.put(i, paramTypes[i]);
            }
        }

        pushEvent(event, paramValueMapping, paramTypeMapping, proceedResult, ms.getReturnType());
        return proceedResult;
    }

    private void pushEvent(Event event, Map<Integer, Object> paramValueMapping, Map<Integer, Class> paramTypeMapping, Object returnObj, Class returnType) {
        Map<String, String> fieldJsonFeeds = new HashMap<>();
        Arrays.stream(event.parameterMappings().mappings()).forEach(m -> {
            String[] namePath = m.pathOfValue().split("\\.");
            Object value = null;
            Class type = null;
            for (int i = 0; i < namePath.length; i++) {
                try {
                    if (i == 0) {
                        int paramIndex = m.paramIndex();
                        value = paramValueMapping.get(paramIndex);
                        type = paramTypeMapping.get(paramIndex);
                    } else {
                        String name = namePath[i];
                        value = reflectValue(name, value, type);
                        type = reflectType(name, type);
                    }
                } catch (Exception e) {
                    break;
                }
            }
            fieldJsonFeeds.put(m.key(), value == null ? null : value.toString());
        });

        Arrays.stream(event.returnMappings().mappings()).forEach(m -> {
            Object value = returnObj;
            Class type = returnType;
            if (StringUtils.isNotBlank(m.pathOfValue())) {
                String[] namePath = m.pathOfValue().split("\\.");
                for (int i = 0; i < namePath.length; i++) {
                    try {
                        String name = namePath[i];
                        value = reflectValue(name, value, type);
                        type = reflectType(name, type);
                    } catch (Exception e) {
                        break;
                    }
                }
            }
            fieldJsonFeeds.put(m.key(), value == null ? null : value.toString());
        });
        try {
            Payload payload = new Payload(event.type().ordinal(), fieldJsonFeeds);
            Body body = BodyHelper.getEventBody(payload);
            String eventBody = objectMapper.writeValueAsString(body);
            redisService.pushFromHead(KeyHelper.EVENT, eventBody);
        } catch (JsonProcessingException e) {

        }
    }

    private Class reflectType(String name, Class type) {
        Method method = null;
        try {
            method = type.getMethod(getterName(name));
        } catch (NoSuchMethodException noGetter) {
            try {
                method = type.getMethod(name);
            } catch (NoSuchMethodException noMethod) {

            }
        }
        if (method == null) {
            return null;
        }
        return method.getReturnType();
    }

    private Object reflectValue(String name, Object value, Class type) {
        Method method = null;
        try {
            method = type.getMethod(getterName(name));
        } catch (NoSuchMethodException noGetter) {
            try {
                method = type.getMethod(name);
            } catch (NoSuchMethodException noMethod) {

            }
        }
        if (method == null) {
            return null;
        }
        try {
            return method.invoke(value);
        } catch (IllegalAccessException | InvocationTargetException ignored) {

        }
        return null;
    }

    private String getterName(String name) {
        String capitalLetter = name.substring(0, 1);
        return "get" + capitalLetter.toUpperCase() + name.substring(1);
    }
}