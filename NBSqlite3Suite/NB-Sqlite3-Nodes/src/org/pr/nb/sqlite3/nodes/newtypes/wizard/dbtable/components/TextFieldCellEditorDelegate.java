/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.sqlite3.nodes.newtypes.wizard.dbtable.components;

import java.util.Objects;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author msivasub
 */
public class TextFieldCellEditorDelegate extends CellEditorDelegate<String>{
    JTextField component;

    public TextFieldCellEditorDelegate(String data) {
        super(data == null?"":data);
        component = new JTextField(getData());
        component.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                validateChangedData(component.getText());
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                validateChangedData(component.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                validateChangedData(component.getText());
            }
        });
    }
    
    @Override
    protected JComponent getEditorComponent() {
        return component;
    }

    @Override
    protected String getChangedData() {
        return component.getText();
    }

    @Override
    protected boolean validate(String changedData) {
        String checkData = Objects.toString(changedData,"");
        if("".equals(checkData))
            return false;
        return !checkData.equals(getData());
    }

}
