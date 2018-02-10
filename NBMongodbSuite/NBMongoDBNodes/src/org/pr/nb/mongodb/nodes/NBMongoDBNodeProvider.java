/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.mongodb.nodes;

import java.util.ArrayList;
import java.util.List;
import org.netbeans.api.db.explorer.node.NodeProvider;
import org.netbeans.api.db.explorer.node.NodeProviderFactory;
import org.openide.*;
import org.openide.nodes.Node;
import org.openide.util.Lookup;

/**
 *
 * @author mahakaal
 */
public class NBMongoDBNodeProvider extends NodeProvider {

    public NBMongoDBNodeProvider(Lookup lookup) {
        super(lookup);
    }

    @Override
    protected void initialize() {
        List<Node> nodes = new ArrayList<>();
        NBMongoDBRootNode rootNode = new NBMongoDBRootNode();
        nodes.add(rootNode);
        setNodes(nodes);
    }

    //requierd by layer.xml for loading in services window
    public static NodeProviderFactory getFactory() {
        return NBMongoNodeProviderHolder.FACTORY;
    }

    private static class NBMongoNodeProviderHolder {

        private static final NodeProviderFactory FACTORY = NBMongoDBNodeProvider::new;
    }

}
