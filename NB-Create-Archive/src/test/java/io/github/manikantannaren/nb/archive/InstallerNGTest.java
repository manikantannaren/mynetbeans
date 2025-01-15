/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.manikantannaren.nb.archive;

import java.util.prefs.Preferences;
import org.openide.util.NbPreferences;
import io.github.manikantannaren.nb.archive.options.ArchiverPreferencesKeys;
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
public class InstallerNGTest {
    
    public InstallerNGTest() {
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
     * Test of restored method, of class Installer.
     */
    @Test
    public void testRestored() {
        System.out.println("restored");
        Installer instance = new Installer();
        instance.restored();
        Preferences prefs = NbPreferences.forModule(ArchiverAction.class);
        System.out.println(prefs.absolutePath());
        Boolean value = prefs.getBoolean(ArchiverPreferencesKeys.LOG_OUTPUT.name(), true);
        assertFalse(value);
        value = prefs.getBoolean(ArchiverPreferencesKeys.SHOW_ADD_TO_FAV_DIALOG.name(), true);
        assertTrue(value);
    }
    
}
