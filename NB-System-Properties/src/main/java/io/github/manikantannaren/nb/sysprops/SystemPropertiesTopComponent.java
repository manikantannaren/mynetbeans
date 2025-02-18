/*
 * Copyright 2015 Manikantan Narender Nath.
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
package io.github.manikantannaren.nb.sysprops;

import io.github.manikantannaren.nb.sysprops.nodes.CategoryNodes;
import java.awt.BorderLayout;
import javax.swing.ActionMap;
import javax.swing.text.DefaultEditorKit;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.explorer.ExplorerManager;
import org.openide.explorer.ExplorerUtils;
import org.openide.explorer.view.OutlineView;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Node;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//org.pr.nb.sysprops//SystemProperties//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "SystemPropertiesTopComponent",
        iconBase = "io/github/manikantannaren/nb/sysprops/sysprops16.png",
        persistenceType = TopComponent.PERSISTENCE_NEVER
)
@TopComponent.Registration(mode = "output", openAtStartup = false)
@ActionID(category = "Window", id = "org.pr.nb.sysprops.SystemPropertiesTopComponent")
@ActionReference(path = "Menu/Window/Tools" /*, position = 333*/)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_SystemPropertiesAction",
        preferredID = "SystemPropertiesTopComponent"
)
@Messages({
    "CTL_SystemPropertiesAction=System Properties",
    "CTL_SystemPropertiesTopComponent=System Properties",
    "HINT_SystemPropertiesTopComponent=Displays the list of environment variables and JVM properties",
    "copyPropertyNamePopupMenuItem.text=Copy Name",
    "copyPropertyValuePopupMenuItem.text=Copy Value",
    "copyPropertyPopupMenuItem.text=Copy Name and Value",
    "outlineview.firstcolumn.name=Properties"
})
public final class SystemPropertiesTopComponent extends TopComponent implements ExplorerManager.Provider {

    public SystemPropertiesTopComponent() {
        initComponents();
        setName(Bundle.CTL_SystemPropertiesTopComponent());
        setToolTipText(Bundle.HINT_SystemPropertiesTopComponent());

        ActionMap map = getActionMap();
        map.put(DefaultEditorKit.copyAction, ExplorerUtils.actionCopy(manager)); 
        associateLookup(ExplorerUtils.createLookup(manager, getActionMap()));
        
        AbstractNode root = new AbstractNode(new CategoryNodes());
        manager.setRootContext(root);
        
        OutlineView view = new OutlineView(Bundle.outlineview_firstcolumn_name());
        view.setPropertyColumns("entryValue", "Value");
        add(view, BorderLayout.CENTER);

        view.getOutline().setRootVisible(false);
        Node node = root.getChildren().getNodeAt(0);
        view.expandNode(node);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {

    }

    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }

    private transient final ExplorerManager manager = new ExplorerManager();

    @Override
    public ExplorerManager getExplorerManager() {
        return manager;
    }
}
