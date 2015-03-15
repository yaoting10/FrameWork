package com.my.Utils.csveed.customer;

import org.csveed.bean.BeanProperty;

/**
 * Created with ECCS
 * User : Ting.Yao
 * Date : 2015/3/16.
 */
public class EmbeddedBeanProperty extends BeanProperty {

    @Override
    public String getColumnName() {
        return super.getColumnName() + "_";
    }
}
