/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.zip;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.tools.ant.module.api.support.ActionUtils;
import org.openide.execution.ExecutorTask;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.Exceptions;
import org.pr.nb.zip.util.LoggerProvider;
import org.pr.nb.zip.wizard.ExportArchiveListValueObject;

/**
 *
 * @author Kaiser
 */
public class ArchiveCreator implements Runnable {

    UserSelections selections;
    Logger logger;

    public ArchiveCreator(UserSelections selections) {
        this.logger = LoggerProvider.getLogger(ArchiveCreator.class);
        this.selections = selections;
    }

    @Override
    public void run() {
        try {
            FileObject antScript = generateAntScript(selections);
            String[] targets = new String[]{GenerateArchiveAntTokens.ANT_TASK_NAME.getToken()};
            ExecutorTask task = ActionUtils.runTarget(antScript, targets, null);
            task.addTaskListener(new ExportActionAntScriptCompletionTaskListener());
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        } catch (IllegalArgumentException ex) {
            Exceptions.printStackTrace(ex);
        } catch (URISyntaxException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    private FileObject generateAntScript(UserSelections userSelections) throws URISyntaxException, IOException {
        //read template file
        URL templateurl = getClass().getResource("gen-archive-template.xml");
        URI templUri = templateurl.toURI();
        Map<String, String> env = new HashMap<String, String>();
        env.put("create", "true");
        FileSystem zipfs = FileSystems.newFileSystem(templUri, env);
        Path templatePath = Paths.get(templUri);
        byte[] contents = Files.readAllBytes(templatePath);
        String antscript = new String(contents);
        //read propsfile
        Properties props = new Properties();
        props.load(getClass().getResourceAsStream("gen-archive-fileset.properties"));
        //we have things we need.
        //1. UserSelectons
        //Primary template
        //Fileset template
        antscript = antscript.replace(GenerateArchiveAntTokens.ANT_PROJECT.getToken(), GenerateArchiveAntTokens.ANT_PROJECT_NAME.getToken());
        antscript = antscript.replace(GenerateArchiveAntTokens.ANT_TASK.getToken(), GenerateArchiveAntTokens.ANT_TASK_NAME.getToken());

        File destFileDir = FileUtil.toFile(userSelections.getDestinationDirectory());
        StringBuilder destination = new StringBuilder(destFileDir.getCanonicalPath());
        destination.append(File.separator);
        destination.append(userSelections.getDestinationZipName()).append(".");
        destination.append(userSelections.getExtension());
        antscript = antscript.replace(GenerateArchiveAntTokens.ANT_ZIP_DESTFILE.getToken(), destination);
        antscript = antscript.replace(GenerateArchiveAntTokens.ANT_ZIP_LEVEL.getToken(), userSelections.getCompressionLevel() + "");

        List<ExportArchiveListValueObject> selectedFiles = userSelections.getUserSelectedFilesInWizard();
        StringBuilder filesets = new StringBuilder();
        assert !selectedFiles.isEmpty();
        
        List<Path> filesToBeZipped = resolveSelectedFiles(selectedFiles);
        for (Path filesToBeZipped1 : filesToBeZipped) {
            String tokenizedString =  Files.isDirectory(filesToBeZipped1)? props.getProperty(GenerateArchiveAntTokens.FILESET_DIR.name()) : props.getProperty(GenerateArchiveAntTokens.FILESET_FILE.name());
            String tokenToReplace = Files.isDirectory(filesToBeZipped1) ? GenerateArchiveAntTokens.ANT_ZIP_FILESET_DIR.getToken() :GenerateArchiveAntTokens.ANT_ZIP_FILESET_FILE.getToken();
            String secondTokenToReplace = Files.isDirectory(filesToBeZipped1) ? GenerateArchiveAntTokens.ANT_ZIP_DIR_PREFIX.getToken() :GenerateArchiveAntTokens.ANT_ZIP_FILE_FULLPATH.getToken();
            File inFile = filesToBeZipped1.toRealPath().toFile();
            tokenizedString = tokenizedString.replace(tokenToReplace, inFile.getCanonicalPath());
            tokenizedString = tokenizedString.replace(secondTokenToReplace, inFile.getName());
            filesets.append(tokenizedString);
        }
        
        
        antscript = antscript.replace(GenerateArchiveAntTokens.ANT_ZIP_FILESET.getToken(), filesets);
        FileAttribute<?>[] attrs = new FileAttribute<?>[0];

        Path path = Files.createTempFile("genarchive", "xml", attrs);
        File antFile = path.toFile();
        antFile.deleteOnExit();
        BufferedWriter out = Files.newBufferedWriter(path);
        logger.log(Level.INFO, antscript);
        out.write(antscript);
        out.flush();
        out.close();
        antFile = FileUtil.normalizeFile(antFile);
        FileObject retValue = FileUtil.toFileObject(antFile);
        return retValue;
    }

    private List<Path> resolveSelectedFiles(List<ExportArchiveListValueObject> userSelectedFiles) throws IOException {
        List<Path> pathsList = new ArrayList<Path>();
        List<FileObject> files = new ArrayList<FileObject>();
        for (ExportArchiveListValueObject userSelectedFile : userSelectedFiles) {
            FileObject dataObject = userSelectedFile.getDataObject();
            String cpath = FileUtil.toFile(dataObject).getCanonicalPath();
            Path path = Paths.get(cpath);
            pathsList.add(path);
        }
        Collections.sort(pathsList, new PathComparator());
        
        List<Path> retValue = new ArrayList<Path>();
        for (Path path : pathsList) {
            Path result = checkPath(path, pathsList);
            if(result != null){
                retValue.add(result);
            }
        }
        return retValue;
    }


    Path checkPath(Path path, List<Path> paths) {
        Path retValue = path;
        for (Path path1 : paths) {
            if(!path1.equals(path) && path.startsWith(path1)){
                retValue = null;
                break;
            }
        }   
        return retValue;
    }

    public static class PathComparator implements Comparator<Path> {

        public PathComparator() {
        }

        @Override
        public int compare(Path o1, Path o2) {
            int retValue = o1.compareTo(o2);
            //determine of the two who is a directory
            boolean o1isDir = Files.isDirectory(o1);
            boolean o2isDir = Files.isDirectory(o2);
            if((!o1isDir && !o2isDir) 
                    || (o1isDir && o2isDir)){ //both are either file or dir
                Integer l1 = o1.getNameCount();
                Integer l2 = o2.getNameCount();
                retValue = l2.compareTo(l1); //we want reverse order
            }else{ //one of them is a dir and the other is a file
                if(o1isDir){
                    retValue = -1;
                }else {
                    retValue = 1;
                }
            }
            return retValue;
        }
    }

}
