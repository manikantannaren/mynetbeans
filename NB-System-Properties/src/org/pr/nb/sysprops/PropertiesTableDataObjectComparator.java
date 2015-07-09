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

import java.util.Comparator;

/**
 *
 * @author Kaiser
 */
class PropertiesTableDataObjectComparator implements Comparator<PropertiesTableDataObject>{

    public PropertiesTableDataObjectComparator() {
    }

    @Override
    public int compare(PropertiesTableDataObject o1, PropertiesTableDataObject o2) {
        assert o1 != null;
        assert o2 != null;
        int retValue = -1;
        if(o1.getFlavour() == o2.getFlavour()){
            retValue = o1.getData().getKey().compareTo(o2.getData().getKey());
        }else{
            retValue = -1*(o1.getFlavour().compareTo(o2.getFlavour()));
        }
        return retValue;
    }
    
}
