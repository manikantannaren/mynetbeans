/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.sqlite3.jdbc;

import java.sql.JDBCType;
import org.pr.nb.sqlite3.common.NBSqlite3Object;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.pr.nb.sqlite3.common.NBSqlite3Exception;

/**
 *
 * @author msivasub
 */
public abstract class TypesFactory<E, P extends NBSqlite3Object> {

    public static TypesFactory getFactory(NBSqlite3Object.Types type) throws NBSqlite3Exception {
        switch (type) {
            case TABLE:
                return new SQLDBTableFactory();
            case COLUMN:
                return new SQLDBColumnFactory();
            default:
                throw new NBSqlite3Exception("Unknown type");
        }
    }

    public abstract E build(P parent, ResultSet rs) throws NBSqlite3Exception;

    private static class SQLDBTableFactory extends TypesFactory<Sqlite3Table, Sqlite3DB> {

        private SQLDBTableFactory() {
        }

        @Override
        public Sqlite3Table build(Sqlite3DB parent, ResultSet rs) throws NBSqlite3Exception {
            try {
                String catalog = rs.getString("TABLE_CAT");
                String schema = rs.getString("TABLE_SCHEM");
                String name = rs.getString("TABLE_NAME");
                String type = rs.getString("TABLE_TYPE");
//            String typeCat = rs.getString("TYPE_CAT");
//            String typeSchema = rs.getString("TYPE_SCHEM");
//            String typeName = rs.getString("TYPE_NAME");

                return new Sqlite3Table.Builder()
                        .withTableName(name)
                        .withCatalog(catalog)
                        .withType(type)
                        .withSchema(schema)
                        .withDatabase(parent)
                        .build();
            } catch (SQLException ex) {
                throw new NBSqlite3Exception(ex);
            }
        }

    }

    private static class SQLDBColumnFactory extends TypesFactory<Sqlite3Column, Sqlite3Table> {

        private SQLDBColumnFactory() {
        }

        @Override
        public Sqlite3Column build(Sqlite3Table parent, ResultSet rs) throws NBSqlite3Exception {
            try {
                String columnName = rs.getString("COLUMN_NAME");
                String columnType = rs.getString("TYPE_NAME");
                int dataType = rs.getInt("DATA_TYPE");
                JDBCType jdbcType = JDBCType.valueOf(dataType);
                int columnSize = rs.getInt("COLUMN_SIZE");
                String nullable = rs.getString("IS_NULLABLE");
                String generated = rs.getString("IS_GENERATEDCOLUMN");
                String autoIncremented = rs.getString("IS_AUTOINCREMENT");

                return new Sqlite3Column.Builder()
                        .withName(columnName)
                        .withColumnType(columnType)
                        .withJDBCType(jdbcType)
                        .withColumnSize(columnSize)
                        .withNullable(nullable)
                        .withGenerated(generated)
                        .withAutoIncrement(autoIncremented)
                        .withTable(parent)
                        .build();
            } catch (SQLException ex) {
                throw new NBSqlite3Exception(ex);
            }
        }
    }
}
