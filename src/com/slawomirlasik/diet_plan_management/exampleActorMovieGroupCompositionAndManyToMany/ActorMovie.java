package com.slawomirlasik.diet_plan_management.exampleActorMovieGroupCompositionAndManyToMany;

import com.slawomirlasik.diet_plan_management.util.ExtensionAnnotationAssociationManager;
import com.slawomirlasik.diet_plan_management.util.ManyToOneAssociation;

@ManyToOneAssociation(target = Actor.class, role = "")
public class ActorMovie extends ExtensionAnnotationAssociationManager {

    private Actor actor;

    private Movie movie;

    public ActorMovie(Actor actor, Movie movie) {
        super();
        this.actor = actor;
        this.movie = movie;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @Override
    public String toString() {
        return "ActorMovie{" +
                "actor=" + actor +
                ", movie=" + movie +
                '}';
    }
}
