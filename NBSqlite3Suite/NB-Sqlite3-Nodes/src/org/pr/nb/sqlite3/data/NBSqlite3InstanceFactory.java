/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.sqlite3.data;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import org.openide.util.Exceptions;
import org.pr.nb.sqlite3.logger.Logger;
import org.pr.nb.sqlite3.nodes.listeners.NBSQliteEventType;
import org.pr.nb.sqlite3.nodes.listeners.Notifier;

/**
 *
 * @author msivasub
 */
public class NBSqlite3InstanceFactory implements PropertyChangeListener {

    private NBSqlite3InstanceFactory() {
    }

    public List<NBSqlite3Object> getExistingConfigs() {
        return ConfigFileUtils.getInstance().getExistingConfigs();
    }

    public NBSqlite3Object fromUserInput(String name, String path) {
        return new Sqlite3InstanceImpl.Builder().withName(name).withPath(path).build();
    }

    public void save(NBSqlite3Object data) throws IOException {
        ConfigFileUtils.getInstance().save(data);
    }

    public void delete(NBSqlite3Object data) throws IOException{
        ConfigFileUtils.getInstance().delete(data);
    }

    public static NBSqlite3InstanceFactory getInstance() {
        return NBSqlite3InstanceFactoryHolder.INSTANCE;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        NBSQliteEventType eventType = NBSQliteEventType.fromName(evt.getPropertyName());
        switch (eventType) {
            case ADD_INSTANCE:
                doAdd(evt);
                break;
            case DELETE_INSTANCE:
                doDelete(evt);
                break;
            default:
                Logger.getDefaultLogger().log(NBSqlite3InstanceFactory.class, Level.FINE, "Ignoring event {0}", null, eventType);
                break;
        }
    }

    private void doAdd(PropertyChangeEvent evt) {
        NBSqlite3Object data = (NBSqlite3Object) evt.getNewValue();
        try {
            save(data);
            sendRefresh();
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    private void doDelete(PropertyChangeEvent evt) {
        try {
            NBSqlite3Object data = (NBSqlite3Object) evt.getNewValue();
            delete(data);
            sendRefresh();
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    private void sendRefresh() {
        Notifier.getInstance().dispatchEvent(NBSQliteEventType.REFRESH, this, null, null);
    }

    private static class NBSqlite3InstanceFactoryHolder {

        private static final NBSqlite3InstanceFactory INSTANCE = new NBSqlite3InstanceFactory();
    }
}
