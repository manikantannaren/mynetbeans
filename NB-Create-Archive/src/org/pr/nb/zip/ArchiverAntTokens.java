/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.zip;

/**
 *
 * @author Kaiser
 */
public enum ArchiverAntTokens {
    
    ANT_PROJECT{

        @Override
        public String getToken() {
           return "@projectname@";
        }
        
    },ANT_TASK{

        @Override
        public String getToken() {
           return "@taskname@";
        }
        
    },ANT_ZIP_DESTFILE{

        @Override
        public String getToken() {
           return "@destfile@";
        }
        
    },ANT_ZIP_FILESET{

        @Override
        public String getToken() {
           return "@filesetlist@";
        }
        
    },ANT_ZIP_FILESET_DIR{

        @Override
        public String getToken() {
           return "@dir@";
        }
        
    },ANT_ZIP_FILESET_FILE{

        @Override
        public String getToken() {
           return "@file@";
        }
        
    },ANT_PROJECT_NAME{

        @Override
        public String getToken() {
           return "Zip Creator";
        }
        
    },ANT_TASK_NAME{

        @Override
        public String getToken() {
           return "gen-archive";
        }
        
    },ANT_ZIP_LEVEL{

        @Override
        public String getToken() {
           return "@level@";
        }
        
    },ANT_ZIP_FILE_FULLPATH{
        @Override
        public String getToken() {
           return "@fullpath@";
        }
        
    },ANT_ZIP_DIR_PREFIX{

        @Override
        public String getToken() {
           return "@filesetprefix@";
        }
        
    },ANT_FILE{

        @Override
        public String getToken() {
           return "";
        }
        
    },FILESET_DIR{

        @Override
        public String getToken() {
           return "";
        }
        
    },FILESET_FILE{

        @Override
        public String getToken() {
           return "";
        }
        
    };
    
    
    public abstract String getToken();
}
