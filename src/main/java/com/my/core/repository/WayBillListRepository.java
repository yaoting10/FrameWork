package com.my.core.repository;

import com.my.core.domain.WayBill;
import com.my.website.controller.vo.WayBillQueryVo;
import com.my.website.controller.vo.WayBillStatisticsVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.text.ParseException;
import java.util.List;

/**
 * Created with ECCS
 * User : Ting.Yao
 * Date : 2015/3/18.
 */
@NoRepositoryBean
public interface WayBillListRepository extends JpaRepository<WayBill,Integer>{
    Page<WayBill> findByConditions(WayBillQueryVo vo, Pageable pageable) throws ParseException;

    List<WayBillStatisticsVo> statisticsWayBill(Long beginDate, Long endDate);

    List<WayBillStatisticsVo> statisticsCompany(Long beginDate, Long endDate);
}
