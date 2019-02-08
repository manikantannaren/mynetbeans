/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.clocks.nodes;

import java.awt.Image;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyEditor;
import java.lang.reflect.InvocationTargetException;
import java.time.ZonedDateTime;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.PropertySupport;
import org.openide.nodes.Sheet;
import org.openide.util.ImageUtilities;
import org.pr.nb.clocks.editor.NBClocksDefaultDatePropertyEditor;
import org.pr.nb.clocks.model.NBClock;
import org.pr.nb.clocks.model.NBClockZone;

/**
 *
 * @author msivasub
 */
public class NBClockNode extends AbstractNode implements PropertyChangeListener{

    private static final String FLAG_ICONS_ROOT_PATH = "org/pr/nb/clocks/nodes/flag/16/";
    private static final String BLANK_FLAG_PATH = FLAG_ICONS_ROOT_PATH
            + "blank.png";
    private final NBClock key;

    public NBClockNode(NBClock key) {
        super(Children.LEAF);
        this.key = key;
        setDisplayName(getNodeDisplayName());
        PropertiesChangeNotifier.getInstance().addPropertyChangeListener(this);
    }

    @Override
    public String getHtmlDisplayName() {
        String text = getNodeDisplayName();
        StringBuilder builder = new StringBuilder("<font color='!textText'>");
        if(key.getZone().isHomeZone()){
            builder.append("<b><i>");
        }
        builder.append(text);
        if(key.getZone().isHomeZone()){
            builder.append("</i></b>");
        }
        builder.append("</font>");
        
        return builder.toString();
    }

    @Override
    public Image getOpenedIcon(int type) {
        return getIcon(type);
    }

    @Override
    public Image getIcon(int type) {

        String imageName = key.getZone().getCode().toLowerCase() + ".png";
        Image img = ImageUtilities.loadImage(FLAG_ICONS_ROOT_PATH + imageName, false);
        if (img == null) {
            img = ImageUtilities.loadImage(BLANK_FLAG_PATH, false);
        }
        return img;
    }

    @Override
    protected Sheet createSheet() {
        Sheet sheet = super.createSheet();
        Sheet.Set propSet = new Sheet.Set();
        sheet.put(propSet);
        PropertySupport.ReadOnly<String> displayNameProp = new PropertySupport.ReadOnly<String>("displayName", String.class, "Display Name", "Human readanle name of clock") {
            @Override

            public String getValue() throws IllegalAccessException, InvocationTargetException {
                return getNodeDisplayName();
            }
        };
        propSet.put(displayNameProp);

        PropertySupport.ReadOnly<String> twoLetterCode = new PropertySupport.ReadOnly<String>("code", String.class, "ISO3166 2 Letter Code", "ISO3166 Two letter code for zone") {
            @Override

            public String getValue() throws IllegalAccessException, InvocationTargetException {
                return key.getZone().getCode();
            }
        };
        propSet.put(twoLetterCode);

        PropertySupport.ReadOnly<String> zoneId = new PropertySupport.ReadOnly<String>("zoneId", String.class, "Zone ID", "ISO3166 Zone Id for time zone") {
            @Override

            public String getValue() throws IllegalAccessException, InvocationTargetException {
                return key.getZone().getZone().getId();
            }
        };
        propSet.put(zoneId);

        PropertySupport.ReadOnly<Boolean> homeZone = new PropertySupport.ReadOnly<Boolean>("homeZone", Boolean.class, "Home Zone", "Is this zone the system zone") {
            @Override

            public Boolean getValue() throws IllegalAccessException, InvocationTargetException {
                return key.getZone().isHomeZone();
            }
        };
        propSet.put(homeZone);
        
        PropertySupport.ReadWrite<ZonedDateTime> currentDateTime = new PropertySupport.ReadWrite<ZonedDateTime>("dateTime", ZonedDateTime.class, "Date/Time", "Current date and time in zone") {
            PropertyEditor editor = new NBClocksDefaultDatePropertyEditor();
            @Override
            public ZonedDateTime getValue() throws IllegalAccessException, InvocationTargetException {
                ZonedDateTime dt = key.getTime();
                return dt;
            }

            @Override
            public void setValue(ZonedDateTime val) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
                ZonedDateTime oldValue = key.getTime();
                key.setZoneDateTime(val);
                PropertiesChangeNotifier.getInstance().changed(key, oldValue);
            }

            @Override
            public PropertyEditor getPropertyEditor() {
                return editor;
            }
            

        };

        propSet.put(currentDateTime);
        return sheet;
    }

    private String getNodeDisplayName() {
        NBClockZone zone = key.getZone();

        return zone.getZone().getId() + "-" + zone.getName();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(!evt.getPropertyName().equals("dateTime")){
            return;
        }
        
        ZonedDateTime newTime = (ZonedDateTime) evt.getNewValue();
        key.setZoneDateTime(newTime);
        firePropertyChange("dateTime", evt.getOldValue(), newTime);
    }
    

}