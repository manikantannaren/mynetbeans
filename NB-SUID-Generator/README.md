#About NetBeans Plugin Generate serialVersionUID
------------------------------------------------

The plugin defines a code generator that will insert the code ``` public static final long serialVersionUID = 0L ```
##Using the plugin

To invoke the Generator

1. Open any Java (.java) Source file
2. Press Alt-Insert (Ctrl-i on Mac OS X)
3. Selected Generate serialVersionUID

Once you trigger the action the plugin will generate the code ``` public static final long serialVersionUID = 0L ``` and 
insert it at the end of the file.

##Getting the plugin
###From NetBeans plugin portal
You can download the plugin from the [Netbeans plugin portal](http://plugins.netbeans.org/plugin/58996/?show=true)
###Using the NetBeans plugin manager
This plugin is verified for NetBeans 8.0.* and can be installed using the plugin manager.

1. Start NetBeans
2. Selected Tools->Plugins
3. In Available Plugins tab, search for ```NetBeans Generate```
4. Select the plugin
5. Click install. 

