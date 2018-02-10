/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.mongodb.nodes;

import org.pr.nb.mongodb.nodes.newtypes.NBMongoDBInstanceType;
import java.awt.Image;
import java.io.IOException;
import javax.swing.Action;
import org.openide.actions.NewAction;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.*;
import org.openide.util.actions.SystemAction;
import org.openide.util.datatransfer.NewType;

/**
 *
 * @author mahakaal
 */
@NbBundle.Messages({
    "LBL_DISPLAY_NAME=MongoDB"
})
class NBMongoDBRootNode extends AbstractNode {
    public NBMongoDBRootNode() {
        super(Children.create(new NBMongoDBNodeFactory(), true));
        setName(Bundle.LBL_DISPLAY_NAME());
        setDisplayName(Bundle.LBL_DISPLAY_NAME());
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
    public Image getIcon(int type) {
        return ImageUtilities.loadImage("org/pr/nb/plugins/mongodb/nodes/db-catalog-node.png");
    }

    @Override
    public Image getOpenedIcon(int type) {
        return ImageUtilities.loadImage("org/pr/nb/plugins/mongodb/nodes/db-catalog-node.png");
    }

    @Override
    public Action[] getActions(boolean context) {
        Action[] retValue = new Action[]{
            SystemAction.get(NewAction.class)
        };
        return retValue;
    }

    @Override
    public NewType[] getNewTypes() {
        return new NewType[]{
            new NBMongoDBInstanceType()
        };
    }

    

}
