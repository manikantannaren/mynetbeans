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

package org.pr.nb.zip.wizard;

import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import org.openide.filesystems.FileUtil;

/**
 *
 * @author Kaiser
 */
public class ArchiverListCellRenderer extends DefaultListCellRenderer{

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel label =  (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        ArchiverListValueObject obj = (ArchiverListValueObject) value;
        label.setText(FileUtil.getFileDisplayName(obj.getDataObject()));
        return label;
    }

    
    
    
}
