/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.sqlite3.nodes;

import java.util.List;
import org.openide.nodes.ChildFactory;
import org.pr.nb.sqlite3.data.NBSqlite3InstanceFactory;
import org.pr.nb.sqlite3.data.NBSqlite3Object;

/**
 *
 * @author msivasub
 */
class NBSQlite3Nodefactory extends ChildFactory.Detachable<NBSqlite3Object> {

    public NBSQlite3Nodefactory() {
    }

    @Override
    protected boolean createKeys(List<NBSqlite3Object> list) {
        list.addAll(NBSqlite3InstanceFactory.getInstance().getExistingConfigs());
        addNotify();
        return true;
    }
    
    
}
