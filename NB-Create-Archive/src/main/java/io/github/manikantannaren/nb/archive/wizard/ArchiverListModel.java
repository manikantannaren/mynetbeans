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

package io.github.manikantannaren.nb.archive.wizard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.AbstractListModel;
import org.openide.filesystems.FileObject;

/**
 *
 * @author Kaiser
 */
public class ArchiverListModel extends AbstractListModel<ArchiverListValueObject>{
    private List<ArchiverListValueObject> contents;

    public ArchiverListModel(List<ArchiverListValueObject> contents) {
        if(contents == null) throw new NullPointerException("Source list cannot be null");
        this.contents = new ArrayList<ArchiverListValueObject>(contents);
    }

    
    @Override
    public int getSize() {
        return contents.size();
    }

    @Override
    public ArchiverListValueObject getElementAt(int index) {
        if(!contents.isEmpty() && index < contents.size()){
            return contents.get(index);
        }
        return null;
    }
    public boolean addAll(List<ArchiverListValueObject> selItems){
        boolean retValue = contents.addAll(selItems);
        Collections.sort(contents);
        super.fireIntervalAdded(this, getSize(), getSize());
        return retValue;
    }

    public boolean removeAll(List<ArchiverListValueObject> selItems) {
        boolean retValue = contents.removeAll(selItems);
        super.fireIntervalRemoved(this, getSize(), getSize());
        return retValue;
    }
    public List<ArchiverListValueObject> getItems(){
        return new ArrayList<ArchiverListValueObject>(contents);
    }
}
