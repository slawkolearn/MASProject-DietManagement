package com.slawomirlasik.diet_plan_management.exampleActorMovieGroupCompositionAndManyToMany;

import com.slawomirlasik.diet_plan_management.util.AssociationClass;
import com.slawomirlasik.diet_plan_management.util.ExtensionAnnotationAssociationManager;

@AssociationClass(
        role1 = "actor middle",
        role2 = "movie middle",
        target1 = Actor.class,
        target2 = Movie.class
)
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
