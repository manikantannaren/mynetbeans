/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.sysprops;

import java.util.Comparator;

/**
 *
 * @author Kaiser
 */
class PropertiesTableDataObjectComparator implements Comparator<PropertiesTableDataObject>{

    public PropertiesTableDataObjectComparator() {
    }

    @Override
    public int compare(PropertiesTableDataObject o1, PropertiesTableDataObject o2) {
        assert o1 != null;
        assert o2 != null;
        int retValue = -1;
        if(o1.getFlavour() == o2.getFlavour()){
            retValue = o1.getData().getKey().compareTo(o2.getData().getKey());
        }else{
            retValue = -1*(o1.getFlavour().compareTo(o2.getFlavour()));
        }
        return retValue;
    }
    
}
