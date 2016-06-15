package com.huobangzhu.foundation.model;

import com.dsecet.core.domain.finance.FundAccount;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wordnik.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author: Tim.Yao
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "customer")
@Getter
@Setter
@ToString(callSuper = true, exclude = {"fundAccount"})
@EqualsAndHashCode(callSuper = true, exclude = {"fundAccount"})
@Inheritance(strategy = InheritanceType.JOINED)
public class Customer extends Domain<Long>{

    public final static String STRING_ENCRYPTION_KEY = "},i7JF7>7m";

    @JsonIgnore
    @Setter(AccessLevel.NONE)
    @Column(name = "no_login", nullable = false)
    protected boolean noLogin;

    @JsonIgnore
    @Column(nullable = false)
    private boolean locked;

    @JsonIgnore
    @NotNull
    @Getter(AccessLevel.PROTECTED)
    @Embedded
    private UserAccount account = new UserAccount();

    @JsonIgnore
    @NotNull
    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private FundAccount fundAccount;

    public Customer register(@NotBlank String username, @NotBlank String password){
        fundAccount = FundAccount.of(this);
        return username(username).password(password);
    }

    public Customer username(@NotBlank String username){
        getAccount().setUsername(username);
        return this;
    }

    public Customer passwordToken(@NotBlank String token){
        getAccount().getPassword().setToken(token);
        return this;
    }

    public Customer password(@NotBlank String password){
        getAccount().password(password);
        return this;
    }

    public boolean resetPassword(@NotBlank String password, @NotBlank String confirmedPassword){
        if(!password.equals(confirmedPassword) || !isActive() || isLocked()) return false;
        password(password);
        return true;
    }

    @ApiModelProperty(value = "Username")
    @JsonProperty(value = "u")
    public String getUsername(){
        return getAccount().getUsername();
    }

    @JsonIgnore
    public String getSalt(){
        return getAccount().getPassword().getSalt();
    }

    @JsonIgnore
    public String getPassword(){
        return getAccount().getPassword().getHash();
    }

    @JsonIgnore
    public String getPasswordToken(){
        return getAccount().getPassword().getToken();
    }
}
