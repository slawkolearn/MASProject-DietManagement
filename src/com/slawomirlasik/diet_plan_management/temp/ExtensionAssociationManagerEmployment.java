package com.slawomirlasik.diet_plan_management.temp;

import com.slawomirlasik.diet_plan_management.util.ExtensionManager;

import java.io.PrintStream;
import java.io.Serializable;
import java.util.*;

public class ExtensionAssociationManagerEmployment<T> extends ExtensionManager implements Serializable {

    /**
     * Stores information about what associations are avaible in the system
     *  then in the specific class in the method it is checked whether it is possible to create
     *  this association (it is permitted by the business logic
     *  like actor can be associated with movies but not with casettes or DVDs
     *
     *  REMEMBER to make adding link in this util as protected. That all public methods
     *  of creating association is handled by the specific class methods
     */


    /**
     * Stores information about all connections of this object.
     */
    private Map<T, Map<Object, ExtensionAssociationManagerEmployment>> links = new Hashtable<>();

    /**
     * Stores information about all parts connected with any objects.
     */
    private static Set<ExtensionAssociationManagerEmployment> allParts = new HashSet<>();


    /**
     * The constructor.
     *
     */
    public ExtensionAssociationManagerEmployment() {
        super();
    }

    /**
     * checks if part already exists somewhere
     * @param part
     * @return
     */

    protected static boolean partAlreadyExists(ExtensionAssociationManagerEmployment part) {

        return allParts.contains(part);
    }


    /**
     * Creates a new link (private, utility method).
     * @param roleName is the opposite role from this class
     * @param reverseRoleName is role from opposite class to yours which is your rolename
     * @param targetObject any qualifier you want to be to get an object later using this qualifier
     * @param counter
     */
    private void addLink(T roleName, T reverseRoleName, ExtensionAssociationManagerEmployment targetObject, Object qualifier, int counter) {
        Map<Object, ExtensionAssociationManagerEmployment> objectLinks;

        // Protection for the reverse connection
        if(counter < 1) {
            return;
        }

        // Find a collection of links for the role
        if(links.containsKey(roleName)) {
            // Get the links
            objectLinks = links.get(roleName);
        }
        else {
            // No links ==> create them
            objectLinks = new HashMap<>();
            links.put(roleName, objectLinks);
        }

        // Check if there is already the connection
        // If yes, then ignore the creation
        if(!objectLinks.containsKey(qualifier)) {
            // Add a link for the target object
            objectLinks.put(qualifier, targetObject);

            // Add the reverse connection
            targetObject.addLink(reverseRoleName, roleName, this, this, counter - 1);
        }
    }

    /**
     * Creates a new link to the given target object (optionally as quilified connection).
     *
     * @param roleName is the opposite role from this class
     * @param reverseRoleName is role from opposite class to yours which is your rolename
     * @param targetObject any qualifier you want to be to get an object later using this qualifier
     * @param qualifier Jezeli rozny od null to tworzona jest asocjacja kwalifikowana.
     */
    public void addLink(T roleName, T reverseRoleName, ExtensionAssociationManagerEmployment targetObject, Object qualifier) {
        addLink(roleName, reverseRoleName, targetObject, qualifier, 2);
    }

    /**
     * Creates a new link to the given target object (as an ordinary association, not the quilified one).
     * @param roleName is the opposite role from this class
     * @param reverseRoleName is role from opposite class to yours which is your rolename
     * @param targetObject any qualifier you want to be to get an object later using this qualifier
     */
    public void addLink(T roleName, T reverseRoleName, ExtensionAssociationManagerEmployment targetObject) {
        addLink(roleName, reverseRoleName, targetObject, targetObject);
    }

    /**
     * Adds an information about a connection (using a "semi" composition).
     * @param roleName is the opposite role from this class
     * @param reverseRoleName is role from opposite class to yours which is your rolename
     * @throws Exception
     */
    public void addPart(T roleName, T reverseRoleName, ExtensionAssociationManagerEmployment partObject) throws Exception {
        // Check if the part exist somewhere
        if(allParts.contains(partObject)) {
            throw new Exception("The part is already connected to a whole!");
        }

        addLink(roleName, reverseRoleName, partObject);

        // Store adding the object as a part
        allParts.add(partObject);
    }

