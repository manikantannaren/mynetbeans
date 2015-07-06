Netbeans Plugin Create Archive
------------------------------

The plugin is re-written using the ActionListener and @Action annotations of Netbeans. 
The changes with the previous version include
1. @Messages used extensively
2. Action registration using the @Action and java.util.List<DataObject>
3. Rely on selection context provided by the platform and rely on java.util.List<DataObject>
4. Enables selecting multiple nodes
5. If selected nodes belong to a hierarchy, the plugin will do the neccessary resolutions
6. If the first selected node is a "non-file" node like "Importany files" or "REST services" the action is disabled.
