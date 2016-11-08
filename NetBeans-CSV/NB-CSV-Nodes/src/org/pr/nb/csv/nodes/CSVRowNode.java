/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.csv.nodes;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.PropertySupport;
import org.openide.nodes.Sheet;
import org.openide.util.Exceptions;
import org.pr.nb.csv.nodes.data.CSVRow;

/**
 *
 * @author Mahakaal
 */
public class CSVRowNode extends AbstractNode {

    private CSVRow key;

    public CSVRowNode(CSVRow key) {
        super(Children.LEAF);
        this.key = key;
        super.setName(key.getRowNumber() + "");
        setIconBaseWithExtension("org/pr/nb/csv/nodes/row16.png");
    }

    @Override
    protected Sheet createSheet() {
        Sheet propertySheet = super.createSheet();
        Sheet.Set propertySet = Sheet.createPropertiesSet();
        propertySheet.put(propertySet);
        try {

            Property<String>[] propertiesToDefine = CSVSheetNode.columnNames();
            List<CSVCellNode> toPopulate = new ArrayList<CSVCellNode>();
            new CSVCellFactory(key).createChildren(toPopulate);
            PropertySupport.Reflection<Integer> prop = new PropertySupport.Reflection<Integer>(key, Integer.class, "getRowNumber", null);
            propertySet.put(prop);
            for (int x = 0; x < propertiesToDefine.length; x++) {
                Property<String> columnProp = propertiesToDefine[x];
                if (x < toPopulate.size()) {
                    CellProperty property = new CellProperty(columnProp.getName(), toPopulate.get(x));
                    propertySet.put(property);
                } else {
                    CellProperty property = new CellProperty(columnProp.getName(), null);
                    propertySet.put(property);
                }
            }
        } catch (NoSuchMethodException ex) {
            Exceptions.printStackTrace(ex);
        }
        return propertySheet;
    }

    private class CellProperty extends PropertySupport.ReadOnly<CSVCellNode> {

        CSVCellNode key;

        public CellProperty(String name, CSVCellNode value) {
            super(name, CSVCellNode.class, value != null?value.getDisplayName():"", "");
            this.key = value;
        }

        @Override
        public CSVCellNode getValue() throws IllegalAccessException, InvocationTargetException {
            return key;
        }

//        @Override
//        public String getDisplayName() {
//            return key != null ? key.getDisplayName() : "";
//        }
    }

}
