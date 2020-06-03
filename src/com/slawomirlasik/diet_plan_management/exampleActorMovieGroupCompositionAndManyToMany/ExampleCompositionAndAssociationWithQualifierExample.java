package com.slawomirlasik.diet_plan_management.exampleActorMovieGroupCompositionAndManyToMany;

public class ExampleCompositionAndAssociationWithQualifierExample {

    public static void main(String[] args) {

        // create sample groups
        Group g1 = new Group(1);
        Group g2 = new Group(2);

        try {

            // create sample actors in the specific groups
            // actor can be created only when the whole exists
            // in this case the existing whole can crate parts
            // TODO:SL make adding new parts as a generic method in ExtensionAnnotationAssociationManager rather in POJOs
            Actor arnold = g1.addActor(
                    "Arnold",
                    "Schwarzenegger"
            );

            // exception -> part cannot be shared
/*            Actor arnold2 = g2.addActor(
                    "Arnold",
                    "Schwarzenegger"
            );*/

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



            // create sample movies
            Movie terminator = new Movie("Terminator");
            Movie avengers = new Movie("Avengers");
            Movie topgun = new Movie("Top Gun");

            // trying creating actor that has no proper whole
/*            Actor twinky = Actor.createActor(terminator, "Twinky", "Winky");
            System.out.println(twinky);
            OUTPUT : java.lang.Exception: There is no valid composition association between whole Movie and part Actor
            */

            // trying to create actor that aleady exists with proper whole
/*            Actor robert2 = Actor.createActor(g1, "Robert", "Downey Jr.");
            System.out.println(robert2);
            OUTPUT: java.lang.Exception: The part is already connected to a whole!

            */

            // trying to create actor (that does not exists) with proper whole outside the whole
            Actor drNo = Actor.createActor(g2, "Dr.", "No");
            System.out.println(drNo);

            // print links between group and actor composition

            g1.printRoles();
            g2.printRoles();

            g1.showLinks("actors", System.out);

            arnold.printRoles();
            arnold.showLinks("group", System.out);

            // add links between actors and movies
            // Its with qualifier
            //
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++");
            avengers.addLink(robert);

            arnold.addLink(terminator);

            robert.addLink(topgun);


            // show links
            robert.showLinks("movies", System.out);

            topgun.printRoles();
            System.out.println(topgun.getLinkedObject("actor", robert.getQualifier()));


        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Ending the program normally");

    }


}
