package com.huobangzhu.util;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author: Tim.Yao
 */
@Getter
public class RespondableException extends RuntimeException{

    private int errorCode;

    public RespondableException(int errorCode) {
        this(errorCode, null, null);
    }

    public RespondableException(int errorCode,
                                @NotNull @Size(min = 1) String message) {
        this(errorCode, message, null);
    }

    public RespondableException(int errorCode,
                                @NotNull Throwable cause) {
        this(errorCode, null, cause);
        this.errorCode = errorCode;
    }

    public RespondableException(int errorCode, String message,
                                Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    @Override
    public String toString() {
        return errorCode + " : " + super.toString();
    }
}
