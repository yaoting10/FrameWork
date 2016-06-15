package com.huobangzhu.core.repository;

import com.huobangzhu.core.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created with Test
 * User : yt
 * Date : 2014/11/11.
 */
public interface UserRepository extends JpaRepository<User, Integer>{
    User findByUserName(String userName);
    Page<User> findByUserType(Integer userType, Pageable pageable);

    User findByUserNumber(String userNumber);
}
