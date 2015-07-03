/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.zip.options;

import java.beans.PropertyChangeListener;
import javax.swing.JComponent;
import org.openide.util.HelpCtx;
import org.openide.util.Lookup;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Kaiser
 */
public class ArchiverOptionsPanelControllerNGTest {
    
    public ArchiverOptionsPanelControllerNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * Test of update method, of class ArchiverOptionsPanelController.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        ArchiverOptionsPanelController instance = new ArchiverOptionsPanelController();
        instance.update();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of applyChanges method, of class ArchiverOptionsPanelController.
     */
    @Test
    public void testApplyChanges() {
        System.out.println("applyChanges");
        ArchiverOptionsPanelController instance = new ArchiverOptionsPanelController();
        instance.applyChanges();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cancel method, of class ArchiverOptionsPanelController.
     */
    @Test
    public void testCancel() {
        System.out.println("cancel");
        ArchiverOptionsPanelController instance = new ArchiverOptionsPanelController();
        instance.cancel();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isValid method, of class ArchiverOptionsPanelController.
     */
    @Test
    public void testIsValid() {
        System.out.println("isValid");
        ArchiverOptionsPanelController instance = new ArchiverOptionsPanelController();
        boolean expResult = false;
        boolean result = instance.isValid();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isChanged method, of class ArchiverOptionsPanelController.
     */
    @Test
    public void testIsChanged() {
        System.out.println("isChanged");
        ArchiverOptionsPanelController instance = new ArchiverOptionsPanelController();
        boolean expResult = false;
        boolean result = instance.isChanged();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getHelpCtx method, of class ArchiverOptionsPanelController.
     */
    @Test
    public void testGetHelpCtx() {
        System.out.println("getHelpCtx");
        ArchiverOptionsPanelController instance = new ArchiverOptionsPanelController();
        HelpCtx expResult = null;
        HelpCtx result = instance.getHelpCtx();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getComponent method, of class ArchiverOptionsPanelController.
     */
    @Test
    public void testGetComponent() {
        System.out.println("getComponent");
        Lookup masterLookup = null;
        ArchiverOptionsPanelController instance = new ArchiverOptionsPanelController();
        JComponent expResult = null;
        JComponent result = instance.getComponent(masterLookup);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addPropertyChangeListener method, of class ArchiverOptionsPanelController.
     */
    @Test
    public void testAddPropertyChangeListener() {
        System.out.println("addPropertyChangeListener");
        PropertyChangeListener l = null;
        ArchiverOptionsPanelController instance = new ArchiverOptionsPanelController();
        instance.addPropertyChangeListener(l);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removePropertyChangeListener method, of class ArchiverOptionsPanelController.
     */
    @Test
    public void testRemovePropertyChangeListener() {
        System.out.println("removePropertyChangeListener");
        PropertyChangeListener l = null;
        ArchiverOptionsPanelController instance = new ArchiverOptionsPanelController();
        instance.removePropertyChangeListener(l);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of changed method, of class ArchiverOptionsPanelController.
     */
    @Test
    public void testChanged() {
        System.out.println("changed");
        ArchiverOptionsPanelController instance = new ArchiverOptionsPanelController();
        instance.changed();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
