Netbeans Plugin Create Archive
------------------------------

The plugin[Old name: Export As Archive](http://plugins.netbeans.org/plugin/10235/?show=true) is re-written using the ActionListener and @Action annotations of Netbeans. 
The changes with the previous version include
*. @Messages used extensively
*. Action registration using the @Action and java.util.List<DataObject>
*. Rely on selection context provided by the platform and rely on java.util.List<DataObject>
*. Enables selecting multiple nodes
*. If selected nodes belong to a hierarchy, the plugin will do the neccessary resolutions
*. If the first selected node is a "non-file" node like "Importany files" or "REST services" the action is disabled.
*. Changed naming and labels

