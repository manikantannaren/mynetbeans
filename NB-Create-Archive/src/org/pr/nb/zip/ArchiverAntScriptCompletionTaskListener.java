/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.zip;

import java.util.prefs.Preferences;
import org.netbeans.modules.favorites.api.Favorites;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.execution.ExecutorTask;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.util.Exceptions;
import org.openide.util.NbPreferences;
import org.openide.util.Task;
import org.openide.util.TaskListener;
import org.pr.nb.zip.options.ArchiverPreferencesKeys;

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
                final AddToFavoritesConfirmPanel addToFavoritesConfirmPanel = new AddToFavoritesConfirmPanel(destFile);
                DialogDescriptor descriptor = new DialogDescriptor(addToFavoritesConfirmPanel, Bundle.add_to_favorites());
                Object userOption = DialogDisplayer.getDefault().notify(descriptor);
                if (userOption == DialogDescriptor.OK_OPTION) {
                    try {
                        Favorites.getDefault().add(destFile);
                    } catch (NullPointerException ex) {
                        Exceptions.printStackTrace(ex);
                    } catch (DataObjectNotFoundException ex) {
                        Exceptions.printStackTrace(ex);
                    }
                }
                addToFavoritesConfirmPanel.storeChanges();
            }
        }
    }
}
