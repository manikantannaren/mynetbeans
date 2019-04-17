/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.sqlite3.logger;

import java.util.logging.Level;

/**
 *
 * @author msivasub
 */
public interface Logger {
    
    public void log(Class logger, Level level, String messagePattern, Throwable ex, final Object... patternArgs);
    
    public static Logger getDefaultLogger(){
        return (Class loggingClass, Level level, String messagePattern, Throwable ex, Object... patternArgs) -> {
            java.util.logging.Logger logger = java.util.logging.Logger.getLogger(loggingClass.getName());
            if(logger.isLoggable(level)){
                logger.log(level, ex, ()->{
                    return String.format(messagePattern, patternArgs);
                });
            }
        };
    }
}
