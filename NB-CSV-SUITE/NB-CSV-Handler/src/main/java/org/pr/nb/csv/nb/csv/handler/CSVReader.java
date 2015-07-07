/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.csv.nb.csv.handler;

import org.openide.filesystems.FileObject;

/**
 *
 * @author Kaiser
 */
public interface CSVReader {
    
    public Sheet readCSVFile(FileObject csvFile) throws CSVFileException;
    
}
