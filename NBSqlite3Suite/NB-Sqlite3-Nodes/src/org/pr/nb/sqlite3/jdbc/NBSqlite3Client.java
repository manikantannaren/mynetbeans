/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.sqlite3.jdbc;

import org.pr.nb.sqlite3.utils.NBSqlite3Exception;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import org.pr.nb.sqlite3.utils.Logger;

/**
 *
 * @author msivasub
 */
@SuppressWarnings({"unchecked","rawtypes"})
public class NBSqlite3Client {

    private static final String DRIVER = "org.sqlite.JDBC";
    private static final String CONNECTIONURISTRING = "jdbc:sqlite:%s";
    private Connection jdbcConnection;
    private final Sqlite3DB db;

    NBSqlite3Client(Sqlite3DB db) {
        this.db = db;
    }

    public void connect() throws NBSqlite3Exception {
        if (connected()) {
            return;
        }
        try {
            Class.forName(DRIVER);
            Logger.getDefaultLogger().info(getClass(), "Loaded Driver %s", DRIVER);
        } catch (ClassNotFoundException ex) {
            throw new NBSqlite3Exception(ex);
        }

        String uri = String.format(CONNECTIONURISTRING, db.getDbPath());
        try {
            jdbcConnection = DriverManager.getConnection(uri);
        } catch (SQLException ex) {
            throw new NBSqlite3Exception(ex);
        }
    }

    public boolean connected() throws NBSqlite3Exception {
        try {
            return jdbcConnection != null && !jdbcConnection.isClosed();
        } catch (SQLException ex) {
            throw new NBSqlite3Exception(ex);
        }
    }

    public boolean close() throws NBSqlite3Exception {
        try {
            jdbcConnection.close();
            jdbcConnection = null;
            return true;
        } catch (SQLException ex) {
            throw new NBSqlite3Exception(ex);
        }
    }

    @SuppressWarnings("unchecked")
    public List<Sqlite3Table> getTables() throws NBSqlite3Exception {
        checkConnection();
        try {
            DatabaseMetaData dbmd = jdbcConnection.getMetaData();
            ResultSet rs = dbmd.getTables(null, null, null, null);
            ResultSetIterator<Sqlite3Table, Sqlite3DB> iterator = new ResultSetIterator<>(db,rs, NBSqlite3Object.Types.TABLE);
            Stream<Sqlite3Table> dataStream = StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator, 0), false);
            return dataStream.collect(Collectors.toList());
        } catch (SQLException ex) {
            throw new NBSqlite3Exception(ex);
        }
    }

    public List<Sqlite3Column> getColumns(Sqlite3Table table) throws NBSqlite3Exception {
        try {
            checkConnection();
            DatabaseMetaData dbmd = jdbcConnection.getMetaData();
            ResultSet rs = dbmd.getColumns(table.getCatalog(), table.getSchema(), table.getName(), null);
            ResultSetIterator<Sqlite3Column, Sqlite3Table> iterator = new ResultSetIterator<>(table,rs, NBSqlite3Object.Types.COLUMN);
            Stream<Sqlite3Column> dataStream = StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator, 0), false);
            return dataStream.collect(Collectors.toList());
        } catch (SQLException ex) {
            throw new NBSqlite3Exception(ex);
        }
    }

    private boolean checkConnection() throws NBSqlite3Exception {
        if (!connected()) {
            throw new NBSqlite3Exception(new IllegalStateException("DB not connected. Use connect first"));
        }
        return true;
    }

}
