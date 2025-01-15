/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.manikantannaren.nb.sysprops.nodes;

import io.github.manikantannaren.nb.sysprops.data.Category;
import static io.github.manikantannaren.nb.sysprops.data.Category.Flavour.ENV;
import static io.github.manikantannaren.nb.sysprops.data.Category.Flavour.NETBEANS;
import static io.github.manikantannaren.nb.sysprops.data.Category.Flavour.PROP;
import org.openide.nodes.AbstractNode;
import org.openide.util.NbBundle;
import org.openide.util.lookup.Lookups;

/**
 *
 * @author Kaiser
 */
@NbBundle.Messages({
    "catenv=Environment variables",
    "catprop=JVM properties",
    "catnb=Netbeans properties"
})
public class CategoryNode extends AbstractNode{
    
    public CategoryNode(Category key) {
        super(new CategoryEntryNodes(key), Lookups.singleton(key));
        
        switch (key.getFlavour()) {
            case ENV:
                setDisplayName(Bundle.catenv());
                setIconBaseWithExtension("io/github/manikantannaren/nb/sysprops/nodes/environment.png");
                break;
            case PROP:
                setDisplayName(Bundle.catprop());
                setIconBaseWithExtension("io/github/manikantannaren/nb/sysprops/nodes/properties.png");
                break;
            case NETBEANS:
                setDisplayName(Bundle.catnb());
                setIconBaseWithExtension("io/github/manikantannaren/nb/sysprops/nodes/nb.png");
                break;
        }
    }
    
}
