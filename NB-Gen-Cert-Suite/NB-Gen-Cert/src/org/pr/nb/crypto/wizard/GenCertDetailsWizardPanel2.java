/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.crypto.wizard;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.openide.WizardDescriptor;
import org.openide.util.ChangeSupport;
import org.openide.util.HelpCtx;
import org.pr.nb.crypto.UserInputs;

public class GenCertDetailsWizardPanel2 implements WizardDescriptor.Panel<WizardDescriptor>, ChangeListener {

    /**
     * The visual component that displays this panel. If you need to access the
     * component from this class, just use getComponent().
     */
    private GenCertInputsVisualPanel component;
    private final ChangeSupport support;

    public GenCertDetailsWizardPanel2() {
        support = new ChangeSupport(this);
    }
    
    // Get the visual component for the panel. In this template, the component
    // is kept separate. This can be more efficient: if the wizard is created
    // but never displayed, or not all panels are displayed, it is better to
    // create only those which really need to be visible.
    @Override
    public GenCertInputsVisualPanel getComponent() {
        if (component == null) {
            component = new GenCertInputsVisualPanel();
            component.addChangeListener(this);
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
        return getComponent().isDataValid();
        // If it depends on some condition (form filled out...) and
        // this condition changes (last form field filled in...) then
        // use ChangeSupport to implement add/removeChangeListener below.
        // WizardDescriptor.ERROR/WARNING/INFORMATION_MESSAGE will also be useful.
    }

    @Override
    public void addChangeListener(ChangeListener l) {
        support.addChangeListener(l);
    }

    @Override
    public void removeChangeListener(ChangeListener l) {
        support.removeChangeListener(l);
    }

    @Override
    public void readSettings(WizardDescriptor wiz) {
        UserInputs inputs = (UserInputs) wiz.getProperty("inputs");
        if(inputs == null){
            inputs = new UserInputs();
            inputs.setKeySize(1024);
            wiz.putProperty("inputs", inputs);
        }
        getComponent().showSettings(inputs);
    }

    @Override
    public void storeSettings(WizardDescriptor wiz) {
        
        wiz.putProperty("inputs", getComponent().getInputs());
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        support.fireChange();
    }

}
