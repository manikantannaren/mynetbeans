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
import org.pr.nb.sqlite3.utils.ConfigFileUtils;
import org.pr.nb.sqlite3.utils.Logger;
import org.pr.nb.sqlite3.utils.NBSqlite3Exception;
import org.pr.nb.sqlite3.utils.TraversalException;
import org.pr.nb.sqlite3.jdbc.Sqlite3DB;
import org.pr.nb.sqlite3.nodes.listeners.NBSQliteEventType;
import org.pr.nb.sqlite3.nodes.listeners.Notifier;

/**
 *
 * @author msivasub
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class NBSqlite3DBInstanceFactory implements PropertyChangeListener {

    private NBSqlite3DBInstanceFactory() {
        Notifier.getInstance().addPropertyChangeListener(this);
    }

    public List<Sqlite3DB> getExistingConfigs() {
        try {
            List<Reader> readers = ConfigFileUtils.getInstance().getExistingConfigs();

            Stream<Sqlite3DB> dataStream = readers.stream().map(reader -> {
                try {
                    Sqlite3DB db = new Sqlite3DB.BuilderWithJson().withReader(reader).build();
                    return db;
                } catch (Exception ex) {
                    throw new TraversalException("coudl not read config", ex);
                }
            });
            return dataStream.collect(Collectors.toList());
        } catch (NBSqlite3Exception ex) {
            Exceptions.printStackTrace(ex);
        }
        return Collections.EMPTY_LIST;
    }

    public Sqlite3DB fromUserInput(String name, String path) {
        return new Sqlite3DB.Builder().withName(name).withPath(path).build();
    }

    public void save(Sqlite3DB data) throws IOException {
        ConfigFileUtils.getInstance().save(data);
    }

    public void delete(Sqlite3DB data) throws IOException {
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
            case DROP_INSTANCE:
                doDelete(evt);
                break;
            default:
                Logger.getDefaultLogger().info(NBSqlite3DBInstanceFactory.class, "Ignoring event {0}", eventType);
                break;
        }
    }

    private void doAdd(PropertyChangeEvent evt) {
        Sqlite3DB data = (Sqlite3DB) evt.getNewValue();
        try {
            save(data);
            sendRefresh();
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    private void doDelete(PropertyChangeEvent evt) {
        try {
            Sqlite3DB data = (Sqlite3DB) evt.getNewValue();
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
