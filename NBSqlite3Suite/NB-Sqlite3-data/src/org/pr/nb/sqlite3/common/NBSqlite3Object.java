/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.sqlite3.common;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author msivasub
 */
public interface NBSqlite3Object extends Serializable{
    public String getId();
    public String getName();
    public String toExternalForm();
    public List<? extends NBSqlite3Object> getChildren() throws NBSqlite3Exception;
    public Types getType();
    public <E extends NBSqlite3Object>E getParent();
    
    public enum Types{
        DB,
        TABLE,
        COLUMN
    }
}
