/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.sqlite3.nodes;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.io.Reader;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.openide.util.Exceptions;
import org.pr.nb.sqlite3.common.ConfigFileUtils;
import org.pr.nb.sqlite3.common.Logger;
import org.pr.nb.sqlite3.common.NBSqlite3Exception;
import org.pr.nb.sqlite3.common.NBSqlite3Object;
import org.pr.nb.sqlite3.common.TraversalException;
import org.pr.nb.sqlite3.jdbc.Sqlite3DB;
import org.pr.nb.sqlite3.nodes.listeners.NBSQliteEventType;
import org.pr.nb.sqlite3.nodes.listeners.Notifier;

/**
 *
 * @author msivasub
 */
public class NBSqlite3DBInstanceFactory implements PropertyChangeListener {

    private NBSqlite3DBInstanceFactory() {
        Notifier.getInstance().addPropertyChangeListener(this);
    }

    public List<NBSqlite3Object> getExistingConfigs() {
        try {
            List<Reader> readers = ConfigFileUtils.getInstance().getExistingConfigs();
            
            Stream<Sqlite3DB> dataStream = readers.stream().map(reader->{
                try {
                    Sqlite3DB db = new Sqlite3DB.BuilderWithJson().withReader(reader).build();
                    return db;
                } catch (Exception ex) {
                    throw new TraversalException("coudl not read config",ex);
                }
            });
            return dataStream.collect(Collectors.toList());
        } catch (NBSqlite3Exception ex) {
            Exceptions.printStackTrace(ex);
        }
        return Collections.EMPTY_LIST;
    }

    public NBSqlite3Object fromUserInput(String name, String path) {
        return new Sqlite3DB.Builder().withName(name).withPath(path).build();
    }

    public void save(NBSqlite3Object data) throws IOException {
        ConfigFileUtils.getInstance().save(data);
    }

    public void delete(NBSqlite3Object data) throws IOException {
        ConfigFileUtils.getInstance().delete(data);
    }

    public static NBSqlite3DBInstanceFactory getInstance() {
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
                Logger.getDefaultLogger().info(NBSqlite3DBInstanceFactory.class, "Ignoring event {0}", eventType);
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

        private static final NBSqlite3DBInstanceFactory INSTANCE = new NBSqlite3DBInstanceFactory();
    }
}
