/*
 * Copyright 2015 Manikantan Narender Nath.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
                    Icon icon = new ImageIcon(getClass().getResource("/org/pr/nb/sysprops/red.png"));
                    label.setIcon(icon);
                } else if (prop.getFlavour() == PropertiesTableDataObject.Flavour.NETBEANS) {
                    Icon icon = new ImageIcon(getClass().getResource("/org/pr/nb/sysprops/nb.png"));
                    label.setIcon(icon);
                }else {
                    Icon icon = new ImageIcon(getClass().getResource("/org/pr/nb/sysprops/green.png"));
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
