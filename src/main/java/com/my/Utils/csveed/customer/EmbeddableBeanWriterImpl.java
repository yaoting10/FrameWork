package com.my.Utils.csveed.customer;

import org.apache.commons.lang.StringUtils;
import org.csveed.bean.BeanProperty;
import org.csveed.bean.BeanWriter;
import org.csveed.bean.conversion.ConversionException;
import org.csveed.bean.conversion.DefaultConverters;
import org.csveed.row.*;

import java.io.Writer;
import java.util.Collection;


/**
 * Created with ECCS
 * User : Ting.Yao
 * Date : 2015/3/16.
 */
public class EmbeddableBeanWriterImpl<T> implements BeanWriter<T> {
    private final RowWriter rowWriter;

    private final EmbeddableBeanInstructionsImpl beanInstructions;

    private boolean headerWritten;

    private DefaultConverters defaultConverters = new DefaultConverters();

    private HeaderImpl header;

    public EmbeddableBeanWriterImpl(Writer writer, EmbeddableBeanInstructionsImpl beanInstructions) {
        this.beanInstructions = beanInstructions;
        this.rowWriter = new RowWriterImpl(writer, this.beanInstructions.getRowInstructions());
    }

    @Override
    public void writeBeans(Collection<T> beans) {
        beans.forEach(this::writeBean);
    }

    @Override
    public void writeBean(T bean) {
        writeHeader();

        LineWithInfo line = new LineWithInfo();

        writeLine(bean, line, beanInstructions);
        rowWriter.writeRow(new RowImpl(line, header));
    }

    private void writeLine(Object bean, LineWithInfo line, EmbeddableBeanInstructionsImpl beanInstructions) {
        EmbeddableBeanWrapper beanWrapper = new EmbeddableBeanWrapper(defaultConverters, bean);
        for (BeanProperty property : beanInstructions.getEmbeddableBeanProperties()) {
            try {
                Object propertyVal = beanWrapper.getProperty(property);
                if (property instanceof EmbeddedBeanProperty) {
                    EmbeddedBeanProperty outerBeanProperty = (EmbeddedBeanProperty) property;
                    EmbeddableBeanInstructionsImpl newBeanInstructions = new EmbeddableBeanParser().getBeanInstructions(propertyVal.getClass(), outerBeanProperty);
                    writeLine(propertyVal, line, newBeanInstructions);
                } else {
                    String propertyValStr = (String) propertyVal;
                    propertyValStr = StringUtils.isNotBlank(propertyValStr) ? propertyValStr : "未提供";
                    line.addCell(propertyValStr);
                }
            } catch (ConversionException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void writeHeader() {
        if (!beanInstructions.useHeader() || headerWritten) {
            return;
        }
        LineWithInfo line = new LineWithInfo();
        writeLineHeader(line, beanInstructions);
        header = new HeaderImpl(line);
        rowWriter.writeHeader(header);
        headerWritten = true;
    }

    private void writeLineHeader(LineWithInfo line, EmbeddableBeanInstructionsImpl beanInstructions) {
        for (BeanProperty property : beanInstructions.getEmbeddableBeanProperties()) {
            if (property instanceof EmbeddedBeanProperty) {
                EmbeddedBeanProperty outerBeanProperty = (EmbeddedBeanProperty) property;
                EmbeddableBeanInstructionsImpl newBeanInstructions = new EmbeddableBeanParser().getBeanInstructions(property.getField().getType(), outerBeanProperty);
                writeLineHeader(line, newBeanInstructions);
            } else {
                line.addCell(property.getColumnName());
            }
        }
    }

    @Override
    public RowWriter getRowWriter() {
        return rowWriter;
    }
}
