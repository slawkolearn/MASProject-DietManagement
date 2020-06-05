package com.slawomirlasik.diet_plan_management.mp2;

import com.slawomirlasik.diet_plan_management.model.DietType;
import com.slawomirlasik.diet_plan_management.model.Recipe;
import com.slawomirlasik.diet_plan_management.util.ExtensionAnnotationAssociationManager;
import com.slawomirlasik.diet_plan_management.util.ExtensionAssociationManager;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class MP2ManyToManyAssociation {

    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private static final short flag = 1;

    public static void main(String[] args) {

        // load extension
        try {
            System.out.println("Próba wczytania poprzedniego statnu...");
            if (ExtensionAnnotationAssociationManager.loadExtensionsFromFile()) {
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

            // Show links for role "concerns" for all DietTypes
            Iterable<DietType> dietTypes = ExtensionAnnotationAssociationManager.getExtension(DietType.class);

            for (DietType dietType : dietTypes) {
                dietType.printRoles();
                if (dietType.getRoles().contains("concerns")) {
                    dietType.showLinks("concerns", System.out);
                }

            }

            // SHow links for role "is Type of" for all Recipes

            Iterable<Recipe> recipes = ExtensionAnnotationAssociationManager.getExtension(Recipe.class);

            for (Recipe recipe : recipes) {
                recipe.printRoles();
                if (recipe.getRoles().contains("is of a type")) {
                    recipe.showLinks("is of a type", System.out);
                }

            }


        } catch (Exception e) {
            e.printStackTrace();
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

        try {

            curryChicken.addDietType(muscleTrainingDietType);
            curryChicken.addDietType(fatReductionDietType);
            curryChicken.addDietType(glutenFreeDietType);

            chickenSalad.addManyToManyLink(fatReductionDietType);
            chickenSalad.addManyToManyLink(glutenFreeDietType);

            yogurtPancakes.addManyToManyLink(fatReductionDietType);
            yogurtPancakes.addManyToManyLink(muscleTrainingDietType);

        } catch (Exception exception) {
            exception.printStackTrace();
        }


        // save extensions
        try {
            ExtensionAnnotationAssociationManager.saveExtensionCurrentState();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
