/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.manikantannaren.nb.suid;

import java.util.Arrays;
import java.util.List;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.spi.editor.codegen.CodeGenerator;
import org.openide.util.Lookup;

/**
 *
 * @author msivasub
 */
@MimeRegistration(mimeType = "text/x-java", service = CodeGenerator.Factory.class)
public class SUIDCodeGeneratorsFactory implements CodeGenerator.Factory {
    
    @Override
    public List<? extends CodeGenerator> create(Lookup context) {
        return Arrays.asList(new DefaultSUIDGenerator(context), new RandomSUIDGenerator(context));
        
    }
    
}
