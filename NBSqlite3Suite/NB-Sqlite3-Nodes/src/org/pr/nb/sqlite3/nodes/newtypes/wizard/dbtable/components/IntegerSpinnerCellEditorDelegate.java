/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.sqlite3.nodes.newtypes.wizard.dbtable.components;

import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author msivasub
 */
public class IntegerSpinnerCellEditorDelegate extends SpinnerCellEditorDelegate<Integer> {

    public IntegerSpinnerCellEditorDelegate(int data, int min, int max, int stepSize) {
        super(data, new SpinnerNumberModel(data, min, max, stepSize));
        getModel().addChangeListener((ChangeEvent e) -> {
            validateChangedData((Integer)getModel().getValue());
        });
    }

    @Override
    protected Integer getChangedData() {
        return (Integer) getModel().getValue();
    }

    @Override
    protected boolean validate(Integer changedData) {
        if (changedData == null) {
            return false;
        }
        Integer originalData = getData();
        return !changedData.equals(originalData);
    }

}
