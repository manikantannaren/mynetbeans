/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.crypto;

import java.util.Objects;
import javax.swing.JTextField;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;

/**
 *
 * @author Mahakaal
 */
public class NBFileObjectTextField extends JTextField {
    private FileObject value;
    public NBFileObjectTextField(){
        super();
    }

    public FileObject getValue() {
        return value;
    }

    public void setValue(FileObject value) {
        this.value = value;
        if(Objects.nonNull(value)){
            setText(FileUtil.getFileDisplayName(value));
        }
    }
}
