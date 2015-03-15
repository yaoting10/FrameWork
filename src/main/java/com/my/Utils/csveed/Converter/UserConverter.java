package com.my.Utils.csveed.Converter;

import com.my.core.domain.User;
import org.csveed.bean.conversion.AbstractConverter;

/**
 * Created with ECCS
 * User : Ting.Yao
 * Date : 2015/3/16.
 */
public class UserConverter extends AbstractConverter<User> {

    public UserConverter() {
        super(User.class);
    }

    @Override
    public User fromString(String text) throws Exception {
        return null;
    }

    @Override
    public String toString(User value) throws Exception {
        return value.getUserName();
    }
}
