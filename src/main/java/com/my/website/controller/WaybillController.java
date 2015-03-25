package com.my.website.controller;

import com.my.Utils.*;
import com.my.core.domain.HandlingCost;
import com.my.core.domain.User;
import com.my.core.domain.WayBill;
import com.my.core.service.ExportService;
import com.my.core.service.HandingCostService;
import com.my.core.service.UserService;
import com.my.core.service.WayBillService;
import com.my.website.controller.vo.WayBillQueryVo;
import com.my.website.controller.vo.WayBillStatisticsVo;
import com.my.website.controller.vo.WayBillVo;
import org.apache.commons.lang.StringUtils;
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
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.my.Utils.PoiUtill.*;

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

    @Autowired
    private ExportService exportService;

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
        try{
            Page<WayBill> wayBillPage = wayBillService.findByConditions(vo, pageable);
            List<WayBillVo> voList = new ArrayList<>();
            wayBillPage.getContent().forEach(w->voList.add(WayBillVo.of(w)));
            return PageableResponse.of(voList, wayBillPage.getContent().size(), wayBillPage.getTotalElements());
        }catch (ParseException e){

            e.printStackTrace();
        }
        return PageableResponse.of(new ArrayList<>(), 0, 0L);
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


    @RequestMapping(value = "/import",method = RequestMethod.GET)
    public ModelAndView goExcelWayBill(){
        ModelAndView modelAndView = new ModelAndView("wayBill/import");
        return modelAndView;
    }
    /**
     * 从excel导入订单
     * @param file
     * @return
     */
    @RequestMapping(value = "/import",method = RequestMethod.POST)
    public ModelAndView addExcelWayBill(MultipartFile file){
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
        StatusResponse response = this.wayBillService.addWayBill(userMap,handlingCostMap,file);
        if(response.getCode()!=ErrorCode.SUCCESSFUL)
            return new ModelAndView("error/error").addObject("errorMsg", response.getData().toString());
        return new ModelAndView("redirect:/wayBill");
    }

    @RequestMapping(value = "/statistics",method = RequestMethod.GET)
    public ModelAndView goStatisticsPage(){
        ModelAndView modelAndView = new ModelAndView("wayBill/statistics");
        return modelAndView;
    }

    @RequestMapping(value = "/statistics/list",method = RequestMethod.GET)
    public @ResponseBody PageableResponse statisticsList(String queryDate) {
        try {
            Long now =ModelUtils.currentMillis();
            Long beginDate =0L;/*TimeUtils.subMonths(ModelUtils.getBeforeDaysMillis(now), 1)*/
            Long endDate = now;
            if(StringUtils.isNotBlank(queryDate)){
                beginDate = ModelUtils.parseToDate(queryDate.split("/")[0]);
                endDate = ModelUtils.parseToDate(queryDate.split("/")[1]);
            }
            List<WayBillStatisticsVo> companyVos = wayBillService.statisticForCompany(beginDate, endDate);
            List<WayBillStatisticsVo> wayBillStatisticsVos = wayBillService.statisticWayBill(beginDate, endDate);

            companyVos.addAll(wayBillStatisticsVos);

            return PageableResponse.of(companyVos, companyVos.size(), Long.valueOf(companyVos.size()));
        }catch (Exception e){
            e.printStackTrace();
        }
        return PageableResponse.of(new ArrayList<>(), 0, 0L);
    }


    @RequestMapping(value = "/export",method = RequestMethod.GET)
    public ModelAndView goExportPage(){
        ModelAndView modelAndView = new ModelAndView("wayBill/export");
        return modelAndView;
    }

    /**
     * excel导出接口
     * @param response
     */
    @RequestMapping(value = "/export",method = RequestMethod.POST)
    public void excelDownLoad(String from, String to, HttpServletResponse response, ExportCategory exportCategory) throws Exception{
        response.addHeader("Content-Disposition", String.format("attachment; filename=%s_%s_%s.xls", exportCategory, from, to));
        response.setContentType("application/octet-stream;charset=GB2312");
        response.setCharacterEncoding("GB2312");
        try{
            switch (exportCategory){
                case STATISTICS : exportService.exportStatisticsVo(parseFrom(from), parseFrom(to), response.getOutputStream());break;
                case WAY_BILL : exportService.exportWayBill(parseFrom(from), parseFrom(to), response.getOutputStream());break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    private Long parseFrom(String from) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.parse(from).getTime();
    }

    private Long parseTo(String to) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse(to);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 1);
        c.add(Calendar.MILLISECOND, -1);
        return c.getTime().getTime();
    }

    public enum ExportCategory {
        STATISTICS, WAY_BILL;
    }
}
