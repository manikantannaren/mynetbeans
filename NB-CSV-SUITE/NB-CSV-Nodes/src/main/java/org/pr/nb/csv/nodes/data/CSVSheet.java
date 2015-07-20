/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.csv.nodes.data;

import org.openide.filesystems.FileObject;

/**
 *
 * @author Kaiser
 */
public class CSVSheet {
    private String name;

    FileObject chosenFile;

    public CSVSheet(String name, FileObject chosenFile) {
        this.name = name;
        this.chosenFile = chosenFile;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FileObject getChosenFile() {
        return chosenFile;
    }
    
}
