/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.changecase;

import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.text.JTextComponent;
import javax.swing.text.StyledDocument;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.openide.awt.NotificationDisplayer;
import org.openide.text.NbDocument;
import org.openide.util.NbBundle;

/**
 *
 * @author msivasub
 */
@NbBundle.Messages(
        {
            "NOT_YET_SUPPORTED=Not yet supported"
        })
public class ChangeCaseUtility {

    public enum CHANGE_CASE_TYPE {
        ALL_CAPS, ALL_LOWER, INVERT_CASE, CAMEL_CASE, INIT_CAPS
    }

    private ChangeCaseUtility() {
    }

    public static ChangeCaseUtility getInstance() {
        return ChangeClassUtilityHolder.INSTANCE;
    }

    private static class ChangeClassUtilityHolder {

        private static final ChangeCaseUtility INSTANCE = new ChangeCaseUtility();
    }

    public void changeCase(JTextComponent component, CHANGE_CASE_TYPE changeType) {
        switch (changeType) {
            case ALL_CAPS:
            case ALL_LOWER:
            case INVERT_CASE:
                if (component.getDocument() instanceof StyledDocument) {
                    NbDocument.runAtomic((StyledDocument) component.
                            getDocument(),
                            new RunnableAction(component, changeType));

                } else {
                    doLocally(component, changeType);
                }
                break;
            default:
                throw new UnsupportedOperationException(
                        "Not yet implemented for " + changeType.name());
        }
    }

    private void doLocally(JTextComponent component, CHANGE_CASE_TYPE changeType) {
        ExecutorService pool = Executors.newSingleThreadExecutor();
        pool.execute(new RunnableAction(component, changeType));
        pool.shutdown();
    }

    private static class RunnableAction implements Runnable {

        private final CHANGE_CASE_TYPE changeType;
        private final JTextComponent component;

        public RunnableAction(JTextComponent component,
                CHANGE_CASE_TYPE changeType) {
            this.component = component;
            this.changeType = changeType;
        }

        @Override
        public void run() {
            String text = component.getSelectedText();
            int selStart = component.getSelectionStart();
            int selEnd = component.getSelectionEnd();
            text = convertCase(text, changeType);

            component.replaceSelection(text);

            component.select(selStart, selEnd);
        }

        private String convertCase(String text, CHANGE_CASE_TYPE changeType) {
            switch (changeType) {
                case ALL_CAPS:
                    text = StringUtils.upperCase(text);
                    break;
                case ALL_LOWER:
                    text = StringUtils.lowerCase(text);
                    break;
                case INVERT_CASE:
                    text = WordUtils.swapCase(text);
                    break;
                case INIT_CAPS:
                    text = WordUtils.capitalizeFully(text);
                    break;
                default:
                    NotificationDisplayer.getDefault().
                            notify(Bundle.NOT_YET_SUPPORTED(),
                                    null, Bundle.NOT_YET_SUPPORTED(),
                                    null, NotificationDisplayer.Priority.LOW,
                                    NotificationDisplayer.Category.WARNING);
            }
            return text;
        }
    }
}
