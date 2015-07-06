/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.zip;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kaiser
 */
public class PathComparatorTest {

    public PathComparatorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void hello() throws IOException {
        List<Path> pathsList = new ArrayList<Path>();
        String tempDir = System.getProperty("java.io.tmpdir");
//         Path parentPath = Paths.get(tempDir);
        Path path = Paths.get(tempDir, "a", "b", "c", "d");
        Path dirPath = Files.createDirectories(path);
        dirPath.toFile().deleteOnExit();
        pathsList.add(dirPath);

        path = Paths.get(tempDir, "a", "b", "c", "c");
        Path filePath = Files.createFile(path);
        filePath.toFile().deleteOnExit();
        pathsList.add(filePath);

        path = Paths.get(tempDir, "a", "a");
        filePath = Files.createFile(path);
        filePath.toFile().deleteOnExit();
        pathsList.add(filePath);

        path = Paths.get(tempDir, "d", "a", "b", "c", "e");
        dirPath = Files.createDirectories(path);
        dirPath.toFile().deleteOnExit();
        pathsList.add(dirPath);

        path = Paths.get(tempDir, "d", "a", "b", "c", "d");
        filePath = Files.createFile(path);
        filePath.toFile().deleteOnExit();
        pathsList.add(filePath);

        path = Paths.get(tempDir, "a", "b");
        dirPath = Files.createDirectories(path);
        dirPath.toFile().deleteOnExit();
        pathsList.add(dirPath);



        Collections.sort(pathsList, new ArchiveCreator.PathComparator());
        dumpList(pathsList);
        
    }

    private void dumpList(List<Path> pathsList) {
        for (Path pathsList1 : pathsList) {
            System.out.println(pathsList1+"::"+Files.isDirectory(pathsList1));
        }
    }
}


