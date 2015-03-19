package com.my.core.service.impl;

import com.my.core.domain.Menu;
import com.my.core.repository.MenuRepository;
import com.my.core.service.MenuService;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * @Description: 菜单业务实现
 * @Company:
 * @create Author: 黄新强
 * @create Date: 2015-3-19 下午02:06:08
 * @version 1.0
 */
public class MenuServiceImpl implements MenuService {
    @Resource
    private MenuRepository menuRepository;
    @Override
    public List<Menu> findByType(int type) {
        return this.menuRepository.findByType(type);
    }
}
