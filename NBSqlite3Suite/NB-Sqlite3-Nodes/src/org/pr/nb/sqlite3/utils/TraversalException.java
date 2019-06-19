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
public class TraversalException extends RuntimeException{

    public TraversalException(String message) {
        super(message);
    }

    public TraversalException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
