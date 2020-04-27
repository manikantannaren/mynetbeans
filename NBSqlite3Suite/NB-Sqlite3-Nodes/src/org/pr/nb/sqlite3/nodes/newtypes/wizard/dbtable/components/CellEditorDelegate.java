/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.sqlite3.nodes.newtypes.wizard.dbtable.components;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;

/**
 *
 * @author msivasub
 */
public abstract class CellEditorDelegate<E> {
    
    private DialogDescriptor descriptor;
    private final E data;
    
    public CellEditorDelegate(E data) {
        this.data = data;
    }
    
    public final E showColumnAttributeEditor(String title) {
        DialogDisplayer displayer = DialogDisplayer.getDefault();
        final JComponent editorComponent = getEditorComponent();
        descriptor = new DialogDescriptor(editorComponent, title, true, NotifyDescriptor.OK_CANCEL_OPTION, null, null);
        descriptor.setValid(false);
        descriptor.setMessageType(NotifyDescriptor.QUESTION_MESSAGE);
        Object input = displayer.notify(descriptor);
        if (input.equals(JOptionPane.OK_OPTION)) {
            return getChangedData();
        }
        return data;
    }

    protected final void validateChangedData(E changedData) {
        boolean valid = validate(changedData);
        if (descriptor != null) {
            descriptor.setValid(valid);
        }
    }

    protected final E getData() {
        return data;
    }

    protected abstract JComponent getEditorComponent();
    
    protected abstract E getChangedData();
    
    protected abstract boolean validate(E changedData);
    
}
