/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.sqlite3.data;

import org.openide.util.Lookup;

/**
 *
 * @author msivasub
 */
public interface NBSQlite3NamingManager {

    
    public String getName(NBSqlite3Object data);
    
    public static NBSQlite3NamingManager getImplementation(){
        return Lookup.getDefault().lookup(NBSQlite3NamingManager.class);
    }
    
}
