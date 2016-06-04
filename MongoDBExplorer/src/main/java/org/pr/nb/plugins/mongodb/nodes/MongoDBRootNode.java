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

import java.awt.Image;
import javax.swing.Action;
import org.openide.actions.NewAction;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.ImageUtilities;
import org.openide.util.NbBundle;
import org.openide.util.actions.SystemAction;
import org.openide.util.datatransfer.NewType;

/**
 *
 * @author Mahakaal
 */
@NbBundle.Messages({
    "LBL_DISPLAY_NAME=MongoDB"
})
class MongoDBRootNode extends AbstractNode {

    public MongoDBRootNode() {
        super(Children.create(new MongoDBChildrenFactory(), true));
        setName(Bundle.LBL_DISPLAY_NAME());
        setDisplayName(Bundle.LBL_DISPLAY_NAME());
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
            new MongoDBInstanceType()
        };
    }

}
