/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.sqlite3.nodes;

import java.util.ArrayList;
import java.util.List;
import org.netbeans.api.db.explorer.node.NodeProvider;
import org.netbeans.api.db.explorer.node.NodeProviderFactory;
import org.openide.util.Lookup;

/**
 *
 * @author msivasub
 */
public class NBSqlite3NodeProvider extends NodeProvider {

    private NBSqlite3NodeProvider(Lookup lookup) {
        super(lookup);
    }

    public static NodeProviderFactory getFactory() {
        return NBSqlite3NodeProviderHolder.INSTANCE;
    }

    @Override
    protected void initialize() {
        List initialNodes = new ArrayList<>();
        initialNodes.add(new NBSqlite3RootNode());
        setNodes(initialNodes);
    }

    private static class NBSqlite3NodeProviderHolder {
        private static final NodeProviderFactory INSTANCE = NBSqlite3NodeProvider::new;
    }
}
