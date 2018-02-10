/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.mongodb.data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.util.UUID;
import org.apache.commons.lang.StringUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openide.filesystems.FileObject;
import org.openide.util.NbBundle;

/**
 *
 * @author mahakaal
 */
@NbBundle.Messages({
    "# {0} - Hostname",
    "DEFAULT_DISPLAY_NAME=Local instance: {0}"
}
)
public class NBMongoDBInstance implements Serializable {

    private String id;
    private String hostName;
    private Integer portNumber;
    private String userName;
    private String displayName;

    public NBMongoDBInstance() {
    }

    NBMongoDBInstance(String id, String hostName, Integer portNumber, String userName, String displayName) {
        this.hostName = hostName;
        this.portNumber = portNumber;
        this.userName = userName;
        this.displayName = displayName;
        this.id = StringUtils.isEmpty(id)
                ? UUID.randomUUID().toString() : id;
    }

    public NBMongoDBInstance(String hostName, Integer portNumber, String userName, String displayName) {
        this(UUID.randomUUID().toString(), hostName, portNumber, userName, displayName);
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

    public static final String FIELD_HOSTNAME = "hostname";
    public static final String FIELD_PORT = "port";
    public static final String FIELD_USERNAME = "username";
    public static final String FIELD_DISPLAY_NAME = "displayName";
    public static final String FIELD_ID = "id";

    public static NBMongoDBInstance deserialize(FileObject registeredInstanceJSONFile) throws FileNotFoundException, IOException, ParseException {
        NBMongoDBInstance retValue = new NBMongoDBInstance();
        JSONParser parser = new JSONParser();
        try (Reader reader = new BufferedReader(new InputStreamReader(registeredInstanceJSONFile.getInputStream()))) {
            JSONObject registeredInstanceJson = (JSONObject) parser.parse(reader);
            String id = santizeValue(registeredInstanceJson.get(FIELD_ID));
            String hostName = santizeValue(registeredInstanceJson.get(FIELD_HOSTNAME));
            hostName = StringUtils.isEmpty(hostName)?"127.0.0.1":hostName;
            String displayName = santizeValue(registeredInstanceJson.get(FIELD_DISPLAY_NAME));
            displayName = StringUtils.isEmpty(displayName) ? hostName : displayName;
            String userName = santizeValue(registeredInstanceJson.get(FIELD_USERNAME));
            String portString = santizeValue(registeredInstanceJson.get(FIELD_PORT));
            int portNumber = 27017;
            try{
                portNumber = Integer.parseInt(portString);
            }catch(NumberFormatException e){
                portNumber = 27017;
            }
            retValue.setId(id);
            retValue.setHostName(hostName);
            retValue.setDisplayName(displayName);
            retValue.setPortNumber(portNumber);
            retValue.setUserName(userName);
        }
        return retValue;
    }

    public void serialize(){
        
    }
    
    private static String santizeValue(Object data) {
        return data == null ? null : data + "";
    }

}
