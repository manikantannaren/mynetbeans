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
import org.openide.util.NbBundle;
import org.pr.nb.zip.UserSelections;

@NbBundle.Messages({
    "ERROR_MSG_EMPTY_SELECTION=Must choose contents to archive",
    "ExportZipWizardPanel2_INFO_MSG=Choose the contents to archive"
})
public class ExportZipWizardPanel2 implements WizardDescriptor.ValidatingPanel<WizardDescriptor> {

    /**
     * The visual component that displays this panel. If you need to access the component from this
     * class, just use getComponent().
     */
    private ExportZipVisualPanel2 component;

    // Get the visual component for the panel. In this template, the component
    // is kept separate. This can be more efficient: if the wizard is created
    // but never displayed, or not all panels are displayed, it is better to
    // create only those which really need to be visible.
    @Override
    public ExportZipVisualPanel2 getComponent() {
        if (component == null) {
            component = new ExportZipVisualPanel2();
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
        // If it is always OK to press Next or Finish, then:
        return getComponent().isPanelValid();

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
        wiz.putProperty(WizardDescriptor.PROP_INFO_MESSAGE, Bundle.ExportZipWizardPanel2_INFO_MSG());
    }

    @Override
    public void storeSettings(WizardDescriptor wiz) {
        // use wiz.putProperty to remember current panel state
        wiz.putProperty(UserSelections.USER_SELECTION, getComponent().getValue());
        if(!isValid()){
            wiz.putProperty(WizardDescriptor.PROP_ERROR_MESSAGE, Bundle.ERROR_MSG_EMPTY_SELECTION());
        }else{
            wiz.putProperty(WizardDescriptor.PROP_ERROR_MESSAGE, "");
        }
    }

    @Override
    public void validate() throws WizardValidationException {
        if(!isValid()){
            throw new WizardValidationException(getComponent(), "Invalid panel", Bundle.ERROR_MSG_EMPTY_SELECTION());
        }
    }

}
