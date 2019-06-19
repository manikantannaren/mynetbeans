/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.sqlite3.nodes.newtypes;

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
import org.pr.nb.sqlite3.nodes.newtypes.wizard.db.NBSQlite3NewDBTypeWizardPanel1;
import org.pr.nb.sqlite3.jdbc.NBSqlite3Object;
import org.pr.nb.sqlite3.utils.Logger;
import org.pr.nb.sqlite3.nodes.listeners.NBSQliteEventType;
import org.pr.nb.sqlite3.nodes.listeners.Notifier;

/**
 *
 * @author msivasub
 */
@NbBundle.Messages({
    "LB_NEWTYPE_NAME=New SQLite3 database",
    "DIALOG_TITLE=Select a Sqlite3 Db file"
})

public class NBSqlite3NewDBType extends NewType{

    @Override
    public void create() throws IOException {
        
        List<WizardDescriptor.Panel<WizardDescriptor>> panels = new ArrayList<>();
        panels.add(new NBSQlite3NewDBTypeWizardPanel1());
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
        wiz.createNotificationLineSupport();
        // {0} will be replaced by WizardDesriptor.Panel.getComponent().getName()
        wiz.setTitleFormat(new MessageFormat("{0}"));
        wiz.setTitle(Bundle.DIALOG_TITLE());
        if (DialogDisplayer.getDefault().notify(wiz) == WizardDescriptor.FINISH_OPTION) {
            notifyNewDB(wiz);
        }
    }

    private void notifyNewDB(WizardDescriptor wiz) {
        NBSqlite3Object data= (NBSqlite3Object) wiz.getProperty("data");
        Logger.getDefaultLogger().info(NBSqlite3NewDBType.class, "User selected sqlite db {0}", data);
        Notifier.getInstance().dispatchEvent(NBSQliteEventType.ADD_INSTANCE, this, null, data);
    }

    @Override
    public String getName() {
        return Bundle.LB_NEWTYPE_NAME();
    }
    
    
}