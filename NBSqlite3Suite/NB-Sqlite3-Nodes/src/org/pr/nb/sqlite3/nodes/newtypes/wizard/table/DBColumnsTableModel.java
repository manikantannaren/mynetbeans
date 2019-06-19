/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.sqlite3.nodes.newtypes.wizard.table;

import java.sql.JDBCType;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;
import javax.swing.table.AbstractTableModel;
import org.apache.commons.lang.StringUtils;
import org.pr.nb.sqlite3.jdbc.Sqlite3Column;

/**
 *
 * @author msivasub
 */
public class DBColumnsTableModel extends AbstractTableModel {

    private final List<Sqlite3Column> data;
    private final List<String> headerVector;

    public DBColumnsTableModel(List<Sqlite3Column> createdColumns) {
        this.data = createdColumns;
        headerVector = new ArrayList<>();
        headerVector.add(Bundle.DBCOLUMNS_TABLE_COLUMN_1_NAME());
        headerVector.add(Bundle.DBCOLUMNS_TABLE_COLUMN_2_NAME());
        headerVector.add(Bundle.DBCOLUMNS_TABLE_COLUMN_3_NAME());
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Sqlite3Column.class;
            case 1:
                return JDBCType.class;
            case 2:
                return Integer.class;
            default:
                throw new IllegalArgumentException("Unknown column index " + columnIndex);
        }
    }

    @Override
    public int findColumn(String columnName) {
        IntStream indexStream = IntStream.range(0, headerVector.size());
        return indexStream.filter(index -> StringUtils.equalsIgnoreCase(columnName, headerVector.get(index)))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(columnName));

    }

    @Override
    public String getColumnName(int column) {
        if (column > 0 && column < headerVector.size()) {
            return headerVector.get(column);
        }
        throw new IndexOutOfBoundsException("Column index out of bounds: " + column);
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return headerVector.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (data.isEmpty()) {
            return null;
        }
        if (rowIndex < 0 || rowIndex > data.size()) {
            throw new IndexOutOfBoundsException("No such row " + rowIndex);
        }

        Sqlite3Column column = data.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return column;
            case 1:
                return column.getJdbcType();
            case 2:
                return column.getColumnSize();
            default:
                throw new IndexOutOfBoundsException("No such column " + columnIndex);
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        super.setValueAt(aValue, rowIndex, columnIndex); //To change body of generated methods, choose Tools | Templates.
    }

    List<Sqlite3Column> getData() {
        return data;
    }

}
