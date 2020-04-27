/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.sqlite3.jdbc;

import java.sql.JDBCType;
import java.util.List;

/**
 *
 * @author msivasub
 */
public class Sqlite3Column implements NBSqlite3Object{

    private final String columnName;
    private final String autoIncremented;
    private final String generated;
    private final String nullable;
    private final int columnSize;
    private final JDBCType jdbcType;
    private final String columnType;
    private final Sqlite3Table table;
    
    private Sqlite3Column(String columnName, String autoIncremented, String generated, String nullable, int columnSize, JDBCType jdbcType, String columnType, Sqlite3Table table) {
        this.columnName = columnName;
        this.autoIncremented = autoIncremented;
        this.generated = generated;
        this.nullable = nullable;
        this.columnSize = columnSize;
        this.jdbcType = jdbcType;
        this.columnType = columnType;
        this.table = table;
    }

    @Override
    public String getId() {
        return getName();
    }

    @Override
    public String getName() {
        return columnName;
    }

    public JDBCType getJdbcType() {
        return jdbcType;
    }

    public int getColumnSize() {
        return columnSize;
    }

    @Override
    public String toExternalForm() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<NBSqlite3Object> getChildren() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Types getType() {
        return Types.COLUMN;
    }

    @Override
    public String toString() {
        return "Sqlite3Column{" + "columnName=" + columnName + ", autoIncremented=" + autoIncremented + ", generated=" + generated + ", nullable=" + nullable + ", columnSize=" + columnSize + ", jdbcType=" + jdbcType + ", columnType=" + columnType + '}';
    }

    @Override
    public <E extends NBSqlite3Object> E getParent() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static class Builder {

        private String columnName;
        private String autoIncremented;
        private String generated;
        private String nullable;
        private int columnSize;
        private JDBCType jdbcType;
        private String columnType;
        private Sqlite3Table table;
        public Builder() {
            
        }

        public Sqlite3Column build() {
            return new Sqlite3Column(columnName, autoIncremented, generated, nullable, columnSize, jdbcType, columnType, table);
        }

        public Builder withName(String columnName) {
            this.columnName = columnName;
            return this;
        }

        public Builder withColumnType(String columnType) {
            this.columnType = columnType;
            return this;
        }

        public Builder withJDBCType(JDBCType jdbcType) {
            this.jdbcType = jdbcType;
            return this;
        }

        public Builder withColumnSize(int columnSize) {
            this.columnSize = columnSize;
            return this;
        }

        public Builder withNullable(String nullable) {
            this.nullable = nullable;
            return this;
        }

        public Builder withGenerated(String generated) {
            this.generated = generated;
            return this;
        }

        public Builder withAutoIncrement(String autoIncremented) {
            this.autoIncremented = autoIncremented;
            return this;
        }
        public Builder withTable(Sqlite3Table table){
            this.table = table;
            return this;
        }
    }
    
}
