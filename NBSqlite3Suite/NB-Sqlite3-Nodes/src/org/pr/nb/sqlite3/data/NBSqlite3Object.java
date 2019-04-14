/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.sqlite3.data;

import java.io.Externalizable;

/**
 *
 * @author msivasub
 */
public interface NBSqlite3Object {
    
    public String getName();
    public String getDbPath();
    public String toExternalForm();
}
