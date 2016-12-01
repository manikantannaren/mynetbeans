/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.crypto;

import org.openide.filesystems.FileObject;

/**
 *
 * @author Mahakaal
 */
public class UserInputs {
    private String alias;
    private char[] storePass;
    private char[] keyPass;
    private Integer keySize;
    private FileObject keyStore;
    private String keyStoreName;
    private String cn;
    private String ou;
    private String o;
    private String c;
    private String st;
    private String l;
    
    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Integer getKeySize() {
        return keySize;
    }

    public void setKeySize(Integer keySize) {
        this.keySize = keySize;
    }

    public FileObject getKeyStore() {
        return keyStore;
    }

    public void setKeyStore(FileObject keyStore) {
        this.keyStore = keyStore;
    }

    public char[] getStorePass() {
        return storePass;
    }

    public void setStorePass(char[] storePass) {
        this.storePass = storePass;
    }

    public char[] getKeyPass() {
        return keyPass;
    }

    public void setKeyPass(char[] keyPass) {
        this.keyPass = keyPass;
    }

    public String getKeyStoreName() {
        return keyStoreName;
    }

    public void setKeyStoreName(String keyStoreName) {
        this.keyStoreName = keyStoreName;
    }

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    public String getOu() {
        return ou;
    }

    public void setOu(String ou) {
        this.ou = ou;
    }

    public String getO() {
        return o;
    }

    public void setO(String o) {
        this.o = o;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getSt() {
        return st;
    }

    public void setSt(String st) {
        this.st = st;
    }

    public String getL() {
        return l;
    }

    public void setL(String l) {
        this.l = l;
    }
}
