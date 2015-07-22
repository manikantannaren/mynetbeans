/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.csv.nodes;

import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.NbBundle;
import org.pr.nb.csv.nodes.data.CSVSheet;

/**
 *
 * @author Kaiser
 */
@NbBundle.Messages({
    "HINT_Sheet=Represents the chosen csv file as rows and cells"
})
public class CSVSheetNode extends AbstractNode{
    CSVSheet key;

    public CSVSheetNode(CSVSheet key) {
        super(Children.create(new CSVRowFactory(key),true));
        this.key = key;
        setDisplayName(key.getName());
        setIconBaseWithExtension("org/pr/nb/csv/nodes/test_csv16.png");
        setShortDescription(Bundle.HINT_Sheet());
    }
    
}
