/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.sysprops.nodes.actions;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.swing.AbstractAction;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;
import org.openide.util.datatransfer.ExClipboard;
import org.pr.nb.sysprops.nodes.CategoryEntryNode;

/**
 *
 * @author Mahakaal
 */
@NbBundle.Messages({
    "copynameaction.label=Copy name"
})
public class CopyNameAction extends AbstractAction {

    CategoryEntryNode context;
    
    public CopyNameAction(CategoryEntryNode context) {
        putValue(NAME, Bundle.copynameaction_label());
        this.context = context;
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        Clipboard clipBoard = Lookup.getDefault().lookup(ExClipboard.class);
        if(clipBoard == null){
            clipBoard = Toolkit.getDefaultToolkit().getSystemClipboard();
        }
        if(clipBoard != null){
            try {
                clipBoard.setContents(context.clipboardCopyOnlyName(), null);
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
    }
    
}
