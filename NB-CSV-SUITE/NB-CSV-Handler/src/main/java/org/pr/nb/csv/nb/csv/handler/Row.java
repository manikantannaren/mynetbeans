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
public class Row implements Serializable {

    private Integer rowNumber;
    private List<Cell> cells;

    public Integer getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(Integer rowNumber) {
        this.rowNumber = rowNumber;
    }

    public List<Cell> getCells() {
        List<Cell> retValue = new ArrayList<Cell>();
        if (cells != null) {
            retValue.addAll(cells);
        }
        return retValue;
    }

    public void setCells(List<Cell> cells) {
        if (this.cells == null) {
            this.cells = new ArrayList<Cell>();
        }
        this.cells.clear();
        this.cells.addAll(cells);
    }

    public Cell getCell(int columnIndex) {
        assert cells != null;
        Cell retValue = null;
        if (columnIndex >= 0 && columnIndex < cells.size()) {
            return cells.get(columnIndex);
        }
        return retValue;
    }

}
