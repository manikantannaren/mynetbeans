/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.capslock;

import java.awt.Component;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import org.openide.awt.StatusLineElementProvider;
import org.openide.modules.OnStart;
import org.openide.util.NbBundle;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Mahakaal
 */
@NbBundle.Messages({
    "LBL_CAPS_ON=CAPS ",
    "LBL_CAPS_OFF= "

})
@OnStart
@ServiceProvider(service = StatusLineElementProvider.class, position = 3)
public class CapsLockNotifier extends JLabel implements StatusLineElementProvider, Runnable {
    @Override
    public Component getStatusLineElement() {
        return this;
    }

    private void updateCapsStatus(KeyEvent evt) {
        if (evt == null || evt.getKeyCode() == KeyEvent.VK_CAPS_LOCK){
            boolean state = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
            if (state) {
                setText(Bundle.LBL_CAPS_ON());
            } else {
                setText(Bundle.LBL_CAPS_OFF());
            }
        }
    }

    @Override
    public void run() {
        updateCapsStatus(null);
        KeyboardFocusManager kbFocusManager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        kbFocusManager.addKeyEventDispatcher(e -> {
            updateCapsStatus(e);
            return false;
        });
    }
}
