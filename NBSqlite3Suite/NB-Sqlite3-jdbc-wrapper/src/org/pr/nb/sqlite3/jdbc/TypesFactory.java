/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.sqlite3.jdbc;

import org.pr.nb.sqlite3.common.NBSqlite3Object;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.pr.nb.sqlite3.common.NBSqlite3Exception;

/**
 *
 * @author msivasub
 */
public abstract class TypesFactory<E> {

    public static TypesFactory getFactory(NBSqlite3Object.Types type, ResultSet rs) throws NBSqlite3Exception {
        switch (type) {
            case TABLE:
                return new SQLDBTableFactory(rs);
            default:
                throw new NBSqlite3Exception("Unknown type");
        }
    }

    public abstract E build() throws NBSqlite3Exception;

    private static class SQLDBTableFactory extends TypesFactory<Sqlite3Table> {

        ResultSet rs;

        public SQLDBTableFactory(ResultSet rs) {
            this.rs = rs;
        }

        @Override
        public Sqlite3Table build() throws NBSqlite3Exception {
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
                        .build();
            } catch (SQLException ex) {
                throw new NBSqlite3Exception(ex);
            }
        }
    }
}
