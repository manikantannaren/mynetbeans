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
package org.pr.nb.plugins.mongodb.components;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import org.pr.nb.plugins.mongodb.data.MongoDBInstance;

/**
 *
 * @author Mahakaal
 */
public class PropertiesNotifier {

    private static final PropertyChangeSupport changeSupport = new PropertyChangeSupport(PropertiesNotifier.class);

    public static void addPropertyChangeListener(PropertyNames propertyName, PropertyChangeListener l) {
        changeSupport.addPropertyChangeListener(propertyName.name(), l);
    }

    public static void removePropertyChangeListener(PropertyNames propertyName, PropertyChangeListener l) {
        changeSupport.removePropertyChangeListener(propertyName.name(), l);
    }

    public static void fireNewMongoDBInstance(MongoDBInstance newInstance) {
        changeSupport.firePropertyChange(PropertyNames.NEW_MONGODB_INSTANCE.name(), null, newInstance);
    }
}
