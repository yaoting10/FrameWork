package com.my.core.repository;

import com.my.core.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Administrator on 2015/3/18.
 */
public interface MenuRepository extends JpaRepository<Menu,Integer> {

    List<Menu> findByType(int type);
}
