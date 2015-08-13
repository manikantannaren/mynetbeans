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
package org.pr.nb.plugins.mongodb.data;

import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;
import org.bson.types.ObjectId;
import org.json.simple.JSONAware;
import org.json.simple.JSONObject;
import org.json.simple.JSONStreamAware;

/**
 *
 * @author Mahakaal
 */
public class MongoDBInstance implements JSONAware, JSONStreamAware, Serializable {

    public static final String FIELD_HOSTNAME = "hostname";
    public static final String FIELD_PORT = "port";
    public static final String FIELD_USERNAME = "username";
    public static final String FIELD_DISPLAY_NAME = "displayName";
    public static final String FIELD_ID = "id";

    private String id;
    private String hostName;
    private Integer portNumber;
    private String userName;
    private String displayName;

    public MongoDBInstance() {
        id = new ObjectId().toHexString();
    }

    public MongoDBInstance(String id, String hostName, Integer portNumber, String userName, String displayName) {
        this.id = id;
        this.hostName = hostName;
        this.portNumber = portNumber;
        this.userName = userName;
        this.displayName = displayName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public Integer getPortNumber() {
        return portNumber;
    }

    public void setPortNumber(Integer portNumber) {
        this.portNumber = portNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toJSONString() {
        StringBuilder builder = new StringBuilder("{");
        if (isNotEmpty(hostName)) {
            builder.append(addIndentation(4)).append(JSONObject.escape(FIELD_HOSTNAME)).append(":").append(JSONObject.escape(hostName));
        }
        if (null != portNumber) {
            builder.append(addIndentation(4)).append(JSONObject.escape(FIELD_PORT)).append(":").append(portNumber);
        }
        if (isNotEmpty(userName)) {
            builder.append(addIndentation(4)).append(JSONObject.escape(FIELD_USERNAME)).append(":").append(JSONObject.escape(userName));
        }
        if (isNotEmpty(displayName)) {
            builder.append(addIndentation(4)).append(JSONObject.escape(FIELD_DISPLAY_NAME)).append(":").append(JSONObject.escape(displayName));
        }

        builder.append("}");
        return builder.toString();
    }

    @Override
    public void writeJSONString(Writer out) throws IOException {
        out.write(toJSONString());
        out.flush();
    }

    private String addIndentation(int numSpaces) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < numSpaces; i++) {
            builder.append(" ");
        }
        return builder.toString();
    }

    private boolean isNotEmpty(String data) {
        return null != data && data.trim().length() > 0;
    }
}
