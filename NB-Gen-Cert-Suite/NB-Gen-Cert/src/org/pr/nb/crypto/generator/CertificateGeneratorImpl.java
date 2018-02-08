/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.crypto.generator;

import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.Exceptions;
import org.openide.util.lookup.ServiceProvider;
import org.pr.nb.crypto.UserInputs;

/**
 *
 * @author Mahakaal
 */
@ServiceProvider(service = CertificateGenerator.class)
public class CertificateGeneratorImpl implements CertificateGenerator {

    private static final String SUBJECT_FORMAT =
        "CN={0}, OU={1}, O={2}, C={3}, ST={4}, L={5}";

    @Override
    public FileObject generateCertificate(UserInputs inputs) {
        FileObject retValue = null;
        try {
            retValue = FileUtil.createData(inputs.getKeyStore(), inputs.getKeyStoreName());
            
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(
                "RSA","BC");
            keyPairGenerator.initialize(inputs.getKeySize());
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            
        } catch (IOException | NoSuchAlgorithmException |
            NoSuchProviderException ex) {
            Exceptions.printStackTrace(ex);
        }
        return retValue;
    }

}
