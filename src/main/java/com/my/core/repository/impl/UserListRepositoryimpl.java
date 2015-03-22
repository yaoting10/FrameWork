package com.my.core.repository.impl;

import com.my.Utils.JpaUtils;
import com.my.core.domain.User;
import com.my.core.domain.WayBill;
import com.my.core.repository.UserListRepository;
import com.my.website.controller.vo.UserVo;
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
 * Date : 2015/3/22.
 */
@Repository
public class UserListRepositoryimpl extends SimpleJpaRepository<User,Integer> implements UserListRepository{

    private EntityManager entityManager;

    @Autowired
    public UserListRepositoryimpl(EntityManager entityManager){
        super(User.class,entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Page<User> findByConditions(UserVo vo, Pageable pageable) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root transactionRoot = criteriaQuery.from(User.class);
        List<Predicate> conditions = new ArrayList<>();

        if(StringUtils.isNotBlank(vo.getUserName())){
            conditions.add(criteriaBuilder.equal(transactionRoot.get("userName"), vo.getUserName()));
        }

        if(StringUtils.isNotBlank(vo.getUserNumber())){
            conditions.add((criteriaBuilder.equal(transactionRoot.get("userNumber"), vo.getUserNumber())));
        }

        criteriaQuery.where(conditions.toArray(new Predicate[conditions.size()]));

        criteriaQuery.orderBy(criteriaBuilder.desc(transactionRoot.get("id")));
        TypedQuery<User> query = entityManager.createQuery(criteriaQuery);

        if(pageable != null){
            query.setFirstResult(pageable.getOffset());
            query.setMaxResults(pageable.getPageSize());
        }

        long count = JpaUtils.count(entityManager, criteriaQuery);

        return new PageImpl<User>(query.getResultList(), pageable, count);
    }
}
