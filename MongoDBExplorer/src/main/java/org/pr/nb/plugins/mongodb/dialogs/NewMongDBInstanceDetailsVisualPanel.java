/*
 * Copyright 2016 Mahakaal.
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
package org.pr.nb.plugins.mongodb.dialogs;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.PlainDocument;
import org.openide.WizardDescriptor;
import org.openide.util.ChangeSupport;
import org.openide.util.NbBundle;
import org.pr.nb.plugins.mongodb.components.PropertyNames;
import org.pr.nb.plugins.mongodb.components.WholeNumberDocumentFilter;
import org.pr.nb.plugins.mongodb.data.MongoDBInstance;
import org.pr.nb.plugins.mongodb.nodes.WizardMessagingInterface;

@NbBundle.Messages({
    "PANEL1_TITLE=Connection details",
    "LBL_HOSTNAME=Host name",
    "LBL_PORTNUMBER=Port number",
    "LBL_USERNAME=User name",
    "LBL_DISPLAYNAME=Display name",
    "ERR_HOSTNAME_REQUIRED=Hostame or IP address is required",
    "ERR_PORT_REQUIRED=Port number is required"
})
public final class NewMongDBInstanceDetailsVisualPanel extends JPanel implements WizardMessagingInterface {

    NewMongDBInstanceDetailsVisualPanel(ChangeSupport changeSupport) {
        this.changeSupport = changeSupport;
        initComponents();
        addDocumentListeners();
    }

    private void addDocumentListeners() {
        hostNameTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                validateHostName(hostNameTextField);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                validateHostName(hostNameTextField);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                validateHostName(hostNameTextField);
            }
        });
        portNumberTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                validatePortNumber(portNumberTextField);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                validatePortNumber(portNumberTextField);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                validatePortNumber(portNumberTextField);
            }
        });
        userNameTextField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void changedUpdate(DocumentEvent e) {
                validateBlanks(userNameTextField);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                validateBlanks(userNameTextField);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                validateBlanks(userNameTextField);
            }
        });
    }

    private void validatePortNumber(JTextField textField) {
        validateBlanks(textField);
    }

    private void validateHostName(JTextField textField) {
        validateBlanks(textField);
    }

    private void validateBlanks(JTextField textField) {
        if (textField.getText().length() <= 0) {
            if (data != null) {
                data.setMessageType(WizardDescriptor.ERROR_MESSAGE);
                data.putProperty(WizardDescriptor.PROP_ERROR_MESSAGE, Bundle.ERR_HOSTNAME_REQUIRED());
            }
        }
    }

    @Override
    public String getName() {
        return Bundle.PANEL1_TITLE();
    }

    @Override
    public void readSettings(WizardDescriptor wiz) {
        // use wiz.getProperty to retrieve previous panel state
        this.data = wiz;
        MongoDBInstance instance = (MongoDBInstance) wiz.getProperty(PropertyNames.NEW_MONGODB_WIZARD_INSTANCE.name());
        assert instance != null;
        hostNameTextField.setText(instance.getHostName());
        portNumberTextField.setText(instance.getPortNumber() + "");
        userNameTextField.setText(instance.getUserName());
        displayNameTextField.setText(instance.getDisplayName());
    }

    @Override
    public void storeSettings(WizardDescriptor wiz) {
        this.data = wiz;
        MongoDBInstance instance = (MongoDBInstance) wiz.getProperty(PropertyNames.NEW_MONGODB_WIZARD_INSTANCE.name());
        instance.setHostName(hostNameTextField.getText());
        instance.setDisplayName(displayNameTextField.getText());
        instance.setUserName(userNameTextField.getText());
        try {
            instance.setPortNumber(Integer.parseInt(portNumberTextField.getText()));
        } catch (NumberFormatException e) {
            instance.setPortNumber(27017);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        hostNameTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        portNumberTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        userNameTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        displayNameTextField = new javax.swing.JTextField();

        jLabel1.setLabelFor(hostNameTextField);
        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, Bundle.LBL_HOSTNAME());

        hostNameTextField.setText(org.openide.util.NbBundle.getMessage(NewMongDBInstanceDetailsVisualPanel.class, "NewMongDBInstanceDetailsVisualPanel.hostNameTextField.text")); // NOI18N

        jLabel2.setLabelFor(portNumberTextField);
        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, Bundle.LBL_PORTNUMBER());

        portNumberTextField.setText(org.openide.util.NbBundle.getMessage(NewMongDBInstanceDetailsVisualPanel.class, "NewMongDBInstanceDetailsVisualPanel.hostNameTextField.text")); // NOI18N
        ((PlainDocument)portNumberTextField.getDocument()).setDocumentFilter(new WholeNumberDocumentFilter());

        jLabel3.setLabelFor(userNameTextField);
        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, Bundle.LBL_USERNAME());

        userNameTextField.setText(org.openide.util.NbBundle.getMessage(NewMongDBInstanceDetailsVisualPanel.class, "NewMongDBInstanceDetailsVisualPanel.hostNameTextField.text")); // NOI18N

        jLabel4.setLabelFor(displayNameTextField);
        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, Bundle.LBL_DISPLAYNAME());

        displayNameTextField.setText(org.openide.util.NbBundle.getMessage(NewMongDBInstanceDetailsVisualPanel.class, "NewMongDBInstanceDetailsVisualPanel.hostNameTextField.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(portNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(userNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(displayNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(15, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(hostNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {displayNameTextField, hostNameTextField, portNumberTextField, userNameTextField});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(hostNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(portNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(userNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(displayNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(162, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {displayNameTextField, jLabel4});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel3, userNameTextField});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel2, portNumberTextField});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {hostNameTextField, jLabel1});

    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField displayNameTextField;
    private javax.swing.JTextField hostNameTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField portNumberTextField;
    private javax.swing.JTextField userNameTextField;
    // End of variables declaration//GEN-END:variables
    private ChangeSupport changeSupport;
    private WizardDescriptor data;

}
