/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.csv.nodes.data;

/**
 *
 * @author Mahakaal
 */
public class CSVCell {

    private String cellValue;
    private int columnNum;
    private int rowNum;
            
    public CSVCell(String data, int columnNum, int rowNum) {
        this.cellValue = data;
        this.columnNum = columnNum;
        this.rowNum = rowNum;
    }

    public String getCellValue() {
        return cellValue;
    }

    public int getColumnNum() {
        return columnNum;
    }

    public int getRowNum() {
        return rowNum;
    }

    
}
