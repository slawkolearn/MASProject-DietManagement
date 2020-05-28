package com.slawomirlasik.diet_plan_management.mp2;

import com.slawomirlasik.diet_plan_management.temp.Actor;
import com.slawomirlasik.diet_plan_management.temp.Group;
import com.slawomirlasik.diet_plan_management.temp.Movie;
import com.slawomirlasik.diet_plan_management.util.ExtensionAssociationManager;

public class MP2CompositionAndAssociationWithQualifierExample {

    public static void main(String[] args) {

        // create sample groups
        Group g1 = new Group(1);
        Group g2 = new Group(2);

        try {

            // create sample actors in the specific groups
            Actor arnold = g1.addActor(
                    "Arnold",
                    "Schwarzenegger"
            );

            Actor tom = g1.addActor(
                    "Tom",
                    "Cruise"
            );

            Actor robert = g1.addActor(
                    "Robert",
                    "Downey Jr."
            );

            Actor mark = g2.addActor(
                    "Mark",
                    "Ruffalo"
            );
            ;


            // create sample movies
            Movie terminator = new Movie("Terminator");
            Movie avengers = new Movie("Avengers");
            Movie topgun = new Movie("Top Gun");

            // add links
            terminator.addLink(
                    Movie.ASSOCIATION_MANY_TO_MANY_ROLE_ACTORS,
                    Actor.ASSOCIATION_MANY_TO_MANY_ROLE_ACTORS,
                    arnold,
                    arnold.getQualifier()
            );
//
            avengers.addLink(
                    Movie.ASSOCIATION_MANY_TO_MANY_ROLE_ACTORS,
                    Actor.ASSOCIATION_MANY_TO_MANY_ROLE_ACTORS,
                    robert,
                    robert.getQualifier()
            );
            avengers.addLink(
                    Movie.ASSOCIATION_MANY_TO_MANY_ROLE_ACTORS,
                    Actor.ASSOCIATION_MANY_TO_MANY_ROLE_ACTORS,
                    mark,
                    mark.getQualifier()
            );

            topgun.addLink(
                    Movie.ASSOCIATION_MANY_TO_MANY_ROLE_ACTORS,
                    Actor.ASSOCIATION_MANY_TO_MANY_ROLE_ACTORS,
                    tom,
                    tom.getQualifier()
            );


            // show links

            System.out.println("\nNormal ManyToMany association");
            try {
                ExtensionAssociationManager[] terminatorActors = terminator.getLinks(Movie.ASSOCIATION_MANY_TO_MANY_ROLE_ACTORS);

                System.out.println("Actors for avengers movie:");
                for (ExtensionAssociationManager actor : terminatorActors) {
                    System.out.println("   " + actor);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


            System.out.println("\nComposition");

            try {
                ExtensionAssociationManager[] participantsOfGroup1 =
                        g1.getLinks(Actor.COMPOSITION_ROLE_GROUP);

                System.out.println("Participants of group 1:");
                for (ExtensionAssociationManager participant : participantsOfGroup1) {
                    System.out.println("   " + participant);
                }

                ExtensionAssociationManager[] participantsOfGroup2 =
                        g2.getLinks(Actor.COMPOSITION_ROLE_GROUP);

                System.out.println("Participants of group 2:");
                for (ExtensionAssociationManager participant : participantsOfGroup2) {
                    System.out.println("   " + participant);
                }

                // qualified association
                System.out.println("Qualified association");

                // print roles for terminator movie
                System.out.println("Print roles for terminator movie:");
                avengers.printRoles();

                // printing links for "movie_actors" role
                avengers.showLinks(Movie.ASSOCIATION_MANY_TO_MANY_ROLE_ACTORS, System.out);

                // show qualifiers for role name "movie_actors"
                avengers.showQualifiers(Movie.ASSOCIATION_MANY_TO_MANY_ROLE_ACTORS, System.out);

                // getting linked object by qualifier with is initials of an actor
                System.out.println("Linked Actor from Avengers movie by qualifier which is his initials ");
                System.out.println(avengers.getLinkedObject(Movie.ASSOCIATION_MANY_TO_MANY_ROLE_ACTORS, "MR"));



            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
