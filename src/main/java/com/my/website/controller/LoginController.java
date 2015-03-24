package com.my.website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created with ECCS
 * User : Ting.Yao
 * Date : 2015/3/24.
 */
@Controller
public class LoginController {

    @RequestMapping(value = "/admin/login", method = RequestMethod.GET)
    public String loginAdmin() {
        return "login";
    }

}
