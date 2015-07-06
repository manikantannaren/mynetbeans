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

package org.pr.nb.zip;

import java.util.List;
import org.openide.filesystems.FileObject;
import org.pr.nb.zip.wizard.ArchiverListValueObject;

/**
 *
 * @author Kaiser
 */
public class ArchiverUserSelections {
    
    public static final String USER_SELECTION = "USER_SELECTION";
    private String destinationZipName;
    private FileObject destinationDirectory;
    private List<ArchiverListValueObject> originalSelectedFiles;
    private List<ArchiverListValueObject> userSelectedFilesInWizard;
    private String extension = "zip";
    private Integer compressionLevel = 9;
    
    public ArchiverUserSelections() {
    }
    
    public String getExtension() {
        return extension;
    }
    
    public void setExtension(String extension) {
        this.extension = extension;
    }
    
    public String getDestinationZipName() {
        return destinationZipName;
    }
    
    public void setDestinationZipName(String destinationZipName) {
        this.destinationZipName = destinationZipName;
    }
    
    public FileObject getDestinationDirectory() {
        return destinationDirectory;
    }
    
    public void setDestinationDirectory(FileObject destinationDirectory) {
        this.destinationDirectory = destinationDirectory;
    }
    
    public List<ArchiverListValueObject> getOriginalSelectedFiles() {
        return originalSelectedFiles;
    }
    
    public void setOriginalSelectedFiles(List<ArchiverListValueObject> originalSelectedFiles) {
        this.originalSelectedFiles = originalSelectedFiles;
    }
    
    public List<ArchiverListValueObject> getUserSelectedFilesInWizard() {
        return userSelectedFilesInWizard;
    }
    
    public void setUserSelectedFilesInWizard(List<ArchiverListValueObject> userSelectedFilesInWizard) {
        this.userSelectedFilesInWizard = userSelectedFilesInWizard;
    }

    public Integer getCompressionLevel() {
        return this.compressionLevel;
    }

    public void setCompressionLevel(Integer compressionLevel) {
        this.compressionLevel = compressionLevel;
    }
    
    
}
