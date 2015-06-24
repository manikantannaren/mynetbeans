/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.zip.wizard;

import javax.swing.event.ChangeListener;
import org.openide.WizardDescriptor;
import org.openide.WizardValidationException;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle.Messages;
import org.pr.nb.zip.UserSelections;


@Messages({
    "ERROR_MSG_EMPTY_FILE_NAME=File name cannot be empty and destination directory must exist",
    "ExportZipWizardPanel1_INFO_MSG=Provide an archive file name and choose destination directory"
})
public class ExportZipWizardPanel1 implements WizardDescriptor.ValidatingPanel<WizardDescriptor> {

    /**
     * The visual component that displays this panel. If you need to access the component from this
     * class, just use getComponent().
     */
    private ExportZipVisualPanel1 component;

    // Get the visual component for the panel. In this template, the component
    // is kept separate. This can be more efficient: if the wizard is created
    // but never displayed, or not all panels are displayed, it is better to
    // create only those which really need to be visible.
    @Override
    public ExportZipVisualPanel1 getComponent() {
        if (component == null) {
            component = new ExportZipVisualPanel1();
        }
        return component;
    }

    @Override
    public HelpCtx getHelp() {
        // Show no Help button for this panel:
        return HelpCtx.DEFAULT_HELP;
        // If you have context help:
        // return new HelpCtx("help.key.here");
    }

    @Override
    public boolean isValid() {
        boolean retValue = getComponent().isPanelValid();
        return retValue;
        // If it depends on some condition (form filled out...) and
        // this condition changes (last form field filled in...) then
        // use ChangeSupport to implement add/removeChangeListener below.
        // WizardDescriptor.ERROR/WARNING/INFORMATION_MESSAGE will also be useful.
    }

    @Override
    public void addChangeListener(ChangeListener l) {
    }

    @Override
    public void removeChangeListener(ChangeListener l) {
    }

    @Override
    public void readSettings(WizardDescriptor wiz) {
        UserSelections selections = (UserSelections) wiz.getProperty(UserSelections.USER_SELECTION);
        getComponent().setValue(selections);
        wiz.putProperty(WizardDescriptor.PROP_INFO_MESSAGE, Bundle.ExportZipWizardPanel1_INFO_MSG());
    }

    @Override
    public void storeSettings(WizardDescriptor wiz) {
        // use wiz.putProperty to remember current panel state
        wiz.putProperty(UserSelections.USER_SELECTION, getComponent().getValue());
    }

    @Override
    public void validate() throws WizardValidationException {
        if(!isValid()){
            throw new WizardValidationException(getComponent(), "Panel invalid", Bundle.ERROR_MSG_EMPTY_FILE_NAME());
        }
    }

}
