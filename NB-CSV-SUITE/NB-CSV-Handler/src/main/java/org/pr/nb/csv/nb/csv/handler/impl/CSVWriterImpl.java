/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.csv.nb.csv.handler.impl;

import org.openide.filesystems.FileObject;
import org.openide.util.lookup.ServiceProvider;
import org.pr.nb.csv.nb.csv.handler.CSVFileException;
import org.pr.nb.csv.nb.csv.handler.CSVWriter;
import org.pr.nb.csv.nb.csv.handler.Sheet;

/**
 *
 * @author Kaiser
 */
@ServiceProvider(service = CSVWriter.class)
public class CSVWriterImpl implements CSVWriter{

    @Override
    public FileObject writeCSV(FileObject csvFile, Sheet data) throws CSVFileException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
