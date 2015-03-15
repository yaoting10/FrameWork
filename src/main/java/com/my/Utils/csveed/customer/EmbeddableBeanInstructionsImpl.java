package com.my.Utils.csveed.customer;

import org.csveed.bean.BeanInstructions;
import org.csveed.bean.BeanInstructionsImpl;
import org.csveed.bean.BeanProperty;
import org.csveed.bean.conversion.Converter;

import java.util.Locale;

/**
 * User: liuchang
 * Date: 14/11/19
 *
 * Important: Make sure BeanProperties can be only got from getEmbeddableBeanProperties() in this situation.
 */
public class EmbeddableBeanInstructionsImpl extends BeanInstructionsImpl {

    private EmbeddableBeanProperties properties;

    private boolean settingsLogged = false;

    public EmbeddableBeanInstructionsImpl(Class beanClass, EmbeddedBeanProperty outerBeanProperty) {
        super(beanClass);
        this.properties = new EmbeddableBeanProperties(beanClass, outerBeanProperty);
        setSeparator(',');
    }

    public void logSettings() {
        if (settingsLogged) {
            return;
        }
        LOG.info("- CSV config / mapping strategy: " + this.getMappingStrategy());
        for (BeanProperty property : properties) {
            LOG.info(getPropertyLogLine(property));
        }
        settingsLogged = true;
    }

    public EmbeddableBeanProperties getEmbeddableBeanProperties() {
        return this.properties;
    }

    @Override
    public BeanInstructions setDate(String propertyName, String dateFormat) {
        this.getEmbeddableBeanProperties().setDate(propertyName, dateFormat);
        return this;
    }

    @Override
    public BeanInstructions setLocalizedNumber(String propertyName, Locale locale) {
        this.getEmbeddableBeanProperties().setLocalizedNumber(propertyName, locale);
        return this;
    }

    @Override
    public BeanInstructions setRequired(String propertyName, boolean required) {
        this.getEmbeddableBeanProperties().setRequired(propertyName, required);
        return this;
    }

    @Override
    public BeanInstructions setConverter(String propertyName, Converter converter) {
        this.getEmbeddableBeanProperties().setConverter(propertyName, converter);
        return this;
    }

    @Override
    public BeanInstructions ignoreProperty(String propertyName) {
        this.getEmbeddableBeanProperties().ignoreProperty(propertyName);
        return this;
    }

    @Override
    public BeanInstructions mapColumnIndexToProperty(int columnIndex, String propertyName) {
        this.getEmbeddableBeanProperties().mapIndexToProperty(columnIndex, propertyName);
        return this;
    }

    @Override
    public BeanInstructions mapColumnNameToProperty(String columnName, String propertyName) {
        this.getEmbeddableBeanProperties().mapNameToProperty(columnName, propertyName);
        return this;
    }

    @Override
    public BeanInstructions setHeaderNameToProperty(String propertyName) {
        this.getEmbeddableBeanProperties().setHeaderNameProperty(propertyName);
        return this;
    }

    @Override
    public BeanInstructions setHeaderValueToProperty(String propertyName) {
        this.getEmbeddableBeanProperties().setHeaderValueProperty(propertyName);
        return this;
    }
}
