/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.clocks.editor;

import java.beans.PropertyEditorSupport;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author msivasub
 */
public class NBClocksDefaultDatePropertyEditor extends PropertyEditorSupport{

    private static final DateTimeFormatter OUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter IN_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm VV");

    
    @Override
    public String getAsText() {
        ZonedDateTime dt = (ZonedDateTime) getValue();
        return dt.format(OUT_FORMATTER);
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        text += " "+((ZonedDateTime)getValue()).getZone().getId();
        setValue(ZonedDateTime.parse(text, IN_FORMATTER));
    }

    
}
