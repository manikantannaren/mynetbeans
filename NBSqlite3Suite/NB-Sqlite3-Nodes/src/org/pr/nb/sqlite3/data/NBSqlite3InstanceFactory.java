/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.sqlite3.data;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.modules.Places;
import org.openide.util.Exceptions;

/**
 *
 * @author msivasub
 */
public class NBSqlite3InstanceFactory {

    private final String CONFIG_DATABASES_SQLITE3 = "config/Databases/sqlite3";

    private NBSqlite3InstanceFactory() {
    }

    public List<NBSqlite3Object> getExistingConfigs() {
        List<NBSqlite3Object> retValue = new ArrayList<>();
        try {

            FileObject[] configs = getConfigStore().getChildren();
            Arrays.stream(configs).forEach(configFile->{
                try {
                    Reader in = new InputStreamReader(configFile.getInputStream());
                    NBSqlite3Object data = new Sqlite3InstanceImpl.BuilderWithJson().withReader(in).build();
                    retValue.add(data);
                } catch (Exception ex) {
                    Exceptions.printStackTrace(ex);
                }
            });
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
        return retValue;
    }
    
    public NBSqlite3Object fromUserInput(String name, String path){
        return new Sqlite3InstanceImpl.Builder().withName(name).withPath(path).build();
    }

    public NBSqlite3Object save(NBSqlite3Object data){
      throw new UnsupportedOperationException();
    }
    
    private FileObject getConfigStore() throws IOException {
        File userDir = Places.getUserDirectory();
        FileObject userDirFileObject = FileUtil.toFileObject(FileUtil.normalizeFile(userDir));
        FileObject retValue = FileUtil.createFolder(userDirFileObject, CONFIG_DATABASES_SQLITE3);
        return retValue;
    }

    public static NBSqlite3InstanceFactory getInstance() {
        return NBSqlite3InstanceFactoryHolder.INSTANCE;
    }

    private static class NBSqlite3InstanceFactoryHolder {

        private static final NBSqlite3InstanceFactory INSTANCE = new NBSqlite3InstanceFactory();
    }
}
