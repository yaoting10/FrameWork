package com.my.website.controller;

import com.my.core.domain.User;
import com.my.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created with ECCS
 * User : Ting.Yao
 * Date : 2015/3/18.
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user/list", method = RequestMethod.GET)
    public ModelAndView list(Integer uerType, Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("user/list");
        Page<User> userPage = userService.findByUserType(uerType, pageable);
        modelAndView.addObject("page", userPage);
        return modelAndView;
    }
}
