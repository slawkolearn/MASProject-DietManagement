package com.slawomirlasik.diet_plan_management.exampleActorMovieGroupCompositionAndManyToMany;

public class ExampleManyToMany {

    public static void main(String[] args) {

        try {
            // create sample movies
            Movie terminator = new Movie("Terminator");
            Movie avengers = new Movie("Avengers");
            Movie topgun = new Movie("Top Gun");

            // create sample groups
            Group g1 = new Group(1);
            Group g2 = new Group(2);


            // create sample actors
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

            System.out.println("////////////////////////////////////////////");
            // add many to many link
            tom.addManyToManyLink(topgun);
            topgun.addManyToManyLink(robert);
            mark.addManyToManyLink(topgun);

            terminator.addManyToManyLink(arnold);
            tom.addManyToManyLink(terminator);

            boolean testLinks = true;

            if (testLinks) {


                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                // print links for movies
                System.out.println("Movie `" + topgun.getTitle() + "'");
                topgun.showLinks("has actors", System.out);

//                 print links for actors

            }


        } catch (Exception exception) {
            exception.printStackTrace();
        }


    }
}
