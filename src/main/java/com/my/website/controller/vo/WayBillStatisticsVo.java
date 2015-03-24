package com.my.website.controller.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created with ECCS
 * User : Ting.Yao
 * Date : 2015/3/23.
 */
@Data
public class WayBillStatisticsVo {

    @JsonProperty("userNumber")
    private String userNumber = "";

    @JsonProperty("wayBillCount")
    private Integer wayBillCount = 0;

    @JsonProperty("totalPrice")
    private Double totalPrice = 0.0;

    @JsonProperty("handlingCost")
    private Double handlingCost = 0.0;

    @JsonProperty("addedWeigh")
    private Double addedWeigh = 0.0;

    public static WayBillStatisticsVo of(Object[] queryResult){
        WayBillStatisticsVo vo = new WayBillStatisticsVo();
        if(queryResult[0]!=null)
            vo.setUserNumber(queryResult[0].toString());
        if(queryResult[1]!=null)
            vo.setWayBillCount(Integer.valueOf(queryResult[1].toString()));
        if(queryResult[2]!=null)
            vo.setHandlingCost(Double.valueOf(queryResult[2].toString()));
        if(queryResult[3]!=null)
            vo.setAddedWeigh(Double.valueOf(queryResult[2].toString()));
        if(queryResult[4]!=null)
            vo.setTotalPrice(Double.valueOf(queryResult[2].toString()));
        return vo;
    }

    public static WayBillStatisticsVo ofComPany(Object[] queryResult){
        WayBillStatisticsVo vo = new WayBillStatisticsVo();
        if(queryResult[0]!=null)
            vo.setUserNumber("公司");
        if(queryResult[1]!=null)
            vo.setWayBillCount(Integer.valueOf(queryResult[1].toString()));
        if(queryResult[2]!=null)
            vo.setHandlingCost(Double.valueOf(queryResult[2].toString()));
        if(queryResult[3]!=null)
            vo.setAddedWeigh(Double.valueOf(queryResult[2].toString()));
        if(queryResult[4]!=null)
            vo.setTotalPrice(Double.valueOf(queryResult[2].toString()));
        return vo;
    }
}
