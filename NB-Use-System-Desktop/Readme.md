In Java 6 a new class was introduced - java.awt.Desktop, using which you can leave file handling to the underlying operating system's file associations.
For ex. Notepad can be the assigned editor for .txt files. Or you can view the System file explorer from the selected node. You might want to view files within the browser. 

This plugin has four actions, Open, Browse, Edit and send by mail. Each of these uses the Desktop.edit/open, browse and mail methods.

Caveat Emptor - Known issues :
1. Behaviour is not guaranteed to be as expected. For example browse and open can do the same thing - open in the default editor. My testing has shown that only html and xml files open properly in browser.
2. Mail - Selected file is not intriduced as an attachment since the mailto protocol does not support attachments. 
3. There are no options to set. What ever is set on the operating system will be used. 
4. When file association are not set; For example for manifest.mf file there might not be a file association. In these case you get an IOException which is shown to the user. 
5. Tested Platforms - Netbeans 8 and Java SE 7 and up. The plugin has been developer tested on Win 7, Windows 8, Mac OS X - Yosemite.
6. BIG NOTE: If an application is not associated with the given file, the behaviour is non-consistent. Some times the Open With dialog appears, sometimes hiddent behind the IDE and many times, throws Exception
