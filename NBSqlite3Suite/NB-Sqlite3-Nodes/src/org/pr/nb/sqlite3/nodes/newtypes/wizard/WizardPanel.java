/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.sqlite3.nodes.newtypes.wizard;

import javax.swing.event.ChangeListener;
import org.openide.WizardDescriptor;

/**
 *
 * @author msivasub
 */
public interface WizardPanel {
    public boolean isPanelValid();
    public void readAndSet(WizardDescriptor wiz);
    public void save(WizardDescriptor wiz);
    public void addChangeListener(ChangeListener l);
    public void removeChangeListener(ChangeListener l);
}
