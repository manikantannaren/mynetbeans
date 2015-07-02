/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.zip;

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
     public void hello() {
         List<Path> pathsList = new ArrayList<Path>();
         pathsList.add(Paths.get("/var/tmp/a/b/c/d/"));
         pathsList.add(Paths.get("/var/tmp/a/b/c/c"));
         pathsList.add(Paths.get("/var/tmp/a/a"));
         pathsList.add(Paths.get("/var/tmp/a/b/"));
         Collections.sort(pathsList);
         System.out.println(pathsList);
         
         Collections.sort(pathsList, new ArchiveCreator.PathComparator());
         System.out.println(pathsList);
     }
}
