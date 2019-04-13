/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.sqlite3.nodes;

import java.awt.Image;
import javax.swing.Action;
import org.openide.actions.NewAction;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.ImageUtilities;
import org.openide.util.NbBundle;
import org.openide.util.actions.SystemAction;
import org.openide.util.datatransfer.NewType;
import org.pr.nb.sqlite3.nodes.newtypes.NBSqlite3NewType;

/**
 *
 * @author msivasub
 */
@NbBundle.Messages({
    "LBL_ROOT_NODE=SQLite3"
})
public class NBSqlite3RootNode extends AbstractNode {

    public NBSqlite3RootNode() {
        super(Children.create(new NBSQlite3Nodefactory(), true));
        setDisplayName(Bundle.LBL_ROOT_NODE());
        setName("SQLite3");
    }

    @Override
    public Image getIcon(int type) {
        return ImageUtilities.loadImage("org/pr/nb/sqlite3/nodes/db-catalog-node.png");
    }

    @Override
    public Image getOpenedIcon(int type) {
        return ImageUtilities.loadImage("org/pr/nb/sqlite3/nodes/db-catalog-node.png");
    }

    @Override
    public boolean canCut() {
        return false;
    }

    @Override
    public boolean canCopy() {
        return false;
    }

    @Override
    public boolean canDestroy() {
        return false;
    }

    @Override
    public boolean canRename() {
        return false;
    }

    @Override
    public NewType[] getNewTypes() {
        return new NewType[]{
            new NBSqlite3NewType()
        };
    }

    @Override
    public Action[] getActions(boolean context) {
        Action[] retValue = new Action[]{
            SystemAction.get(NewAction.class)
        };
        return retValue;
    }

}
