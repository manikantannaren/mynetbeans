/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.sysprops.nodes;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.PropertySupport;
import org.openide.nodes.Sheet;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle;
import org.openide.util.lookup.Lookups;
import org.pr.nb.sysprops.data.Category;
import org.pr.nb.sysprops.data.CategoryEntry;

/**
 *
 * @author Kaiser
 */
@NbBundle.Messages({
    "categoryentry.entryname.displayname=Variable/Property Name",
    "categoryentry.entryname.hint=Name of the environment variable or system property",
    "categoryentry.entryvalue.displayname=Value",
    "categoryentry.entryvalue.hint=Value of the above environment variable or system property"
    
        
})
public class CategoryEntryNode extends AbstractNode {

    private CategoryEntry key;

    public CategoryEntryNode(CategoryEntry key) throws IntrospectionException {
        super(Children.LEAF, Lookups.singleton(key));
        this.key = key;
        setDisplayName(key.getEntryName());
        setIconBaseWithExtension("org/pr/nb/sysprops/nodes/property.gif");
    }

    @Override
    protected Sheet createSheet() {
        Sheet propertySheet = super.createSheet();

        Sheet.Set propertySet = Sheet.createPropertiesSet();
        propertySheet.put(propertySet);

        String displayName ="";
        switch (key.getFlavour()) {
            case NETBEANS:
                displayName = Bundle.catnb();
                break;
            case PROP:
                displayName = Bundle.catprop();
                break;
            case ENV:
                displayName = Bundle.catenv();
                break;
        }

        try {
            Property<String> prop = new PropertySupport.Reflection<String>(key, String.class, "getEntryName",null);
            prop.setDisplayName(Bundle.categoryentry_entryname_displayname());
            prop.setShortDescription(Bundle.categoryentry_entryname_hint());
            prop.setValue("htmlDisplayValue", "<font color='!textText'>" + key.getEntryName());
            propertySet.put(prop);
            
            prop = new PropertySupport.Reflection<String>(key, String.class, "getEntryValue",null);
            prop.setDisplayName(Bundle.categoryentry_entryname_displayname());
            prop.setShortDescription(Bundle.categoryentry_entryname_hint());
            prop.setValue("htmlDisplayValue", "<font color='!textText'>" + key.getEntryValue());
            propertySet.put(prop);
            
            Property<Category.Flavour> flvProperty = new PropertySupport.Reflection<Category.Flavour>(key,Category.Flavour.class,"getFlavour",null);
            prop.setValue("htmlDisplayValue", "<font color='!textText'>" + displayName);
            propertySet.put(flvProperty);
            
        } catch (NoSuchMethodException ex) {
            Exceptions.printStackTrace(ex);
        }

        return propertySheet;
    }

//    @Override
//    public PropertySet[] getPropertySets() {
//
//        PropertySet[] sets = super.getPropertySets();
//        if (sets.length > 0) {
//            PropertySet set = sets[0];
//            Property[] allprops = set.getProperties();
//
//            for (Property allprop : allprops) {
//                if (allprop.getName().equals("entryValue")) {
//                    try {
//                        allprop.setValue("htmlDisplayValue", "<font color='!textText'>" + allprop.getValue());
//                    } catch (IllegalAccessException ex) {
//                        Exceptions.printStackTrace(ex);
//                    } catch (InvocationTargetException ex) {
//                        Exceptions.printStackTrace(ex);
//                    }
//                }
//            }
//        }
//        return sets;
//    }
}
