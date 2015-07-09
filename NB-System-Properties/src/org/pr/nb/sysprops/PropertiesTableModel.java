/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.sysprops;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.table.AbstractTableModel;
import org.openide.util.NbBundle;

/**
 *
 * @author Kaiser
 */
@NbBundle.Messages({
    "propertiesTable.headerColumn1=Property Name",
    "propertiesTable.headerColumn2=Property Value"

})
public class PropertiesTableModel extends AbstractTableModel {

    List<PropertiesTableDataObject> data = new ArrayList<PropertiesTableDataObject>();

    public PropertiesTableModel(List<PropertiesTableDataObject> data) {
        if (data != null) {
            this.data = data;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public String getColumnName(int column) {
        if(column == 0){
            return Bundle.propertiesTable_headerColumn1();
        }else{
            return Bundle.propertiesTable_headerColumn2();
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object retValue = "";
        if (rowIndex < data.size() && rowIndex >= 0) {
            PropertiesTableDataObject entry = data.get(rowIndex);
            if (columnIndex == 1) {
                entry.setDescriptor(PropertiesTableDataObject.DataDescriptor.VALUE);
            } else if (columnIndex == 0) {
                entry.setDescriptor(PropertiesTableDataObject.DataDescriptor.NAME);
            }
            retValue = entry;
        }
        return retValue;
    }

}
