package com.my.core.repository.impl;

import com.my.Utils.JpaUtils;
import com.my.core.domain.WayBill;
import com.my.core.repository.WayBillRepository;
import com.my.website.controller.vo.WayBillQueryVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created with ECCS
 * User : Ting.Yao
 * Date : 2015/3/18.
 */
@Repository
public class WayBillRepositoryImpl extends SimpleJpaRepository<WayBill, Integer> implements WayBillRepository{

    private EntityManager entityManager;

    @Autowired
    public WayBillRepositoryImpl(EntityManager entityManager){
        super(WayBill.class,entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Page<WayBill> findByConditions(WayBillQueryVo vo, Pageable pageable) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<WayBill> criteriaQuery = criteriaBuilder.createQuery(WayBill.class);
        Root transactionRoot = criteriaQuery.from(WayBill.class);
        List<Predicate> conditions = new ArrayList<>();

        if(!Objects.isNull(vo.getBeginDate()) && !Objects.isNull(vo.getEndDate())){
            conditions.add((criteriaBuilder.between(transactionRoot.get("createTime"), vo.getBeginDate(), vo.getEndDate())));
        }

        if(StringUtils.isNotBlank(vo.getAddress())){
            conditions.add(criteriaBuilder.equal(transactionRoot.get("address"), vo.getAddress()));
        }

        if(StringUtils.isNotBlank(vo.getUsername())){
            conditions.add((criteriaBuilder.equal(transactionRoot.get("user").get("userName"), vo.getUsername())));
        }

        if(StringUtils.isNotBlank(vo.getAwb())){
            conditions.add((criteriaBuilder.equal(transactionRoot.get("awb"), vo.getAwb())));
        }

        criteriaQuery.where(conditions.toArray(new Predicate[conditions.size()]));

        criteriaQuery.orderBy(criteriaBuilder.desc(transactionRoot.get("createTime")));
        TypedQuery<WayBill> query = entityManager.createQuery(criteriaQuery);

        if(pageable != null){
            query.setFirstResult(pageable.getOffset());
            query.setMaxResults(pageable.getPageSize());
        }

        long count = JpaUtils.count(entityManager, criteriaQuery);

        return new PageImpl<WayBill>(query.getResultList(), pageable, count);
    }
}
