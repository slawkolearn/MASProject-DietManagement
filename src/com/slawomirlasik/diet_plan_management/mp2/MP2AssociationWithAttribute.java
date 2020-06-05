package com.slawomirlasik.diet_plan_management.mp2;

import com.slawomirlasik.diet_plan_management.model.Ingredient;
import com.slawomirlasik.diet_plan_management.model.MeasurementUnits;
import com.slawomirlasik.diet_plan_management.model.Recipe;
import com.slawomirlasik.diet_plan_management.util.ExtensionAnnotationAssociationManager;
import com.slawomirlasik.diet_plan_management.util.ExtensionAssociationManager;

public class MP2AssociationWithAttribute {

    public static void main(String[] args) {

        
        int flag = 0;
        if(flag == 0){
            generateData();
        } else {
            try {
                printData();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        

    }

    private static void printData() throws Exception {

        System.out.println("------------------------------------");
        System.out.println("--------Printing all recipes--------");
        System.out.println("------------------------------------");

        ExtensionAnnotationAssociationManager.printExtension(Recipe.class);


        Iterable<Recipe> recipes = ExtensionAssociationManager.getExtension(Recipe.class);

        System.out.println("---------------------------------------------------------");
        System.out.println("--------Printing all associations for all respies--------");
        System.out.println("---------------------------------------------------------");


        for(Recipe recipe : recipes){
            recipe.printAssociations(System.out);
        }

    }

    private static void generateData() {

        // crate sample Recipes
        Recipe curryChicken = new Recipe("Kurczak Curry");
        Recipe chickenSalad = new Recipe("Sałatka z kurczaka");
        Recipe yogurtPancakes = new Recipe("Pancake z Jogurtem");

        // create sample Ingredient
        Ingredient chicken = new Ingredient("Kurczak",10f, 10f, 20f, MeasurementUnits.GRAMS, 100f);
        Ingredient whiteCheese = new Ingredient("Biały Ser",20f, 30f, 10f, MeasurementUnits.GRAMS, 100f);
        Ingredient milk = new Ingredient("Mleko",20f, 30f, 10f, MeasurementUnits.MILLILITERS,100f);
        Ingredient currySpice = new Ingredient("Przyprawa Curry",20f, 30f, 10f, MeasurementUnits.GRAMS);
        Ingredient olive = new Ingredient("Oliwa z oliwek",20f, 30f, 10f, MeasurementUnits.MILLILITERS, 10f);
        Ingredient flour = new Ingredient("Mąka",20f, 30f, 10f, MeasurementUnits.MILLILITERS, 10f);

        try {
            // add ingredients to Curry Chicken recipe
            curryChicken.addIngredient(chicken, 200f);
            curryChicken.addIngredient(currySpice);
            curryChicken.addIngredient(olive, 5f);


            // add ingredients to chickenSalat

            // add ingredients to yogurtPancakes
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
