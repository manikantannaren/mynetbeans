/*
 * Copyright 2015 Manikantan Narender Nath.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.manikantannaren.nb.archive;

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
