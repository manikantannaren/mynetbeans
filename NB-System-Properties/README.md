#About NetBeans System Properties
---------------------------------

A simple NetBeans plugin which demonstrates using TopComponent. The original idea I started with was to create a Multi view editor without having nodes and data objects



The plugin displays a table of the 

1. Operating system environment variables (obtained by ```System.getEnv()```) annotated with ![red badge](https://github.com/manikantannaren/mynetbeans/blob/master/NB-System-Properties/src/org/pr/nb/sysprops/red.png)
2. JVM startup system properties (obtained by ```System.getProperties()```) annotated with ![green badge] (https://github.com/manikantannaren/mynetbeans/blob/master/NB-System-Properties/src/org/pr/nb/sysprops/green.png)
3. Netbeans injected Startup properties (available within the JVM startup properties) annotated with ![Netbeans icon](https://github.com/manikantannaren/mynetbeans/blob/master/NB-System-Properties/src/org/pr/nb/sysprops/nb.png)

##Using the Plugin
After installation, you can activate the plugin by using the Netbeans main menu
Windows->IDE Tools->System Properties

##Features
It is possible to copy the properties by right clickign on any row. If no rows are selected, the row on which the popup menu is triggered gets copied else the contents of selected rows are selected. it is possible to copy

1. Copy property names of selected rows or current row
2. Copy values from selected rows or current row
3. Copy name and value (in name=value format) from selected rows or current row

##Downloading the plugin