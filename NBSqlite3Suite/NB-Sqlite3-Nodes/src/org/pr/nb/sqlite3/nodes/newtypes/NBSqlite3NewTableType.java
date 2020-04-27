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
import org.openide.util.Utilities;
import org.openide.util.datatransfer.NewType;
import org.pr.nb.sqlite3.jdbc.Sqlite3Column;
import org.pr.nb.sqlite3.nodes.newtypes.wizard.dbtable.NBSqlite3NewTableWizardPanel1;
import org.pr.nb.sqlite3.nodes.newtypes.wizard.dbtable.NBSqlite3NewTableWizardPanel2;
import org.pr.nb.sqlite3.nodes.newtypes.wizard.dbtable.NBSqlite3NewTableWizardPanel3;
import org.pr.nb.sqlite3.jdbc.Sqlite3DB;
import org.pr.nb.sqlite3.jdbc.Sqlite3Table;
/**
 *
 * @author msivasub
 */
@NbBundle.Messages({
    "LBL_NEW_TABLE=New Table",
    "NEW_TABLE_DIALOG_TITLE=Create a new table"
})
public class NBSqlite3NewTableType extends NewType {

    public NBSqlite3NewTableType() {
        
    }

    @Override
    public void create() throws IOException {
        Sqlite3DB database = Utilities.actionsGlobalContext().lookup(Sqlite3DB.class);
        List<WizardDescriptor.Panel<WizardDescriptor>> panels = new ArrayList<>();
        panels.add(new NBSqlite3NewTableWizardPanel1());
        panels.add(new NBSqlite3NewTableWizardPanel2());
        panels.add(new NBSqlite3NewTableWizardPanel3());
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
        wiz.putProperty("database", database);
        wiz.createNotificationLineSupport();
        // {0} will be replaced by WizardDesriptor.Panel.getComponent().getName()
        wiz.setTitleFormat(new MessageFormat("{0}"));
        wiz.setTitle(Bundle.NEW_TABLE_DIALOG_TITLE());
        if (DialogDisplayer.getDefault().notify(wiz) == WizardDescriptor.FINISH_OPTION) {
            // do something
            Sqlite3Table table = (Sqlite3Table) wiz.getProperty("table");
            List<Sqlite3Column> columns = (List<Sqlite3Column>) wiz.getProperty("columns");
            
            
        }
    }

    @Override
    public String getName() {
        return Bundle.LBL_NEW_TABLE();
    }
}
