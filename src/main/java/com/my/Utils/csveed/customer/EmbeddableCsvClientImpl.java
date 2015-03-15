package com.my.Utils.csveed.customer;

import lombok.Getter;
import lombok.Setter;
import org.csveed.api.CsvClientImpl;
import org.csveed.bean.BeanWriter;

import java.io.Writer;
import java.util.Collection;

/**
 * Created with ECCS
 * User : Ting.Yao
 * Date : 2015/3/16.
 */
public class EmbeddableCsvClientImpl<T> extends CsvClientImpl<T> {

    @Getter
    @Setter
    private BeanWriter<T> beanWriter;

    public EmbeddableCsvClientImpl(Writer writer, Class beanClass) {
        super(writer, beanClass);
        this.beanWriter = new EmbeddableBeanWriterImpl<T>(writer, new EmbeddableBeanParser().getBeanInstructions(beanClass, null));
    }

    @Override
    public void writeBeans(Collection<T> beans) {
        getBeanWriter().writeBeans(beans);
    }

    @Override
    public void writeBean(T bean) {
        getBeanWriter().writeBean(bean);
    }
}
