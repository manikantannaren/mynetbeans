/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.clocks.nodes;

import java.awt.Image;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.pr.nb.clocks.model.NBClock;

/**
 *
 * @author manis
 */
public class NBClockNode extends AbstractNode {

    NBClock data;
    NBClockZoneInfo zoneInfo;

    public NBClockNode(NBClock key) {
        super(Children.LEAF);
        this.data = key;
        zoneInfo = NBClockNodeUtilities.getInstance().getZoneInfo(data.
                getZoneId());
        setDisplayName(zoneInfo.getTimeZoneId() + "-" + zoneInfo.
                getIso3166Name());
    }

    @Override
    public Image getIcon(int type) {
        return NBClockNodeUtilities.getInstance().getFlagIcon(zoneInfo);
    }

}
