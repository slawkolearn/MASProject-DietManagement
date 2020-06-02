package com.slawomirlasik.diet_plan_management.developementActorMovieGroupComposition;

import com.slawomirlasik.diet_plan_management.util.ExtensionAssociationManager;

public class Actor extends ExtensionAssociationManager {

    public static final String ASSOCIATION_MANY_TO_MANY_ROLE_ACTORS = "actor_movies";
    public static final String COMPOSITION_ROLE_GROUP = "actor_group";

    public enum associations {
        ACTOR_MOVIES,
        ACTOR_GROUP
    };


    private String firstName;

    private String lastName;

    private Actor() {
        super();
    }

    private Actor(String firstName, String lastName) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public static Actor createActor(String firstName, String lastName) throws Exception {
        Actor actor = new Actor(firstName, lastName);
        if( ExtensionAssociationManager.partAlreadyExists(actor) ){
            throw new Exception(actor + " already exists!!");
        }
        return actor;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    public String getQualifier() {
        return String.valueOf(firstName.toUpperCase().charAt(0))   + String.valueOf(lastName.toUpperCase().charAt(0));
    }
}
