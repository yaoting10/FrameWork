package com.huobangzhu.util;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;


/**
 * Created with ECCS
 * User : Ting.Yao
 * Date : 2015/3/22.
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class StatusResponse {

    @JsonProperty("c")
    @NonNull
    private int code;

    @JsonProperty("m")
    private Object data;

    private static final StatusResponse EMPTY_SUCCESS = of(ErrorCode.SUCCESSFUL, null);
    private static final StatusResponse ERR_UNDEFINED = of(ErrorCode.UNDEFINDED, null);

    @JsonCreator
    public static StatusResponse of(
            @JsonProperty(value = "c", required = true) int code,
            @JsonProperty("m") Object data) {
        return new StatusResponse(code, data);
    }

    public static StatusResponse success() {
        return EMPTY_SUCCESS;
    }

    public static StatusResponse undefined() {
        return ERR_UNDEFINED;
    }

    public static StatusResponse error(int code) {
        return of(code, null);
    }

    public static StatusResponse error(int code, Object data) {
        return of(code, data);
    }

    public static StatusResponse success(Object data) {
        return of(ErrorCode.SUCCESSFUL, data);
    }
}

