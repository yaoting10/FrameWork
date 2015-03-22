package com.my.website.controller;

import com.my.core.domain.WayBill;
import com.my.core.service.WayBillService;
import com.my.website.controller.vo.WayBillQueryVo;
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
@RequestMapping("/way_bill")
public class WaybillController {

    @Autowired
    private WayBillService wayBillService;



    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView wayBill(WayBillQueryVo vo, Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("wayBill/list");
        return modelAndView;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(WayBillQueryVo vo, Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("wayBill/list");
        Page<WayBill> wayBillPage = wayBillService.findByConditions(vo, pageable);

        return modelAndView;
    }
}
