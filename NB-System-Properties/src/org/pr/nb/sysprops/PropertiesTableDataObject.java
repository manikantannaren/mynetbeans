/*
 * Copyright 2015 Manikantan Narender Nath.
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

package org.pr.nb.sysprops;

import java.io.Serializable;
import java.util.Map;

/**
 *
 * @author Kaiser
 */
public class PropertiesTableDataObject implements Serializable {

    private Map.Entry<String, String> data;

    public enum Flavour {

        PROP, NETBEANS,ENV
    }
    private Flavour flavour = Flavour.PROP;

    public enum DataDescriptor {

        NAME, VALUE
    }

    private DataDescriptor descriptor;

    public PropertiesTableDataObject(Map.Entry<String, String> data, Flavour flavour) {
        assert data != null;
        this.data = data;
        this.flavour = flavour;
    }

    public Map.Entry<String, String> getData() {
        return data;
    }

    public DataDescriptor getDescriptor() {
        return descriptor;
    }

    public void setDescriptor(DataDescriptor descriptor) {
        this.descriptor = descriptor;
    }

    
    public Flavour getFlavour() {
        return flavour;
    }
}
