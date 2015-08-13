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

import java.util.ArrayList;
import java.util.List;
import org.netbeans.api.db.explorer.node.NodeProvider;
import org.netbeans.api.db.explorer.node.NodeProviderFactory;
import org.openide.nodes.Node;
import org.openide.util.Lookup;

/**
 *
 * @author Mahakaal
 */
public class MongoDBNodeProvider extends NodeProvider{
    
   private MongoDBNodeProvider(Lookup lookup) {
        super(lookup);
    }

    @Override
    protected void initialize() {
        List<Node> nodes = new ArrayList<>();
        MongoDBRootNode rootNode = new MongoDBRootNode();
        nodes.add(rootNode);
        setNodes(nodes);
    }
    public static NodeProviderFactory getFactory() {
        return MongoNodeProviderHolder.FACTORY;
    }
    
    private static class MongoNodeProviderHolder {

        private static final NodeProviderFactory FACTORY = new NodeProviderFactory() {
            @Override
            public NodeProvider createInstance(Lookup lkp) {
                return new MongoDBNodeProvider(lkp);
            }
            
        };
    }
}
