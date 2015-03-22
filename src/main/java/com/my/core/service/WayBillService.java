package com.my.core.service;

import com.my.core.domain.WayBill;
import com.my.website.controller.vo.WayBillQueryVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created with ECCS
 * User : Ting.Yao
 * Date : 2015/3/18.
 */
public interface WayBillService {

    Page<WayBill> findByConditions(WayBillQueryVo vo, Pageable pageable);

    WayBill save(WayBill wayBill);

    WayBill get(Integer wayBillId);

    void delete(Integer wayBillId);

}
