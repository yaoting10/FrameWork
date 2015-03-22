package com.my.website.controller;

import com.my.Utils.PageableResponse;
import com.my.core.domain.User;
import com.my.core.service.UserService;
import com.my.website.controller.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

/**
 * Created with ECCS
 * User : Ting.Yao
 * Date : 2015/3/18.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(){
        return "user/userList";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public
    @ResponseBody
    PageableResponse list(UserVo vo, Pageable pageable) {
        Page<User> userPage = userService.findByConditions(vo, pageable);
        return PageableResponse.of(userPage.getContent(), userPage.getContent().size(), userPage.getTotalElements());
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView goCreate() {
        ModelAndView modelAndView = new ModelAndView("user/userCreate");
        return modelAndView;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView create(UserVo vo) {
        ModelAndView modelAndView = new ModelAndView("user/userList");
        userService.save(User.of(vo));
        return modelAndView;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView goEdit(@RequestParam(value = "userId")Integer userId) {
        ModelAndView modelAndView = new ModelAndView("user/userEdit");
        User user = userService.findOne(userId);
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView edit(UserVo vo, Integer userId) {
        ModelAndView modelAndView = new ModelAndView("redirect:/user");
        userService.update(vo, userId);
        return modelAndView;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ModelAndView delete(Integer userId) {
        userService.delete(userId);
        ModelAndView modelAndView = new ModelAndView("redirect:/user");
        return modelAndView;
    }
}
