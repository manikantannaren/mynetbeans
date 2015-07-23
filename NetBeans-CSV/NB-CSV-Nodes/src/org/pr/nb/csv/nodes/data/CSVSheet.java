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

    private String csvText;
    
    public CSVSheet(String name, FileObject chosenFile) {
        this.name = name;
        this.chosenFile = chosenFile;
    }


    public CSVSheet(String name,FileObject fobj,  String csvText) {
        this(name,fobj);
        this.csvText = csvText;
    }

    public String getCsvText() {
        return csvText;
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
