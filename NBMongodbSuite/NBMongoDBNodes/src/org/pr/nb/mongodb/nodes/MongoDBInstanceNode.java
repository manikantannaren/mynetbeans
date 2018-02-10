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
package org.pr.nb.mongodb.nodes;

import java.awt.Image;
import java.beans.IntrospectionException;
import java.io.IOException;
import javax.swing.Action;
import org.openide.nodes.BeanNode;
import org.pr.nb.mongodb.data.NBMongoDBInstance;

/**
 *
 * @author Mahakaal
 */
public class MongoDBInstanceNode extends BeanNode<NBMongoDBInstance> {

    public MongoDBInstanceNode(NBMongoDBInstance bean) throws IntrospectionException {
        super(bean);
    }

    @Override
    public Action getPreferredAction() {
        return super.getPreferredAction(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Image getOpenedIcon(int type) {
        return super.getOpenedIcon(type); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Image getIcon(int type) {
        return super.getIcon(type); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean canDestroy() {
        return true;
    }

    @Override
    public void destroy() throws IOException {
        fireNodeDestroyed();
    }

}
