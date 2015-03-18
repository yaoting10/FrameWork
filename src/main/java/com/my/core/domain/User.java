package com.my.core.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.my.website.controller.vo.UserVo;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with Test
 * User : yt
 * Date : 2014/11/11.
 */
@Entity
@Table(name = "t_user")
@Data
public class User implements Serializable{

    @Id
    @GeneratedValue
    @JsonProperty("id")
    @Column(name = "pk_id")
    private int id;

    @JsonProperty("userNumber")
    @Column(name = "user_number", length = 20, nullable = false)
    private String userNumber;

    @JsonProperty("userName")
    @Column(name = "user_name", length = 50, nullable = false)
    private String userName;

    @JsonProperty("password")
    @Column(name = "password", length = 30, nullable = false)
    private String password;

    @JsonProperty("phone")
    @Column(name = "phone", length = 30)
    private String phone;

    @JsonProperty("userType")
    @Column(name = "userType", precision = 2,nullable = false)
    private int userType;

    @JsonProperty("userSex")
    @Column(name = "userSex", precision = 2,nullable = false)
    private int userSex;

    @JsonProperty("idCard")
    @Column(name = "idCard", length = 20)
    private String idCard;


    public static User of(UserVo userVo){
        User user = new User();
        user.setIdCard(userVo.getIdCard());
        user.setPassword(userVo.getPassword());
        user.setPhone(userVo.getPhone());
        user.setUserName(userVo.getUserName());
        user.setUserSex(userVo.getUserSex());
        user.setUserNumber(userVo.getUserNumber());
        user.setUserType(userVo.getUserType());
        return user;
    }
}
