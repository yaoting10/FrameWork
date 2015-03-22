package com.my.core.service;

import com.my.core.domain.Menu;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 * @Description: 菜单业务接口
 * @Company:
 * @create Author: 黄新强
 * @create Date: 2015-3-19 下午02:06:08
 * @version 1.0
 */
@Service
@Transactional(readOnly = false)
public interface MenuService {
    /**
     * 根据用户权限查询菜单
     * @param type
     * @return
     */
    public List<Menu> findByType(int type);

}
