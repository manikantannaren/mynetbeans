/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.clocks.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author msivasub
 */
public class NBClock implements Comparable<NBClock> {

    private ZonedDateTime zoneDateTime;
    private final NBClockZone zone;
    private final List<PropertyChangeListener> listeners = new ArrayList<>();

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        listeners.add(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        listeners.remove(pcl);
    }

    public NBClock(NBClockZone zone) {
        this.zone = zone;
        zoneDateTime = ZonedDateTime.now(zone.getZone());
    }

    public ZonedDateTime getTime() {
        return zoneDateTime;
    }

    public void setZoneDateTime(ZonedDateTime zoneDateTime) {
        ZonedDateTime old = this.zoneDateTime;
        this.zoneDateTime = zoneDateTime.withZoneSameInstant(old.getZone());
//        this.zoneDateTime = zoneDateTime;
        fire("dateTime", old, this.zoneDateTime);
    }

    public NBClockZone getZone() {
        return zone;
    }

    public void changeTime(Date dt) {
        setZoneDateTime(ZonedDateTime.ofInstant(dt.toInstant(), getZone().getZone()));
    }

    @Override
    public int compareTo(NBClock o) {
        return this.zone.compareTo(o.zone);
    }

    private void fire(String propertyName, Object old, Object nue) {
        //Passing 0 below on purpose, so you only synchronize for one atomic call:
        listeners.forEach((listener) -> {
            listener.propertyChange(new PropertyChangeEvent(this, propertyName, old, nue));
        });
    }

    @Override
    public String toString() {
        return "NBClock{" + "zone=" + zone + '}';
    }
    
}
