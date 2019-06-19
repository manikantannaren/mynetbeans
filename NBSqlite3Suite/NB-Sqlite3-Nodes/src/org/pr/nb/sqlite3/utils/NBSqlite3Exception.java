/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.sqlite3.utils;

/**
 *
 * @author msivasub
 */
public class NBSqlite3Exception extends Exception {

    public NBSqlite3Exception(String message) {
        super(message);
    }

    
    public NBSqlite3Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public NBSqlite3Exception(Throwable cause) {
        super(cause);
    }
    
    
}
