package com.my.website.controller;

import com.my.Utils.*;
import com.my.core.domain.HandlingCost;
import com.my.core.domain.User;
import com.my.core.domain.WayBill;
import com.my.core.service.HandingCostService;
import com.my.core.service.UserService;
import com.my.core.service.WayBillService;
import com.my.website.controller.vo.WayBillQueryVo;
import com.my.website.controller.vo.WayBillVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with ECCS
 * User : Ting.Yao
 * Date : 2015/3/18.
 */
@Controller
@RequestMapping("/wayBill")
public class WaybillController {

    @Autowired
    private WayBillService wayBillService;
    @Resource
    private UserService userService;
    @Resource
    private HandingCostService handingCostService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView wayBill() {
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
        Page<WayBill> wayBillPage = wayBillService.findByConditions(vo, pageable);
        List<WayBillVo> voList = new ArrayList<>();
        wayBillPage.getContent().forEach(w->voList.add(WayBillVo.of(w)));
        return PageableResponse.of(voList, wayBillPage.getContent().size(), wayBillPage.getTotalElements());
    }

    /**
     * 增加订单
     *
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView goWayBill(){
        ModelAndView modelAndView = new ModelAndView("wayBill/create");
        return modelAndView;
    }


    /**
     * 增加订单
     * @param wayBillVo
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView addWayBill(WayBillVo wayBillVo){
        StatusResponse statusResponse = this.wayBillService.save(wayBillVo);

        if(statusResponse.getCode() != ErrorCode.NO_SUCH_USER)
            return new ModelAndView("error").addObject("errorMsg", statusResponse.getData());

        return new ModelAndView("redirect:/wayBill");
    }

    /**
     * 查询订单详情
     * @param  wayBillId
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView findWayBillById(int wayBillId){
        WayBill wayBill=this.wayBillService.get(wayBillId);
        return new ModelAndView("wayBill/edit").addObject("wayBill",wayBill);
    }

    /**
     * 删除订单
    * @param  id
    * @return
            */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ModelAndView delWayBill(Integer id){
        this.wayBillService.delete(id);
        return new ModelAndView("redirect:/wayBill");
    }
    /**
     * 从excel导入订单
     * @param file
     * @return
     */
    @RequestMapping(value = "/addExcelWayBill",method = RequestMethod.POST)
    public ModelAndView addExcelWayBill(@RequestParam("file") MultipartFile file){
        List<User> users=this.userService.findAll();
        List <HandlingCost> handlingCosts=this.handingCostService.findHandlingCosts();
        Map<String,User>userMap=new HashMap<String,User>();
        Map<String,HandlingCost>handlingCostMap=new HashMap<String,HandlingCost>();
        for(HandlingCost handlingCost:handlingCosts){
            handlingCostMap.put(handlingCost.getArea(),handlingCost);
        }
        for(User user:users){
           userMap.put(user.getUserNumber(),user);
        }
        this.wayBillService.addWayBill(userMap,handlingCostMap,file);

        return null;
    }



}
