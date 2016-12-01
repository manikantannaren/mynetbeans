/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.crypto.generator;

import org.openide.filesystems.FileObject;
import org.openide.util.lookup.ServiceProvider;
import org.pr.nb.crypto.UserInputs;

/**
 *
 * @author Mahakaal
 */
@ServiceProvider(service = CertificateGenerator.class) 
public class CertificateGeneratorImpl implements CertificateGenerator{

    @Override
    public FileObject generateCertificate(UserInputs inputs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
