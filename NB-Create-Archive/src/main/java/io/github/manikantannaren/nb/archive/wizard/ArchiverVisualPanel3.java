/*
 * Copyright 2015 Manikantan Narender Nath.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.manikantannaren.nb.archive.wizard;

import javax.swing.JPanel;
import org.openide.filesystems.FileUtil;
import org.openide.util.NbBundle;
import io.github.manikantannaren.nb.archive.ArchiverUserSelections;

@NbBundle.Messages({
    "ExportZipVisualPanel3.name=Summary",
    "ExportZipVisualPanel3.jlabel4.text=Selected contents"
})
public final class ArchiverVisualPanel3 extends JPanel implements ComponentMessagingInterface{
    private ArchiverUserSelections selections;

    /**
     * Creates new form ExportZipVisualPanel3
     */
    public ArchiverVisualPanel3() {
        initComponents();
    }

    @Override
    public String getName() {
        return Bundle.ExportZipVisualPanel3_name();
    }
    @Override
    public void setValue(ArchiverUserSelections selections) {
        if(selections != null){
            this.selections = selections;
            fileNameTextField.setText(selections.getDestinationZipName()+"."+selections.getExtension());
            destinationTextField.setText(FileUtil.getFileDisplayName(selections.getDestinationDirectory()));
            ArchiverListModel model = new ArchiverListModel(selections.getUserSelectedFilesInWizard());
            selectedContentsList.setModel(model);
            compressionLevelTextField.setText(selections.getCompressionLevel().toString());
        }
    }

    @Override
    public ArchiverUserSelections getValue() {
        return this.selections;
    }

    @Override
    public Boolean isPanelValid() {
        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT
     * modify this code. The content of this method is always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        destinationTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        selectedContentsList = new javax.swing.JList<>();
        fileNameTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        compressionLevelTextField = new javax.swing.JTextField();

        jLabel1.setLabelFor(fileNameTextField);
        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, Bundle.ExportZipVisualPanel1_fileNameLabel_text());

        jLabel3.setLabelFor(destinationTextField);
        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, Bundle.ExportZipVisualPanel1_locationLabel_text());

        destinationTextField.setEditable(false);

        jLabel4.setLabelFor(selectedContentsList);
        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, Bundle.ExportZipVisualPanel3_jlabel4_text());

        selectedContentsList.setCellRenderer(new ArchiverListCellRenderer());
        selectedContentsList.setEnabled(false);
        jScrollPane2.setViewportView(selectedContentsList);

        fileNameTextField.setEditable(false);

        jLabel2.setLabelFor(compressionLevelTextField);
        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, Bundle.ExportZipVisualPanel1_COMPRESSION_LEVEL());

        compressionLevelTextField.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fileNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(destinationTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(compressionLevelTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {compressionLevelTextField, destinationTextField, fileNameTextField, jScrollPane2});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel1, jLabel2, jLabel3, jLabel4});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(fileNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(destinationTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 156, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(compressionLevelTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {compressionLevelTextField, destinationTextField, jLabel1, jLabel2, jLabel3, jLabel4});

    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField compressionLevelTextField;
    private javax.swing.JTextField destinationTextField;
    private javax.swing.JTextField fileNameTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<ArchiverListValueObject> selectedContentsList;
    // End of variables declaration//GEN-END:variables


}
