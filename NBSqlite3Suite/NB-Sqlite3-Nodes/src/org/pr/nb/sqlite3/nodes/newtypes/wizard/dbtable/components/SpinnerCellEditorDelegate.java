/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.sqlite3.nodes.newtypes.wizard.dbtable.components;

import javax.swing.JComponent;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;

/**
 *
 * @author msivasub
 */
public abstract class SpinnerCellEditorDelegate<E> extends CellEditorDelegate<E> {

    protected final JSpinner component;

    public SpinnerCellEditorDelegate(E data, SpinnerModel model) {
        super(data);
        component = new JSpinner(model);
    }
    
    protected final SpinnerModel getModel(){
        return component.getModel();
    }
    @Override
    protected final JComponent getEditorComponent() {
        return component;
    }

}
