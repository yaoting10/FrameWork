package com.my.core.service;

import com.my.Utils.StatusResponse;
import com.my.core.domain.WayBill;
import com.my.website.controller.vo.WayBillQueryVo;
import com.my.website.controller.vo.WayBillVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created with ECCS
 * User : Ting.Yao
 * Date : 2015/3/18.
 */
public interface WayBillService {

    Page<WayBill> findByConditions(WayBillQueryVo vo, Pageable pageable);

    StatusResponse save(WayBillVo wayBillVo);

    WayBill get(Integer wayBillId);

    void delete(Integer wayBillId);

}
