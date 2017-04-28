/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.clocks.nodes;

import java.awt.Image;
import java.io.File;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.openide.modules.InstalledFileLocator;
import org.openide.util.Exceptions;
import org.openide.util.ImageUtilities;

/**
 *
 * @author manis
 */
public class NBClockNodeUtilities {

    private static final Logger LOG = Logger.getLogger(
            NBClockNodeUtilities.class.getName());
    private static final String TZ_FILE = "tz.xml";

    private static final String FLAG_ICONS_ROOT_PATH = "org/pr/nb/clocks/nodes/flag/16";
    private static final String BLANK_FLAG_PATH = FLAG_ICONS_ROOT_PATH
            + "/blank.png";
    Map<String, NBClockZoneInfo> allZones;

    private NBClockNodeUtilities() {
        InstalledFileLocator fileLocator = InstalledFileLocator.getDefault();
        allZones = new WeakHashMap<>();

        try {
            //parse tz.xml and populate the map
            JAXBContext context = JAXBContext.newInstance(TZRootElement.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            final File file = fileLocator.locate(TZ_FILE, "org.pr.nb.clocks",
                    false);
            TZRootElement rootElement = (TZRootElement) unmarshaller.unmarshal(
                    file);
            rootElement.timeZones.forEach(zone -> allZones.put(zone.
                    getTimeZoneId(), zone));
        } catch (JAXBException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    public static NBClockNodeUtilities getInstance() {
        return NBClockNodeUtilitiesHolder.INSTANCE;
    }

    NBClockZoneInfo getZoneInfo(ZoneId zone) {
        if (!allZones.containsKey(zone.getId())) {
            LOG.log(Level.WARNING, "No zone info found for {0}", zone.getId());
            NBClockZoneInfo zoneInfo = new NBClockZoneInfo();
            zoneInfo.setIso3166Name(zone.getId());
            zoneInfo.setTimeZoneId(zone.getId());
            zoneInfo.setIso31662LetterCode("");
            allZones.put(zone.getId(), zoneInfo);
        }
        return allZones.get(zone.getId());
    }

    List<NBClockZoneInfo> allZones() {
        return new ArrayList(allZones.values());
    }

    Image getFlagIcon(NBClockZoneInfo zoneInfo) {
        String iconPath = FLAG_ICONS_ROOT_PATH + "/" + zoneInfo.
                getIso31662LetterCode().toLowerCase() + ".png";
        Image img = ImageUtilities.loadImage(iconPath, false);
        if (img == null) {
            img = ImageUtilities.loadImage(BLANK_FLAG_PATH, false);
        }
        return img;
    }

    private static class NBClockNodeUtilitiesHolder {

        private static final NBClockNodeUtilities INSTANCE = new NBClockNodeUtilities();
    }

    @XmlRootElement(name = "zones")
    @XmlAccessorType(XmlAccessType.FIELD)
    private static class TZRootElement {

        @XmlElement(name = "zone")
        private List<NBClockZoneInfo> timeZones = new ArrayList<>();

        public List<NBClockZoneInfo> getTimeZones() {
            return timeZones;
        }

        public void setTimeZones(List<NBClockZoneInfo> timeZones) {
            this.timeZones = timeZones;
        }

    }
}
