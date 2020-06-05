package com.slawomirlasik.diet_plan_management.util;

import java.io.*;
import java.util.*;

public class ExtensionManager implements Serializable {

    // TODO:SL make util class extending? this extensionmanager class for showing errors etc
    // simple label for showing the same staring message for errors
    protected static String ERROR_LABEL = "!!ERROR!!";
    // file name for storing extensions
    public static final String EXTENSION_FILE = "extensionData.dtd";
    // main container for applications extensions storage
    // TODO:SL it is not safe because all business logic class inherits this class and have direct access to it
    protected static Map<Class, List<ExtensionManager>> extensions = new HashMap<>();

    public ExtensionManager() {
        // this constructor adds the creating at the moment object to extensions conainter
        // we use Class from this.getClass() method for map key
        Class extensionKey = this.getClass();
        // here we add the current object to a extension
        List<ExtensionManager> extensionList = null;
        // if there is already key with the class that we are adding (under this pointer) then we retrieve it
        if (extensions.containsKey(extensionKey)) {
            extensionList = extensions.get(extensionKey);
        } else {
            // if not we are creating empty extension List for the new key
            extensionList = new LinkedList<>(); // TODO:SL check which container should be more correct for this
            // we add container of extensions to the main container with all extensions
            extensions.put(extensionKey, extensionList);
        }
        // and we add this object that is being created to the retrieved / created list of extensions
        extensionList.add(this);
    }




    public static void printExtension(Class extensionKey) throws Exception {
        List<ExtensionManager> extensionList = null;

        if (extensions.containsKey(extensionKey)) {
            extensionList = extensions.get(extensionKey);
        } else {
            throw new Exception("Unknown class :" + extensionKey.getSimpleName());
        }

        for (Object object : extensionList) {
            System.out.println(object);
        }
    }

    // getting extension
    public static <T> Iterable<T> getExtension(Class<T> extensionKey) {
        return (Iterable<T>) extensions.getOrDefault(extensionKey, new ArrayList<>());
    }

    public static <T> boolean hasExtensionType(Class<T> dietUserClass) {
        return extensions.getOrDefault(dietUserClass, new ArrayList<>() ).size() > 0;
    }
}
