package com.my.website.controller;

import com.my.core.service.ExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created with ECCS
 * User : Ting.Yao
 * Date : 2015/3/15.
 */
@Controller
public class ExportController {

    @Autowired
    private ExportService exportService;

    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public ModelAndView goExportPage() throws Exception{
        ModelAndView modelAndView = new ModelAndView("testExport");
        return modelAndView;
    }
    @RequestMapping(value = "/export", method = RequestMethod.POST)
    public ModelAndView export(HttpServletRequest request, MultipartFile file) throws Exception{
        InputStreamReader reader = new InputStreamReader(file.getInputStream());
        String slogan = "Live long and prosper.";
        ModelAndView modelAndView = new ModelAndView("index");
        exportService.read(reader);
        modelAndView.addObject("slogan", slogan);
        return modelAndView;
    }
}
