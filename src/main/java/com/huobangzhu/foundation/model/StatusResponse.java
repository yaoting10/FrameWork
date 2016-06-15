package com.huobangzhu.foundation.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wordnik.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import static com.huobangzhu.foundation.model.ErrorCode.SUCCESSFUL;

/**
 * @author: Tim.Yao
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class StatusResponse {

    @ApiModelProperty(value = "Status Code, {'c': 0, 'm': {${response}}}", required = true)
    @JsonProperty("c")
    @NonNull
    private int code;

    @ApiModelProperty(value = "Message Body, {'c': 0, 'm': {${response}}}", dataType = "JSON Object")
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

    public static StatusResponse success(Object data) {
        return of(SUCCESSFUL, data);
    }
}

