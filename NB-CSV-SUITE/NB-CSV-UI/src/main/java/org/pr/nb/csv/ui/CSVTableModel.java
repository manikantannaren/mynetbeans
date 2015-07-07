/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.csv.ui;

import javax.swing.table.AbstractTableModel;
import org.pr.nb.csv.nb.csv.handler.Sheet;
import org.pr.nb.csv.nb.csv.handler.Cell;

/**
 *
 * @author Kaiser
 */
public class CSVTableModel extends AbstractTableModel {

    Sheet csvSheet;

    public CSVTableModel(Sheet csvSheet) {
        this.csvSheet = csvSheet;
    }

    
    @Override
    public int getRowCount() {
        assert csvSheet != null;
        return csvSheet.getRows().size();
    }

    @Override
    public int getColumnCount() {
        assert  csvSheet != null;
        return csvSheet.getColumns().size();
    }

    @Override
    public Cell getValueAt(int rowIndex, int columnIndex) {
        return (Cell) csvSheet.getValueAt(rowIndex, columnIndex);
    }

}
