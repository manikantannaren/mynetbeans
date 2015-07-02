/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.zip.util;

import java.util.logging.Logger;

/**
 *
 * @author Kaiser
 */
public class LoggerProvider {
    
    public static Logger getLogger(Class whichClass){
        return Logger.getLogger(whichClass.getName());
    }
}
