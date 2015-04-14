package com.my.core.domain;

import lombok.Data;

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
@Data
public class HandlingCost {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="pk_id")
    private int id;
    /**
     * 地区
     */
    @Column(name="area",length = 600,nullable = false)
    private String area;
    /**
     * 空运单价
     */
    @Column(name = "air_price")
    private double airPrice;
    /**
     * 空运最低价格
     */
    @Column(name = "air_low_price")
    private double ariLowPrice;
    /**
     * 汽运操作费
     */
    @Column(name = "car_operate_price")
    private double carOperatePrice;
    /**
     * 汽运续重费
     */
    @Column(name = "car_weight_price")
    private double carWeightPrice;
    /**
     * 汽运最低费用
     */
    @Column(name = "car_low_price")
    private double carLowPrice;
    /**
     * 包括地区
     */
    @Column(name = "include_area",length = 1000)
    private String includeArea;


}
