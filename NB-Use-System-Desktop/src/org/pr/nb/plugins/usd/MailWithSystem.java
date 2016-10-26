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

package org.pr.nb.plugins.usd;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.loaders.DataObject;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.awt.StatusDisplayer;
import org.openide.filesystems.FileObject;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "File",
        id = "org.pr.nb.plugins.usd.MailWithSystem"
)
@ActionRegistration(
        displayName = "#CTL_MailWithSystem"
)
@ActionReferences({
    @ActionReference(path = "Menu/Tools/Desktop"),
    @ActionReference(path = "Loaders/folder/any/Actions/Desktop"),
    @ActionReference(path = "Editors/Popup/Desktop"),
    @ActionReference(path = "Editors/TabActions/Desktop"),
    @ActionReference(path = "Projects/Actions/Desktop"),
    @ActionReference(path = "Projects/packages/Actions/Desktop"),
    @ActionReference(path = "Loaders/Desktop")

})
@Messages({"CTL_MailWithSystem=Send by mail",
    "# {0} - the file to be sent",
    "CTL_Mail_Status=Opening mailing client for {0}",
    "# {0} - the file to be sent",
    "# {1} - the reason for error",
    "CTL_Mail_Error=Could not launch mail client for {0}: {1}",
    "# {0} - the file to be sent",
    "CTL_Mail_Error_Folder={0} is a directory. Please choose a file to email",
    "# {0} - the file to be sent",
    "CTL_Mail_subject=Emailing%20{0}",
    "# {0} - the file to be sent",
    "CTL_Mail_body=Please%20attach%20{0}%20to%20this%20mail"})

public final class MailWithSystem implements ActionListener {

    private final DataObject context;

    public MailWithSystem(DataObject context) {
        this.context = context;
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        try {
            // TODO use context
            FileObject file = DesktopHelper.getInstance().getFileObjectFromContext(context);

            if (context.getPrimaryFile().isData()) {
                String msg = Bundle.CTL_Mail_Status(file.getPath());
                StatusDisplayer.getDefault().setStatusText(msg);
                DesktopHelper.getInstance().mailWithDesktop(context);
            } else {
                
                String msg = Bundle.CTL_Mail_Error_Folder(file.getPath());
                StatusDisplayer.getDefault().setStatusText(msg);
                NotifyDescriptor desc = new NotifyDescriptor.Message(msg,NotifyDescriptor.ERROR_MESSAGE);
                DialogDisplayer.getDefault().notify(desc);
            }
        } catch (IOException ex) {
            FileObject file = DesktopHelper.getInstance().getFileObjectFromContext(context);
            String msg = Bundle.CTL_Mail_Error(file.getPath(), ex.getLocalizedMessage());
            StatusDisplayer.getDefault().setStatusText(msg);
            Exceptions.printStackTrace(ex);
        } catch (URISyntaxException ex) {
            FileObject file = DesktopHelper.getInstance().getFileObjectFromContext(context);
            String msg = Bundle.CTL_Mail_Error(file.getPath(), ex.getLocalizedMessage());
            StatusDisplayer.getDefault().setStatusText(msg);
            Exceptions.printStackTrace(ex);
        }
    }
}
