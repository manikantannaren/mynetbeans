/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.sqlite3.nodes;

import java.awt.Image;
import java.beans.IntrospectionException;
import java.io.IOException;
import org.openide.nodes.BeanNode;
import org.openide.util.ImageUtilities;
import org.pr.nb.sqlite3.data.Sqlite3Object;

/**
 *
 * @author msivasub
 */
public class NBSQlite3ObjectNode extends BeanNode<Sqlite3Object>{

    private final Sqlite3Object key;

    public NBSQlite3ObjectNode(Sqlite3Object bean) throws IntrospectionException {
        super(bean);
        this.key = bean;
    }

    @Override
    public Image getOpenedIcon(int type) {
        return ImageUtilities.loadImage("org/pr/nb/sqlite3/nodes/sqlite316x16-icon.svg.png");
    }

    @Override
    public Image getIcon(int type) {
        return ImageUtilities.loadImage("org/pr/nb/sqlite3/nodes/sqlite316x16-icon.svg.png");
    }

    @Override
    public boolean canRename() {
        return true;
    }

    @Override
    public boolean canDestroy() {
        return true;
    }

    @Override
    public void destroy() throws IOException {
        fireNodeDestroyed();
    }
    
    
}
