/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.clocks.model;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author manis
 */
public class NBClock implements Comparable<NBClock> {

    private ZoneId zoneId;
    private ZonedDateTime zoneDateTime;
    private boolean homeZone;

    public NBClock(String zoneId) {
        this.zoneId = ZoneId.of(zoneId, ZoneId.SHORT_IDS);
        zoneDateTime = ZonedDateTime.now(this.zoneId);
        homeZone = StringUtils.equals(zoneId, ZoneId.systemDefault().getId());
    }

    public ZonedDateTime getZoneDateTime() {
        return zoneDateTime;
    }

    public void setZoneDateTime(ZonedDateTime zoneDateTime) {
        this.zoneDateTime = zoneDateTime;
    }

    public boolean isHomeZone() {
        return homeZone;
    }

    public void setHomeZone(boolean homeZone) {
        this.homeZone = homeZone;
    }

    public ZoneId getZoneId() {
        return zoneId;
    }

    public void setZoneId(ZoneId zoneId) {
        this.zoneId = zoneId;
    }

    @Override
    public int compareTo(NBClock o) {
        if (this.homeZone) {
            return Integer.MIN_VALUE;
        }
        return this.zoneId.getId().compareTo(o.zoneId.getId());
    }

}
