/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.manikantannaren.nb.archive.options;

import java.util.prefs.BackingStoreException;
import java.util.prefs.PreferenceChangeEvent;
import java.util.prefs.PreferenceChangeListener;
import java.util.prefs.Preferences;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openide.util.NbPreferences;
import io.github.manikantannaren.nb.archive.ArchiverAction;

/**
 *
 * @author Kaiser
 */
public class PreferencesTest {
    
    public PreferencesTest() {
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
     public void hello() throws BackingStoreException {
         Preferences prefs = NbPreferences.forModule(ArchiverAction.class);
         prefs.addPreferenceChangeListener(new PreferenceChangeListener() {

             @Override
             public void preferenceChange(PreferenceChangeEvent evt) {
                 System.out.println("Preference chnaged for "+evt.getKey()+"::"+evt.getNewValue()+"IN Node "+evt.getNode());
             }
         });
         
         Preferences _prefs = NbPreferences.forModule(ArchiverAction.class);
         _prefs.put("something","somehting");
         _prefs.flush();
     }
}
