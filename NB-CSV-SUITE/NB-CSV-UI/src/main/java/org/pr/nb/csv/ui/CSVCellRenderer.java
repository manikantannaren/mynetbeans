/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.csv.ui;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.Date;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import org.openide.util.NbBundle;
import org.pr.nb.csv.nb.csv.handler.Cell;

/**
 *
 * @author Kaiser
 */
@NbBundle.Messages({
    "editorTriggerButton.text=..."
})

public class CSVCellRenderer extends AbstractCellEditor implements TableCellRenderer, TableCellEditor {

    @Override
    public Object getCellEditorValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private class RendererEditor extends JPanel implements ActionListener {

        private JLabel valueDisplayLabel;
        private JButton editorTriggerButton;

        public RendererEditor(JTable table, Cell value) {
            setLayout(new GridBagLayout());
            valueDisplayLabel = new JLabel();
            editorTriggerButton = new JButton(Bundle.editorTriggerButton_text());
            Cell.Cell_Type type = value.getType();
            switch (type) {
                case DateTime:
                case Number:
                    valueDisplayLabel.setHorizontalAlignment(JLabel.RIGHT);
                    break;
                case Character:
                default:
                    valueDisplayLabel.setHorizontalAlignment(JLabel.LEFT);
                    break;
            }
            String text = getFormattedValue(value);
            valueDisplayLabel.setText(text);
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.weightx = 1.0;
            constraints.anchor = GridBagConstraints.NORTHWEST;
            constraints.fill = GridBagConstraints.BOTH;
            add(valueDisplayLabel, constraints);

            constraints = new GridBagConstraints();
            constraints.anchor = GridBagConstraints.NORTHWEST;
            add(editorTriggerButton, constraints);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            fireEditingStopped();
            //display the editor here
        }

    }

    private String getFormattedValue(Cell value) {
        String retValue=null;
        Cell.Cell_Type type = value.getType();
        switch (type) {
            case DateTime:
                DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
                retValue = df.format(new Date(value.getLongValue()));
                break;
            default:
                retValue = value.getStringValue();
                break;
        }

        return retValue;
    }

    private class SimpleRenderer extends JLabel {

        public SimpleRenderer(String text) {
            super(text);
        }

    }
}
