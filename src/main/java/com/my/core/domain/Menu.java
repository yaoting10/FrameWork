package com.my.core.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @Description: 菜单实体类
 * @Company: tq
 * @create Author: 黄新强
 * @create Date: 2015-3-17 下午01:57:32
 * @version 1.0
 */
@Entity
@Table(name="t_menu")
public class Menu {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="pk_id")
    private int id;
    /**
     * 菜单名
     */
    @Column(name="menu_name",length=50,nullable=false)
    private String menuName;
    /**
     * 菜单路径
     */
    @Column(name="url",length = 200,nullable = false)
    private String url;
    /**
     * 0管理员，1普通用户和管理员都能看
     */
    @Column(name="type",nullable = false)
    private int type;

    public Menu() {
        super();
    }

    public Menu(int id, String menuName, String url, int type) {
        super();
        this.id = id;
        this.menuName = menuName;
        this.url = url;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}
