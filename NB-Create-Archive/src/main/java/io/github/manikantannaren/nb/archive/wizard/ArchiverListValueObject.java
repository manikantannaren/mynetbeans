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

import org.openide.filesystems.FileObject;

/**
 *
 * @author Kaiser
 */
public class ArchiverListValueObject implements Comparable<ArchiverListValueObject>{
    private Integer rank = 0;

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public FileObject getDataObject() {
        return dataObject;
    }

    public void setDataObject(FileObject dataObject) {
        this.dataObject = dataObject;
    }
    private FileObject dataObject;

    public ArchiverListValueObject() {
    }

    public ArchiverListValueObject(Integer rank, FileObject dataObject) {
        this.rank = rank;
        this.dataObject = dataObject;
    }

    @Override
    public int compareTo(ArchiverListValueObject that) {
        int retValue = -1;
        if(that != null){
            retValue = this.rank.compareTo(that.rank);
        }
        return retValue;
    }

}
