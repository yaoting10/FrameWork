package com.my.website.controller;

import com.my.Utils.csveed.Converter.PoiUtill;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * Created by Administrator on 2015/3/21.
 */
@Controller
@RequestMapping("/HandlingCostController")
public class HandlingCostController {

    @RequestMapping(value = "/addHandlingCost",method = RequestMethod.POST)
    public ModelAndView addHandlingCost(@RequestParam("file") MultipartFile file){
        ModelAndView modelAndView = new ModelAndView("");
        try {
            PoiUtill.readXls(file,6);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return modelAndView;
    }
}
