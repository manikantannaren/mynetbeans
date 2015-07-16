/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.sysprops.nodes;

import java.beans.IntrospectionException;
import org.openide.nodes.Index;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.pr.nb.sysprops.data.Category;
import org.pr.nb.sysprops.data.CategoryEntry;

/**
 *
 * @author Kaiser
 */
public class CategoryEntryNodes extends Index.KeysChildren<CategoryEntry>{

    public CategoryEntryNodes(Category key) {
        super(key.getItems());
    }

    
    @Override
    protected Node[] createNodes(CategoryEntry key) {
        try {
            return new Node[]{new CategoryEntryNode(key)};
        } catch (IntrospectionException ex) {
            Exceptions.printStackTrace(ex);
        }
        return new Node[0];
    }

    
}