    /**
     * Gets an array of connected objects for the given role name.
     * @param roleName
     * @return
     * @throws Exception
     */
    public ExtensionAssociationManagerEmployment[] getLinks(T roleName) throws Exception {
        Map<Object, ExtensionAssociationManagerEmployment> objectLinks;

        if(!links.containsKey(roleName)) {
            // No links for the role
            throw new Exception("No links for the role: " + roleName);
        }

        objectLinks = links.get(roleName);

        return (ExtensionAssociationManagerEmployment[]) objectLinks.values().toArray(new ExtensionAssociationManagerEmployment[0]);
    }

    /**
     * Shows links to the given stream.
     * @param roleName this should be the role name from your class perspective looking to another class A[role1] -- B[role2]. when looking from A class you should put "role2" in this case
     * @param stream
     * @throws Exception
     */
    public void showLinks(T roleName, PrintStream stream) throws Exception {
        Map<Object, ExtensionAssociationManagerEmployment> objectLinks;

        if(!links.containsKey(roleName)) {
            // No links
            throw new Exception("No links for the role: " + roleName);
        }

        objectLinks = links.get(roleName);

        Collection col = objectLinks.values();

        stream.println(this.getClass().getSimpleName() + " links, role '" + roleName + "':");

        for(Object obj : col) {
            stream.println("   " + obj);
        }
    }

    /**
     * printing all qualifiers for a specific role name
     * @param roleName the roleName of a oposite class
     * @param stream
     */

    public void showQualifiers(T roleName, PrintStream stream) throws Exception {
        Map<Object, ExtensionAssociationManagerEmployment> objectLinks;

        if(!links.containsKey(roleName)) {
            // No links
            throw new Exception("No links for the role: " + roleName);
        }

        objectLinks = links.get(roleName);

        Collection qualifiers = objectLinks.keySet();


        stream.println(this.getClass().getSimpleName() + " qualifiers, role '" + roleName + "':");
        for ( Object qualifier : qualifiers ) {
            System.out.println("   " + qualifier);
        }

    }

    /**
     * Gets an object for the given qualifier (a qualified association).
     * @param roleName
     * @param qualifier
     * @return
     * @throws Exception
     */
    public ExtensionAssociationManagerEmployment getLinkedObject(T roleName, Object qualifier) throws Exception {
        Map<Object, ExtensionAssociationManagerEmployment> objectLinks;

        if(!links.containsKey(roleName)) {
            // No links
            throw new Exception("No links for the role: " + roleName);
        }

        objectLinks = links.get(roleName);
        if(!objectLinks.containsKey(qualifier)) {
            // No link for the qualifer
            throw new Exception("No link for the qualifer: " + qualifier);
        }

        return objectLinks.get(qualifier);
    }

    /**
     * Checks if there are any links for the given role name.
     * @param nazwaRoli
     * @return
     */
    public boolean anyLink(T nazwaRoli) {
        if(!links.containsKey(nazwaRoli)) {
            return false;
        }

        Map<Object, ExtensionAssociationManagerEmployment> links = this.links.get(nazwaRoli);
        return links.size() > 0;
    }

    /**
     * Checks if there is a link to a given object as a given role.
     * @param roleName
     * @param targetObject
     * @return
     */
    public boolean isLink(T roleName, ExtensionAssociationManagerEmployment targetObject) {
        Map<Object, ExtensionAssociationManagerEmployment> objectLink;

        if(!links.containsKey(roleName)) {
            // No links for the role
            return false;
        }

        objectLink = links.get(roleName);

        return objectLink.containsValue(targetObject);
    }


    public void printRoles(){
        System.out.println("\n ===================================");
        System.out.printf("The %s has roles:", this.getClass().getSimpleName());
        for(T role : links.keySet() ){
            System.out.println("   " + role);
        }
    }



}
