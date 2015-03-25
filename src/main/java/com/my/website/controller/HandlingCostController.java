package com.my.website.controller;

import com.my.Utils.PageableResponse;
import com.my.Utils.PoiUtill;
import com.my.core.domain.HandlingCost;
import com.my.core.service.HandingCostService;
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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/3/21.
 */
@Controller
@RequestMapping("/handlingCost")
public class HandlingCostController {
    @Resource
    private HandingCostService handingCostService;


    @RequestMapping(value = "/import",method = RequestMethod.GET)
    public ModelAndView goExcelHandlingCost(){
        ModelAndView modelAndView = new ModelAndView("handlingCost/import");
        return modelAndView;
    }

    /**
     * 从excel导入价格
     * @param file
     * @return
     */
    @RequestMapping(value = "/import",method = RequestMethod.POST)
    public ModelAndView addExcelHandlingCost(MultipartFile file){
        int num=0;
        try {
            List <List>excelList=PoiUtill.readXls(file, 7);
            List<HandlingCost> addList=new ArrayList<HandlingCost>();
            for (int i=0;i<excelList.size();i++){
                num=i;
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
        }catch (IOException e){
            e.printStackTrace();
            return new ModelAndView("error/error").addObject("errorMsg", "excel格式有误请检查。");
        }catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("error/error").addObject("errorMsg", "第"+num+1+"行代码价格必须是整数或小数。");
        }
    }

    @RequestMapping(value = "/create",method = RequestMethod.GET)
    public ModelAndView goHandlingCost(){
        ModelAndView modelAndView = new ModelAndView("handlingCost/create");
        return modelAndView;
    }

    /**
     * 增加价格
     * @param handlingCost
     * @return
     */
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public ModelAndView addHandlingCost(HandlingCost handlingCost){
        List<HandlingCost> list=new ArrayList<HandlingCost>();
        list.add(handlingCost);
        handingCostService.addHandingCost(list);
        return new ModelAndView("redirect:/handlingCost");
    }

    /**
     * 查询详情
     * @param id
     * @return
     */
    @RequestMapping(value = "/edit",method = RequestMethod.GET)
    public ModelAndView findHandlingCost(@RequestParam("id")int id){
        HandlingCost handingCost=handingCostService.findHandlingCost(id);
        ModelAndView modelAndView = new ModelAndView("handlingCost/edit").addObject("handlingCost",handingCost);
        return modelAndView;
    }
    /**
     * 修改价格表
     * @param handlingCost
     * @return
     */
    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    public ModelAndView updHandlingCost(HandlingCost handlingCost){
        handingCostService.updHandlingCost(handlingCost);
        ModelAndView modelAndView = new ModelAndView("redirect:/handlingCost");
        return modelAndView;
    }
    /**
     * 删除价格表
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public ModelAndView delHandlingCost(@RequestParam("id")int id){
        handingCostService.delHandingCost(id);
        ModelAndView modelAndView = new ModelAndView("redirect:/handlingCost");
        return modelAndView;
    }



    @RequestMapping(value = "",method = RequestMethod.GET)
    public ModelAndView findHandlingCost(){
        ModelAndView modelAndView = new ModelAndView("handlingCost/list");
        return modelAndView;
    }
    /**
     * 查询所有价格
     * @param pageable
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public PageableResponse getAllHandlingCost(Pageable pageable){
       Page page=handingCostService.findAll(pageable);
       return  PageableResponse.of(page.getContent(), page.getContent().size(), page.getTotalElements());
    }
}
