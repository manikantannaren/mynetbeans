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
        Object retValue = null;
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
