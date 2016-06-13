/*
 * Copyright 2015 Manikantan Narender Nath.
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
package org.pr.nb.zip;

import java.io.IOException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.modules.ModuleInstall;
import org.openide.util.Exceptions;
import org.openide.util.NbPreferences;
import org.openide.windows.WindowManager;
import org.pr.nb.zip.options.ArchiverPreferencesKeys;

public class Installer extends ModuleInstall {

    private static final Logger logger = Logger.getLogger(Installer.class.getName());

    @Override
    public void restored() {
        // TODO
        Preferences prefs = NbPreferences.forModule(ArchiverAction.class);
        prefs.putBoolean(ArchiverPreferencesKeys.LOG_OUTPUT.name(), true);
        prefs.putBoolean(ArchiverPreferencesKeys.SHOW_ADD_TO_FAV_DIALOG.name(), false);
        try {
            prefs.flush();
        } catch (BackingStoreException ex) {
            Exceptions.printStackTrace(ex);
        }
        WindowManager.getDefault().invokeWhenUIReady(new ArchiveActionInstaller());
    }

    @Override
    public void uninstalled() {
        WindowManager.getDefault().invokeWhenUIReady(new ArchiveActionUninstaller());
    }

    private abstract class FileTypeActionHandler implements Runnable {

        protected static final String LOADERS_CONFIG = "Loaders";
        protected static final String ACTIONS_CONFIG = "Actions";
        protected static final String ARCHIVER_ACTION_ORIG = "Actions/Tools/org-pr-nb-zip-ArchiverAction.instance";
        protected static final String ARCHIVER_ACTION_SHADOW = "org-pr-nb-zip-ArchiverAction.shadow";

        protected abstract FileObject handleLoaderType(FileObject loaderType);

        @Override

        public void run() {
            FileObject loadersConfig = FileUtil.getConfigFile(LOADERS_CONFIG);
            Enumeration< ? extends FileObject> loadersTypes = loadersConfig.getChildren(true);
            while (loadersTypes.hasMoreElements()) {
                FileObject loaderType = loadersTypes.nextElement();
                FileObject actionForLoaderType = handleLoaderType(loaderType);
            }
        }
    }

    private class ArchiveActionInstaller extends FileTypeActionHandler {

        @Override

        protected FileObject handleLoaderType(FileObject loaderType) {
            FileObject actionConfig = loaderType.getFileObject(ACTIONS_CONFIG);
            if (actionConfig != null) {
                try {
                    FileObject archiverAction = actionConfig.createData(ARCHIVER_ACTION_SHADOW);
                    archiverAction.setAttribute("originalFile", ARCHIVER_ACTION_ORIG);
                    archiverAction.setAttribute("position", 500);
                    return archiverAction;
                } catch (IOException ex) {
                    logger.log(Level.FINE, "Action {0} exists in {1}", new Object[]{ARCHIVER_ACTION_SHADOW, actionConfig.getPath()});
                }
            }
            return null;
        }

    }

    private class ArchiveActionUninstaller extends FileTypeActionHandler {

        @Override

        protected FileObject handleLoaderType(FileObject loaderType) {
            FileObject actionConfig = loaderType.getFileObject(ACTIONS_CONFIG);
            if (actionConfig != null) {
                FileObject archiverAction = actionConfig.getFileObject(ARCHIVER_ACTION_SHADOW);
                if (archiverAction != null) {
                    try {
                        archiverAction.delete();
                    } catch (IOException ex) {
                        Exceptions.printStackTrace(ex);
                    }
                }
            }
            return null;
        }

    }
}
