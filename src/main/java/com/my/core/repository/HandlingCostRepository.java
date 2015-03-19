package com.my.core.repository;

import com.my.core.domain.HandlingCost;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @Description: 价格表JPA接口
 * @Company: tq
 * @create Author: 黄新强
 * @create Date: 2015-3-18 下午09:57:32
 * @version 1.0
 */
public interface HandlingCostRepository extends JpaRepository<HandlingCost,Integer> {
        HandlingCost findByTypeAndArea(int type,String area);
}
