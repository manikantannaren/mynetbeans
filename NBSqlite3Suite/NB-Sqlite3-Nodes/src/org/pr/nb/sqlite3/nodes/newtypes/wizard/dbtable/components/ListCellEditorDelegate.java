/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.sqlite3.nodes.newtypes.wizard.dbtable.components;

import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;

/**
 *
 * @author msivasub
 */
public class ListCellEditorDelegate<E> extends CellEditorDelegate<E> {

    private final JList<E> component;

    public ListCellEditorDelegate(E data, E[] listValues) {
        super(data);
        component = new JList<>(listValues);
        component.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        component.setSelectedValue(data, true);
        component.addListSelectionListener((ListSelectionEvent e) -> {
            validateChangedData(component.getSelectedValue());
        });
    }

    @Override
    protected JComponent getEditorComponent() {
        JScrollPane scrollPane = new JScrollPane(component);
        return scrollPane;
    }

    @Override
    protected E getChangedData() {
        return component.getSelectedValue();
    }

    @Override
    protected boolean validate(E changedData) {
        if(changedData == null) return false;
        return !changedData.equals(getData());
    }
}
