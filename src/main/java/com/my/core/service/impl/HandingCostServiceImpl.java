package com.my.core.service.impl;

import com.my.core.domain.HandlingCost;
import com.my.core.repository.HandlingCostRepository;
import com.my.core.service.HandingCostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * @Description: 价格表业务实现类
 * @Company: tq
 * @create Author: 黄新强
 * @create Date: 2015-3-18 下午09:57:32
 * @version 1.0
 */
@Service
@Transactional
public class HandingCostServiceImpl implements HandingCostService {
    @Resource
    private HandlingCostRepository handlingCostRepository;
    @Override
    public HandlingCost addHandingCost(List<HandlingCost> handingCosts) {
//        for (int i=0;i<handingCosts.size();i++){
            this.handlingCostRepository.save(handingCosts);
//        }
        return null;
    }

    @Override
    public void delHandingCost(int id) {
       this.handlingCostRepository.delete(id);
    }

    @Override
    public HandlingCost updHandlingCost(HandlingCost handlingCost) {
        return this.handlingCostRepository.saveAndFlush(handlingCost);
    }

    @Override
    public HandlingCost findByArea(String area) {
        return this.handlingCostRepository.findByArea(area);
    }

    @Override
    public Page<HandlingCost> findAll(Pageable pageable) {
        return this.handlingCostRepository.findAllOderByIdDesc(pageable);
    }

    @Override
    public HandlingCost findHandlingCost(int id) {
        return this.handlingCostRepository.findOne(id);
    }

    /**
     * 得到所有价格表
     *
     * @return
     */
    @Override
    public List<HandlingCost> findHandlingCosts() {
        return this.handlingCostRepository.findAll();
    }
}
