/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.clocks.nodes;

import java.time.zone.ZoneRulesException;
import java.util.Collections;
import org.pr.nb.clocks.model.NBClock;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;

/**
 *
 * @author manis
 */
public class NBClocksNodeFactory extends ChildFactory<NBClock> {

    public NBClocksNodeFactory() {
    }

    @Override
    protected boolean createKeys(List<NBClock> toPopulate) {
        List<NBClockZoneInfo> allZones = NBClockNodeUtilities.getInstance().
                allZones();
        allZones.forEach(zoneInfo -> checkAndAddNBClock(zoneInfo, toPopulate));
        Collections.sort(toPopulate);
        return true;
    }
    private static final Logger LOG = Logger.getLogger(
            NBClocksNodeFactory.class.getName());

    @Override
    protected Node createNodeForKey(NBClock key) {
        return new NBClockNode(key);
    }

    private void checkAndAddNBClock(NBClockZoneInfo zoneInfo,
            List<NBClock> toPopulate) {
        try {
            NBClock clock = new NBClock(zoneInfo.getTimeZoneId());
            toPopulate.add(clock);
        } catch (ZoneRulesException e) {
            LOG.log(Level.WARNING, "No zone found with id {0}", zoneInfo.
                    getTimeZoneId());
        }
    }

}
