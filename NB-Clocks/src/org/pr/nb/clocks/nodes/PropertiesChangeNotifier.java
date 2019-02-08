/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.clocks.nodes;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.ZonedDateTime;
import org.pr.nb.clocks.model.NBClock;



/**
 *
 * @author msivasub
 */
public class PropertiesChangeNotifier {
    private final PropertyChangeSupport changeSupport;
    
    private PropertiesChangeNotifier() {
        changeSupport = new PropertyChangeSupport(this);
    }
    
    public void addPropertyChangeListener(PropertyChangeListener l){
        changeSupport.addPropertyChangeListener(l);
    }
    public void removePropertyChangeListener(PropertyChangeListener l){
        changeSupport.removePropertyChangeListener(l);
    }
    public void changed(NBClock clock, ZonedDateTime oldTime){
        PropertyChangeEvent event = new PropertyChangeEvent(clock, "dateTime", oldTime, clock.getTime());
        changeSupport.firePropertyChange(event);
    }
    public static PropertiesChangeNotifier getInstance() {
        return PropertiesChangeNotifierHolder.INSTANCE;
    }
    
    private static class PropertiesChangeNotifierHolder {

        private static final PropertiesChangeNotifier INSTANCE = new PropertiesChangeNotifier();
    }
}
