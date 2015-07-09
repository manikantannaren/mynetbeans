#About Netbeans Plugin Create Archive
-------------------------------------

The plugin replaces the earlier plugin named as *Export As Archive*. It is re-written using the ActionListener and @Action annotations of Netbeans. 

The changes with the previous version include
* @Messages used extensively
* Action registration using the @Action and java.util.List<DataObject>
* Rely on selection context provided by the platform and rely on java.util.List<DataObject>
* Enables selecting multiple nodes
* If selected nodes belong to a hierarchy, the plugin will do the neccessary resolutions
* If the first selected node is a "non-file" node like "Importany files" or "REST services" the action is disabled.
* Changed naming and labels

##Getting the plugin
###Netbeans plugin portal
You can download the plugin from [Netbeans plugin portal](http://plugins.netbeans.org/plugin/58976/?show=true)

###Using the NetBeans plugin manager
This plugin is verified for NetBeans 8.0.* and can be installed using the plugin manager.

1. Start NetBeans
2. Selected Tools->Plugins
3. In Available Plugins tab, search for ```NetBeans Create Archive```
4. Select the plugin
5. Click install. 
##Acknowledgements
A big thank you to @Chris2011 for trying out the plugin in full trust and reporting issues

1. [Issue #3: Can't install plugin, favorites is needed.](https://github.com/manikantannaren/mynetbeans/issues/3). Had to disable a feature to fix the bug. :disappointed_relieved:
2. [Issue #4: Missing resource exception](https://github.com/manikantannaren/mynetbeans/issues/4)
