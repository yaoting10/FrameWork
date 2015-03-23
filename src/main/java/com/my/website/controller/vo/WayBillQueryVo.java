package com.my.website.controller.vo;

import lombok.Data;

/**
 * Created with ECCS
 * User : Ting.Yao
 * Date : 2015/3/18.
 */
@Data
public class WayBillQueryVo {

    private Long beginDate;

    private Long endDate;

    private String userNumber;

    private String awb;

    private String area;

}
