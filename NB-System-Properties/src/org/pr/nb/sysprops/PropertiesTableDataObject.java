/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.sysprops;

import java.io.Serializable;
import java.util.Map;

/**
 *
 * @author Kaiser
 */
public class PropertiesTableDataObject implements Serializable {

    private Map.Entry<String, String> data;

    public enum Flavour {

        PROP, ENV
    }
    private Flavour flavour = Flavour.PROP;

    public enum DataDescriptor {

        NAME, VALUE
    }

    private DataDescriptor descriptor;

    public PropertiesTableDataObject(Map.Entry<String, String> data, Flavour flavour) {
        assert data != null;
        this.data = data;
        this.flavour = flavour;
    }

    public Map.Entry<String, String> getData() {
        return data;
    }

    public DataDescriptor getDescriptor() {
        return descriptor;
    }

    public void setDescriptor(DataDescriptor descriptor) {
        this.descriptor = descriptor;
    }

    
    public Flavour getFlavour() {
        return flavour;
    }
}
