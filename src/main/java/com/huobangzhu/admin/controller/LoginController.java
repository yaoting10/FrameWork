package com.huobangzhu.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by penghongqin on 14-8-26.
 */
@Controller
public class LoginController {

    @RequestMapping(method = RequestMethod.GET)
    public String siginAdmin() {
        return "login";
    }

    @RequestMapping(value = "/signed", method = RequestMethod.GET)
    public String signed() {
        return "index";
    }
}
