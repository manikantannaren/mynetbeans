/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.capslock;

import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import org.openide.awt.StatusLineElementProvider;
import org.openide.util.NbBundle;
import org.openide.util.lookup.ServiceProvider;
import org.openide.windows.WindowManager;

/**
 *
 * @author Mahakaal
 */
@NbBundle.Messages({
    "LBL_CAPS_ON=CAPS ",
    "LBL_CAPS_OFF= "

})
@ServiceProvider(service = StatusLineElementProvider.class, position = 3)
public class CapsLockNotifier extends JLabel implements StatusLineElementProvider {

    public CapsLockNotifier() {
        updateCapsStatus();
        JFrame frame = (JFrame) WindowManager.getDefault().getMainWindow();
        InputMap map = frame.getRootPane().getInputMap(JRootPane.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        KeyStroke stroke = KeyStroke.getKeyStroke(KeyEvent.VK_CAPS_LOCK, 0, true);
        frame.setFocusable(true);
        map.put(stroke, "Caps Lock");
        ActionMap actMap = frame.getRootPane().getActionMap();
        actMap.put("Caps Lock", new CapsLockAction());

    }

    @Override
    public Component getStatusLineElement() {
        return this;
    }

    private void updateCapsStatus() {
        boolean state = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
        if (state) {
            setText(Bundle.LBL_CAPS_ON());
        } else {
            setText(Bundle.LBL_CAPS_OFF());
        }
    }

    private class CapsLockAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            updateCapsStatus();
        }

    }
}
