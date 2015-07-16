/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.sysprops.data;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 *
 * @author Kaiser
 */
public class Category {

    public enum Flavour {
        PROP, NETBEANS, ENV
    }
    private Flavour flavour = Flavour.PROP;

    public Category(Flavour flavour) {
        this.flavour = flavour;
    }

    public Flavour getFlavour() {
        return flavour;
    }

    public List<CategoryEntry> getItems() {
        List<CategoryEntry> entries = new ArrayList<CategoryEntry>();
        if (this.flavour == Flavour.ENV) {

            Map<String, String> envs = System.getenv();
            Set<Map.Entry<String, String>> entrySet = envs.entrySet();
            for (Map.Entry<String, String> entry : entrySet) {
                String key = entry.getKey();
                String value = entry.getValue();
                value = escapeNewLine(value);
                Map.Entry<String, String> newentry = new AbstractMap.SimpleImmutableEntry<String, String>(key, value);
                CategoryEntry _entry = new CategoryEntry(newentry, flavour);
                entries.add(_entry);
            }
        } else {
            Properties props = System.getProperties();
            Enumeration<String> keys = (Enumeration<String>) props.propertyNames();
            while (keys.hasMoreElements()) {
                String key = keys.nextElement();
                String value = props.getProperty(key);
                value = escapeNewLine(value);
                Map.Entry<String, String> entry = new AbstractMap.SimpleImmutableEntry<String, String>(key, value);

                boolean addToList = checkIfToAdd(entry);
                if (addToList) {
                    CategoryEntry _entry = new CategoryEntry(entry,flavour);
                    entries.add(_entry);
                }
            }
        }
        Collections.sort(entries);
        return entries;
    }

    private String escapeNewLine(String value) {
        if (value.contains(System.lineSeparator())) {
            char[] data = value.toCharArray();
            StringBuilder builder = new StringBuilder();
            for (char e : data) {
                switch ((int) e) {
                    case 10:
                        builder.append("\\n");
                        break;
                    case 13:
                        builder.append("\\r");
                        break;
                    default:
                        builder.append(e);
                        break;
                }
            }
            value = builder.toString();
        }
        return value;
    }

    //nb.native.filechooser
//nb.show.statistics.ui
//netbeans.accept_license_class
//netbeans.buildnumber
//netbeans.default_userdir_root
//netbeans.dirs
//netbeans.dynamic.classpath
//netbeans.home
//netbeans.importclass
//netbeans.logger.console
//netbeans.productversion
//netbeans.user
//org.openide.awt.ActionReference.completion
//org.openide.major.version
//org.openide.specification.version
//org.openide.version
    private boolean checkIfToAdd(Map.Entry<String, String> entry) {
        Boolean retValue = false;
        String key = entry.getKey();
        if (Flavour.NETBEANS == flavour) {
            retValue = (key.contains("nb.native") || key.contains("nb.show") || key.contains("openide") || key.contains("netbeans"));
        } else{
            retValue = (!key.contains("nb.native") && !key.contains("nb.show") && !key.contains("openide") && !key.contains("netbeans"));
        }
        return retValue;
    }

}
