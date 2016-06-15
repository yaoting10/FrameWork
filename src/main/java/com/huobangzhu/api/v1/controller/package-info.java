package com.huobangzhu.api.v1.controller;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@ApiModel
@Getter
@NoArgsConstructor
@EqualsAndHashCode
final class BooleanEntity{

    @ApiModelProperty(value = "Value", required = true)
    @JsonProperty("v")
    @NotNull
    private Boolean value;

    @JsonCreator
    public BooleanEntity(@JsonProperty("v") @NotNull Boolean value){
        this.value = value;
    }
}

@ApiModel
@Getter
@NoArgsConstructor
@EqualsAndHashCode
final class IntegerEntity{

    @ApiModelProperty(value = "Value", required = true)
    @JsonProperty("v")
    private long value;

    @JsonCreator
    public IntegerEntity(@JsonProperty("v") @NotNull Long value){
        this.value = value;
    }
}

@ApiModel
@Getter
@NoArgsConstructor
@EqualsAndHashCode
final class StringEntity{

    @ApiModelProperty(value = "Value", required = true)
    @JsonProperty("v")
    @NotBlank
    private String value;

    @JsonCreator
    public StringEntity(@JsonProperty("v") @NotBlank String value){
        this.value = value;
    }
}

class Constant {

    public static final String EMPTY = "";
    public static final String IDENTITY = "identity";
    public static final String CELL_NO = "cellNo";
    public static final String PASSWORD = "password";
    public static final String TRANSACTION_CODE = "transactionCode";
    public static final String BANK_CARD = "bankCard";
    public static final String WDZJ_USER_NAME = "wdjz_66cf";
    public static final String WDZJ_PASSWORD = "YioGIP67fvY4nVso";
}


