/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.csv.nodes;

import com.opencsv.CSVReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import org.openide.filesystems.FileUtil;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle;
import org.pr.nb.csv.nodes.data.CSVRow;
import org.pr.nb.csv.nodes.data.CSVSheet;

/**
 *
 * @author Kaiser
 */
@NbBundle.Messages({
    "waitnode.text=Please wait, reading file"
})
public class CSVSheetFactory extends ChildFactory.Detachable<CSVRow> {

    private CSVSheet key;

    public CSVSheetFactory(CSVSheet key) {
        this.key = key;
    }

    @Override
    protected boolean createKeys(List<CSVRow> toPopulate) {

        Reader in = null;
        try {
            assert key.getChosenFile() != null;
            File file = FileUtil.toFile(key.getChosenFile());
            in = new BufferedReader(new FileReader(file));
            CSVReader reader = new CSVReader(in);
            List<String[]> data = reader.readAll();
            for (int i = 0; i < data.size(); i++) {
                String[] datum = data.get(i);
                CSVRow row = new CSVRow(datum,i);
                toPopulate.add(row);
            }
        } catch (FileNotFoundException ex) {
            Exceptions.printStackTrace(ex);
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
        }
        return true;
    }

    @Override
    protected Node createWaitNode() {
        Node waitNode = super.createWaitNode(); 
        waitNode.setDisplayName(Bundle.waitnode_text());
        return waitNode;
    }

    @Override
    protected Node createNodeForKey(CSVRow key) {
        CSVRowNode rowNode = new CSVRowNode(key);
        
        return rowNode;
    }
    

    
}
