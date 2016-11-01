/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.sysprops.nodes;

import java.awt.Component;
import java.beans.PropertyEditorSupport;
import java.util.Objects;

/**
 *
 * @author Mahakaal
 */
public class SysProEditor extends PropertyEditorSupport {
    
    public SysProEditor() {
        super();
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(text);
    }

    @Override
    public String getAsText() {
        return Objects.toString(getValue());
    }

    @Override
    public boolean supportsCustomEditor() {
        return true;
    }

    @Override
    public Component getCustomEditor() {
        return new SplitRenderer(getAsText());
    }
    
}
