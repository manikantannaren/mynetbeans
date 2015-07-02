/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.zip;

import org.openide.execution.ExecutorTask;
import org.openide.util.Task;
import org.openide.util.TaskListener;

/**
 *
 * @author Kaiser
 */
class ExportActionAntScriptCompletionTaskListener implements TaskListener {

    public ExportActionAntScriptCompletionTaskListener() {
    }

    @Override
    public void taskFinished(Task task) {
        ExecutorTask thisTask = (ExecutorTask) task;
        
                
        //TODO ask if user would like to add zi to favorites.
    }
    
}
