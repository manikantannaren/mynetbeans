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

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.FileSystem;
import java.nio.file.FileSystemAlreadyExistsException;
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
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import org.apache.tools.ant.module.api.support.ActionUtils;
import org.openide.execution.ExecutorTask;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle;
import org.openide.util.NbPreferences;
import org.openide.windows.IOProvider;
import org.openide.windows.InputOutput;
import io.github.manikantannaren.nb.archive.options.ArchiverPreferencesKeys;
import io.github.manikantannaren.nb.archive.util.LoggerProvider;
import io.github.manikantannaren.nb.archive.wizard.ArchiverListValueObject;

/**
 *
 * @author Kaiser
 */
@NbBundle.Messages({
    "loading.templates=Loading templates",
    "# {0} - list of files to be user has selected",
    "Checking.files.to.be.zipped=Resolving files to be archived {0}",
    "# {0} - list of files to be user has selected",
    "resolved.files=Final list of files to be archived {0}",
    "replace.tokens=Replacing template tokens with proper values",
    "# {0} - the token to be replaced",
    "# {1} - the value replacing the token",
    "replaced.token=Replacing template token {0} with proper value {1}",
    "# {0} - generated antscript",
    "antscript.generated=Ant script to be executed {0}",
    "archiver.done=Done",})
public class ArchiveCreator implements Runnable {

    ArchiverUserSelections selections;
    Logger logger;

    Preferences prefs;

    public ArchiveCreator(ArchiverUserSelections selections) {
        this.logger = LoggerProvider.getLogger(ArchiveCreator.class);
        this.selections = selections;
        prefs = NbPreferences.forModule(ArchiverAction.class);
    }

