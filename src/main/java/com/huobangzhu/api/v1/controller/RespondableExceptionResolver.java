package com.huobangzhu.api.v1.controller;

import com.huobangzhu.foundation.exception.RespondableException;
import com.huobangzhu.foundation.model.ErrorCode;
import com.huobangzhu.foundation.model.StatusResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: Tim.Yao
 */
@Slf4j
public class RespondableExceptionResolver extends AbstractHandlerExceptionResolver{

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request,
                                              HttpServletResponse response,
                                              Object handler,
                                              Exception ex){
        StatusResponse result = StatusResponse.undefined();

        if(RespondableException.class.isInstance(ex)){
            RespondableException respondableException = RespondableException.class.cast(ex);
            result = StatusResponse.of(respondableException.getErrorCode(), respondableException.getMessage());
        }

        if(MethodArgumentNotValidException.class.isInstance(ex)){
            result = StatusResponse.of(ErrorCode.METHOD_ARGUMENT_NOT_VALID, ex.getMessage());
        }

        response.setContentType(MediaType.APPLICATION_JSON.toString());
        response.setCharacterEncoding("UTF-8");

        log.error(ex.getMessage(), ex);

        try{
            response.getWriter().print(objectMapper.writeValueAsString(result));
            response.getWriter().flush();
            return new ModelAndView();
        }catch(Exception e){
            log.error(e.getMessage(), e);
            return null;
        }
    }

}