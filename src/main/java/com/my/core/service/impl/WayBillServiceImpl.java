package com.my.core.service.impl;

import com.my.core.domain.WayBill;
import com.my.core.repository.WayBillRepository;
import com.my.core.service.WayBillService;
import com.my.website.controller.vo.WayBillQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created with ECCS
 * User : Ting.Yao
 * Date : 2015/3/18.
 */
@Service
@Transactional
public class WayBillServiceImpl implements WayBillService{

    @Autowired
    private WayBillRepository wayBillRepository;

    @Override
    public Page<WayBill> findByConditions(WayBillQueryVo vo, Pageable pageable) {
        return wayBillRepository.findByConditions(vo, pageable);
    }
}
