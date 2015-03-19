package com.my.core.service;

import com.my.core.domain.HandlingCost;

import java.util.List;

/**
 *
 * @Description: 价格表业务接口
 * @Company: tq
 * @create Author: 黄新强
 * @create Date: 2015-3-18 下午09:57:32
 * @version 1.0
 */
public interface HandingCostService {
    /**
     * 增加单价
     * @param handingCosts
     * @return
     */
    HandlingCost addHandingCost(List<HandlingCost> handingCosts);

    /**
     * 删除单价
     * @param id
     */
    void delHandingCost(int id);

    /**
     *修改单价
     * @param handlingCost
     * @return
     */
    HandlingCost updHandlingCost(HandlingCost handlingCost);

    /**
     * 根据地区和类型查单价
     * @param type
     * @param area
     * @return
     */
    HandlingCost findByTypeAndArea(int type,String area);

}
