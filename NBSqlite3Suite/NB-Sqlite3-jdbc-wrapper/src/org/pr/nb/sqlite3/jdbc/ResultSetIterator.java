/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.sqlite3.jdbc;

import org.pr.nb.sqlite3.common.NBSqlite3Object;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.function.Consumer;
import org.pr.nb.sqlite3.common.NBSqlite3Exception;
import org.pr.nb.sqlite3.common.TraversalException;

/**
 *
 * @author msivasub
 * @param <E> type of Object expected in next call. The iterator should return
 * fully constructed data;
 */
public class ResultSetIterator<E> implements Iterator {
    
    private final ResultSet rs;
    private final NBSqlite3Object.Types type;
    
    public ResultSetIterator(ResultSet rs, NBSqlite3Object.Types type) {
        this.rs = rs;
        this.type = type;
    }
    
    @Override
    public boolean hasNext() {
        
        try {
            boolean retValue = rs != null && !rs.isClosed() && rs.next();
            if (!retValue) {
                close();
            }
            return retValue;
        } catch (SQLException ex) {
            throw new TraversalException("Could not query result set for more results", ex);
        }
    }
    
    @Override
    public E next() {
        try {
            TypesFactory<E> factory = TypesFactory.getFactory(type, rs);
            return factory.build();
        } catch (NBSqlite3Exception ex) {
            Throwable cause = ex.getCause();
            cause = cause != null? cause:ex;
            throw new TraversalException("Error in building object", cause);
        }
    }
    
    @Override
    public void forEachRemaining(Consumer action) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void close() throws TraversalException {
        try {
            rs.close();
        } catch (SQLException ex) {
            throw new TraversalException("Could not close resultset", ex);
        }
    }
    
}
