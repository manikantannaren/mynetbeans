/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.mongodb.nodes.newtypes;

import java.awt.Component;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import org.openide.DialogDisplayer;
import org.openide.WizardDescriptor;
import org.openide.util.NbBundle;
import org.openide.util.datatransfer.NewType;
import org.pr.nb.mongodb.component.PropertiesNotifier;
import org.pr.nb.mongodb.component.PropertyNames;
import org.pr.nb.mongodb.data.NBMongoDBInstance;
import org.pr.nb.mongodb.nodes.wizard.NBMongoDBNewInstanceWizardPanel1;
import org.pr.nb.mongodb.nodes.wizard.NBMongoDBNewInstanceWizardPanel2;
import org.pr.nb.mongodb.nodes.wizard.WizardMessagingInterface;

/**
 *
 * @author mahakaal
 */
@NbBundle.Messages({
    "LBL_NEWTYPE_INSTANCE=New MongoDB Instance"
})
public class NBMongoDBInstanceType extends NewType {

    public NBMongoDBInstanceType() {
    }

    @Override
    public void create() throws IOException {
        launchWizard();
    }

    @Override
    public String getName() {
        return Bundle.LBL_NEWTYPE_INSTANCE();
    }
    
    

    private void launchWizard() {
        List<WizardDescriptor.Panel<WizardDescriptor>> panels = new ArrayList<>();
        panels.add(new NBMongoDBNewInstanceWizardPanel1());
        panels.add(new NBMongoDBNewInstanceWizardPanel2());
        String[] steps = new String[panels.size()];
        for (int i = 0; i < panels.size(); i++) {
            Component c = panels.get(i).getComponent();
            // Default step name to component name of panel.
            steps[i] = c.getName();
            if (c instanceof JComponent) { // assume Swing components
                JComponent jc = (JComponent) c;
                jc.putClientProperty(WizardDescriptor.PROP_CONTENT_SELECTED_INDEX, i);
                jc.putClientProperty(WizardDescriptor.PROP_CONTENT_DATA, steps);
                jc.putClientProperty(WizardDescriptor.PROP_AUTO_WIZARD_STYLE, true);
                jc.putClientProperty(WizardDescriptor.PROP_CONTENT_DISPLAYED, true);
                jc.putClientProperty(WizardDescriptor.PROP_CONTENT_NUMBERED, true);
            }
        }
        WizardDescriptor wiz = new WizardDescriptor(new WizardDescriptor.ArrayIterator<>(panels));
        wiz.putProperty(WizardMessagingInterface.KEY_USER_SETTINGS, new NBMongoDBInstance());
        // {0} will be replaced by WizardDesriptor.Panel.getComponent().getName()
        wiz.setTitleFormat(new MessageFormat("{0} ({1})"));
        wiz.setTitle(Bundle.LBL_NEWTYPE_INSTANCE());
        if (DialogDisplayer.getDefault().notify(wiz) == WizardDescriptor.FINISH_OPTION) {
            PropertiesNotifier.fireNewMongoDBInstance((NBMongoDBInstance) wiz.getProperty(PropertyNames.NEW_MONGODB_WIZARD_INSTANCE.name()));
        }
    }

}
