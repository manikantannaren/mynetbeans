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
package org.pr.nb.mongodb.nodes.wizard;

import org.openide.WizardDescriptor;

/**
 *
 * @author Mahakaal
 */
public interface WizardMessagingInterface {

    public static final String KEY_USER_SETTINGS = "KEY_USER_SETTINGS";
    public void readSettings(WizardDescriptor wiz);

    public void storeSettings(WizardDescriptor wiz);
    
    public boolean isPanelValid();
}
