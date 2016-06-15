package com.huobangzhu.foundation.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;

/**
 * @author: lxp
 * Date: 2015/5/5 15:22
 * 66cf-v2
 */
@AllArgsConstructor
@Getter
public class StatusModel<T> {

    @JsonProperty("c")
    @NotNull
    private int code;

    @JsonProperty("t")
    private T t;

    public static <T> StatusModel<T> of(int code, T t){
       return new StatusModel(code, t);
    }

    public static <T> StatusModel<T> of(int code){
        return new StatusModel(code, null);
    }

}
