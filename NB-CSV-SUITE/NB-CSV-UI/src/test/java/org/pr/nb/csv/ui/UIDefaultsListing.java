/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.csv.ui;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Mahakaal
 */
public class UIDefaultsListing {
    
    public UIDefaultsListing() {
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
         UIDefaults defaults = UIManager.getDefaults();
         Set<Map.Entry<Object,Object>> entries = defaults.entrySet();
         Path outPath  = Paths.get(new File("uidefaults.properties").toURI());
         System.out.println(outPath);
         List<String> data = new ArrayList<String>();
         for (Map.Entry<Object, Object> entry : entries) {
             Object key = entry.getKey();
             Object value = entry.getValue();
             StringBuilder row = new StringBuilder();
             if(key instanceof String){
                 row.append(key);
             }else{
                 row.append(key.getClass());
             }
             
             row.append("=");
             row.append(value);
             data.add(row.toString());
         }
         Collections.sort(data);
         Files.write(outPath, data, Charset.defaultCharset(), StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE);
     }
}
