package com.slawomirlasik.diet_plan_management.mp2;

import com.slawomirlasik.diet_plan_management.model.DietType;
import com.slawomirlasik.diet_plan_management.model.Recipe;
import com.slawomirlasik.diet_plan_management.util.ExtensionAssociationManager;
import com.slawomirlasik.diet_plan_management.util.ExtensionManager;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class MP2ManyToManyAssociation {

    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private static final short flag = 1;

    public static void main(String[] args) {

        // load extension
        try {
            System.out.println("Próba wczytania poprzedniego statnu...");
            if (ExtensionManager.loadExtensionsFromFile()) {
                System.out.println("Wczytano poprzedni stan ekstensji...");
            } else {
                System.out.println("Nie udało się wczytać poprzedniego stanu ekstensji...");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // generate association
        if (flag == 0) {

            // generate OneToMany assocations
            generateManyToManyLinks();

        // show association
        } else {

            // show links
            showManyToManyLinks();


        }

    }

    private static void showManyToManyLinks() {

        System.out.println("\n========================================");
        System.out.println("   Printing Recipe And DietType Association (ManyToMany) ");
        System.out.println("\n========================================");
        // ===========================================

        try {

            // show DietTypes
            ExtensionAssociationManager.printExtension(DietType.class);

            // show Recipes
            ExtensionAssociationManager.printExtension(Recipe.class);

        } catch (Exception e) {
            e.printStackTrace();
        }


        // Show links for role "concerns" for all DietTypes
        Iterable<DietType> dietTypes = ExtensionAssociationManager.getExtension(DietType.class);

        System.out.println("\n========================================");
        System.out.println("Printing all association for each DietType for role `concerns`:");

        for( DietType dietType : dietTypes ) {

            try {
                dietType.showLinks("concerns", System.out);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // SHow links for role "is Type of" for all Recipes

        System.out.println("\n========================================");
        System.out.println("Printing all association for each DietType for role `is Type of`:");

        Iterable<Recipe> recipes = ExtensionAssociationManager.getExtension(Recipe.class);

        for( Recipe recipe : recipes ) {

            try {
                recipe.showLinks("is Type of", System.out);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private static void generateManyToManyLinks() {
        // ===========================================
        // create sample dietTypes
        DietType muscleTrainingDietType = new DietType("Dieta treningowa",
                "Dieta przeznaczona dla osób, którzy chcą uczęszczać na siłownię i zbudować masę mięśniową");
        DietType fatReductionDietType = new DietType("Dieta na obniżenie tkanki tłuszczowej",
                "Dieta przeznaczona dla osób które chcą obniżyć swoją wagę, i zredukować tkankę tłuszczową");
        DietType glutenFreeDietType = new DietType("Dieta bezglutenowa",
                "Dieta przeznaczona dla osób nie tolerująca glutenu");

        // crate sample Recipes
        Recipe curryChicken = new Recipe("Kurczak Curry");
        Recipe chickenSalad = new Recipe("Sałatka z kurczaka");
        Recipe yogurtPancakes = new Recipe("Pancake z Jogurtem");

        // create links

        muscleTrainingDietType.addLink("concerns", "is Type of", curryChicken);
        muscleTrainingDietType.addLink("concerns", "is Type of", chickenSalad);

        fatReductionDietType.addLink("concerns", "is Type of", chickenSalad);
        fatReductionDietType.addLink("concerns", "is Type of", curryChicken);
        fatReductionDietType.addLink("concerns", "is Type of", yogurtPancakes);

        glutenFreeDietType.addLink("concerns", "is Type of", chickenSalad);
        glutenFreeDietType.addLink("concerns", "is Type of", yogurtPancakes);


        // save extensions
        try {
            ExtensionAssociationManager.saveExtensionCurrentState();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
