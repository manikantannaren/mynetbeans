/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pr.nb.suid;

import com.sun.source.tree.ClassTree;
import com.sun.source.tree.CompilationUnitTree;
import com.sun.source.tree.ExpressionTree;
import com.sun.source.tree.LiteralTree;
import com.sun.source.tree.ModifiersTree;
import com.sun.source.tree.PrimitiveTypeTree;
import com.sun.source.tree.Tree;
import com.sun.source.tree.VariableTree;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;
import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeKind;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.api.java.source.CancellableTask;
import org.netbeans.api.java.source.JavaSource;
import org.netbeans.api.java.source.ModificationResult;
import org.netbeans.api.java.source.TreeMaker;
import org.netbeans.api.java.source.WorkingCopy;
import org.netbeans.spi.editor.codegen.CodeGenerator;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;

@NbBundle.Messages({
    "GENERATOR_NAME=Generate serialVersionUID"
})
public class SUIDGenerator implements CodeGenerator {

    JTextComponent textComp;

    /**
     * 
     * @param context containing JTextComponent and possibly other items registered by {@link CodeGeneratorContextProvider}
     */
    private SUIDGenerator(Lookup context) { // Good practice is not to save Lookup outside ctor
        textComp = context.lookup(JTextComponent.class);
    }

    @MimeRegistration(mimeType="text/x-java", service=CodeGenerator.Factory.class)
    public static class Factory implements CodeGenerator.Factory {
        @Override
        public List<? extends CodeGenerator> create(Lookup context) {
            return Collections.singletonList(new SUIDGenerator(context));
        }
    }

    /**
     * The name which will be inserted inside Insert Code dialog
     */
    @Override
    public String getDisplayName() {
        return Bundle.GENERATOR_NAME();
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
            CancellableTask suidGenTask = new SUIDGenCancellableTask();
            ModificationResult result = javaSource.runModificationTask(suidGenTask);
            result.commit();
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }
    
    private class SUIDGenCancellableTask implements CancellableTask<WorkingCopy>{

        @Override
        public void cancel() {
        }

        @Override
        public void run(WorkingCopy workingCopy) throws Exception {
            workingCopy.toPhase(JavaSource.Phase.RESOLVED);
            CompilationUnitTree compilationUnitTree = workingCopy.getCompilationUnit();

            TreeMaker maker = workingCopy.getTreeMaker();
            ClassTree clazz = null;
            List<? extends Tree> treeDeclarations = compilationUnitTree.getTypeDecls();
            for (Tree treeDeclaration : treeDeclarations) {
                if (Tree.Kind.CLASS != treeDeclaration.getKind()) {
                    continue;
                }
                clazz = (ClassTree) treeDeclaration;
                break;
            }
            TreeSet<Modifier> modifiersSet = new TreeSet<>();
            modifiersSet.add(Modifier.PRIVATE);
            modifiersSet.add(Modifier.STATIC);
            modifiersSet.add(Modifier.FINAL);
            ModifiersTree modifiers = maker.Modifiers(modifiersSet);
            PrimitiveTypeTree longTypeTree = maker.PrimitiveType(TypeKind.LONG);
            LiteralTree initialValueTree = maker.Literal(new Long(0));
            VariableTree suidVarTree = maker.Variable(modifiers, (CharSequence) "serialVersionUID", longTypeTree, initialValueTree);
            ClassTree modifiedClazz = maker.insertClassMember(clazz,0, suidVarTree);
            workingCopy.rewrite(clazz, modifiedClazz);
        }
        
    }

}
