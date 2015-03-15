package com.my.Utils.csveed.customer;

import org.csveed.bean.BeanProperty;
import org.csveed.bean.conversion.*;

import java.lang.reflect.Method;


/**
 * Created with ECCS
 * User : Ting.Yao
 * Date : 2015/3/16.
 */
public class EmbeddableBeanWrapper {

    private DefaultConverters defaultConverters;

    private Object bean;

    public EmbeddableBeanWrapper(DefaultConverters defaultConverters, Object bean) {
        this.defaultConverters = defaultConverters;
        this.bean = bean;
    }

    public Object getProperty(BeanProperty property) throws ConversionException {
        Method readMethod = property.getPropertyDescriptor().getReadMethod();
        if (property instanceof EmbeddedBeanProperty) {
            try {
                return readMethod.invoke(bean);
            } catch (Exception err) {
                throw new BeanPropertyConversionException("Problem converting", "ObjectBeanProperty", err);
            }
        }
        Converter converter = getConverter(property);
        if (converter == null) {
            throw new NoConverterFoundException("No Converter found for", getPropertyType(property));
        }
        try {
            Object value = readMethod.invoke(bean);
            return converter.toString(value);
        } catch (Exception err) {
            throw new BeanPropertyConversionException("Problem converting", converter.infoOnType(), err);
        }
    }

    public void setProperty(BeanProperty property, String value) throws ConversionException {
        Method writeMethod = property.getPropertyDescriptor().getWriteMethod();
        Converter converter = getConverter(property);
        if (converter == null) {
            throw new NoConverterFoundException("No Converter found for", getPropertyType(property));
        }
        try {
            writeMethod.invoke(bean, (value == null || value.equals("")) ? null : converter.fromString(value));
        } catch (Exception err) {
            throw new BeanPropertyConversionException("Problem converting", converter.infoOnType(), err);
        }
    }

    protected Converter getConverter(BeanProperty property) {
        if (property.getConverter() != null) {
            return property.getConverter();
        } else {
            return defaultConverters.getConverter(getPropertyType(property));
        }
    }

    protected Class getPropertyType(BeanProperty property) {
        return property.getPropertyDescriptor().getPropertyType();
    }
}
