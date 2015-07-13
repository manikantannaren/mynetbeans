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
        prefs.putBoolean(ArchiverPreferencesKeys.SHOW_ADD_TO_FAV_DIALOG.name(),false);
        try {
            prefs.flush();
        } catch (BackingStoreException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

}
