/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.capslock;

import java.awt.Color;
import java.awt.Component;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.LookAndFeel;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import org.openide.awt.StatusLineElementProvider;
import org.openide.modules.OnStart;
import org.openide.util.NbBundle;
import org.openide.util.lookup.ServiceProvider;
import org.pr.nb.capslock.balloon.BallonDisplayer;

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
public class CapsLockNotifier extends JLabel implements
    StatusLineElementProvider, Runnable {

    public CapsLockNotifier() {
        super();
    }

    @Override
    public Component getStatusLineElement() {
        return this;
    }

    private void updateCapsStatus(KeyEvent evt) {
        final LookAndFeel lookAndFeel = UIManager.getLookAndFeel();
        final UIDefaults defaults = lookAndFeel.getDefaults();

        Color foreground = defaults.getColor("Label.foreground");
        setForeground(foreground);
        Color background = defaults.getColor("Label.background");
        setBackground(background);

        if (evt == null || evt.getKeyCode() == KeyEvent.VK_CAPS_LOCK) {
            boolean state = Toolkit.getDefaultToolkit().getLockingKeyState(
                KeyEvent.VK_CAPS_LOCK);
            if (state) {
                setText(Bundle.LBL_CAPS_ON());
//                BallonDisplayer.getInstance().showBalloon();
            } else {
                setText(Bundle.LBL_CAPS_OFF());
            }
        }
    }

    @Override
    public void run() {
        updateCapsStatus(null);
        KeyboardFocusManager kbFocusManager = KeyboardFocusManager.
            getCurrentKeyboardFocusManager();
        kbFocusManager.addKeyEventDispatcher(e -> {
            updateCapsStatus(e);
            return false;
        });
    }
}
