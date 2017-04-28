/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.sysprops;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Mahakaal
 */
public class TestLineFeed {
    
    public TestLineFeed() {
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
         String lf = System.lineSeparator();
         char[] data = lf.toCharArray();
         for (char e : data) {
             if(((int)e) == 10){
                 System.out.println("\\n");
             }else if(((int)e) == 13){
                 System.out.println("\\r");
             }
         }
     }
}
