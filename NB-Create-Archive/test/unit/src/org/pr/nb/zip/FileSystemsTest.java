/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.zip;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.FileSystems;
import java.util.HashMap;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Kaiser
 */
public class FileSystemsTest {
    
    public FileSystemsTest() {
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
     public void hello() throws URISyntaxException, IOException {
         URL url = getClass().getResource("gen-archive-template.xml");
         URI uri = url.toURI();
         try{
         FileSystems.getFileSystem(uri);
         }catch(FileSystemNotFoundException e){
                     Map<String, String> env = new HashMap<String, String>();
        env.put("create", "true");

             FileSystems.newFileSystem(uri, env);
         }
     }
}
