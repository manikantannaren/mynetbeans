/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.sqlite3.nodes.listeners;

import java.util.Arrays;

/**
 *
 * @author msivasub
 */
public enum NBSQliteEventType {
    ADD_INSTANCE,
    DELETE_INSTANCE,
    REFRESH;
    
    public static NBSQliteEventType fromName(String name){
        return Arrays.stream(NBSQliteEventType.values()).filter(type->type.name().equalsIgnoreCase(name)).findFirst().orElse(null);
    }
}
