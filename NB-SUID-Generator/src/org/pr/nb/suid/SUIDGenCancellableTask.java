/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.suid;

import com.sun.source.tree.ClassTree;
import com.sun.source.tree.CompilationUnitTree;
import com.sun.source.tree.LiteralTree;
import com.sun.source.tree.ModifiersTree;
import com.sun.source.tree.PrimitiveTypeTree;
import com.sun.source.tree.Tree;
import com.sun.source.tree.VariableTree;
import java.io.ObjectStreamClass;
import java.util.List;
import java.util.TreeSet;
import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeKind;
import org.netbeans.api.java.classpath.ClassPath;
import org.netbeans.api.java.source.CancellableTask;
import org.netbeans.api.java.source.ClasspathInfo;
import org.netbeans.api.java.source.JavaSource;
import org.netbeans.api.java.source.TreeMaker;
import org.netbeans.api.java.source.WorkingCopy;
import org.openide.util.Exceptions;

/**
 *
 * @author manis
 */
class SUIDGenCancellableTask implements CancellableTask<WorkingCopy> {

    Long suidToCreate;
    String fqn;

    SUIDGenCancellableTask(Long suidToCreate, String fqn) {
        this.suidToCreate = suidToCreate;
        this.fqn = fqn;
    }

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
        LiteralTree initialValueTree = maker.Literal(getSuid(workingCopy));
        VariableTree suidVarTree = maker.Variable(modifiers, (CharSequence) "serialVersionUID", longTypeTree, initialValueTree);
        ClassTree modifiedClazz = maker.insertClassMember(clazz, 0, suidVarTree);
        workingCopy.rewrite(clazz, modifiedClazz);
    }

    private Long getSuid(WorkingCopy workingCopy) {
        Long retValue = 0L;
        if (fqn != null && !fqn.isEmpty()) {
            try {
                ClassPath cp = ClassPath.getClassPath(workingCopy.getFileObject(), "COMPILE");
                Class clazz = cp.getClassLoader(true).loadClass(fqn);
//                Class clazz = Class.forName(fqn);
                retValue = ObjectStreamClass.lookup(clazz).getSerialVersionUID();
            } catch (Exception e) {
                Exceptions.printStackTrace(e);
                retValue = 0L;
            }
        }
        return retValue;
    }

}
