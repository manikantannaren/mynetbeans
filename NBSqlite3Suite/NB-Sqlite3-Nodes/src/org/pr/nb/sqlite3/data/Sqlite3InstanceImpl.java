/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.sqlite3.data;

import java.io.Reader;
import java.io.StringReader;
import java.util.Map;
import java.util.Objects;
import java.util.WeakHashMap;
import org.apache.commons.lang.StringUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author msivasub
 */
@org.openide.util.NbBundle.Messages({
    "# {0} - DB file user has selected",
    "ERR_INVALID_DB_PATH=Invalid path {0}. Path is null or is a directory"
})
final class Sqlite3InstanceImpl implements NBSqlite3Object {

    private static final long serialVersionUID = 1L;

    private final String name;
    private final String dbPath;
    private String id;

    private Sqlite3InstanceImpl(String name, String dbPath) {
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

    @Override
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
        data.put("id", id);
        data.put("name", getName());
        data.put("dbPath", getDbPath());
        JSONObject json = new JSONObject(data);
        return json.toJSONString();
    }

    @Override
    public String getId() {
        if(StringUtils.isEmpty(id)){
            id = ConfigFileUtils.getInstance().generateId(this);
        }
        return id;
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
            String dbName = Objects.toString(registeredInstance.get("dbname"), "").trim();
            String dbPath = Objects.toString(registeredInstance.get("dbpath"), "").trim();
            return new Builder().withName(dbName).withPath(dbPath);
        }
    }

    public static class Builder {

        private String name;
        private String path;

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

        public Sqlite3InstanceImpl build() {
            return new Sqlite3InstanceImpl(this.name, this.path);
        }
    }
}
