/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pr.nb.suid;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import javax.tools.FileObject;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.api.java.source.CancellableTask;
import org.netbeans.api.java.source.CompilationController;
import org.netbeans.api.java.source.ElementHandle;
import org.netbeans.api.java.source.JavaSource;
import org.netbeans.api.java.source.ModificationResult;
import org.netbeans.api.java.source.Task;
import org.netbeans.api.java.source.WorkingCopy;
import org.netbeans.spi.editor.codegen.CodeGenerator;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;

@NbBundle.Messages({
    "OBJECTSTREAM_GENERATOR_NAME=Generate serialVersionUID"
})
public class ObjectStreamSUIDGenerator implements CodeGenerator {

    JTextComponent textComp;
    FileObject classFileObject;

    /**
     * 
     * @param context containing JTextComponent and possibly other items registered by {@link CodeGeneratorContextProvider}
     */
    private ObjectStreamSUIDGenerator(Lookup context) { // Good practice is not to save Lookup outside ctor
        textComp = context.lookup(JTextComponent.class);
        
        this.classFileObject = context.lookup(FileObject.class);
    }

    private String constructClassFQN(Element classElement, Element packageElement) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @MimeRegistration(mimeType="text/x-java", service=CodeGenerator.Factory.class)
    public static class Factory implements CodeGenerator.Factory {
        @Override
        public List<? extends CodeGenerator> create(Lookup context) {
            return Collections.singletonList(new ObjectStreamSUIDGenerator(context));
        }
    }

    /**
     * The name which will be inserted inside Insert Code dialog
     */
    @Override
    public String getDisplayName() {
        return Bundle.OBJECTSTREAM_GENERATOR_NAME();
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
                String fqn = getClassFQN(javaSource);
                CancellableTask<WorkingCopy> suidGenTask = new SUIDGenCancellableTask(null, fqn);
                ModificationResult result = javaSource.runModificationTask(suidGenTask);
                result.commit();
            }
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }
    
    private String getClassFQN(JavaSource source) throws IOException{
        List<ElementHandle<Element>> retValue = new ArrayList<>();
        
        if(source != null){
            source.runUserActionTask(new Task<CompilationController>() {
                @Override
                public void run(CompilationController cc) throws Exception {
                    cc.toPhase(JavaSource.Phase.ELEMENTS_RESOLVED);
                    List<? extends TypeElement> topLevelElements = cc.getTopLevelElements();
                    Element classElement = topLevelElements.stream().filter(element->element.getKind() == ElementKind.CLASS).findFirst().orElse(null);
                    if(classElement != null){
                        ElementHandle<Element> handle = ElementHandle.create(classElement);
                        retValue.add(handle);
                    }
                }
            }, true);
        }
        return retValue.isEmpty()?null:retValue.get(0).getQualifiedName();
    }
}
