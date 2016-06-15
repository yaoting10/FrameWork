package com.huobangzhu.security;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created with ECCS
 * User : Ting.Yao
 * Date : 2015/3/15.
 */
public class UserPasswordEncoder implements PasswordEncoder {

    // 此方法不会被DaoAuthenticationProvider调用, 所以在matches方法上会重新encode
    @Override
    public String encode(CharSequence rawPassword) {
        return "";
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return true;
    }
}
