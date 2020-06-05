package com.slawomirlasik.diet_plan_management.exampleActorMovieGroupCompositionAndManyToMany;

import com.slawomirlasik.diet_plan_management.util.CompositionWhole;
import com.slawomirlasik.diet_plan_management.util.ExtensionAnnotationAssociationManager;
import com.slawomirlasik.diet_plan_management.util.OneToManyAssociation;


@CompositionWhole(partTarget = Designer.class)
@CompositionWhole(partTarget = Actor.class)
@OneToManyAssociation(target = Actor.class, role = "actors")
@OneToManyAssociation(target = Designer.class, role = "designers")
public class Group extends ExtensionAnnotationAssociationManager {

    public static final String COMPOSITION_ROLE_ACTORS = "group_actors";

    public enum associations {
        GROUP_ACTORS
    }

    private int id;

    public Group() {
        super();
    }

    public Group(int id) {
        super();
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                '}';
    }


    public Actor addActor(String firstName, String lastName) throws Exception {

            // create actor object
            Actor actor = Actor.createActor(this, firstName, lastName);

            // return this actor
            return actor;
    }
}
