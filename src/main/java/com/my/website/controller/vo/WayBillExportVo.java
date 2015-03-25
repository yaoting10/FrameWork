package com.my.website.controller.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.my.Utils.TimeUtils;
import lombok.Data;

/**
 * Created with ECCS
 * User : Ting.Yao
 * Date : 2015/3/25.
 */
@Data
public class WayBillExportVo {

    @JsonProperty("awb")
    private String awb = "";

    @JsonProperty("userNumber")
    private String userNumber = "";

    @JsonProperty("weight")
    private String weight = "";

    @JsonProperty("address")
    private String address = "";

    @JsonProperty("area")
    private String area = "";

    @JsonProperty("createTime")
    private String createTime = "";

    @JsonProperty("type")
    private String type = "";

    @JsonProperty("handlingCost")
    private String handlingCost = "";

    @JsonProperty("totalPrice")
    private String totalPrice = "";

    @JsonProperty("addedWeigh")
    private String addedWeigh = "";

    public static WayBillExportVo of(Object[] objects){
        WayBillExportVo vo = new WayBillExportVo();
        if(objects[0]!= null)
            vo.setAwb(objects[0].toString());
        if(objects[1]!= null)
            vo.setUserNumber(objects[1].toString());
        if(objects[2]!= null)
            vo.setWeight(objects[2].toString());
        if(objects[3]!= null)
            vo.setAddress(objects[3].toString());
        if(objects[4]!= null)
            vo.setArea(objects[4].toString());
        if(objects[5]!= null)
            vo.setCreateTime(TimeUtils.millisToDateTime(Long.valueOf(objects[5].toString()), "yyyy-MM-dd"));
        if(objects[6]!= null){
            if(objects[6].toString().equals("1")){
                vo.setArea("汽运");
            }else if(objects[6].toString().equals("2")){
                vo.setArea("空运");
            }
        }
        if(objects[7]!= null)
            vo.setArea(objects[7].toString());
        if(objects[8]!= null)
            vo.setArea(objects[8].toString());
        if(objects[9]!= null)
            vo.setArea(objects[9].toString());
        return vo;
    }
}
