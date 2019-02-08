/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.xml;

import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author msivasub
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Zone {

    @XmlElement(required = true)
    protected String iso3166Name;
    @XmlElement(required = true)
    protected String iso31662LetterCode;
    @XmlElement(required = true)
    protected String timeZoneId;

    public Zone() {
    }

    public Zone(String iso3166Name, String iso31662LetterCode, String timeZoneId) {
        this.iso3166Name = iso3166Name;
        this.iso31662LetterCode = iso31662LetterCode;
        this.timeZoneId = timeZoneId;
    }
    
    public String getIso3166Name() {
        return iso3166Name;
    }

    public void setIso3166Name(String iso3166Name) {
        this.iso3166Name = iso3166Name;
    }

    public String getIso31662LetterCode() {
        return iso31662LetterCode;
    }

    public void setIso31662LetterCode(String iso31662LetterCode) {
        this.iso31662LetterCode = iso31662LetterCode;
    }

    public String getTimeZoneId() {
        return timeZoneId;
    }

    public void setTimeZoneId(String timeZoneId) {
        this.timeZoneId = timeZoneId;
    }
    public boolean isOk(){
        return !(Objects.toString(iso31662LetterCode).equals("") && Objects.toString(iso3166Name).equals(""));
    }
    public boolean isAllOk(){
        return isOk() && !(Objects.toString(timeZoneId).equals(""));
    }
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.iso3166Name);
        hash = 37 * hash + Objects.hashCode(this.iso31662LetterCode);
        hash = 37 * hash + Objects.hashCode(this.timeZoneId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Zone other = (Zone) obj;
        if (!Objects.equals(this.iso3166Name, other.iso3166Name)) {
            return false;
        }
        if (!Objects.equals(this.iso31662LetterCode, other.iso31662LetterCode)) {
            return false;
        }
        if (!Objects.equals(this.timeZoneId, other.timeZoneId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Zone{" + "iso3166Name=" + iso3166Name + ", iso31662LetterCode=" + iso31662LetterCode + ", timeZoneId=" + timeZoneId + '}';
    }

}