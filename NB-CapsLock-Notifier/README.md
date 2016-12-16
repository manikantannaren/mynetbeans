#About NetBeans Caps Lock Notifier
----------------------------------

A simple NetBeans plugin which demonstrates usage of @OnStart annotation 

When the plugin is installed, it shows CAPS if the caps lock key is pressed by the user

#How does it work
-----------------

1. The plugin uses an annotation ```org.openide.modules.OnStart``` for the plugin to be available on startup
2. Using the ```java.awt.KeyboardFocusManager``` register a KeyEventDispatcher to capture key board inputs
3. If the key code in the key event is caps lock, key, it toggles CAPS on the status bar.

#Inspiration
------------

I was struggling to do register, a keyevent handler for the whole of the IDE when I stumbled upon (literally) Geertjan's blog post [Listening to Key Events in NetBeans IDE] (https://blogs.oracle.com/geertjan/entry/listening_to_key_events_in)

#Downloading the plugin
------------------------

The plugin is available for download from the [NetBeans plugin portal] (http://plugins.netbeans.org/plugin/69579/?show=true)
The plugin will also be available in the NetBeans plugin manager once it is verified.


#Attributions
-------------
#Geertjan's blog post [Listening to Key Events in NetBeans IDE] (https://blogs.oracle.com/geertjan/entry/listening_to_key_events_in)
#Balloon Icon Some icons by [Yusuke Kamiyamane](http://p.yusukekamiyamane.com/). Licensed under a Creative Commons Attribution 3.0 License.
#Notification API understanding from [DZone](https://dzone.com/articles/nb-tips-4-notifications)
