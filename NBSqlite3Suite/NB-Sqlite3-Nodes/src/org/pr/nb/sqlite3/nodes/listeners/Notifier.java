/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.sqlite3.nodes.listeners;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 *
 * @author msivasub
 */
public class Notifier {
    
    private final PropertyChangeSupport changeSupport;
    private Notifier() {
        changeSupport = new PropertyChangeSupport(this);
    }
    
    public void addPropertyChangeListener(PropertyChangeListener l){
        changeSupport.addPropertyChangeListener(l);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener l){
        changeSupport.removePropertyChangeListener(l);
    }
    
    public void dispatchEvent(NBSQliteEventType type, Object source, Object oldValue, Object newValue ){
        changeSupport.firePropertyChange(new PropertyChangeEvent(source, type.name(), oldValue, newValue));
    }
    
    public static Notifier getInstance() {
        return NotifierHolder.INSTANCE;
    }
    
    private static class NotifierHolder {

        private static final Notifier INSTANCE = new Notifier();
    }
}
