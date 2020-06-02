package com.slawomirlasik.diet_plan_management.developementActorMovieGroupComposition;

import com.slawomirlasik.diet_plan_management.util.ExtensionAssociationManager;

public class Group extends ExtensionAssociationManager {

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


    public Actor addActor( String firstName, String lastName) throws Exception {

            // create actor
            Actor actor = Actor.createActor(firstName, lastName);

            // add actor as a part (we now that it does not exists anywhere)
            this.addPart(Actor.COMPOSITION_ROLE_GROUP, Group.COMPOSITION_ROLE_ACTORS, actor);

            // return this actor
            return actor;
    }
}
