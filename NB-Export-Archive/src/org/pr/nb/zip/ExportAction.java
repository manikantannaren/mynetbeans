/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.zip;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import org.apache.tools.ant.module.api.support.ActionUtils;
import org.openide.DialogDisplayer;
import org.openide.WizardDescriptor;
import org.openide.loaders.DataObject;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.execution.ExecutorTask;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle.Messages;
import org.pr.nb.zip.wizard.ExportArchiveListValueObject;
import org.pr.nb.zip.wizard.ExportZipWizardIterator;

@ActionID(
        category = "Tools",
        id = "org.pr.nb.zip.ExportAction"
)
@ActionRegistration(
        iconBase = "org/pr/nb/zip/jar.png",
        displayName = "#CTL_ExportAction"
)
@ActionReferences({
    @ActionReference(path = "Menu/BuildProject", position = 1200, separatorBefore = 1150),
    @ActionReference(path = "Toolbars/Build", position = 500)
})
@Messages({
    "CTL_ExportAction=Zip selected nodes",
    "WIZARD_TITLE=Exporting options"
})
public final class ExportAction implements ActionListener {

    private static Logger logger = Logger.getLogger(ExportAction.class.getName());
    private final List<DataObject> context;

    public ExportAction(List<DataObject> context) {
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
        UserSelections userSelection = createDefaults(dataFiles);
        WizardDescriptor wiz = new WizardDescriptor(new ExportZipWizardIterator());
        wiz.putProperty(UserSelections.USER_SELECTION, userSelection);
        // {0} will be replaced by WizardDescriptor.Panel.getComponent().getName()
        // {1} will be replaced by WizardDescriptor.Iterator.name()
        wiz.setTitleFormat(new MessageFormat("{0} ({1})"));
        wiz.setTitle(Bundle.WIZARD_TITLE());
        if (DialogDisplayer.getDefault().notify(wiz) == WizardDescriptor.FINISH_OPTION) {
            //TO DO use wiz.getProperty for user inputs
            //generate ant script
            //Run Ant script
            //Ask if to be added to favorites?? Should we be doing this?
            ArchiveCreator creator = new ArchiveCreator((UserSelections) wiz.getProperty(UserSelections.USER_SELECTION));
            SwingUtilities.invokeLater(creator);
        }
    }

    private UserSelections createDefaults(List<FileObject> dataFiles) {
        UserSelections retValue = new UserSelections();
        if (!dataFiles.isEmpty()) {
            FileObject obj = dataFiles.get(0);
            FileObject destination = obj.getParent();
            retValue.setDestinationZipName(obj.getName());
            retValue.setDestinationDirectory(destination);
            retValue.setUserSelectedFilesInWizard(createListContents(dataFiles));
            retValue.setOriginalSelectedFiles(new ArrayList<ExportArchiveListValueObject>());
        }
        return retValue;
    }

    private List<ExportArchiveListValueObject> createListContents(List<FileObject> dataFiles) {
        List<ExportArchiveListValueObject> retValue = new ArrayList<ExportArchiveListValueObject>();
        for (FileObject dataFile : dataFiles) {
            ExportArchiveListValueObject val = new ExportArchiveListValueObject(retValue.size(), dataFile);
            retValue.add(val);
        }
        return retValue;
    }

}
