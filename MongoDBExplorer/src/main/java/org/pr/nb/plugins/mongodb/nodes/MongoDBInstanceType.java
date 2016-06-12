/*
 * Copyright 2016 Mahakaal.
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
package org.pr.nb.plugins.mongodb.nodes;

import java.awt.Component;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import org.openide.DialogDisplayer;
import org.openide.WizardDescriptor;
import org.openide.util.ChangeSupport;
import org.openide.util.NbBundle;
import org.openide.util.datatransfer.NewType;
import org.pr.nb.plugins.mongodb.components.PropertiesNotifier;
import org.pr.nb.plugins.mongodb.components.PropertyNames;
import org.pr.nb.plugins.mongodb.data.MongoDBInstance;
import org.pr.nb.plugins.mongodb.dialogs.NewMongDBInstanceDetailsWizardPanel;
import org.pr.nb.plugins.mongodb.dialogs.NewMongDBInstanceTestConnectionWizardPanel;

/**
 *
 * @author Mahakaal
 */
@NbBundle.Messages({
    "LBL_NEWTYPE_INSTANCE=New MongoDB Instance"
})
class MongoDBInstanceType extends NewType {

    public MongoDBInstanceType() {
    }

    private void launchWizard() {
        ChangeSupport changeSupport = new ChangeSupport(this);
        List<WizardDescriptor.Panel<WizardDescriptor>> panels = new ArrayList<WizardDescriptor.Panel<WizardDescriptor>>();
        panels.add(new NewMongDBInstanceDetailsWizardPanel(changeSupport));
        panels.add(new NewMongDBInstanceTestConnectionWizardPanel(changeSupport));
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
        WizardDescriptor wiz = new WizardDescriptor(new WizardDescriptor.ArrayIterator<WizardDescriptor>(panels));
        // {0} will be replaced by WizardDesriptor.Panel.getComponent().getName()
        wiz.setTitleFormat(new MessageFormat("{0} ({1})"));
        wiz.setTitle(Bundle.LBL_NEWTYPE_INSTANCE());
        wiz.putProperty(PropertyNames.NEW_MONGODB_WIZARD_INSTANCE.name(), MongoDBInstance.createWithDefaults());
        if (DialogDisplayer.getDefault().notify(wiz) == WizardDescriptor.FINISH_OPTION) {
            PropertiesNotifier.fireNewMongoDBInstance((MongoDBInstance) wiz.getProperty(PropertyNames.NEW_MONGODB_WIZARD_INSTANCE.name()));
        }
    }

    @Override
    public String getName() {
        return Bundle.LBL_NEWTYPE_INSTANCE();
    }

    @Override
    public void create() throws IOException {
        launchWizard();
    }

}
