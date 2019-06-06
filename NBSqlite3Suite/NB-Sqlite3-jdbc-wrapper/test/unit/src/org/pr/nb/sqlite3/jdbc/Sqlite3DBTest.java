/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.sqlite3.jdbc;

import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.netbeans.junit.NbTestCase;
import org.pr.nb.sqlite3.common.NBSqlite3Exception;

/**
 *
 * @author msivasub
 */

public class Sqlite3DBTest {
    
    public Sqlite3DBTest() {
//        super("Sqlite3DBTest");
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getName method, of class Sqlite3DB.
     * @throws org.pr.nb.sqlite3.common.NBSqlite3Exception
     */
    @Test
    public void testGetName() throws NBSqlite3Exception {
        System.out.println("getName");
        Sqlite3DB instance = new Sqlite3DB.Builder().withName("abra").withPath("build/abra.db").build();
        List<Sqlite3Table> children = instance.getChildren();
        System.out.println(children);
    }


}
