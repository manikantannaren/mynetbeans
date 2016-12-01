/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.crypto.generator;

import org.openide.filesystems.FileObject;
import org.pr.nb.crypto.UserInputs;

/**
 *
 * @author Mahakaal
 */
public interface CertificateGenerator {
    public FileObject generateCertificate(UserInputs inputs);
}
