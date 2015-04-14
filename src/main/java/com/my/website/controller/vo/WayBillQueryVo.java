package com.my.website.controller.vo;

import com.my.core.domain.User;
import lombok.Data;

/**
 * Created with ECCS
 * User : Ting.Yao
 * Date : 2015/3/18.
 */
@Data
public class WayBillQueryVo {

    private Integer userId;

    private String begDate;

    private String endDate;

    private String userNumber;

    private String awb;

    private String area;

}
