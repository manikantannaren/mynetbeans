/*
 * Copyright 2016 Mahakaal.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.pr.nb.plugins.mongodb.nodes;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.modules.Places;
import org.openide.nodes.ChildFactory;
import org.openide.util.Exceptions;
import org.pr.nb.plugins.mongodb.data.MongoDBInstance;

/**
 *
 * @author Mahakaal
 */
class MongoDBChildrenFactory extends ChildFactory.Detachable<MongoDBInstance> {

    private static final String CONFIG_DATABASES_MONGO_DB = "config/Databases/MongoDB";

    List<MongoDBInstance> registeredInstances;

    MongoDBChildrenFactory() {
        registeredInstances = readStoredInstances();
    }

    @Override
    protected boolean createKeys(List<MongoDBInstance> list) {
        list.addAll(registeredInstances);
        return true;
    }

    private List<MongoDBInstance> readStoredInstances() {
        List<MongoDBInstance> retValue = new ArrayList<>();
        try {
            FileObject mongoDBInstanceDirectory = locateOrCreateMongoDBConfigStore();
            FileObject[] registeredInstancesJSON = mongoDBInstanceDirectory.getChildren();
            for (FileObject registeredInstanceJSON : registeredInstancesJSON) {
                MongoDBInstance registeredInstance = parseJSOn(registeredInstanceJSON);
                if (registeredInstance != null) {
                    retValue.add(registeredInstance);
                }
            }
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
        return retValue;
    }

    private MongoDBInstance parseJSOn(FileObject registeredInstanceJSON) {
        MongoDBInstance retValue = null;
        try (InputStreamReader in = new InputStreamReader(registeredInstanceJSON.getInputStream())) {
            JSONParser parser = new JSONParser();
            JSONObject parsedObject = (JSONObject) parser.parse(in);
            String id = sanitizeValue(parsedObject.get(MongoDBInstance.FIELD_ID));
            String hostName = sanitizeValue(parsedObject.get(MongoDBInstance.FIELD_HOSTNAME));
            Integer portNumber = (Integer) parsedObject.get(MongoDBInstance.FIELD_PORT);
            String userName = sanitizeValue(parsedObject.get(MongoDBInstance.FIELD_USERNAME));
            String displayName = sanitizeValue(parsedObject.get(MongoDBInstance.FIELD_DISPLAY_NAME));
            retValue = new MongoDBInstance(id, hostName, portNumber, userName, displayName);
        } catch (IOException | ParseException ex) {
            Exceptions.printStackTrace(ex);
        }
        return retValue;
    }

    private String sanitizeValue(Object data) {
        return data == null ? null : data + "";
    }

    private FileObject locateOrCreateMongoDBConfigStore() throws IOException {
        File userDir = Places.getUserDirectory();
        FileObject userDirFileObject = FileUtil.toFileObject(FileUtil.normalizeFile(userDir));
        return FileUtil.createFolder(userDirFileObject, CONFIG_DATABASES_MONGO_DB);
    }
}
