/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.sqlite3.data;

import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author msivasub
 */
@ServiceProvider(service = NBSQlite3NamingManager.class, path = "SQlite3NamingManager/default")
public class NBDefaultSqlite3NamingManager implements NBSQlite3NamingManager {

    
    @Override
    public String getName(NBSqlite3Object data) {
        NBSqlite3InstanceFactory instanceFactory = NBSqlite3InstanceFactory.getInstance();
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
