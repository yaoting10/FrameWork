package com.my.Utils.csveed.customer;

import org.csveed.annotations.*;
import org.csveed.bean.BeanProperty;
import org.csveed.common.Column;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Locale;

/**
 * Created with ECCS
 * User : Ting.Yao
 * Date : 2015/3/16.
 */
public class EmbeddableBeanParser {

    private EmbeddableBeanInstructionsImpl beanInstructions;

    private Column currentColumn = new Column();

    private EmbeddedBeanProperty outerBeanProperty;

    public EmbeddableBeanInstructionsImpl getBeanInstructions(Class beanClass, EmbeddedBeanProperty embeddedBeanProperty) {
        outerBeanProperty = embeddedBeanProperty;
        this.beanInstructions = new EmbeddableBeanInstructionsImpl(beanClass, embeddedBeanProperty);

        Annotation[] annotations = beanClass.getAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation instanceof CsvFile) {
                parseCsvFile((CsvFile) annotation);
            }
        }

        for (BeanProperty beanProperty : beanInstructions.getEmbeddableBeanProperties()) {
            checkForAnnotations(beanProperty);
        }

        return this.beanInstructions;
    }

    public void checkForAnnotations(BeanProperty beanProperty) {

        Field currentField = beanProperty.getField();
        Annotation[] annotations = currentField.getDeclaredAnnotations();
        String propertyName = beanProperty.getPropertyName();
        String columnName = propertyName;
        for (Annotation annotation : annotations) {
            if (annotation instanceof CsvCell) {
                columnName = parseCsvCell(propertyName, (CsvCell) annotation);
            } else if (annotation instanceof CsvConverter) {
                parseCsvConverter(propertyName, (CsvConverter) annotation);
            } else if (annotation instanceof CsvDate) {
                parseCsvDate(propertyName, (CsvDate) annotation);
            } else if (annotation instanceof CsvLocalizedNumber) {
                parseCsvLocalizedNumber(propertyName, (CsvLocalizedNumber) annotation);
            } else if (annotation instanceof CsvHeaderName) {
                this.beanInstructions.setHeaderNameToProperty(propertyName);
            } else if (annotation instanceof CsvHeaderValue) {
                this.beanInstructions.setHeaderValueToProperty(propertyName);
            } else if (annotation instanceof CsvIgnore) {
                this.beanInstructions.ignoreProperty(propertyName);
                return;
            }
        }
        this.beanInstructions.mapColumnNameToProperty(columnName, propertyName);
        this.beanInstructions.mapColumnIndexToProperty(currentColumn.getColumnIndex(), propertyName);
        currentColumn = currentColumn.nextColumn();
    }

    private void parseCsvLocalizedNumber(String propertyName, CsvLocalizedNumber annotation) {
        final Locale locale;
        if (annotation.country().equals("")) {
            locale = new Locale(annotation.language());
        } else if (annotation.variant().equals("")) {
            locale = new Locale(annotation.language(), annotation.country());
        } else {
            locale = new Locale(annotation.language(), annotation.country(), annotation.variant());
        }
        this.beanInstructions.setLocalizedNumber(propertyName, locale);
    }

    private void parseCsvFile(CsvFile csvFile) {

        this.beanInstructions
                .setEscape(csvFile.escape())
                .setQuote(csvFile.quote())
                .setSeparator(csvFile.separator())
                .setComment(csvFile.comment())
                .setEndOfLine(csvFile.endOfLine())
                .setMapper(csvFile.mappingStrategy())
                .setStartRow(csvFile.startRow())
                .setUseHeader(csvFile.useHeader())
                .skipEmptyLines(csvFile.skipEmptyLines())
                .skipCommentLines(csvFile.skipCommentLines())
                .setStartIndexDynamicColumns(csvFile.startIndexDynamicColumns());
    }

    private void parseCsvDate(String propertyName, CsvDate csvDate) {
        this.beanInstructions.setDate(propertyName, csvDate.format());
    }

    private void parseCsvConverter(String propertyName, CsvConverter csvConverter) {
        try {
            this.beanInstructions.setConverter(propertyName, csvConverter.converter().newInstance());
        } catch (Exception err) {
            throw new RuntimeException(err);
        }
    }

    private String parseCsvCell(String propertyName, CsvCell csvCell) {
        String prefix = outerBeanProperty != null ? outerBeanProperty.getColumnName() : "";
        String columnName = (csvCell.columnName() == null || csvCell.columnName().equals("")) ? propertyName : csvCell.columnName();
        this.beanInstructions.setRequired(propertyName, csvCell.required());
        currentColumn = csvCell.columnIndex() != -1 ? new Column(csvCell.columnIndex()) : currentColumn;
        return prefix + columnName;
    }
}
