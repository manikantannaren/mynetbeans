/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.sysprops;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author Kaiser
 */
public class PropertyTableCellRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (value instanceof PropertiesTableDataObject) {
            PropertiesTableDataObject prop = (PropertiesTableDataObject) value;
            if (prop.getDescriptor() == PropertiesTableDataObject.DataDescriptor.NAME) {
                if (prop.getFlavour() == PropertiesTableDataObject.Flavour.ENV) {
                    Icon icon = new ImageIcon(getClass().getResource("/org/pr/nb/sysprops/env.png"));
                    label.setIcon(icon);
                } else {
                    Icon icon = new ImageIcon(getClass().getResource("/org/pr/nb/sysprops/property.png"));
                    label.setIcon(icon);
                }
                label.setText(prop.getData().getKey());
            } else {
                label.setText(prop.getData().getValue());
            }
        }
        return label;
    }

}
