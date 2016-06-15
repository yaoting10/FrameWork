package com.huobangzhu.core.service;

import com.huobangzhu.core.domain.User;
import com.huobangzhu.website.controller.vo.UserVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created with ECCS
 * User : Ting.Yao
 * Date : 2015/3/15.
 */
public interface UserService {
    User save(User user);

    User findByUserNumber(String userNumber);

    Page<User> findByConditions(UserVo vo, Pageable pageable);

    User findOne(Integer userId);

    User update(UserVo vo, Integer userId);

    void delete(Integer userId);

    List<User> findAll();
}
