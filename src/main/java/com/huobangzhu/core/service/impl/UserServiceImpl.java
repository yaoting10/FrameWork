package com.huobangzhu.core.service.impl;

import com.huobangzhu.core.domain.User;
import com.huobangzhu.core.repository.UserListRepository;
import com.huobangzhu.core.repository.UserRepository;
import com.huobangzhu.core.service.UserService;
import com.huobangzhu.website.controller.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with ECCS
 * User : Ting.Yao
 * Date : 2015/3/15.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserListRepository userListRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findByUserNumber(String userNumber) {
        return userRepository.findByUserNumber(userNumber);
    }

    @Override
    public Page<User> findByConditions(UserVo vo, Pageable pageable) {
        return userListRepository.findByConditions(vo, pageable);
    }

    @Override
    public User findOne(Integer userId) {
        return userRepository.findOne(userId);
    }

    @Override
    public User update(UserVo vo, Integer userId) {
        User user = userRepository.findOne(userId);
        user.setIdCard(vo.getIdCard());
        user.setPassword(vo.getPassword());
        user.setPhone(vo.getPhone());
        user.setUserName(vo.getUserName());
        user.setUserSex(vo.getUserSex());
        user.setUserNumber(vo.getUserNumber());
        user.setUserType(vo.getUserType());
        return userRepository.save(user);
    }

    @Override
    public void delete(Integer userId) {
        User user = userRepository.findOne(userId);
        userRepository.findAll();
        userRepository.delete(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
