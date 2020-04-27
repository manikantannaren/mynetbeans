/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.sqlite3.jdbc;

import java.io.File;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.WeakHashMap;
import org.apache.commons.lang.StringUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.pr.nb.sqlite3.utils.NBSqlite3Exception;

/**
 *
 * @author msivasub
 */
@org.openide.util.NbBundle.Messages({
    "# {0} - DB file user has selected",
    "ERR_INVALID_DB_PATH=Invalid path {0}. Path is null or is a directory"
})
public final class Sqlite3DB implements NBSqlite3Object {

    private static final long serialVersionUID = 1L;
    private static final String ID = "id";
    private static final String DB_NAME = "dbName";
    private static final String DB_PATH = "dbPath";

    private final String name;
    private final String dbPath;
    private String id;
    private NBSqlite3Client client;

    private Sqlite3DB(String name, String dbPath) {
        this.dbPath = dbPath;

        if (StringUtils.isEmpty(name)) {
            this.name = dbPath;
        } else {
            this.name = name;
        }
    }

    @Override
    public String getName() {
        return this.name;
    }

    public String getDbPath() {
        return dbPath;
    }

    @Override
    public String toString() {
        return "Sqlite3InstanceImpl{" + "name=" + name + ", dbPath=" + dbPath + '}';
    }

    @Override
    public String toExternalForm() {
        //return json string
        Map<String, String> data = new WeakHashMap<>();
        data.put(ID, getId());
        data.put(DB_NAME, getName());
        data.put(DB_PATH, getDbPath());
        JSONObject json = new JSONObject(data);
        return json.toJSONString();
    }

    @Override
    public String getId() {
        if (StringUtils.isEmpty(id)) {
            id = generateId();
        }
        return id;
    }

    @Override
    public List<Sqlite3Table> getChildren() throws NBSqlite3Exception {
        NBSqlite3Client dbClient = getClient();
        return dbClient.getTables();
    }

    @Override
    public Types getType() {
        return Types.DB;
    }

    void createTable(String name, List<Sqlite3Column> columns) {
        
    }
    
    private String generateId() {
        String _id = getName();
        if (StringUtils.isEmpty(_id)) {
            //construct name from path
            _id = getDbPath();
        }
        //replace all path separators with _
        //replace all whitespaces with -
        _id = StringUtils.replaceChars(_id, ' ', '-');
        _id = StringUtils.replaceChars(_id, File.separatorChar, '_');
        return _id + "-" + System.currentTimeMillis();
    }

    @Override
    public <E extends NBSqlite3Object> E getParent() {
        throw new UnsupportedOperationException("No parent for database");
    }

    public List<Sqlite3Column> getColumns(Sqlite3Table table) throws NBSqlite3Exception {
        NBSqlite3Client dbClient = getClient();
        return dbClient.getColumns(table);
    }
    
    private NBSqlite3Client getClient() throws NBSqlite3Exception {
        if(client == null){
            client = new NBSqlite3Client(this);
            client.connect();
        }
        return client;
    }

    public static class BuilderWithJson {

        private Reader in;

        public Builder withJson(String json) throws Exception {
            return withReader(new StringReader(json));
        }

        public Builder withReader(Reader in) throws Exception {
            this.in = in;
            return build();
        }

        private Builder build() throws Exception {
            JSONParser parser = new JSONParser();
            JSONObject registeredInstance = (JSONObject) parser.parse(in);
            String dbName = Objects.toString(registeredInstance.get(DB_NAME), "").trim();
            String dbPath = Objects.toString(registeredInstance.get(DB_PATH), "").trim();
            String id = Objects.toString(registeredInstance.get(ID), "").trim();
            return new Builder().withId(id).withName(dbName).withPath(dbPath);
        }
    }

    public static class Builder {

        private String name;
        private String path;
        private String id;

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withPath(String dbPath) {
            if (StringUtils.isEmpty(dbPath)) {
                throw new IllegalArgumentException(Bundle.ERR_INVALID_DB_PATH(dbPath));
            }
            this.path = dbPath;
            return this;
        }

        public Sqlite3DB build() {
            return new Sqlite3DB(this.name, this.path);
        }
    }
}
