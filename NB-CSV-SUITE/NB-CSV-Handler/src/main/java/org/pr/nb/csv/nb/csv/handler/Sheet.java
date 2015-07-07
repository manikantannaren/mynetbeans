/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.csv.nb.csv.handler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kaiser
 */
public class Sheet implements Serializable{
    
    private String name;
    private List<Row> rows;
    private List<String> columns;

    public List<String> getColumns() {
        List<String> retValue = new ArrayList<String>();
        if(this.columns != null){
            retValue.addAll(columns);
        }
        return retValue;
    }

    public void setColumns(List<String> columns) {
        if(this.columns == null)this.columns = new ArrayList<String>();
        this.columns.clear();
        this.columns.addAll(columns);
    }
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Row> getRows() {
        List<Row> retValue = new ArrayList<Row>();
        if(this.rows != null){
            retValue.addAll(rows);
        }
        return retValue;
    }

    public void setRows(List<Row> rows) {
        if(this.rows == null)this.rows = new ArrayList<Row>();
        this.rows.clear();
        this.rows.addAll(rows);
    }

    public Cell getValueAt(int rowIndex, int columnIndex) {
        assert rows != null;
       Cell retValue = null;
       if(rowIndex >=0 && rowIndex < rows.size()) {
           Row row = rows.get(rowIndex);
           retValue = row.getCell(columnIndex);
       }
       return retValue;
    }

    
}
