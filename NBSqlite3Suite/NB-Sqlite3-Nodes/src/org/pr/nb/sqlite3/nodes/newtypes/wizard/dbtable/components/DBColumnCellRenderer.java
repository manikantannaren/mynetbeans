/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.sqlite3.nodes.newtypes.wizard.dbtable.components;

import java.awt.Component;
import java.sql.JDBCType;
import java.util.NoSuchElementException;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import org.openide.util.NbBundle;
import org.pr.nb.sqlite3.jdbc.Sqlite3Column;

/**
 *
 * @author msivasub
 */
@NbBundle.Messages({
    "# {0} - index of column",
    "defaultColumnName=column_{0}"
})
public class DBColumnCellRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        DBColumnsTableModel model = (DBColumnsTableModel) table.getModel();
        Sqlite3Column sqlColumn = model.getValueAt(row);
        if (sqlColumn != null) {
            String text = getRendererText(table, column, sqlColumn);
            label.setText(text);
        }
        return label;
    }

    public static String getRendererText(JTable table, int column, Sqlite3Column sqlColumn) {
        switch (column) {
            case 0:
                return sqlColumn == null ? Bundle.defaultColumnName(table.getRowCount()) : sqlColumn.getName();
            case 1:
                return sqlColumn == null ? JDBCType.VARCHAR.name() : sqlColumn.getJdbcType().name();
            case 2:
                return sqlColumn == null ? 255 + "" : sqlColumn.getColumnSize() + "";
            default:
                throw new NoSuchElementException("No column attribute matching index " + column);
        }
    }

}
