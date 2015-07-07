/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.csv.nb.csv.handler.impl;

import org.openide.filesystems.FileObject;
import org.openide.util.lookup.ServiceProvider;
import org.pr.nb.csv.nb.csv.handler.CSVFileException;
import org.pr.nb.csv.nb.csv.handler.CSVReader;
import org.pr.nb.csv.nb.csv.handler.Sheet;

/**
 *
 * @author Kaiser
 */
@ServiceProvider(service = CSVReader.class)
public class CSVReaderImpl implements CSVReader{

    @Override
    public Sheet readCSVFile(FileObject csvFile) throws CSVFileException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
