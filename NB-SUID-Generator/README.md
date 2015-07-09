#About Netbeans Plugin Generate serialVersionUID
------------------------------------------------

The plugin defines a code generator that will insert the code ``` public static final long serialVersionUID = 0L ```
##Using the plugin

To invoke the Generator

1. Open any Java (.java) Source file
2. Press Alt-Insert (Ctrl-i on Mac OS X)
3. Selected Generate serialVersionUID

Once you trigger the action the plugin will generate the code ``` public static final long serialVersionUID = 0L ``` and 
insert it at the end of the file.
