package com.my.core.service.impl;

import com.my.Utils.*;
import com.my.core.domain.HandlingCost;
import com.my.core.domain.User;
import com.my.core.domain.WayBill;
import com.my.core.repository.WayBillListRepository;
import com.my.core.repository.WayBillRepository;
import com.my.core.service.HandingCostService;
import com.my.core.service.UserService;
import com.my.core.service.WayBillService;
import com.my.website.controller.vo.WayBillQueryVo;
import com.my.website.controller.vo.WayBillStatisticsVo;
import com.my.website.controller.vo.WayBillVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with ECCS
 * User : Ting.Yao
 * Date : 2015/3/18.
 */
@Service
@Transactional
public class WayBillServiceImpl implements WayBillService{

    @Autowired
    private WayBillListRepository wayBillListRepository;

    @Autowired
    private WayBillRepository wayBillRepository;

    @Resource
    private HandingCostService handingCostService;

    @Autowired
    private UserService userService;

    @Override
    public Page<WayBill> findByConditions(WayBillQueryVo vo, Pageable pageable) throws ParseException{
        return wayBillListRepository.findByConditions(vo, pageable);
    }

    @Override
    public StatusResponse save(WayBillVo wayBillVo) {

        User user = userService.findByUserNumber(wayBillVo.getUserNumber());
        HandlingCost handlingCost = handingCostService.findByArea(wayBillVo.getArea());

        WayBill wayBill = new WayBill();
        wayBill.setType(wayBillVo.getType());
        wayBill.setAddress(wayBillVo.getAddress());
        wayBill.setAwb(wayBillVo.getAwb());
        wayBill.setWeight(wayBillVo.getWeight());
        try {
            wayBill.setCreateTime(ModelUtils.parseToDate(wayBillVo.getCreateTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(user==null){
             return StatusResponse.error(ErrorCode.NO_SUCH_USER);
        }
        wayBill.setUser(user);
        if(handlingCost==null){
            return StatusResponse.error(ErrorCode.NO_SUCH_AREA);
        }
        wayBill.setCost(handlingCost);
        wayBill.setType(wayBillVo.getType());
        wayBill = PriceUtill.getPrice(wayBill,wayBillVo.getType(),handlingCost);
        wayBillRepository.save(wayBill);
        return  StatusResponse.success();
    }

    @Override
    public WayBill get(Integer wayBillId) {
        return wayBillRepository.findOne(wayBillId);
    }

    public void delete(Integer wayBillId) {
        wayBillRepository.delete(wayBillId);
    }

    @Override
    public StatusResponse addWayBill(Map<String,User>userMap,Map<String,HandlingCost>handlingCostMap,MultipartFile file) {
        List<WayBill> addList = new ArrayList<WayBill>();
        int num = 0;
        try {
            List<List> excelList = PoiUtill.readXls(file, 6);
            for (int i = 0; i < excelList.size(); i++) {
                num = i;
                WayBill wayBill = new WayBill();
                int type = 0;
                for (int j = 0; j < excelList.get(i).size(); j++) {
                    String str = excelList.get(i).get(j) + "";
                    switch (j) {
                        case 0:
                            wayBill.setAwb(str);
                            break;
                        case 1:
                            wayBill.setWeight(Double.parseDouble(str));
                            break;
                        case 2:
                            wayBill.setCreateTime(ModelUtils.parseToDate(str));
                            break;
                        case 3:
                            User user = userMap.get(str);
                            if (user == null) {
                                return StatusResponse.error(ErrorCode.NO_SUCH_USER, "第" + i + 1 + "行，用户编号有误。");
                            }
                            wayBill.setUser(user);
                            break;
                        case 4:
                            HandlingCost hc = handlingCostMap.get(str);
                            if (hc == null) {
                                return StatusResponse.error(ErrorCode.NO_SUCH_AREA, "第" + i + 1 + "行，地区有误。");
                            }
                            wayBill.setCost(hc);
                            break;
                        case 5:
                            if ("空运".equals(str)) {
                                type = 2;
                            } else {
                                type = 1;
                            }
                            ;
                            break;
                    }
                }
                wayBill.setType(type);
                addList.add(PriceUtill.getPrice(wayBill, type, wayBill.getCost()));
            }
            wayBillRepository.save(addList);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            return StatusResponse.error(ErrorCode.DATE_FORMAT_ERRO, "第" + num + 1 + "行，日期格式不对");

        }
        return StatusResponse.success();
    }

    @Override
    public List<WayBillStatisticsVo> statisticWayBill(Long beginDate, Long endDate) {
        return wayBillListRepository.statisticsWayBill(beginDate, endDate);
    }

    @Override
    public List<WayBillStatisticsVo> statisticForCompany(Long beginDate, Long endDate) {
        return wayBillListRepository.statisticsCompany(beginDate, endDate);
    }

    @Override
    public List<WayBill> exportWayBill(Long beginDate, Long endDate) {
        return wayBillListRepository.exportWayBill(beginDate, endDate);
    }
}
