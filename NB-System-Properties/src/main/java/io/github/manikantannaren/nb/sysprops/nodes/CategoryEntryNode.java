/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.manikantannaren.nb.sysprops.nodes;

import static io.github.manikantannaren.nb.sysprops.data.Category.Flavour.ENV;
import static io.github.manikantannaren.nb.sysprops.data.Category.Flavour.NETBEANS;
import static io.github.manikantannaren.nb.sysprops.data.Category.Flavour.PROP;
import io.github.manikantannaren.nb.sysprops.data.CategoryEntry;
import io.github.manikantannaren.nb.sysprops.nodes.actions.CopyNameAction;
import io.github.manikantannaren.nb.sysprops.nodes.actions.CopyValueAction;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import javax.swing.Action;
import org.openide.actions.CopyAction;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.PropertySupport;
import org.openide.nodes.Sheet;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle;
import org.openide.util.actions.SystemAction;
import org.openide.util.datatransfer.ExTransferable;
import org.openide.util.lookup.Lookups;

/**
 *
 * @author Kaiser
 */
@NbBundle.Messages({
    "categoryentry.entryname.displayname=Variable/Property Name",
    "categoryentry.entryname.hint=Name of the environment variable or system property",
    "categoryentry.entryvalue.displayname=Value",
    "categoryentry.entryvalue.hint=Value of the above environment variable or system property",
    "categoryentry.flavour.displayName=Category",
    "categoryentry.flavour.hint=Category to which this entry belongs to. viz Environment variables, System properties and NetBeans properties"
})
public class CategoryEntryNode extends AbstractNode {

    private final CategoryEntry key;

    public CategoryEntryNode(CategoryEntry key) throws IntrospectionException {
        super(Children.LEAF, Lookups.singleton(key));
        this.key = key;
        setDisplayName(key.getEntryName());
        setIconBaseWithExtension("io/github/manikantannaren/nb/sysprops/nodes/property.gif");
    }

    @Override
    protected Sheet createSheet() {
        Sheet propertySheet = super.createSheet();

        Sheet.Set propertySet = Sheet.createPropertiesSet();
        propertySheet.put(propertySet);

        String categoryDisplayName = "";
        switch (key.getFlavour()) {
            case NETBEANS:
                categoryDisplayName = Bundle.catnb();
                break;
            case PROP:
                categoryDisplayName = Bundle.catprop();
                break;
            case ENV:
                categoryDisplayName = Bundle.catenv();
                break;
        }

        try {
            Property<String> prop = new PropertySupport.Reflection<>(key,
                    String.class, "getEntryName", null);
            prop.setDisplayName(Bundle.categoryentry_entryname_displayname());
            prop.setShortDescription(Bundle.categoryentry_entryname_hint());
            prop.setValue("htmlDisplayValue", "<font color='!textText'>" + key.
                    getEntryName());
            propertySet.put(prop);

            PropertySupport.Reflection<String> propvalue = new PropertySupport.Reflection<>(
                    key, String.class, "getEntryValue", null);
            propvalue.setDisplayName(Bundle.
                    categoryentry_entryvalue_displayname());
            propvalue.
                    setShortDescription(Bundle.categoryentry_entryvalue_hint());
            propvalue.setValue("htmlDisplayValue", "<font color='!textText'>"
                    + key.getEntryValue());
            propvalue.setPropertyEditorClass(SysProEditor.class);
            propertySet.put(propvalue);
            String catNAme = categoryDisplayName;
            Property<String> flvProperty = new PropertySupport.ReadOnly<String>(
                    "Category", String.class, Bundle.categoryentry_flavour_displayName(),
                    Bundle.categoryentry_flavour_hint()) {
                @Override
                public String getValue() throws IllegalAccessException,
                        InvocationTargetException {
                    return catNAme;
                }

            };
            propertySet.put(flvProperty);

        } catch (NoSuchMethodException ex) {
            Exceptions.printStackTrace(ex);
        }

        return propertySheet;
    }

    @Override
    public Action[] getActions(boolean context) {
        Action act = SystemAction.get(CopyAction.class);
        return new Action[]{act,
            new CopyNameAction(this),
            new CopyValueAction(this)
        };
    }

    @Override
    public boolean canCut() {
        return false;
    }

    @Override
    public boolean canCopy() {
        return super.canCopy(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean canDestroy() {
        return false;
    }

    @Override
    public boolean canRename() {
        return false;
    }

    @Override
    public Transferable clipboardCopy() throws IOException {
        Transferable t = super.clipboardCopy(); //To change body of generated methods, choose Tools | Templates.
        ExTransferable added = ExTransferable.create(t);

        added.put(new ExTransferable.Single(DataFlavor.stringFlavor) {

            @Override
            protected String getData() throws IOException,
                    UnsupportedFlavorException {
                CategoryEntry thisEntry = getLookup().
                        lookup(CategoryEntry.class);
                StringBuilder builder = new StringBuilder(thisEntry.
                        getEntryName());
                builder.append("=").append(thisEntry.getEntryValue());
                return builder.toString();
            }

        });
        return added;
    }

    public Transferable clipboardCopyOnlyName() throws IOException {
        Transferable t = super.clipboardCopy();
        ExTransferable added = ExTransferable.create(t);

        added.put(new ExTransferable.Single(DataFlavor.stringFlavor) {

            @Override
            protected String getData() throws IOException,
                    UnsupportedFlavorException {
                CategoryEntry thisEntry = getLookup().
                        lookup(CategoryEntry.class);
                return thisEntry.getEntryName();
            }

        });
        return added;

    }

    public Transferable clipboardCopyOnlyValue() throws IOException {
        Transferable t = super.clipboardCopy();
        ExTransferable added = ExTransferable.create(t);

        added.put(new ExTransferable.Single(DataFlavor.stringFlavor) {

            @Override
            protected String getData() throws IOException,
                    UnsupportedFlavorException {
                CategoryEntry thisEntry = getLookup().
                        lookup(CategoryEntry.class);
                return thisEntry.getEntryValue();
            }

        });
        return added;

    }
}
