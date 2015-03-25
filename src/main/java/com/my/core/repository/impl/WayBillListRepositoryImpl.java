package com.my.core.repository.impl;

import com.my.Utils.JpaUtils;
import com.my.Utils.ModelUtils;
import com.my.Utils.TimeUtils;
import com.my.core.domain.WayBill;
import com.my.core.repository.WayBillListRepository;
import com.my.website.controller.vo.WayBillExportVo;
import com.my.website.controller.vo.WayBillQueryVo;
import com.my.website.controller.vo.WayBillStatisticsVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import sun.util.resources.cldr.uk.TimeZoneNames_uk;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

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

    @Override
    public List<WayBillStatisticsVo> statisticsWayBill(Long beginDate, Long endDate) {
        String sql = "SELECT u.user_number, COUNT(*), SUM(w.handling_cost), SUM(w.added_weight), SUM(w.total) FROM t_user u LEFT JOIN t_waybll w ON w.fk_user_id = u.pk_id WHERE w.create_time >= :beginDate AND w.create_time < :endDate GROUP BY u.pk_id";
        Map<String, Object> parameters = new HashMap<>();
        if (!Objects.isNull(beginDate) && !Objects.isNull(endDate)) {
            parameters.put("beginDate", beginDate);
            parameters.put("endDate", endDate);
        }
        Query query = entityManager.createNativeQuery(sql);
        for (String key : parameters.keySet()) {
            query.setParameter(key, parameters.get(key));
        }
        List<Object[]> objects = query.getResultList();
        List<WayBillStatisticsVo> voList = objects.stream().map(o -> WayBillStatisticsVo.of(o)).collect(Collectors.toList());
        return voList;
    }

    @Override
    public List<WayBillStatisticsVo> statisticsCompany(Long beginDate, Long endDate) {
        String sql = "SELECT u.user_number,COUNT(*),SUM(w.handling_cost), SUM(w.added_weight), SUM(w.total) FROM t_waybll w LEFT JOIN t_user u ON w.fk_user_id = u.pk_id  WHERE w.create_time >= :beginDate AND w.create_time < :endDate";
        Map<String, Object> parameters = new HashMap<>();
        if (!Objects.isNull(beginDate) && !Objects.isNull(endDate)) {
            parameters.put("beginDate", beginDate);
            parameters.put("endDate", endDate);
        }
        Query query = entityManager.createNativeQuery(sql);
        for (String key : parameters.keySet()) {
            query.setParameter(key, parameters.get(key));
        }
        List<Object[]> objects = query.getResultList();
        List<WayBillStatisticsVo> voList = objects.stream().map(o -> WayBillStatisticsVo.ofComPany(o)).collect(Collectors.toList());
        return voList;
    }

    @Override
    public List<WayBillExportVo> exportWayBill(Long beginDate, Long endDate) {
        String sql = "SELECT w.awb, u.user_number, w.weight, w.address, h.area, w.create_time, w.type, w.handling_cost, w.added_weight, w.total" +
                     " FROM t_waybll w LEFT JOIN t_user u ON w.fk_user_id = u.pk_id LEFT JOIN t_handling_cost h ON w.fk_handling_cost_id = h.pk_id " +
                     " WHERE w.create_time >= :beginDate AND w.create_time < :endDate order by w.create_time desc ";
        Map<String, Object> parameters = new HashMap<>();
        if (!Objects.isNull(beginDate) && !Objects.isNull(endDate)) {
            parameters.put("beginDate", beginDate);
            parameters.put("endDate", endDate);
        }
        Query query = entityManager.createNativeQuery(sql);
        for (String key : parameters.keySet()) {
            query.setParameter(key, parameters.get(key));
        }
        List<Object[]> objects = query.getResultList();
        List<WayBillExportVo> voList = objects.stream().map(o -> WayBillExportVo.of(o)).collect(Collectors.toList());
        return  voList;
    }
}
