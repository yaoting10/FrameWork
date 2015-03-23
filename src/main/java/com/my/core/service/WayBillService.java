package com.my.core.service;

import com.my.Utils.StatusResponse;
import com.my.core.domain.HandlingCost;
import com.my.core.domain.User;
import com.my.core.domain.WayBill;
import com.my.website.controller.vo.WayBillQueryVo;
import com.my.website.controller.vo.WayBillVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

import java.text.ParseException;

/**
 * Created with ECCS
 * User : Ting.Yao
 * Date : 2015/3/18.
 */
public interface WayBillService {

    Page<WayBill> findByConditions(WayBillQueryVo vo, Pageable pageable) throws ParseException;

    StatusResponse save(WayBillVo wayBillVo);

    WayBill get(Integer wayBillId);

    void delete(Integer wayBillId);

    StatusResponse addWayBill(Map<String,User> userMap,Map<String,HandlingCost>handlingCostMap,MultipartFile file);
}
