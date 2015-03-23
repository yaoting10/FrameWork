package com.my.website.controller.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.my.core.domain.WayBill;
import lombok.Data;

import java.util.Date;

/**
 * Created with ECCS
 * User : Ting.Yao
 * Date : 2015/3/22.
 */
@Data
public class WayBillVo {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("userNumber")
    private String userNumber;

    @JsonProperty("awb")
    private String awb;

    @JsonProperty("weight")
    private Double weight;

    @JsonProperty("address")
    private String address;

    @JsonProperty("area")
    private String area;

    @JsonProperty("createTime")
    private String createTime;

    @JsonProperty("type")
    private Integer type;

    @JsonProperty("handlingCost")
    private Double handlingCost = 0.00;

    @JsonProperty("totalPrice")
    private Double totalPrice;

    @JsonProperty("addedWeigh")
    private Double addedWeigh = 0.00;;

    @JsonProperty("createDate")
    private Date createDate;


    public static WayBillVo of(WayBill wayBill){
        WayBillVo vo = new WayBillVo();
        vo.setId(wayBill.getId());
        vo.setAwb(wayBill.getAwb());
        vo.setAddress(wayBill.getAddress());
        vo.setArea(wayBill.getCost().getArea());
        vo.setCreateDate(wayBill.getCreateTime());
        vo.setType(wayBill.getType());
        vo.setUserNumber(wayBill.getUser().getUserNumber());
        vo.setWeight(wayBill.getWeight());
        vo.setAddedWeigh(wayBill.getAddedWeigh());
        vo.setHandlingCost(wayBill.getHandlingCost());
        vo.setTotalPrice(wayBill.getTotal());
        return vo;
    }
}
