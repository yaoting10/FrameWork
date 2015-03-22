package com.my.website.controller;

import com.my.Utils.PoiUtill;
import com.my.core.domain.HandlingCost;
import com.my.core.service.HandingCostService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/3/21.
 */
@Controller
@RequestMapping("/HandlingCostController")
public class HandlingCostController {
    @Resource
    private HandingCostService handingCostService;

    /**
     * 从excel导入价格
     * @param file
     * @return
     */
    @RequestMapping(value = "/addExcelHandlingCost",method = RequestMethod.POST)
    public ModelAndView addExcelHandlingCost(@RequestParam("file") MultipartFile file){
        try {
            List <List>excelList=PoiUtill.readXls(file, 6);
            List<HandlingCost> addList=new ArrayList<HandlingCost>();
            for (int i=0;i<excelList.size();i++){
                HandlingCost cost = new HandlingCost();
                 for (int j=0;j<excelList.get(i).size();j++){
                   switch (j){
                       case 0:cost.setArea(excelList.get(i).get(j)+"");break;
                       case 1:cost.setAirPrice(Double.parseDouble(excelList.get(i).get(j)+""));break;
                       case 2:cost.setAriLowPrice(Double.parseDouble(excelList.get(i).get(j)+""));break;
                       case 3:cost.setCarOperatePrice(Double.parseDouble(excelList.get(i).get(j)+""));break;
                       case 4:cost.setCarWeightPrice(Double.parseDouble(excelList.get(i).get(j)+""));break;
                       case 5:cost.setCarLowPrice(Double.parseDouble(excelList.get(i).get(j)+""));break;
                       case 6:cost.setIncludeArea(excelList.get(i).get(j)+"");break;
                   }
                 }
                addList.add(cost);
            }
            handingCostService.addHandingCost(addList);
            ModelAndView modelAndView = new ModelAndView("").addObject("");
            return modelAndView;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 增加价格
     * @param handlingCost
     * @return
     */
    @RequestMapping(value = "/addHandlingCost",method = RequestMethod.POST)
    public ModelAndView addHandlingCost(@RequestParam("handlingCost")HandlingCost handlingCost){
        List<HandlingCost> list=new ArrayList<HandlingCost>();
        handingCostService.addHandingCost(list);
        ModelAndView modelAndView = new ModelAndView("").addObject("");
        return modelAndView;
    }
    /**
     * 修改价格表
     * @param handlingCost
     * @return
     */
    @RequestMapping(value = "/updHandlingCost",method = RequestMethod.POST)
    public ModelAndView updHandlingCost(@RequestParam("handlingCost")HandlingCost handlingCost){
        handingCostService.updHandlingCost(handlingCost);
        ModelAndView modelAndView = new ModelAndView("").addObject("handlingCost",handlingCost);
        return modelAndView;
    }
    /**
     * 删除价格表
     * @param id
     * @return
     */
    @RequestMapping(value = "/delHandlingCost",method = RequestMethod.GET)
    public ModelAndView delHandlingCost(@RequestParam("id")int id){
        handingCostService.delHandingCost(id);
        ModelAndView modelAndView = new ModelAndView("").addObject("handlingCost","");
        return modelAndView;
    }
    /**
     * 查询详情
     * @param id
     * @return
     */
    @RequestMapping(value = "/findHandlingCost",method = RequestMethod.GET)
    public ModelAndView findHandlingCost(@RequestParam("id")int id){
        HandlingCost handingCost=handingCostService.findHandlingCost(id);
        ModelAndView modelAndView = new ModelAndView("").addObject("handlingCost",handingCost);
        return modelAndView;
    }

}