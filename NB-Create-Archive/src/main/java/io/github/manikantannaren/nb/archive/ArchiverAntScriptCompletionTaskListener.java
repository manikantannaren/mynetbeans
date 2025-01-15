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

package io.github.manikantannaren.nb.archive;

import java.util.prefs.Preferences;
import org.openide.execution.ExecutorTask;
import org.openide.filesystems.FileObject;
import org.openide.util.NbPreferences;
import org.openide.util.Task;
import org.openide.util.TaskListener;
import io.github.manikantannaren.nb.archive.options.ArchiverPreferencesKeys;

/**
 *
 * @author Kaiser
 */
class ArchiverAntScriptCompletionTaskListener implements TaskListener {

    FileObject destFile;

    public ArchiverAntScriptCompletionTaskListener(FileObject destinationFile) {
        this.destFile = destinationFile;
    }

    @Override
    public void taskFinished(Task task) {
        Preferences prefs = NbPreferences.forModule(ArchiverAction.class);
        boolean showDialog = prefs.getBoolean(ArchiverPreferencesKeys.SHOW_ADD_TO_FAV_DIALOG.name(), true);
        if (showDialog) {
            ExecutorTask thisTask = (ExecutorTask) task;
            if (thisTask.result() == 0) {
//                final AddToFavoritesConfirmPanel addToFavoritesConfirmPanel = new AddToFavoritesConfirmPanel(destFile);
//                DialogDescriptor descriptor = new DialogDescriptor(addToFavoritesConfirmPanel, Bundle.add_to_favorites());
//                Object userOption = DialogDisplayer.getDefault().notify(descriptor);
//                if (userOption == DialogDescriptor.OK_OPTION) {
//                    try {
//                        Favorites.getDefault().add(destFile);
//                    } catch (NullPointerException ex) {
//                        Exceptions.printStackTrace(ex);
//                    } catch (DataObjectNotFoundException ex) {
//                        Exceptions.printStackTrace(ex);
//                    }
//                }
//                addToFavoritesConfirmPanel.storeChanges();
            }
        }
    }
}
