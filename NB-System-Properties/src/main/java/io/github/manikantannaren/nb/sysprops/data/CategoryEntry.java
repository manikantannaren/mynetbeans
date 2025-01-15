/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.manikantannaren.nb.sysprops.data;

import java.util.Map;

/**
 *
 * @author Kaiser
 */
public class CategoryEntry implements Comparable<CategoryEntry> {

    private final Map.Entry<String, String> data;
    private final Category.Flavour flavour;

    CategoryEntry(Map.Entry<String, String> data, Category.Flavour flavour) {
        assert data != null;
        this.data = data;
        this.flavour = flavour;
    }

    public String getEntryName() {
        assert (data != null);
        return data.getKey();
    }

    public String getEntryValue() {
        assert (data != null);
        return data.getValue();
    }

    public Category.Flavour getFlavour() {
        return flavour;
    }

    @Override
    public int compareTo(CategoryEntry o) {
        int retValue = -1;
        if (o != null) {
            retValue = getEntryName().compareTo(o.getEntryName());
        }
        return retValue;
    }
}
