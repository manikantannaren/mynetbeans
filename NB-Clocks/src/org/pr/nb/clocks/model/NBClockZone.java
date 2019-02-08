/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.clocks.model;

import java.time.ZoneId;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author msivasub
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class NBClockZone implements Comparable<NBClockZone>{

    @XmlElement(required = true, name = "timeZoneId")
    @XmlJavaTypeAdapter(ZoneIdAdapter.class)
    private ZoneId zone;
    @XmlElement(required = true, name = "iso31662LetterCode")
    private String code;
    @XmlElement(required = true, name = "iso3166Name")
    private String name;

    public ZoneId getZone() {
        return zone;
    }

    public void setZone(ZoneId zone) {
        this.zone = zone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isHomeZone() {
        return ZoneId.systemDefault().getId().equals(zone.getId());
    }

    @Override
    public int compareTo(NBClockZone o) {
        return zone.getId().compareTo(o.getZone().getId());
    }

    @Override
    public String toString() {
        return "NBClockZone{" + "zone=" + zone + ", code=" + code + ", name=" + name + ", homeZone=" + isHomeZone() + '}';
    }
   
    private static  class ZoneIdAdapter extends XmlAdapter<String, ZoneId> {
        
        @Override
        public ZoneId unmarshal(String zoneIdString) throws Exception {
            ZoneId retValue = ZoneId.of(zoneIdString);
            return  retValue;
        }

        @Override
        public String marshal(ZoneId v) throws Exception {
            return v != null ? v.getId() : "";
        }

    }

}
