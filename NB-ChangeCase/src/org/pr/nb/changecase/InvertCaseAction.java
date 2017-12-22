/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.changecase;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.text.JTextComponent;
import org.netbeans.api.editor.EditorRegistry;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "Source",
        id = "org.pr.nb.changecase.InvertCaseAction"
)
@ActionRegistration(
        displayName = "#CTL_InvertCase"
)
@ActionReferences({
    @ActionReference(path = "Menu/Source/Text", position = 9200),
    @ActionReference(path = "Editors/Popup/Text")

})
@Messages("CTL_InvertCase=Invert Case")
public final class InvertCaseAction implements ActionListener {

    @Override public void actionPerformed(ActionEvent e) {
        // TODO implement action body
        JTextComponent currentEditorTextComponent = EditorRegistry.
                lastFocusedComponent();
        ChangeCaseUtility.getInstance().changeCase(currentEditorTextComponent,
                ChangeCaseUtility.CHANGE_CASE_TYPE.INVERT_CASE);
    }
}
