package com.my.core.repository;

import com.my.core.domain.HandlingCost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 *
 * @Description: 价格表JPA接口
 * @Company: tq
 * @create Author: 黄新强
 * @create Date: 2015-3-18 下午09:57:32
 * @version 1.0
 */
public interface HandlingCostRepository extends JpaRepository<HandlingCost,Integer> {
        HandlingCost findByArea(String area);
        @Query("select h from HandlingCost h order by h.id DESC ")
        Page<HandlingCost> findAllOderByIdDesc(Pageable pageable);
}
