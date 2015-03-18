package com.my.core.repository;

import com.my.core.domain.WayBill;
import com.my.website.controller.vo.WayBillQueryVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Created with ECCS
 * User : Ting.Yao
 * Date : 2015/3/18.
 */
@NoRepositoryBean
public interface WayBillRepository extends JpaRepository<WayBill,Integer>{
    Page<WayBill> findByConditions(WayBillQueryVo vo, Pageable pageable);
}
