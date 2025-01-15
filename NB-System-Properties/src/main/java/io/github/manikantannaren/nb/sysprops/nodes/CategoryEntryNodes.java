/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.manikantannaren.nb.sysprops.nodes;

import io.github.manikantannaren.nb.sysprops.data.Category;
import io.github.manikantannaren.nb.sysprops.data.CategoryEntry;
import java.beans.IntrospectionException;
import org.openide.nodes.Index;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;

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
