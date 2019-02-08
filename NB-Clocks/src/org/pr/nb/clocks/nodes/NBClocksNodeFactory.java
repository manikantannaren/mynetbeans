/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.clocks.nodes;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.openide.modules.InstalledFileLocator;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.pr.nb.clocks.model.NBClock;
import org.pr.nb.clocks.model.NBClockZone;

/**
 *
 * @author msivasub
 */
public class NBClocksNodeFactory extends ChildFactory<NBClock> {

    private static final String TZ_FILE = "tz.xml";

    @Override
    protected boolean createKeys(List<NBClock> toPopulate) {
        populateFromDB(toPopulate);
        return true;
    }

    @Override
    protected Node createNodeForKey(NBClock key) {
        return new NBClockNode(key);
    }

    private void populateFromDB(List<NBClock> toPopulate) {
        InstalledFileLocator fileLocator = InstalledFileLocator.getDefault();
        File tzFile = fileLocator.locate(TZ_FILE, "org.pr.nb.clocks", false);

        try {
            JAXBContext context = JAXBContext.newInstance(TZRootElement.class);
            TZRootElement rootElement = (TZRootElement) context.createUnmarshaller().unmarshal(tzFile);
            List<NBClockZone> results = rootElement.getTimeZones();
            results.forEach(nbclockZone -> checkAndAddZoneClock(toPopulate, nbclockZone));
            Collections.sort(toPopulate);
            bubbleUpHomeZoneClock(toPopulate);
        } catch (JAXBException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    private void checkAndAddZoneClock(List<NBClock> toPopulate, NBClockZone nbclockZone) {
        if (Objects.nonNull(nbclockZone.getZone())) {
            toPopulate.add(new NBClock(nbclockZone));
        }else{
            Logger.getLogger(NBClocksNodeFactory.class.getName()).log(Level.WARNING, "Could not find zone for {0}",nbclockZone);
        }
    }

    private void bubbleUpHomeZoneClock(List<NBClock> toPopulate) {
        NBClock homeZoneClock = toPopulate.stream().filter(clock->clock.getZone().isHomeZone()).findFirst().orElse(null);
        if(homeZoneClock != null )
            toPopulate.remove(homeZoneClock);
        toPopulate.add(0,homeZoneClock);
    }

    @XmlRootElement(name = "zones")
    @XmlAccessorType(XmlAccessType.FIELD)
    private static class TZRootElement {

        @XmlElement(name = "zone")
        private List<NBClockZone> timeZones = new ArrayList<>();

        public List<NBClockZone> getTimeZones() {
            return timeZones;
        }

        public void setTimeZones(List<NBClockZone> timeZones) {
            this.timeZones = timeZones;
        }

    }

}
