package com.my.website.controller;

import com.my.Utils.PageableResponse;
import com.my.core.domain.HandlingCost;
import com.my.core.domain.WayBill;
import com.my.core.service.HandingCostService;
import com.my.core.service.WayBillService;
import com.my.website.controller.vo.WayBillQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

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
    @Resource
    private HandingCostService handingCostService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView wayBill(WayBillQueryVo vo, Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("wayBill/list");
        return modelAndView;
    }

    /**
     * 查询所有订单
     * @param vo
     * @param pageable
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public PageableResponse list(WayBillQueryVo vo, Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("wayBill/list");
        Page<WayBill> wayBillPage = wayBillService.findByConditions(vo, pageable);
        return PageableResponse.of(wayBillPage.getContent(), wayBillPage.getContent().size(), wayBillPage.getTotalElements());
    }

    /**
     * 增加订单
     * @param wayBill
     * @return
     */
    @RequestMapping(value = "/addWayBill", method = RequestMethod.POST)
    public ModelAndView addWayBill(WayBill wayBill,@RequestParam("area") String area,@RequestParam("type") int type){
        HandlingCost handlingCost=this.handingCostService.findByArea(area);
        if(handlingCost==null){
            return new ModelAndView("rediect:/way_bill");
        }

        wayBill=this.wayBillService.save(wayBill);
        return new ModelAndView("rediect:/way_bill");
    }

    /**
     * 修改订单
     * @param wayBill
     * @return
     */
    @RequestMapping(value = "/updWayBill", method = RequestMethod.POST)
    public ModelAndView updWayBill(WayBill wayBill){
        wayBill=this.wayBillService.save(wayBill);
        return new ModelAndView("rediect:/way_bill");
    }
    /**
     * 删除订单
    * @param  wayBillId
    * @return
            */
    @RequestMapping(value = "/delWayBill", method = RequestMethod.POST)
    public ModelAndView delWayBill(@RequestParam("wayBillId") int wayBillId){
        this.wayBillService.delete(wayBillId);
        return new ModelAndView("rediect:/way_bill");
    }
    /**
     * 查询订单详情
     * @param  wayBillId
     * @return
     */
    @RequestMapping(value = "/findWayBillById", method = RequestMethod.POST)
    public ModelAndView findWayBillById(@RequestParam("wayBillId") int wayBillId){
        WayBill wayBill=this.wayBillService.get(wayBillId);
        return new ModelAndView("").addObject("",wayBill);
    }



}
