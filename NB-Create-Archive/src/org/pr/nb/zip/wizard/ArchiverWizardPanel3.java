/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.zip.wizard;

import javax.swing.event.ChangeListener;
import org.openide.WizardDescriptor;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;
import org.pr.nb.zip.ArchiverUserSelections;

@NbBundle.Messages({
    "ExportZipWizardPanel3.INFO_MSG=Review your choices and click finish"
})
public class ArchiverWizardPanel3 implements WizardDescriptor.Panel<WizardDescriptor> {

    /**
     * The visual component that displays this panel. If you need to access the component from this
     * class, just use getComponent().
     */
    private ArchiverVisualPanel3 component;

    // Get the visual component for the panel. In this template, the component
    // is kept separate. This can be more efficient: if the wizard is created
    // but never displayed, or not all panels are displayed, it is better to
    // create only those which really need to be visible.
    @Override
    public ArchiverVisualPanel3 getComponent() {
        if (component == null) {
            component = new ArchiverVisualPanel3();
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
        ArchiverUserSelections selections = (ArchiverUserSelections) wiz.getProperty(ArchiverUserSelections.USER_SELECTION);
        getComponent().setValue(selections);
        wiz.putProperty(WizardDescriptor.PROP_INFO_MESSAGE, Bundle.ExportZipWizardPanel3_INFO_MSG());
    }

    @Override
    public void storeSettings(WizardDescriptor wiz) {
        wiz.putProperty(ArchiverUserSelections.USER_SELECTION, getComponent().getValue());
    }

}
