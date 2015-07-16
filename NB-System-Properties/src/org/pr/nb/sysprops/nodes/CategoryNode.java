/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.sysprops.nodes;

import org.openide.nodes.AbstractNode;
import org.openide.util.NbBundle;
import org.openide.util.lookup.Lookups;
import org.pr.nb.sysprops.data.Category;

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
                setIconBaseWithExtension("org/pr/nb/sysprops/nodes/environment.png");
                break;
            case PROP:
                setDisplayName(Bundle.catprop());
                setIconBaseWithExtension("org/pr/nb/sysprops/nodes/properties.png");
                break;
            case NETBEANS:
                setDisplayName(Bundle.catnb());
                setIconBaseWithExtension("org/pr/nb/sysprops/nodes/nb.png");
                break;
        }
    }
    
}
