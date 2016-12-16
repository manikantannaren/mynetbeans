/*
 * Balloon Icon by Yusuke Kamiyamane (http://p.yusukekamiyamane.com/). 
 * Licensed under a Creative Commons Attribution 3.0 License.
 */
package org.pr.nb.capslock.balloon;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import org.openide.awt.Notification;
import org.openide.awt.NotificationDisplayer;
import org.openide.util.NbBundle;

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
    
    private BallonDisplayer() {
        warningIcon = new ImageIcon(getClass().getResource(
            "light_bulb_exclamation.png"));
    }

    public static BallonDisplayer getInstance() {
        return NewSingletonHolder.INSTANCE;
    }

    private static class NewSingletonHolder {
        private static final BallonDisplayer INSTANCE = new BallonDisplayer();
    }

    public void showBalloon() {
        Notification notification = NotificationDisplayer.getDefault().notify(Bundle.BALLOON_TITLE(),
            warningIcon, Bundle.CAPS_ON(),
            null, NotificationDisplayer.Priority.NORMAL,
            NotificationDisplayer.Category.WARNING);
    }
}
