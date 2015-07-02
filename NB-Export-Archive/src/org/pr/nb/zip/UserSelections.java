/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.zip;

import java.util.List;
import org.openide.filesystems.FileObject;
import org.pr.nb.zip.wizard.ExportArchiveListValueObject;

/**
 *
 * @author Kaiser
 */
public class UserSelections {
    
    public static final String USER_SELECTION = "USER_SELECTION";
    private String destinationZipName;
    private FileObject destinationDirectory;
    private List<ExportArchiveListValueObject> originalSelectedFiles;
    private List<ExportArchiveListValueObject> userSelectedFilesInWizard;
    private String extension = "zip";
    private Integer compressionLevel = 9;
    
    public UserSelections() {
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
    
    public List<ExportArchiveListValueObject> getOriginalSelectedFiles() {
        return originalSelectedFiles;
    }
    
    public void setOriginalSelectedFiles(List<ExportArchiveListValueObject> originalSelectedFiles) {
        this.originalSelectedFiles = originalSelectedFiles;
    }
    
    public List<ExportArchiveListValueObject> getUserSelectedFilesInWizard() {
        return userSelectedFilesInWizard;
    }
    
    public void setUserSelectedFilesInWizard(List<ExportArchiveListValueObject> userSelectedFilesInWizard) {
        this.userSelectedFilesInWizard = userSelectedFilesInWizard;
    }

    public Integer getCompressionLevel() {
        return this.compressionLevel;
    }

    public void setCompressionLevel(Integer compressionLevel) {
        this.compressionLevel = compressionLevel;
    }
    
    
}
