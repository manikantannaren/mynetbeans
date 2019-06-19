/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.sqlite3.utils;

import java.util.logging.Level;

/**
 *
 * @author msivasub
 */
public interface Logger {
    
    public void info(Class<?> loggingClass, String messagePattern, Object... patternArgs);
    public void debug(Class<?> loggingClass, String messagePattern, Object... patternArgs);
    public void warn(Class<?> loggingClass, String messagePattern, Throwable ex, Object... patternArgs);
    public void error(Class<?> loggingClass, String messagePattern, Throwable ex, final Object... patternArgs);
    
    default void log(Class<?> loggingClass, Level level, String messagePattern, Throwable ex, final Object... patternArgs){
        java.util.logging.Logger logger = getLogger(loggingClass);
            if(logger.isLoggable(level)){
                logger.log(level, ex, ()->{
                    return String.format(messagePattern, patternArgs);
                });
            }
    }
    
    private java.util.logging.Logger getLogger(Object loggerObject){
        
        String name = java.util.logging.Logger.GLOBAL_LOGGER_NAME;
        if(loggerObject instanceof Class){
            name = ((Class)loggerObject).getName();
        }else if(loggerObject instanceof String){
            name = String.class.cast(loggerObject);
        }else if(loggerObject != null){
           name = loggerObject.getClass().getName();
        }
        java.util.logging.Logger retValue = java.util.logging.Logger.getLogger(name);
        retValue.setLevel(Level.FINE);
        return retValue;
    }
    
    public static Logger getDefaultLogger(){
        return new Logger(){
            @Override
            public void info(Class<?> loggingClass, String messagePattern, Object... patternArgs) {
                log(loggingClass, Level.INFO,messagePattern,null,patternArgs);
            }

            @Override
            public void debug(Class<?> loggingClass, String messagePattern, Object... patternArgs) {
                log(loggingClass, Level.FINER,messagePattern,null,patternArgs);
            }

            @Override
            public void warn(Class<?> loggingClass, String messagePattern, Throwable ex, Object... patternArgs) {
                log(loggingClass, Level.WARNING,messagePattern,ex,patternArgs);
            }

            @Override
            public void error(Class<?> loggingClass, String messagePattern, Throwable ex, Object... patternArgs) {
                log(loggingClass, Level.SEVERE,messagePattern,ex,patternArgs);
            }
            
        };
    }
    
}
