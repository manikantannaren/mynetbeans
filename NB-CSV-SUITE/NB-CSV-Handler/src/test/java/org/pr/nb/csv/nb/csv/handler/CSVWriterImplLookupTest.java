/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.csv.nb.csv.handler;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openide.util.Lookup;

/**
 *
 * @author Kaiser
 */
public class CSVWriterImplLookupTest {
    
    public CSVWriterImplLookupTest() {
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
//         Lookup lookup = Lookups.forPath("org.pr.nb.csv.nb.csv.handler");
         CSVWriter writer = Lookup.getDefault().lookup(CSVWriter.class);
         assertNotNull(writer);
     }
}
