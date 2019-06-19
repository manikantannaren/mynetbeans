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
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.pr.nb.sqlite3.utils.NBSqlite3Exception;
import org.pr.nb.sqlite3.jdbc.Sqlite3DB;
import org.pr.nb.sqlite3.jdbc.Sqlite3Table;

/**
 *
 * @author msivasub
 */
public class NBSqlite3TableNodeFactory extends ChildFactory.Detachable<Sqlite3Table> implements PropertyChangeListener {

    private final Sqlite3DB database;

    public NBSqlite3TableNodeFactory(Sqlite3DB database) {
        this.database = database;
    }

    @Override
    protected boolean createKeys(List<Sqlite3Table> list) {
        try {
            list.addAll(database.getChildren());
        } catch (NBSqlite3Exception ex) {
            Exceptions.printStackTrace(ex);
        }
        return true;
    }

    @Override
    protected Node createNodeForKey(Sqlite3Table key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.    }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
