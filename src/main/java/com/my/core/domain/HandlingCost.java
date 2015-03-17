package com.my.core.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @Description: 价格费用实体类
 * @Company: sgjy
 * @create Author: 黄新强
 * @create Date: 2015-3-17 下午02:06:08
 * @version 1.0
 */
@Entity
@Table(name="t_handling_cost")
public class HandlingCost {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="pk_id")
    private int id;
    /**
     * 1汽运（操作），2空运（操作）3汽运（续重），4空运（续重）
     */
    @Column(name="type",nullable=false)
    private int type;
    /**
     * 地区
     */
    @Column(name="area",length = 600,nullable = false)
    private String area;
    /**
     * 单价
     */
    @Column(name="price",nullable = false)
    private double price;
    /**
     * 最低价格
     */
    @Column(name="low_price",nullable = false)
    private double lowPrice;

    public HandlingCost() {
        super();
    }

    public HandlingCost(int id, int type, String area, double price,
                        double lowPrice) {
        super();
        this.id = id;
        this.type = type;
        this.area = area;
        this.price = price;
        this.lowPrice = lowPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(double lowPrice) {
        this.lowPrice = lowPrice;
    }


}
