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
import org.pr.nb.sqlite3.common.NBSqlite3Object;
import org.pr.nb.sqlite3.nodes.listeners.NBSQliteEventType;
import org.pr.nb.sqlite3.nodes.listeners.Notifier;

/**
 *
 * @author msivasub
 */
class NBSQlite3DBNodefactory extends ChildFactory.Detachable<NBSqlite3Object> implements PropertyChangeListener {

    public NBSQlite3DBNodefactory() {
        Notifier.getInstance().addPropertyChangeListener(this);
    }

    @Override
    protected boolean createKeys(List<NBSqlite3Object> list) {
        list.addAll(NBSqlite3DBInstanceFactory.getInstance().getExistingConfigs());
        addNotify();
        return true;
    }

    @Override
    protected Node createNodeForKey(NBSqlite3Object key) {
        try {
            return new NBSQlite3DBNode(key);
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
