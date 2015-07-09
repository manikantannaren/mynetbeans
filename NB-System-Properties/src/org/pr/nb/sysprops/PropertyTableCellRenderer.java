/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.sysprops;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Kaiser
 */
public class PropertyTableCellRenderer extends DefaultTableCellRenderer {
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
        label.setOpaque(true);
        label.setForeground(Color.BLACK);
        if (value instanceof PropertiesTableDataObject) {
            PropertiesTableDataObject prop = (PropertiesTableDataObject) value;
            if (prop.getFlavour() == PropertiesTableDataObject.Flavour.ENV) {
                Color c = new Color(100, 255, 206, 100);
                label.setBackground(c);
            } else {
                Color c = new Color(18, 192, 206, 100);
                label.setBackground(c);
            }
            if (prop.getDescriptor() == PropertiesTableDataObject.DataDescriptor.NAME) {
                label.setText(prop.getData().getKey());
            } else {
                label.setText(prop.getData().getValue());
            }
        }
        
        return label;
    }
    
}
