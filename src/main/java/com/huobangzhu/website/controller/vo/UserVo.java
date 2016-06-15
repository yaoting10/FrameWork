package com.huobangzhu.website.controller.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created with ECCS
 * User : Ting.Yao
 * Date : 2015/3/22.
 */
@Data
public class UserVo {

    @JsonProperty("userNumber")
    private String userNumber;

    @JsonProperty("userName")
    private String userName;

    @JsonProperty("password")
    private String password;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("userType")
    private int userType;

    @JsonProperty("userSex")
    private int userSex;

    @JsonProperty("idCard")
    private String idCard;
}
