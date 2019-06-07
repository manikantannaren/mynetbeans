/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.sqlite3.nodes;

import org.pr.nb.sqlite3.nodes.newtypes.NBSqlite3NewColumnType;
import javax.swing.Action;
import org.openide.actions.NewAction;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.actions.SystemAction;
import org.openide.util.datatransfer.NewType;
import org.openide.util.lookup.Lookups;
import org.pr.nb.sqlite3.jdbc.Sqlite3Table;

/**
 *
 * @author msivasub
 */
public class NBSqlite3TableNode extends AbstractNode {

    private final Sqlite3Table dbtable;

    public NBSqlite3TableNode(Sqlite3Table table) {
        super(Children.create(new NBSqlite3ColumnNodeFactory(table), true), Lookups.singleton(table));
        this.dbtable = table;
        setName(dbtable.getName());
        setDisplayName(dbtable.getName());
    }

    @Override
    public NewType[] getNewTypes() {
        return new NewType[]{
          new NBSqlite3NewColumnType()  
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
