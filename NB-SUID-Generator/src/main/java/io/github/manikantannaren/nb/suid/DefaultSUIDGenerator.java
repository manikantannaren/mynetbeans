/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package io.github.manikantannaren.nb.suid;

import java.io.IOException;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import org.netbeans.api.java.source.CancellableTask;
import org.netbeans.api.java.source.JavaSource;
import org.netbeans.api.java.source.ModificationResult;
import org.netbeans.api.java.source.WorkingCopy;
import org.netbeans.spi.editor.codegen.CodeGenerator;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;

@NbBundle.Messages({
    "DEFAULT_GENERATOR_NAME=Default serialVersionUID"
})
public class DefaultSUIDGenerator implements CodeGenerator {

    JTextComponent textComp;

    /**
     * 
     * @param context containing JTextComponent and possibly other items registered by {@link CodeGeneratorContextProvider}
     */
     DefaultSUIDGenerator(Lookup context) { // Good practice is not to save Lookup outside ctor
        textComp = context.lookup(JTextComponent.class);
    }

    /**
     * The name which will be inserted inside Insert Code dialog
     */
    @Override
    public String getDisplayName() {
        return Bundle.DEFAULT_GENERATOR_NAME();
    }

    /**
     * This will be invoked when user chooses this Generator from Insert Code
     * dialog
     */
    @Override
    public void invoke() {
        try {
            Document editorDoc = textComp.getDocument();
            JavaSource javaSource = JavaSource.forDocument(editorDoc);
            if(javaSource != null){
                CancellableTask<WorkingCopy> suidGenTask = new SUIDGenCancellableTask(0L);
                ModificationResult result = javaSource.runModificationTask(suidGenTask);
                result.commit();
            }
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }
    

}
