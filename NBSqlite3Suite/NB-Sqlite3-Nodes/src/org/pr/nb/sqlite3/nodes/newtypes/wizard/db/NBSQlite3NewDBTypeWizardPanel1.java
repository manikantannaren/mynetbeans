/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.sqlite3.nodes.newtypes.wizard.db;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.openide.WizardDescriptor;
import org.openide.util.ChangeSupport;
import org.openide.util.HelpCtx;

public class NBSQlite3NewDBTypeWizardPanel1 implements WizardDescriptor.Panel<WizardDescriptor>, ChangeListener {

    public NBSQlite3NewDBTypeWizardPanel1() {
    }

    
    /**
     * The visual component that displays this panel. If you neesd to access the
     * component from this class, just use getComponent().
     */
    private NBSQlite3NewDBTypeVisualPanel1 component;

    // Get the visual component for the panel. In this template, the component
    // is kept separate. This can be more efficient: if the wizard is created
    // but never displayed, or not all panels are displayed, it is better to
    // create only those which really need to be visible.
    @Override
    public NBSQlite3NewDBTypeVisualPanel1 getComponent() {
        if (component == null) {
            component = new NBSQlite3NewDBTypeVisualPanel1();
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
        return component.isPanelValid();
        // If it depends on some condition (form filled out...) and
        // this condition changes (last form field filled in...) then
        // use ChangeSupport to implement add/removeChangeListener below.
        // WizardDescriptor.ERROR/WARNING/INFORMATION_MESSAGE will also be useful.
    }

    ChangeSupport changeSupport = new ChangeSupport(this);
    @Override
    public void addChangeListener(ChangeListener l) {
        changeSupport.addChangeListener(l);
    }

    @Override
    public void removeChangeListener(ChangeListener l) {
        changeSupport.addChangeListener(l);
    }
    

    @Override
    public void readSettings(WizardDescriptor wiz) {
        // use wiz.getProperty to retrieve previous panel state
        getComponent().setDescriptor(wiz);
    }

    @Override
    public void storeSettings(WizardDescriptor wiz) {
        // use wiz.putProperty to remember current panel state
        getComponent().getDescriptor();
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        isValid();
        changeSupport.fireChange();
    }

}
