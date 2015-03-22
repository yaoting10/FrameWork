package com.my.core.service;

import com.my.core.domain.User;
import com.my.website.controller.vo.UserVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created with ECCS
 * User : Ting.Yao
 * Date : 2015/3/15.
 */
public interface UserService {
    User save(User user);

    Page<User> findByConditions(UserVo vo, Pageable pageable);

    User findOne(Integer userId);

    User update(UserVo vo, Integer userId);

    void delete(Integer userId);
}
