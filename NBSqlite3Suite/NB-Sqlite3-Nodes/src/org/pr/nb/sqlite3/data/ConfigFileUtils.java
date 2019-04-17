/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.sqlite3.data;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import org.apache.commons.lang.StringUtils;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.modules.Places;
import org.openide.util.Exceptions;
import org.pr.nb.sqlite3.logger.Logger;

/**
 *
 * @author msivasub
 */
class ConfigFileUtils {

    public static ConfigFileUtils getInstance() {
        return ConfigFileUtilsHolder.INSTANCE;
    }
    private final String CONFIG_DATABASES_SQLITE3 = "config/Databases/sqlite3";

    private ConfigFileUtils() {
    }

    String generateId(NBSqlite3Object data) {
        String id = data.getName();
        if (StringUtils.isEmpty(id)) {
            //construct name from path
            id = data.getDbPath();
        }
        //replace all path separators with _
        //replace all whitespaces with -
        id = StringUtils.replaceChars(id, ' ', '-');
        id = StringUtils.replaceChars(id, File.pathSeparatorChar, '_');
        return id;

    }

    List<NBSqlite3Object> getExistingConfigs() {
        List<NBSqlite3Object> retValue = new ArrayList<>();
        try {

            FileObject[] configs = getConfigStore().getChildren();
            Arrays.stream(configs).forEach(configFile -> {
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

    private FileObject getConfigStore() throws IOException {
        File userDir = Places.getUserDirectory();
        FileObject userDirFileObject = FileUtil.toFileObject(FileUtil.normalizeFile(userDir));
        FileObject retValue = FileUtil.createFolder(userDirFileObject, CONFIG_DATABASES_SQLITE3);
        Logger.getDefaultLogger().log(ConfigFileUtils.class, Level.FINE, "Found SQLite3 Config store at {0}",null,retValue.getPath());
        return retValue;
    }

    void save(NBSqlite3Object data) throws IOException {
        FileObject contentFile=FileUtil.createData(getConfigStore(), data.getId());
        try (PrintWriter out = new PrintWriter(contentFile.getOutputStream(contentFile.lock()))) {
            out.println(data.toExternalForm());
            out.flush();
        }
    }

    void delete(NBSqlite3Object data) throws IOException {
        FileObject contentFile = FileUtil.createData(getConfigStore(), data.getId());
        contentFile.delete();
    }

    private static class ConfigFileUtilsHolder {

        private static final ConfigFileUtils INSTANCE = new ConfigFileUtils();
    }
}
