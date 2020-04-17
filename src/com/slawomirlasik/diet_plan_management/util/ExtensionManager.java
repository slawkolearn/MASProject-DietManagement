package com.slawomirlasik.diet_plan_management.util;

import java.io.*;
import java.util.*;

public class ExtensionManager {

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

    // saving logic

    public static void saveExtensionCurrentState() throws IOException {
        saveExtensionsToFile(EXTENSION_FILE);
    }

    public static void saveExtensionsToFile(String filePath) throws IOException {
        // add some basic security for not getting null pointer exception
        if (filePath == null) filePath = EXTENSION_FILE;
        //Create Stream for writing to a file
        FileOutputStream fileOutputStream = new FileOutputStream(new File(filePath));
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        // write object to a file and close a stream
        objectOutputStream.writeObject(extensions);
        objectOutputStream.close();
    }

    // loading logic

    public static Boolean loadExtensionsFromFile() throws IOException, ClassNotFoundException {
        return loadExtensionsFromFile(EXTENSION_FILE);
    }

    public static Boolean loadExtensionsFromFile(String filePath) throws IOException, ClassNotFoundException {
        // ad some basic security for not getting null pointer exception
        if (filePath == null) filePath = EXTENSION_FILE;

        //create Stream for reading from a file
        File dataFile = new File(filePath);
        if (!dataFile.exists()) return false;
        FileInputStream fi = new FileInputStream(dataFile);
        ObjectInputStream oi = new ObjectInputStream(fi);
        return
                ((extensions = (Map<Class, List<ExtensionManager>>) oi.readObject()) != null ? true : false);
    }


    public static  void printExtension(Class extensionKey) throws Exception {
       List<ExtensionManager> extensionList = null;

       if(extensions.containsKey(extensionKey)){
           extensionList = extensions.get(extensionKey);
       }else{
           throw new Exception("Unknown class :" + extensionKey.getSimpleName());
       }

       for(Object object : extensionList){
           System.out.println(object);
       }
    }
}
