/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.sqlite3.jdbc;

import org.pr.nb.sqlite3.common.NBSqlite3Object;
import org.pr.nb.sqlite3.common.NBSqlite3Exception;
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
import org.pr.nb.sqlite3.common.Logger;

/**
 *
 * @author msivasub
 */
public class NBSqlite3Client {

    private static final String DRIVER = "org.sqlite.JDBC";
    private static final String CONNECTIONURISTRING = "jdbc:sqlite3:%s";
    private Connection jdbcConnection;

    public NBSqlite3Client() {
    }

    public void connect(String dbPath) throws NBSqlite3Exception {
        if (connected()) {
            return;
        }
        try {
            Class.forName(DRIVER);
            Logger.getDefaultLogger().info(getClass(), "Loaded Driver {0}", DRIVER);
        } catch (ClassNotFoundException ex) {
            throw new NBSqlite3Exception(ex);
        }

        String uri = String.format(CONNECTIONURISTRING, dbPath);
        try {
            NBSqlite3Client client = new NBSqlite3Client();
            client.jdbcConnection = DriverManager.getConnection(uri);
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

    public List<Sqlite3Table> getTables() throws NBSqlite3Exception {
        if (!connected()) {
            throw new NBSqlite3Exception(new IllegalStateException("DB not connected. Use connect first"));
        }
        try {
            DatabaseMetaData dbmd = jdbcConnection.getMetaData();
            ResultSet rs = dbmd.getTables(null, null, null, null);
            ResultSetIterator<Sqlite3Table> iterator = new ResultSetIterator<>(rs, NBSqlite3Object.Types.TABLE);
            Stream<Sqlite3Table> dataStream = StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator, 0), false);
            return dataStream.collect(Collectors.toList());
        } catch (SQLException ex) {
            throw new NBSqlite3Exception(ex);
        }
    }

}
