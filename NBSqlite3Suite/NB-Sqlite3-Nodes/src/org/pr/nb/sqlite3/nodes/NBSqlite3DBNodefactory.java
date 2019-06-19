/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.sqlite3.nodes;

import java.beans.IntrospectionException;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.pr.nb.sqlite3.jdbc.NBSqlite3Object;
import org.pr.nb.sqlite3.jdbc.Sqlite3DB;
import org.pr.nb.sqlite3.nodes.listeners.NBSQliteEventType;
import org.pr.nb.sqlite3.nodes.listeners.Notifier;

/**
 *
 * @author msivasub
 */
class NBSqlite3DBNodefactory extends ChildFactory.Detachable<Sqlite3DB> implements PropertyChangeListener {

    public NBSqlite3DBNodefactory() {
        Notifier.getInstance().addPropertyChangeListener(this);
    }

    @Override
    protected boolean createKeys(List<Sqlite3DB> list) {
        list.addAll(NBSqlite3DBInstanceFactory.getInstance().getExistingConfigs());
        addNotify();
        return true;
    }

    @Override
    protected Node createNodeForKey(Sqlite3DB key) {
        try {
            return new NBSqlite3DBNode(key);
        } catch (IntrospectionException ex) {
            Exceptions.printStackTrace(ex);
            return null;
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (NBSQliteEventType.REFRESH.equals(NBSQliteEventType.fromName(evt.getPropertyName()))) {
            refresh(true);
        }
    }


}
