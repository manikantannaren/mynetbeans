/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.crypto.generator;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import org.openide.filesystems.FileObject;
import org.openide.util.Exceptions;
import org.openide.util.lookup.ServiceProvider;
import org.pr.nb.crypto.UserInputs;

/**
 *
 * @author Mahakaal
 */
@ServiceProvider(service = CertificateGenerator.class) 
public class CertificateGeneratorImpl implements CertificateGenerator{

    private static final String SUBJECT_FORMAT="CN={0}, OU={1}, O={2}, C={3}, ST={4}, L={5}";
    @Override
    public FileObject generateCertificate(UserInputs inputs) throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(inputs.getKeySize(), new SecureRandom());
        KeyPair pair = keyGen.generateKeyPair();
       
        V3TBSCertificateGenerator 
        
    }
    
}
