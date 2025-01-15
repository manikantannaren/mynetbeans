/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.manikantannaren.nb.sysprops.nodes.actions;

import io.github.manikantannaren.nb.sysprops.nodes.CategoryEntryNode;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.swing.AbstractAction;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;
import org.openide.util.datatransfer.ExClipboard;

/**
 *
 * @author Mahakaal
 */
@NbBundle.Messages({
    "copyvalueaction.label=Copy value"
})
public class CopyValueAction extends AbstractAction{

    private CategoryEntryNode context;

    public CopyValueAction(CategoryEntryNode context) {
        this.context = context;
        putValue(NAME, Bundle.copyvalueaction_label());
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Clipboard clipBoard = Lookup.getDefault().lookup(ExClipboard.class);
        if(clipBoard == null){
            clipBoard = Toolkit.getDefaultToolkit().getSystemClipboard();
        }
        System.out.println(clipBoard);
        if(clipBoard != null){
            try {
                clipBoard.setContents(context.clipboardCopyOnlyValue(), null);
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
    }
    
}
