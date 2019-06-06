/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.sqlite3.nodes;

import java.awt.Image;
import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import org.openide.nodes.BeanNode;
import org.openide.nodes.PropertySupport;
import org.openide.nodes.Sheet;
import org.openide.util.ImageUtilities;
import org.openide.util.NbBundle;
import org.openide.util.actions.SystemAction;
import org.pr.nb.sqlite3.common.NBSqlite3Object;

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
public class NBSQlite3DBNode extends BeanNode<NBSqlite3Object> {

    private final NBSqlite3Object key;

    public NBSQlite3DBNode(NBSqlite3Object bean) throws IntrospectionException {
        super(bean);
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

}
