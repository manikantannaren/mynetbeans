/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.sqlite3.nodes;

import org.pr.nb.sqlite3.nodes.newtypes.NBSqlite3NewTableType;
import java.awt.Image;
import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import javax.swing.Action;
import org.openide.actions.NewAction;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.PropertySupport;
import org.openide.nodes.Sheet;
import org.openide.util.ImageUtilities;
import org.openide.util.NbBundle;
import org.openide.util.actions.SystemAction;
import org.openide.util.datatransfer.NewType;
import org.openide.util.lookup.Lookups;
import org.pr.nb.sqlite3.jdbc.Sqlite3DB;

/**
 *
 * @author msivasub
 */
@NbBundle.Messages({
    "LBL_PROP_SHEET_DISPLAY_NAME=Properties",
    "LBL_NAME_PROP_NAME=DB Name",
    "LBL_NAME_PROP_DESC=Name of DB as provided by user",
    "LBL_DBPATH_PROP_NAME=DB path",
    "LBL_DBPATH_PROP_DESC=Location of SQLite3 db file"
})
public class NBSqlite3DBNode extends AbstractNode {

    private final Sqlite3DB key;

    public NBSqlite3DBNode(Sqlite3DB bean) throws IntrospectionException {
        super(Children.create(new NBSqlite3TableNodeFactory(bean), true), Lookups.singleton(bean));
        this.key = bean;
        setName(bean.getName());
        setDisplayName(bean.getName());
    }

    @Override
    public Image getOpenedIcon(int type) {
        return ImageUtilities.loadImage("org/pr/nb/sqlite3/nodes/sqlite316x16-icon.svg.png");
    }

    @Override
    public Image getIcon(int type) {
        return ImageUtilities.loadImage("org/pr/nb/sqlite3/nodes/sqlite316x16-icon.svg.png");
    }

    @Override
    public boolean canRename() {
        return true;
    }

    @Override
    public boolean canDestroy() {
        return true;
    }

    @Override
    public void destroy() throws IOException {
        fireNodeDestroyed();
    }

    @Override
    protected Sheet createSheet() {
        Sheet retValue = super.createSheet();
        retValue.put(createProperties());
        return retValue;
    }

    private Sheet.Set createProperties() {
        Sheet.Set propSet = new Sheet.Set();
        propSet.setDisplayName(Bundle.LBL_PROP_SHEET_DISPLAY_NAME());
        PropertySupport.ReadOnly<String> nameProp = new PropertySupport.ReadOnly<String>("DB Name", String.class,
                Bundle.LBL_NAME_PROP_NAME(), Bundle.LBL_NAME_PROP_DESC()) {
            @Override
            public String getValue() throws IllegalAccessException, InvocationTargetException {
                return key.getName();
            }
        };
        propSet.put(nameProp);

        PropertySupport.ReadOnly<String> pathProp = new PropertySupport.ReadOnly<String>("DB path", String.class,
                Bundle.LBL_DBPATH_PROP_NAME(), Bundle.LBL_DBPATH_PROP_DESC()) {
            @Override
            public String getValue() throws IllegalAccessException, InvocationTargetException {
                return key.getDbPath();
            }
        };
        propSet.put(pathProp);

        return propSet;
    }

    @Override
    public NewType[] getNewTypes() {
        return new NewType[]{
          new NBSqlite3NewTableType()  
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
