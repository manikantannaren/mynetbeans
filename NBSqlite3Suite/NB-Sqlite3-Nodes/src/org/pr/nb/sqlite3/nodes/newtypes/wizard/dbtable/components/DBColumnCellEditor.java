/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.sqlite3.nodes.newtypes.wizard.dbtable.components;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import javax.swing.AbstractCellEditor;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import org.pr.nb.sqlite3.jdbc.Sqlite3Column;

/**
 *
 * @author msivasub
 */
public class DBColumnCellEditor extends AbstractCellEditor implements TableCellEditor {

    DBColumnCellEditorComponent component;

    @Override
    public boolean isCellEditable(EventObject e) {
        if (e instanceof MouseEvent) {
            return ((MouseEvent) e).getClickCount() >= 2;
        }
        return true;
    }

    @Override
    public Object getCellEditorValue() {
        return component != null ? component.getSqlite3Column() : null;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        DBColumnsTableModel model = (DBColumnsTableModel) table.getModel();
        Sqlite3Column dbColumn = model.getValueAt(row);
        component = new DBColumnCellEditorComponent(table,dbColumn, column);
        JComponent renderer = (JComponent) table.getCellRenderer(row, column).getTableCellRendererComponent(table, value, isSelected, isSelected, row, column);
        component.setBorder(renderer.getBorder());
        component.setOpaque(renderer.isOpaque());
        component.setBackground(renderer.getBackground());
        component.setForeground(renderer.getForeground());
        component.setFont(renderer.getFont());
        return component;
    }

}
