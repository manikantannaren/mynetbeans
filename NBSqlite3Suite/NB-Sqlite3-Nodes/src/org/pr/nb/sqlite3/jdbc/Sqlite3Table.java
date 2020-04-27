/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.sqlite3.jdbc;

import java.util.List;
import org.pr.nb.sqlite3.utils.NBSqlite3Exception;

/**
 *
 * @author msivasub
 */
@SuppressWarnings({"unchecked","rawtypes"})
public class Sqlite3Table implements NBSqlite3Object {
    
    private final String name;
    private final String schema;
    private final String tableType;
    private final String catalog;
    private final Sqlite3DB database;
    
    private Sqlite3Table(String name, String schema,  String catalog,String tableType, Sqlite3DB db) {
        this.name = name;
        this.schema = schema;
        this.tableType = tableType;
        this.catalog = catalog;
        this.database = db;
    }

    public String getSchema() {
        return schema;
    }

    public String getTableType() {
        return tableType;
    }

    public String getCatalog() {
        return catalog;
    }
    
    @Override
    public String getId() {
        return getName();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toExternalForm() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Types getType() {
        return Types.TABLE;
    }

    @Override
    public List<Sqlite3Column> getChildren() throws NBSqlite3Exception {
        return database.getColumns(this);
    }

    @Override
    public Sqlite3DB getParent() {
        return database;
    }

    public void createTable(List<Sqlite3Column> columns) throws NBSqlite3Exception{
        database.createTable(this.getName(), columns);
    }
    public static class Builder {

        private String name;
        private String catalog;
        private String type;
        private String schema;
        private Sqlite3DB db;

        public Builder() {
        }

        public Builder withTableName(String name) {
            this.name = name;
            return this;
        }

        public Builder withCatalog(String catalog) {
            this.catalog = catalog;
            return this;
        }

        public Builder withType(String type) {
            this.type = type;
            return this;
        }

        public Builder withSchema(String schema) {
            this.schema = schema;
            return this;
        }

        public Builder withDatabase(Sqlite3DB db){
            this.db = db;
            return this;
        }
        public Sqlite3Table build() {
            return new Sqlite3Table(name, schema, catalog, type,db);
        }
    }

}
