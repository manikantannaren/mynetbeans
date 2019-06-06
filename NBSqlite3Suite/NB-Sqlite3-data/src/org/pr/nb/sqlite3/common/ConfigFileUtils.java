/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.sqlite3.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
public class ConfigFileUtils {

    public static ConfigFileUtils getInstance() {
        return ConfigFileUtilsHolder.INSTANCE;
    }
    private final String CONFIG_DATABASES_SQLITE3 = "config/Databases/sqlite3";

    private ConfigFileUtils() {
    }



    public List<Reader> getExistingConfigs() throws NBSqlite3Exception{
        List<Reader> retValue = new ArrayList<>();
        try {

            FileObject[] configs = getConfigStore().getChildren();
            Arrays.stream(configs).forEach(configFile -> {
                    Reader in;
                try {
                    in = new InputStreamReader(configFile.getInputStream());
                    retValue.add(in);
                } catch (FileNotFoundException ex) {
                    throw new TraversalException("Could read config", ex);
                }
            });
        } catch (IOException ex) {
            throw new NBSqlite3Exception(ex);
        }
        return retValue;
    }

    private FileObject getConfigStore() throws IOException {
        File userDir = Places.getUserDirectory();
        FileObject userDirFileObject = FileUtil.toFileObject(FileUtil.normalizeFile(userDir));
        FileObject retValue = FileUtil.createFolder(userDirFileObject, CONFIG_DATABASES_SQLITE3);
        Logger.getDefaultLogger().info(ConfigFileUtils.class, "Found SQLite3 Config store at {0}", retValue.getPath());
        return retValue;
    }

    public void save(NBSqlite3Object data) throws IOException {
        FileObject contentFile = FileUtil.createData(getConfigStore(), data.getId());
        try (PrintWriter out = new PrintWriter(contentFile.getOutputStream())) {
            out.println(data.toExternalForm());
            out.flush();
        }
    }

    public void delete(NBSqlite3Object data) throws IOException {
        FileObject contentFile = FileUtil.createData(getConfigStore(), data.getId());
        contentFile.delete();
    }

    private static class ConfigFileUtilsHolder {

        private static final ConfigFileUtils INSTANCE = new ConfigFileUtils();
    }
}
