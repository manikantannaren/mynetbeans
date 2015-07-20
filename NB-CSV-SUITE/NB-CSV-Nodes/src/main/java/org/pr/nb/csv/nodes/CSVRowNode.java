/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.csv.nodes;

import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.pr.nb.csv.nodes.data.CSVRow;

/**
 *
 * @author Mahakaal
 */
public class CSVRowNode extends AbstractNode {

    private CSVRow key;
    public CSVRowNode(CSVRow key) {
        super(Children.create(new CSVRowFactory(key), true));
        this.key = key;
        super.setName(key.getRowNumber()+"");
        setIconBaseWithExtension("org/pr/nb/csv/nodes/row16.png");
    }

    
}
