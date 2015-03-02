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

package org.pr.nb.plugins.usd;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataShadow;
import org.openide.util.Utilities;

/**
 *
 * @author Manikantan Narender Nath
 */

public class DesktopHelper {
    
    private DesktopHelper() {
    }
    
    public Boolean isDesktopSupported(){
        return Desktop.isDesktopSupported();
    }
    public Boolean openWithDesktop(DataObject context) throws IOException{
        if(isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.OPEN)){
            FileObject fileToBeOpened = getFileObjectFromContext(context);
            if(fileToBeOpened != null){
                Desktop.getDesktop().open(FileUtil.toFile(fileToBeOpened));
                return true;
            }
        }
        return false;
    }
    public Boolean editWithDesktop(DataObject context) throws IOException{
        if(isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.EDIT)){
            FileObject fileToBeOpened = getFileObjectFromContext(context);
            if(fileToBeOpened != null){
                Desktop.getDesktop().edit(FileUtil.toFile(fileToBeOpened));
                return true;
            }
        }
        return false;
    }
    
    public static DesktopHelper getInstance() {
        return DesktopHelperHolder.INSTANCE;
    }

    public boolean browseWithDesktop(DataObject context) throws IOException{
        if(isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)){
            FileObject fileToBeOpened = getFileObjectFromContext(context);
            if(fileToBeOpened != null){
                Desktop.getDesktop().browse(Utilities.toURI(FileUtil.toFile(fileToBeOpened)));
                return true;
            }
        }
        return false;
    }

    public boolean mailWithDesktop(DataObject context) throws IOException, URISyntaxException{
        if(isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.MAIL)){
            FileObject fileToBeOpened = getFileObjectFromContext(context);
            if(fileToBeOpened != null){
                StringBuilder builder = new StringBuilder();
                builder.append("mailto:?subject=");
                String subject = Bundle.CTL_Mail_subject(URLEncoder.encode(fileToBeOpened.getNameExt(), "UTF-8"));
                builder.append(subject);
                builder.append("&body=");
                
                String body = Bundle.CTL_Mail_body(URLEncoder.encode(fileToBeOpened.getPath(), "UTF-8"));
                builder.append(body);
                URI mailtouri = new URI(builder.toString());
                Desktop.getDesktop().mail(mailtouri);
                return true;
            }
        }
        return false;
    }

    FileObject getFileObjectFromContext(DataObject context) {
        if(context instanceof DataShadow){
            context = ((DataShadow)context).getOriginal();
        }
        FileObject fileToBeOpened = context.getPrimaryFile();
        return fileToBeOpened;
    }
    
    
    private static class DesktopHelperHolder {

        private static final DesktopHelper INSTANCE = new DesktopHelper();
    }
}
