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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import org.openide.DialogDisplayer;
import org.openide.WizardDescriptor;
import org.openide.loaders.DataObject;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.filesystems.FileObject;
import org.openide.util.NbBundle.Messages;
import org.pr.nb.zip.util.LoggerProvider;
import org.pr.nb.zip.wizard.ArchiverListValueObject;
import org.pr.nb.zip.wizard.ArchiverWizardIterator;

@ActionID(
        category = "Tools",
        id = "org.pr.nb.zip.ArchiverAction"
)
@ActionRegistration(
        iconBase = "org/pr/nb/zip/jar.png",
        displayName = "#CTL_ExportAction"
)
@ActionReferences({
    @ActionReference(path = "Menu/BuildProject", position = 1200, separatorBefore = 1150),
    @ActionReference(path = "Toolbars/Build", position = 500),
    @ActionReference(path = "Shortcuts", name = "D-S-F3")
})
@Messages({
    "CTL_ExportAction=Zip selected nodes",
    "WIZARD_TITLE=Archiving options"
})
public final class ArchiverAction implements ActionListener {

    private static final Logger logger = LoggerProvider.getLogger(ArchiverAction.class);
    private final List<DataObject> context;

    public ArchiverAction(List<DataObject> context) {
        this.context = context;
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        List<FileObject> dataFiles = new ArrayList<FileObject>();
        for (DataObject dataObject : context) {
            FileObject primaryFile = dataObject.getPrimaryFile();
            logger.log(Level.FINER, primaryFile.getPath());
            dataFiles.add(primaryFile);
        }

        //call wizard here
        //Question: Should Nested selected nodes be resolved here or in the wizard
        ArchiverUserSelections userSelection = createDefaults(dataFiles);
        WizardDescriptor wiz = new WizardDescriptor(new ArchiverWizardIterator());
        wiz.putProperty(ArchiverUserSelections.USER_SELECTION, userSelection);
        // {0} will be replaced by WizardDescriptor.Panel.getComponent().getName()
        // {1} will be replaced by WizardDescriptor.Iterator.name()
        wiz.setTitleFormat(new MessageFormat("{0} ({1})"));
        wiz.setTitle(Bundle.WIZARD_TITLE());
        if (DialogDisplayer.getDefault().notify(wiz) == WizardDescriptor.FINISH_OPTION) {
            //TO DO use wiz.getProperty for user inputs
            //generate ant script
            //Run Ant script
            //Ask if to be added to favorites?? Should we be doing this?
            ArchiveCreator creator = new ArchiveCreator((ArchiverUserSelections) wiz.getProperty(ArchiverUserSelections.USER_SELECTION));
            SwingUtilities.invokeLater(creator);
        }
    }

    private ArchiverUserSelections createDefaults(List<FileObject> dataFiles) {
        ArchiverUserSelections retValue = new ArchiverUserSelections();
        if (!dataFiles.isEmpty()) {
            FileObject obj = dataFiles.get(0);
            FileObject destination = obj.getParent();
            retValue.setDestinationZipName(obj.getName());
            retValue.setDestinationDirectory(destination);
            retValue.setUserSelectedFilesInWizard(createListContents(dataFiles));
            retValue.setOriginalSelectedFiles(new ArrayList<ArchiverListValueObject>());
        }
        return retValue;
    }

    private List<ArchiverListValueObject> createListContents(List<FileObject> dataFiles) {
        List<ArchiverListValueObject> retValue = new ArrayList<ArchiverListValueObject>();
        for (FileObject dataFile : dataFiles) {
            ArchiverListValueObject val = new ArchiverListValueObject(retValue.size(), dataFile);
            retValue.add(val);
        }
        return retValue;
    }

}
