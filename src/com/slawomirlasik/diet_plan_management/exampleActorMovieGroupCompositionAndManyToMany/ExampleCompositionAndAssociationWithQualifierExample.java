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

            g1.printRoles();
            g2.printRoles();

            g1.showLinks("actors", System.out);


            // create sample movies
            Movie terminator = new Movie("Terminator");
            Movie avengers = new Movie("Avengers");
            Movie topgun = new Movie("Top Gun");

            // add links


            // show links


            } catch (Exception e) {
                e.printStackTrace();
            }

        System.out.println("Ending the program normally");

    }


}
