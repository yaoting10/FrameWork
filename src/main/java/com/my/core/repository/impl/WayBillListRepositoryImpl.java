package com.my.core.repository.impl;

import com.my.Utils.JpaUtils;
import com.my.Utils.ModelUtils;
import com.my.Utils.TimeUtils;
import com.my.core.domain.WayBill;
import com.my.core.repository.WayBillListRepository;
import com.my.website.controller.vo.WayBillQueryVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import sun.util.resources.cldr.uk.TimeZoneNames_uk;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created with ECCS
 * User : Ting.Yao
 * Date : 2015/3/18.
 */
@Repository
public class WayBillListRepositoryImpl extends SimpleJpaRepository<WayBill, Integer> implements WayBillListRepository {

    private EntityManager entityManager;

    @Autowired
    public WayBillListRepositoryImpl(EntityManager entityManager){
        super(WayBill.class,entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Page<WayBill> findByConditions(WayBillQueryVo vo, Pageable pageable) throws ParseException{
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<WayBill> criteriaQuery = criteriaBuilder.createQuery(WayBill.class);
        Root transactionRoot = criteriaQuery.from(WayBill.class);
        List<Predicate> conditions = new ArrayList<>();


        if(!Objects.isNull(vo.getQueryDate())){
            Long beginDate = ModelUtils.parseToDate(vo.getQueryDate().split("/")[0]);
            Long endDate = ModelUtils.parseToDate(vo.getQueryDate().split("/")[1]);

                conditions.add(
                        criteriaBuilder.and(
                                criteriaBuilder.greaterThanOrEqualTo(transactionRoot.get("createTime"), beginDate),
                                criteriaBuilder.lessThanOrEqualTo(transactionRoot.get("createTime"), endDate)
                        )
                );
        }

        if(StringUtils.isNotBlank(vo.getUserNumber())){
            conditions.add((criteriaBuilder.equal(transactionRoot.get("user").get("userNumber"), vo.getUserNumber())));
        }

        if(StringUtils.isNotBlank(vo.getAwb())){
            conditions.add((criteriaBuilder.equal(transactionRoot.get("awb"), vo.getAwb())));
        }

        if(StringUtils.isNotBlank(vo.getArea())){
            conditions.add((criteriaBuilder.equal(transactionRoot.get("cost").get("area"), vo.getArea())));
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
