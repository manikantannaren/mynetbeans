/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.zip;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import org.openide.modules.ModuleInstall;
import org.openide.util.Exceptions;
import org.openide.util.NbPreferences;
import org.pr.nb.zip.options.ArchiverPreferencesKeys;

public class Installer extends ModuleInstall {

    @Override
    public void restored() {
        // TODO
        Preferences prefs = NbPreferences.forModule(ArchiverAction.class);
        prefs.putBoolean(ArchiverPreferencesKeys.LOG_OUTPUT.name(), true);
        prefs.putBoolean(ArchiverPreferencesKeys.SHOW_ADD_TO_FAV_DIALOG.name(),true);
        try {
            prefs.flush();
        } catch (BackingStoreException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

}
