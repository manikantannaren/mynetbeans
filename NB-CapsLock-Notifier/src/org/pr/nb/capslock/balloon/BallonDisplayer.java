/*
 * Balloon Icon by Yusuke Kamiyamane (http://p.yusukekamiyamane.com/). 
 * Licensed under a Creative Commons Attribution 3.0 License.
 */
package org.pr.nb.capslock.balloon;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import org.openide.awt.Notification;
import org.openide.awt.NotificationDisplayer;
import org.openide.util.*;

/**
 *
 * @author Mahakaal
 */
@NbBundle.Messages({
    "CAPS_ON=CAPS key is on",
    "BALLOON_TITLE=CAPS On"
})
public class BallonDisplayer {

    Icon warningIcon;
    private Notification notification;

    private BallonDisplayer() {
        warningIcon = ImageUtilities.loadImageIcon(
            "org/pr/nb/capslock/balloon/light_bulb_exclamation.png", true);
    }

    public static BallonDisplayer getInstance() {
        return NewSingletonHolder.INSTANCE;
    }

    public void hideBalloon() {
        if (notification != null) {
            notification.clear();
        }
        notification = null;
    }

    private static class NewSingletonHolder {

        private static final BallonDisplayer INSTANCE = new BallonDisplayer();
    }

    public void showBalloon() {
        if (notification == null) {

            notification =
                NotificationDisplayer.getDefault().
                    notify(Bundle.BALLOON_TITLE(),
                        warningIcon, Bundle.CAPS_ON(),
                        null, NotificationDisplayer.Priority.NORMAL,
                        NotificationDisplayer.Category.WARNING);
        }
    }
}
