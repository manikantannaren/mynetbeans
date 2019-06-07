/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.sqlite3.nodes.newtypes.wizard;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import javax.swing.JPanel;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileFilter;
import org.apache.commons.lang.StringUtils;
import org.openide.filesystems.FileChooserBuilder;
import org.openide.filesystems.FileUtil;
import org.openide.util.ChangeSupport;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle;
import org.pr.nb.sqlite3.common.NBSqlite3Object;
import org.pr.nb.sqlite3.jdbc.Sqlite3DB;
import org.pr.nb.sqlite3.nodes.NBSqlite3DBInstanceFactory;

@NbBundle.Messages({
    "# {0} - Step number in wizard",
    "# {1} - Name of the step in the wizard",
    "LBL_STEP_NAME=Step #{0}:  {1}",
    "LBL_FILE_CHOOSER_FILTER_DESC=SQLite3 DB files",
    "LBL_STEP_NAME1=Choose Sqlite3 DB file"
})
public final class NBSQlite3NewTypeVisualPanel1 extends JPanel{

    
    /**
     * Creates new form NBSQlite3NewTypeVisualPanel1
     */
    public NBSQlite3NewTypeVisualPanel1() {
        initComponents();
    }

    @Override
    public String getName() {
        return Bundle.LBL_STEP_NAME(1, Bundle.LBL_STEP_NAME1());
    }


    public void addChangeListener(ChangeListener l){
        changeSupport.addChangeListener(l);
    }
    public void removeChangeListener(ChangeListener l){
        changeSupport.removeChangeListener(l);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        dbPathTextField = new javax.swing.JTextField();
        dbNameTextField = new javax.swing.JTextField();
        fileChooserButton = new javax.swing.JButton();

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(NBSQlite3NewTypeVisualPanel1.class, "NBSQlite3NewTypeVisualPanel1.jLabel1.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(NBSQlite3NewTypeVisualPanel1.class, "NBSQlite3NewTypeVisualPanel1.jLabel2.text")); // NOI18N

        dbPathTextField.setEditable(false);
        dbPathTextField.setText(org.openide.util.NbBundle.getMessage(NBSQlite3NewTypeVisualPanel1.class, "NBSQlite3NewTypeVisualPanel1.dbPathTextField.text")); // NOI18N

        dbNameTextField.setText(org.openide.util.NbBundle.getMessage(NBSQlite3NewTypeVisualPanel1.class, "NBSQlite3NewTypeVisualPanel1.dbNameTextField.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(fileChooserButton, "...");
        fileChooserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileChooserButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dbNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dbPathTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fileChooserButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {dbNameTextField, dbPathTextField});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel1, jLabel2});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dbPathTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fileChooserButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dbNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {dbNameTextField, jLabel1, jLabel2});

    }// </editor-fold>//GEN-END:initComponents

    private void fileChooserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileChooserButtonActionPerformed
        FileChooserBuilder builder = new FileChooserBuilder(getClass());
        builder.setAcceptAllFileFilterUsed(true);
        builder.addFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                return StringUtils.endsWithAny(f.getName(), new String[]{"db","DB","dB","Db"});
            }

            @Override
            public String getDescription() {
                return Bundle.LBL_FILE_CHOOSER_FILTER_DESC();
            }
        });
        builder.setFilesOnly(true);
        File _selFile = builder.showSaveDialog();
        
        if(_selFile !=null){
            this.selFile = _selFile;
            if(!selFile.exists()){
                try {
                    selFile.createNewFile();
                } catch (IOException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
            this.dbPathTextField.setText(FileUtil.toFileObject(FileUtil.normalizeFile(selFile)).getPath());
            changeSupport.fireChange();
        }
    }//GEN-LAST:event_fileChooserButtonActionPerformed

    public Sqlite3DB getData() {
        if(selFile == null ) return null;
        return NBSqlite3DBInstanceFactory.getInstance().fromUserInput(dbNameTextField.getText(), FileUtil.toFileObject(FileUtil.normalizeFile(selFile)).getPath());
    }

    public void setData(Sqlite3DB data) {
        if(data == null) return;
        
        this.dbNameTextField.setText(Objects.toString(data.getName(), ""));
        if(StringUtils.isNotEmpty(data.getDbPath())){
            selFile = new File(FileUtil.normalizePath(data.getDbPath()));
        }
        
    }

    
    public boolean isPanelValid(){
        return selFile != null;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField dbNameTextField;
    private javax.swing.JTextField dbPathTextField;
    private javax.swing.JButton fileChooserButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables

    //Non - Desginer fields
    private File selFile;
    private ChangeSupport changeSupport = new ChangeSupport(this);

}
