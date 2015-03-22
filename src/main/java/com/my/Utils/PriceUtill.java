package com.my.Utils;

import com.my.core.domain.HandlingCost;
import com.my.core.domain.WayBill;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.List;

/**
 * Created by Administrator on 2015/3/22.
 */
public class PriceUtill {

    public static WayBill getPrice(WayBill wayBill,int type,HandlingCost handlingCost){
        if(wayBill.getWeight()%1!=0){
            wayBill.setWeight((int)wayBill.getWeight()+1);
        }
       if(type==1){
           double price=0;
         if(wayBill.getWeight()>3){
             price=wayBill.getWeight()*handlingCost.getCarWeightPrice();
             wayBill.setAddedWeigh(price);
         }else{
             price=wayBill.getWeight()*handlingCost.getCarOperatePrice();
             wayBill.setHandlingCost(price);
         }
           if(price<handlingCost.getCarLowPrice()){
               price=handlingCost.getCarLowPrice();
           }
           wayBill.setTotal(price);
       }else{
           double price=wayBill.getWeight()*handlingCost.getAirPrice();
           if(price<handlingCost.getAriLowPrice()){
               price=handlingCost.getAriLowPrice();
           }
           wayBill.setHandlingCost(price);
           wayBill.setTotal(price);
       }
       return wayBill;
    }
}
