package com.slawomirlasik.diet_plan_management.exampleActorMovieGroupCompositionAndManyToMany;

import com.slawomirlasik.diet_plan_management.util.CompositionPart;
import com.slawomirlasik.diet_plan_management.util.ExtensionAnnotationAssociationManager;
import com.slawomirlasik.diet_plan_management.util.ManyToOneAssociation;

import java.util.Objects;

@CompositionPart(wholeTarget = Group.class)
@ManyToOneAssociation(
        target = Group.class,
        role = "group"
)
public class Actor extends ExtensionAnnotationAssociationManager {

    public static final String ASSOCIATION_MANY_TO_MANY_ROLE_ACTORS = "actor_movies";
    public static final String COMPOSITION_ROLE_GROUP = "actor_group";

    public enum associations {
        ACTOR_MOVIES,
        ACTOR_GROUP
    }

    ;


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

    public static Actor createActor(
            ExtensionAnnotationAssociationManager whole,
            String firstName,
            String lastName
    ) throws Exception {

        // check if the whole exists and it is valid for this part type
        if( !wholeIsValid(Actor.class, whole) ){
            throw new Exception(String.format(
                    "There is no valid composition association between whole %s and part %s",
                    whole.getClass().getSimpleName(),
                    Actor.class.getSimpleName()

            ));
        }

        // create an actor - its safe now
        Actor actor = new Actor(firstName, lastName);

        // add actor to the parts -> throw exception if part already exists
        addPart(whole, actor);

        // return actor
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
        return String.valueOf(firstName.toUpperCase().charAt(0)) + String.valueOf(lastName.toUpperCase().charAt(0));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Actor actor = (Actor) o;
        return Objects.equals(firstName, actor.firstName) &&
                Objects.equals(lastName, actor.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }
}