    @Override
    public void run() {
        try {
            FileObject antScript = generateAntScript(selections);
//            String[] targets = new String[]{ArchiverAntTokens.ANT_TASK_NAME.getToken()};
            Properties props = new Properties();
            
            ExecutorTask task = ActionUtils.runTarget(antScript, null, null);
            StringBuilder destination = getDestinationFile(selections);
            File file = new File(destination.toString());
            file = FileUtil.normalizeFile(file);
            
            task.addTaskListener(new ArchiverAntScriptCompletionTaskListener(FileUtil.toFileObject(file)));
            log(Bundle.archiver_done(), true);
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        } catch (IllegalArgumentException ex) {
            Exceptions.printStackTrace(ex);
        } catch (URISyntaxException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    private FileObject generateAntScript(ArchiverUserSelections userSelections) throws URISyntaxException, IOException {
        //read template file
        log(Bundle.loading_templates(), false);
        URL templateurl = getClass().getResource("gen-archive-template.xml");
        URI templUri = templateurl.toURI();

        Map<String, String> env = new HashMap<String, String>();
        env.put("create", "true");
        try {
            //load a file system if not exists since the file being read is inside a jar
            FileSystem zipfs = FileSystems.newFileSystem(templUri, env);
        } catch (FileSystemAlreadyExistsException e) {
            //if called within same netbeans session
            //exception is thrown since the FS is already loaded.
            //do nothing unless some other specific problem arises
        }
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

        List<ArchiverListValueObject> selectedFiles = userSelections.getUserSelectedFilesInWizard();
        StringBuilder filesets = new StringBuilder();
        assert !selectedFiles.isEmpty();

        List<Path> filesToBeZipped = resolveSelectedFiles(selectedFiles);
        log(Bundle.replace_tokens(), false);
        for (Path filesToBeZipped1 : filesToBeZipped) {
            String tokenizedString = Files.isDirectory(filesToBeZipped1) ? props.getProperty(ArchiverAntTokens.FILESET_DIR.name()) : props.getProperty(ArchiverAntTokens.FILESET_FILE.name());
            String tokenToReplace = Files.isDirectory(filesToBeZipped1) ? ArchiverAntTokens.ANT_ZIP_FILESET_DIR.getToken() : ArchiverAntTokens.ANT_ZIP_FILESET_FILE.getToken();

            String secondTokenToReplace = Files.isDirectory(filesToBeZipped1) ? ArchiverAntTokens.ANT_ZIP_DIR_PREFIX.getToken() : ArchiverAntTokens.ANT_ZIP_FILE_FULLPATH.getToken();
            File inFile = filesToBeZipped1.toRealPath().toFile();
            tokenizedString = tokenizedString.replace(tokenToReplace, inFile.getCanonicalPath());
            log(Bundle.replaced_token(tokenToReplace, inFile.getCanonicalPath()), false);
            tokenizedString = tokenizedString.replace(secondTokenToReplace, inFile.getName());
            log(Bundle.replaced_token(tokenToReplace, inFile.getName()), false);
            filesets.append(tokenizedString);
        }

        antscript = antscript.replace(ArchiverAntTokens.ANT_ZIP_FILESET.getToken(), filesets);
        log(Bundle.replaced_token(ArchiverAntTokens.ANT_ZIP_FILESET.getToken(), filesets), false);
        antscript = antscript.replace(ArchiverAntTokens.ANT_PROJECT.getToken(), ArchiverAntTokens.ANT_PROJECT_NAME.getToken());
        log(Bundle.replaced_token(ArchiverAntTokens.ANT_PROJECT.getToken(), ArchiverAntTokens.ANT_PROJECT_NAME.getToken()), false);
        antscript = antscript.replace(ArchiverAntTokens.ANT_TASK.getToken(), ArchiverAntTokens.ANT_TASK_NAME.getToken());
        log(Bundle.replaced_token(ArchiverAntTokens.ANT_TASK.getToken(), ArchiverAntTokens.ANT_TASK_NAME.getToken()), false);

        StringBuilder destination = getDestinationFile(userSelections);

        antscript = antscript.replace(ArchiverAntTokens.ANT_ZIP_DESTFILE.getToken(), destination);
        log(Bundle.replaced_token(ArchiverAntTokens.ANT_ZIP_DESTFILE.getToken(), destination), false);
        antscript = antscript.replace(ArchiverAntTokens.ANT_ZIP_LEVEL.getToken(), userSelections.getCompressionLevel() + "");
        log(Bundle.replaced_token(ArchiverAntTokens.ANT_ZIP_LEVEL.getToken(), userSelections.getCompressionLevel()), false);
        log(Bundle.antscript_generated(antscript), false);
        FileAttribute<?>[] attrs = new FileAttribute<?>[0];

        Path path = Files.createTempFile("genarchive", "xml", attrs);
        File antFile = path.toFile();
        antFile.deleteOnExit();
        BufferedWriter out = Files.newBufferedWriter(path, Charset.defaultCharset());
        out.write(antscript);
        out.flush();
        out.close();
        antFile = FileUtil.normalizeFile(antFile);
        FileObject retValue = FileUtil.toFileObject(antFile);
        return retValue;
    }

    StringBuilder getDestinationFile(ArchiverUserSelections userSelections) throws IOException {
        File destFileDir = FileUtil.toFile(userSelections.getDestinationDirectory());
        StringBuilder destination = new StringBuilder(destFileDir.getCanonicalPath());
        destination.append(File.separator);
        destination.append(userSelections.getDestinationZipName()).append(".");
        destination.append(userSelections.getExtension());
        return destination;
    }

    private List<Path> resolveSelectedFiles(List<ArchiverListValueObject> userSelectedFiles) throws IOException {
        List<Path> pathsList = new ArrayList<Path>();
        for (ArchiverListValueObject userSelectedFile : userSelectedFiles) {
            FileObject dataObject = userSelectedFile.getDataObject();
            String cpath = FileUtil.toFile(dataObject).getCanonicalPath();
            Path path = Paths.get(cpath);
            pathsList.add(path);
        }
    
        log(Bundle.Checking_files_to_be_zipped(pathsList), false);
        Collections.sort(pathsList, new PathComparator());

        List<Path> retValue = new ArrayList<Path>();
        for (Path path : pathsList) {
            Path result = checkPath(path, pathsList);
            if (result != null) {
                retValue.add(result);
            }
        }
        return retValue;
    }

    Path checkPath(Path path, List<Path> paths) {
        Path retValue = path;
        for (Path path1 : paths) {
            if (!path1.equals(path) && path.startsWith(path1)) {
                retValue = null;
                break;
            }
        }
        return retValue;
    }

    private void log(String message, boolean done) {
        Boolean logOutput = prefs.getBoolean(ArchiverPreferencesKeys.LOG_OUTPUT.name(), false);
        if (logOutput) {
            String outputTabName = getIOTabName();
            InputOutput io = IOProvider.getDefault().getIO(outputTabName, false);
            io.getOut().println(message);
            io.select();
            if (done) {
                io.getOut().close();
                io.getErr().close();
            }
        }
    }

    String getIOTabName() {
        String outputTabName = ArchiverAntTokens.ANT_PROJECT_NAME.getToken();//+ " ("+ ArchiverAntTokens.ANT_TASK_NAME.getToken()+")";
        return outputTabName;
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
            if ((!o1isDir && !o2isDir)
                    || (o1isDir && o2isDir)) { //both are either file or dir
                Integer l1 = o1.getNameCount();
                Integer l2 = o2.getNameCount();
                retValue = l2.compareTo(l1); //we want reverse order
            } else { //one of them is a dir and the other is a file
                if (o1isDir) {
                    retValue = -1;
                } else {
                    retValue = 1;
                }
            }
            return retValue;
        }
    }

}
