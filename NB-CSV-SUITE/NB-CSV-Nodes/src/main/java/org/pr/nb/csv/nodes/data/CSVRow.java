/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.csv.nodes.data;

/**
 *
 * @author Kaiser
 */
public class CSVRow {

    private String[] rowData;
    private int rowNumber;
    
    public CSVRow(String[] datum, int rowNum) {
        this.rowData = datum;
        this.rowNumber = rowNum;
    }

    public String[] getRowData() {
        return rowData;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    
}
