/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.sqlite3.nodes;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import org.openide.nodes.ChildFactory;
import org.openide.util.Exceptions;
import org.pr.nb.sqlite3.common.NBSqlite3Exception;
import org.pr.nb.sqlite3.jdbc.Sqlite3Column;
import org.pr.nb.sqlite3.jdbc.Sqlite3Table;

/**
 *
 * @author msivasub
 */
public class NBSqlite3ColumnNodeFactory extends ChildFactory.Detachable<Sqlite3Column> implements PropertyChangeListener {

    private final Sqlite3Table table;

    NBSqlite3ColumnNodeFactory(Sqlite3Table table) {
        this.table = table;
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected boolean createKeys(List<Sqlite3Column> list) {
        try {
            list.addAll(table.getChildren());
        } catch (NBSqlite3Exception ex) {
            Exceptions.printStackTrace(ex);
        }
        return true;
    }

}
