/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.zip.wizard;

import org.pr.nb.zip.ArchiverUserSelections;

/**
 *
 * @author Kaiser
 */
public interface ComponentMessagingInterface {
   public void setValue(ArchiverUserSelections selections);
   public ArchiverUserSelections getValue();
   public Boolean isPanelValid();
}
