/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.xml;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author msivasub
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Zones {

    @XmlElement(name="zone")
    protected List<Zone> zones;
    public List<Zone> getZone() {
        if (zones == null) {
            zones = new ArrayList<>();
        }
        return zones;
    }
    
    public void addZone(Zone zone){
        if(!getZone().contains(zone)){
            getZone().add(zone);
        }
    }
    public Zone getZone(String iso31662LetterCode){
        return getZone().stream().filter(zone->zone.getIso31662LetterCode().equals(iso31662LetterCode)).findFirst().orElse(null);
    }
}
