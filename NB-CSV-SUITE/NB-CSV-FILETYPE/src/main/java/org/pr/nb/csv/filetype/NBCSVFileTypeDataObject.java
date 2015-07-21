/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.csv.filetype;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import org.netbeans.core.spi.multiview.MultiViewElement;
import org.netbeans.core.spi.multiview.text.MultiViewEditorElement;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.filesystems.MIMEResolver;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectExistsException;
import org.openide.loaders.FileEntry;
import org.openide.loaders.MultiDataObject;
import org.openide.loaders.MultiFileLoader;
import org.openide.modules.Places;
import org.openide.util.Lookup;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;

@Messages({
    "LBL_NBCSVFileType_LOADER=Files of type csv"
})
@MIMEResolver.ExtensionRegistration(
        displayName = "#LBL_NBCSVFileType_LOADER",
        mimeType = "text/x-csv",
        extension = {"csv", "CSV"}
)
@DataObject.Registration(
        mimeType = "text/x-csv",
        iconBase = "org/pr/nb/csv/filetype/text_csv16.png",
        displayName = "#LBL_NBCSVFileType_LOADER",
        position = 300
)
@ActionReferences({
    @ActionReference(
            path = "Loaders/text/x-csv/Actions",
            id = @ActionID(category = "System", id = "org.openide.actions.OpenAction"),
            position = 100,
            separatorAfter = 200
    ),
    @ActionReference(
            path = "Loaders/text/x-csv/Actions",
            id = @ActionID(category = "Edit", id = "org.openide.actions.CutAction"),
            position = 300
    ),
    @ActionReference(
            path = "Loaders/text/x-csv/Actions",
            id = @ActionID(category = "Edit", id = "org.openide.actions.CopyAction"),
            position = 400,
            separatorAfter = 500
    ),
    @ActionReference(
            path = "Loaders/text/x-csv/Actions",
            id = @ActionID(category = "Edit", id = "org.openide.actions.DeleteAction"),
            position = 600
    ),
    @ActionReference(
            path = "Loaders/text/x-csv/Actions",
            id = @ActionID(category = "System", id = "org.openide.actions.RenameAction"),
            position = 700,
            separatorAfter = 800
    ),
    @ActionReference(
            path = "Loaders/text/x-csv/Actions",
            id = @ActionID(category = "System", id = "org.openide.actions.SaveAsTemplateAction"),
            position = 900,
            separatorAfter = 1000
    ),
    @ActionReference(
            path = "Loaders/text/x-csv/Actions",
            id = @ActionID(category = "System", id = "org.openide.actions.FileSystemAction"),
            position = 1100,
            separatorAfter = 1200
    ),
    @ActionReference(
            path = "Loaders/text/x-csv/Actions",
            id = @ActionID(category = "System", id = "org.openide.actions.ToolsAction"),
            position = 1300
    ),
    @ActionReference(
            path = "Loaders/text/x-csv/Actions",
            id = @ActionID(category = "System", id = "org.openide.actions.PropertiesAction"),
            position = 1400
    )
})
public class NBCSVFileTypeDataObject extends MultiDataObject {

    public NBCSVFileTypeDataObject(FileObject pf, MultiFileLoader loader) throws DataObjectExistsException, IOException {
        super(pf, loader);
        registerEditor("text/x-csv", true);

        //register secondry file.
        File dir = Places.getCacheSubdirectory("org.pr.nb.csv");
        File propFile = new File(dir,pf.getNameExt()+".properties");
        propFile = FileUtil.normalizeFile(propFile);
        FileObject propFileFo = FileUtil.toFileObject(propFile);
        Properties p = new Properties();
        p.load(propFileFo.getInputStream());
        if(p.isEmpty()){
            p.setProperty("allcolumns", "CHARACTER");
            p.store(propFileFo.getOutputStream(), "formatting properties");
        }
        registerEntry(propFileFo);
    }

    @Override
    protected int associateLookup() {
        return 1;
    }

    @MultiViewElement.Registration(
            displayName = "#LBL_NBCSVFileType_EDITOR",
            iconBase = "org/pr/nb/csv/filetype/text_csv16.png",
            mimeType = "text/x-csv",
            persistenceType = TopComponent.PERSISTENCE_ONLY_OPENED,
            preferredID = "NBCSVFileType",
            position = 1000
    )
    @Messages("LBL_NBCSVFileType_EDITOR=Source")
    public static MultiViewEditorElement createEditor(Lookup lkp) {
        return new MultiViewEditorElement(lkp);
    }

}
