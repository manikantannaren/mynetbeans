/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.clocks.nodes;

/**
 *
 * @author manis
 */
public class NBClockZoneInfo {

    private String iso3166Name;
    private String iso31662LetterCode;
    private String timeZoneId;

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

}
