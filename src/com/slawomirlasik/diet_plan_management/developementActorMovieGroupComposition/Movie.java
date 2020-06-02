package com.slawomirlasik.diet_plan_management.developementActorMovieGroupComposition;

import com.slawomirlasik.diet_plan_management.util.ExtensionAssociationManager;

public class Movie extends ExtensionAssociationManager {

    public static final String ASSOCIATION_MANY_TO_MANY_ROLE_ACTORS= "movie_actors";

    public enum associations {
        MOVIE_ACTORS
    };


    private String title;

    public Movie() {
        super();
    }

    public Movie(String title) {
        super();
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                '}';
    }
}
