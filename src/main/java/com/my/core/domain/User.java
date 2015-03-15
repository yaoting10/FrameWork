package com.my.core.domain;

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
    @Column(name = "pk_id")
    private long id;

    @Column(name = "user_number", length = 20, nullable = false)
    private String userNumber;

    @Column(name = "user_name", length = 50, nullable = false)
    private String userName;

    @Column(name = "password", length = 30, nullable = false)
    private String password;

    @Column(name = "userType", precision = 2,nullable = false)
    private int userType;

    @Column(name = "userSex", precision = 2,nullable = false)
    private int userSex;

    @Column(name = "idCard", length = 20)
    private String idCard;

}
