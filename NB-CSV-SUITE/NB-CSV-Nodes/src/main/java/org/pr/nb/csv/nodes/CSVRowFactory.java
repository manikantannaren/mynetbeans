/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.csv.nodes;

import java.util.List;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;
import org.pr.nb.csv.nodes.data.CSVCell;
import org.pr.nb.csv.nodes.data.CSVRow;

/**
 *
 * @author Mahakaal
 */
public class CSVRowFactory extends ChildFactory.Detachable<CSVCell> {

    private CSVRow key;
    public CSVRowFactory(CSVRow key) {
        this.key = key;
    }

    @Override
    protected boolean createKeys(List<CSVCell> toPopulate) {
        String [] datum = key.getRowData();
        for (int i = 0; i < datum.length; i++) {
            String data = datum[i];
            CSVCell cell = new CSVCell(data, i,key.getRowNumber());
            toPopulate.add(cell);
        }
        return true;
    }

    @Override
    protected Node createNodeForKey(CSVCell key) {
        return new CSVCellNode(key);
    }
    
    
}
