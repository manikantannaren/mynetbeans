/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.csv.nodes;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.PropertySupport;
import org.openide.util.NbBundle;
import org.pr.nb.csv.nodes.data.CSVSheet;

/**
 *
 * @author Kaiser
 */
@NbBundle.Messages({
    "HINT_Sheet=Represents the chosen csv file as rows and cells",
    "HINT_ROW_NUMBER_COLUMN=Row number",
    "LBL_ROW="

})
public class CSVSheetNode extends AbstractNode {

    CSVSheet key;

    public CSVSheetNode(CSVSheet key) {
        super(Children.create(new CSVRowFactory(key), false));
        this.key = key;
        setDisplayName(key.getName());
        setIconBaseWithExtension("org/pr/nb/csv/nodes/test_csv16.png");
        setShortDescription(Bundle.HINT_Sheet());
    }

    public static Property[] columnNames() {
        List<ColumnNameProperty> columnNames = new ArrayList<ColumnNameProperty>();
        for (int x = 65; x < 91; x++) {

            char c = (char) x;
//                for(int y = 65; y < 91; y++){
//                    char c1 = (char)y;
//                    ColumnNameProperty prop = new ColumnNameProperty(c1+""+c, c1+""+c, "");
//                    columnNames.add(prop);
//                }
            ColumnNameProperty prop = new ColumnNameProperty("" + c, "" + c, "");
            columnNames.add(prop);
        }

        Collections.sort(columnNames);
        ColumnNameProperty prop = new ColumnNameProperty("rownum", Bundle.LBL_ROW(), Bundle.HINT_ROW_NUMBER_COLUMN());
        columnNames.add(0,prop);
        Property[] retValue = new Property[columnNames.size()];
        return columnNames.toArray(retValue);
    }

    private static class ColumnNameProperty extends PropertySupport.ReadOnly<String> implements Comparable<ColumnNameProperty> {

        public ColumnNameProperty(String name, String displayName, String shortDescription) {
            super(name, String.class, displayName, shortDescription);
        }

        @Override
        public String getValue() throws IllegalAccessException, InvocationTargetException {
            return getDisplayName();
        }

        @Override
        public int compareTo(ColumnNameProperty o) {
            return getName().compareTo(o.getName());
        }

    }
}
