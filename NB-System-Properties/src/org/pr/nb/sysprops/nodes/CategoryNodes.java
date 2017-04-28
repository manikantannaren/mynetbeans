/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.sysprops.nodes;

import java.util.ArrayList;
import java.util.List;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.pr.nb.sysprops.data.Category;

/**
 *
 * @author Kaiser
 */
public class CategoryNodes extends Children.Keys<Category> {

    @Override
    protected void addNotify() {
        super.addNotify();
        List<Category> categories = new ArrayList<>();

        Category cat = new Category(Category.Flavour.ENV);
        categories.add(cat);

        cat = new Category(Category.Flavour.PROP);
        categories.add(cat);

        cat = new Category(Category.Flavour.NETBEANS);
        categories.add(cat);

        setKeys(categories);
    }

    @Override
    protected Node[] createNodes(Category key) {
        return new Node[]{new CategoryNode(key)};
    }

}
