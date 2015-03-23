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
import com.my.website.controller.vo.WayBillVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;

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
    public Page<WayBill> findByConditions(WayBillQueryVo vo, Pageable pageable) {
        return wayBillListRepository.findByConditions(vo, pageable);
    }

    @Override
    public StatusResponse save(WayBillVo wayBillVo) {

        User user = userService.findByUserNumber(wayBillVo.getUserNumber());
        HandlingCost handlingCost = handingCostService.findByArea(wayBillVo.getArea());

        WayBill wayBill = new WayBill();
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
        wayBillListRepository.save(wayBill);
        return  StatusResponse.success();
    }

    @Override
    public WayBill get(Integer wayBillId) {
        return wayBillRepository.findOne(wayBillId);
    }

    public void delete(Integer wayBillId) {
        wayBillRepository.delete(wayBillId);
    }
}
