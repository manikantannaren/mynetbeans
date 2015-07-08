/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.csv.nb.csv.handler;

/**
 *
 * @author Kaiser
 */
public class Cell<T> {

    public enum Cell_Type {

        Character, Number, DateTime
    }
    private String columnName;
    private Integer cellNumber;
    private Cell_Type type = Cell_Type.Character;
    T value;

    private Integer rowNumber;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public Cell_Type getType() {
        return type;
    }

    public void setType(Cell_Type type) {
        this.type = type;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Integer getCellNumber() {
        return cellNumber;
    }

    public void setCellNumber(Integer cellNumber) {
        this.cellNumber = cellNumber;
    }

    public Integer getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(Integer rowNumber) {
        this.rowNumber = rowNumber;
    }

    public String getStringValue() {
        return value != null ? value.toString() : "";
    }

    public Boolean getBooleanValue() {
        String value = getStringValue();
        return Boolean.valueOf(value);
    }

    public Integer getIntegerValue() {
        Integer retValue = null;
        String val = getStringValue();
        try {
            retValue = new Integer(val);
        } catch (NumberFormatException e) {
        }
        return retValue;
    }

    public Long getLongValue() {
        Long retValue = null;
        String val = getStringValue();
        try {
            retValue = new Long(val);
        } catch (NumberFormatException e) {
        }
        return retValue;
    }

    public Float getFloatValue() {
        Float retValue = null;
        String val = getStringValue();
        try {
            retValue = new Float(val);
        } catch (NumberFormatException e) {
        }
        return retValue;
    }
    
    public Double getDoubleValue() {
        Double retValue = null;
        String val = getStringValue();
        try {
            retValue = new Double(val);
        } catch (NumberFormatException e) {
        }
        return retValue;
    }

}
