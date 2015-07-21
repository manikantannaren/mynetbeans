/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;
import org.netbeans.api.progress.ProgressHandle;
import org.netbeans.api.progress.ProgressHandleFactory;
import org.openide.util.Cancellable;
import org.openide.util.RequestProcessor;
import org.openide.util.Task;
import org.openide.util.TaskListener;

 class SomeCancellableAction implements ActionListener {

    private final static RequestProcessor RP = new RequestProcessor("interruptible tasks", 1, true);
    private final static Logger LOG = Logger.getLogger(SomeCancellableAction.class.getName());
    private RequestProcessor.Task theTask = null;

    public void actionPerformed(ActionEvent e) {
        final ProgressHandle ph = ProgressHandleFactory.createHandle("task thats shows progress", new Cancellable() {

            public boolean cancel() {
                return handleCancel();
            }
        });

        Runnable runnable = new Runnable() {

            private final int NUM = 60000;

            public void run() {
                try {
                    ph.start(); //we must start the PH before we swith to determinate
                    ph.switchToDeterminate(NUM);
                    for (int i = 0; i < NUM; i++) {
                        doSomething(i);
                        ph.progress(i);
                        Thread.sleep(0); //throws InterruptedException is the task was cancelled
                    }

                } catch (InterruptedException ex) {
                    LOG.info("the task was CANCELLED");
                    return;
                } 

            }

            private void doSomething(int i) {
                LOG.info("doSomething with " + i);                
                return;
            }
        };

        theTask = RP.create(runnable); //the task is not started yet

        theTask.addTaskListener(new TaskListener() {
            public void taskFinished(Task task) {
                ph.finish();
            }
        });

        theTask.schedule(0); //start the task


    }

    private boolean handleCancel() {
        LOG.info("handleCancel");
        if (null == theTask) {
            return false;
        }

        return theTask.cancel();
    }
}