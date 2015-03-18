package com.my.core.service;

import com.my.core.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created with ECCS
 * User : Ting.Yao
 * Date : 2015/3/15.
 */
public interface UserService {
    User save(User user);

    Page<User> findByUserType(Integer userType, Pageable pageable);
}
