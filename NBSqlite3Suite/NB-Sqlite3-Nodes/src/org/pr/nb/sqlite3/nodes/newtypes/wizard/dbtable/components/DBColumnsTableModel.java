/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.sqlite3.nodes.newtypes.wizard.dbtable.components;

import java.sql.JDBCType;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;
import javax.swing.table.AbstractTableModel;
import org.apache.commons.lang.StringUtils;
import org.openide.util.NbBundle;
import org.pr.nb.sqlite3.jdbc.Sqlite3Column;

/**
 *
 * @author msivasub
 */
@NbBundle.Messages({
    "DBCOLUMNS_TABLE_COLUMN_1_NAME=Column name",
    "DBCOLUMNS_TABLE_COLUMN_2_NAME=Column type",
    "DBCOLUMNS_TABLE_COLUMN_3_NAME=Column size"
})
public class DBColumnsTableModel extends AbstractTableModel {

    private final List<Sqlite3Column> data;
    private final boolean defaultEditable;
    private final List<String> headerVector;

    public DBColumnsTableModel(List<Sqlite3Column> createdColumns, boolean defaultEditable) {
        if (createdColumns != null) {
            this.data = createdColumns;
        } else {
            this.data = new ArrayList<>();
        }
        this.defaultEditable = defaultEditable;
        headerVector = new ArrayList<>();
        headerVector.add(Bundle.DBCOLUMNS_TABLE_COLUMN_1_NAME());
        headerVector.add(Bundle.DBCOLUMNS_TABLE_COLUMN_2_NAME());
        headerVector.add(Bundle.DBCOLUMNS_TABLE_COLUMN_3_NAME());
    }

    public void add(Sqlite3Column column) {
        data.add(column);
        fireTableDataChanged();
    }

    @Override
    public int findColumn(String columnName) {
        IntStream indexStream = IntStream.range(0, headerVector.size());
        return indexStream.filter(index -> StringUtils.equalsIgnoreCase(columnName, headerVector.get(index)))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(columnName));

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
    public int getColumnCount() {
        return headerVector.size();
    }

    @Override
    public String getColumnName(int column) {
        if (column >= 0 && column < headerVector.size()) {
            return headerVector.get(column);
        }
        throw new IndexOutOfBoundsException("Column index out of bounds: " + column);
    }

    public List<Sqlite3Column> getData() {
        return data;
    }

    @Override
    public int getRowCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return getValueAt(rowIndex);
    }

    public void move(int selRow, int step) {
        isValidRowIndex(selRow, true);

        int indexToMoveTo = selRow + step;
        if (indexToMoveTo >= data.size()) {
            indexToMoveTo = data.size() - 1;
        }
        if (indexToMoveTo < 0) {
            indexToMoveTo = 0;
        }

        Sqlite3Column dbColumn = data.remove(selRow);
        data.add(indexToMoveTo, dbColumn);
        fireTableDataChanged();
    }

    public Sqlite3Column remove(int selRow) {
        isValidRowIndex(selRow, true);
        return data.remove(selRow);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return defaultEditable;
    }

    
    Sqlite3Column getValueAt(int rowIndex) {
        isValidRowIndex(rowIndex, true);
        return data.get(rowIndex);
    }

    private boolean isValidRowIndex(int rowIndex, boolean throwException) {
        boolean valid = rowIndex >= 0 && rowIndex < data.size();
        if (!valid && throwException) {
            throw new IndexOutOfBoundsException("Unknown row Index " + rowIndex);
        }
        return valid;
    }

}
