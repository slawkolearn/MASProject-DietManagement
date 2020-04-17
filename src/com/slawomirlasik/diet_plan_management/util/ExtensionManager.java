package com.slawomirlasik.diet_plan_management.util;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ExtensionManager {

    // TODO:SL implement managing extensions
    // file name for storing extensions
    private static final String EXTENSION_FILE = "extensionData.dtd";
    // main container for applications extensions storage
    private static Map<Class, List<ExtensionManager>> extensions = new HashMap<>();

    public ExtensionManager() {
        // this constructor adds the creating at the moment object to extensions conainter
        // we use Class from this.getClass() method for map key
        Class extensionKey = this.getClass();
        // here we add the current object to a extension
        List<ExtensionManager> extensionList = null;
        // if there is already key with the class that we are adding (under this pointer) then we retrieve it
        if(extensions.containsKey(extensionKey)){
            extensionList = extensions.get(extensionKey);
        }else{
            // if not we are creating empty extension List for the new key
            extensionList = new LinkedList<>(); // TODO:SL check which container should be more correct for this
            // we add container of extensions to the main container with all extensions
            extensions.put(extensionKey, extensionList);
        }
        // and we add this object that is being created to the retrieved / created list of extensions
        extensionList.add(this);

    }

    public static void saveExtensionCurrentState(){

    }
}
