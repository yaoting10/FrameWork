package com.my.core.repository;

import com.my.core.domain.User;
import com.my.website.controller.vo.UserVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Created with ECCS
 * User : Ting.Yao
 * Date : 2015/3/22.
 */
@NoRepositoryBean
public interface UserListRepository extends JpaRepository<User, Integer>{
   Page<User> findByConditions(UserVo vo, Pageable pageable);
}
