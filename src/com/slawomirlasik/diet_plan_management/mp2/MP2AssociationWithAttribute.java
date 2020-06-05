package com.slawomirlasik.diet_plan_management.mp2;

import com.slawomirlasik.diet_plan_management.model.Ingredient;
import com.slawomirlasik.diet_plan_management.model.MeasurementUnits;
import com.slawomirlasik.diet_plan_management.model.Recipe;
import com.slawomirlasik.diet_plan_management.model.RecipeIngredient;
import com.slawomirlasik.diet_plan_management.util.ExtensionAnnotationAssociationManager;
import com.slawomirlasik.diet_plan_management.util.ExtensionAssociationManager;

import java.io.IOException;

public class MP2AssociationWithAttribute {

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

        int flag = 1;
        if(flag == 0){
            try {
                generateData();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                printData();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        

    }

    private static void printData() throws Exception {


        System.out.println("----------------------------------------------");
        System.out.println("--------Printing all RecipeIngredients--------");
        System.out.println("----------------------------------------------");

        ExtensionAnnotationAssociationManager.printExtension(RecipeIngredient.class);

        System.out.println("------------------------------------");
        System.out.println("--------Printing all recipes--------");
        System.out.println("------------------------------------");

        ExtensionAnnotationAssociationManager.printExtension(Recipe.class);



        System.out.println("---------------------------------------------------------");
        System.out.println("--------Printing all associations for all recopies--------");
        System.out.println("---------------------------------------------------------");

        Iterable<Recipe> recipes = ExtensionAssociationManager.getExtension(Recipe.class);

        for(Recipe recipe : recipes){
            recipe.printAssociations(System.out);
        }

        System.out.println("---------------------------------------------------------");
        System.out.println("------Printing all associations for all Ingredients------");
        System.out.println("---------------------------------------------------------");


        Iterable<Ingredient> ingredients = ExtensionAssociationManager.getExtension(Ingredient.class);

        for(Ingredient ingredient : ingredients){
            ingredient.printAssociations(System.out);
        }

    }

    private static void generateData() throws Exception {

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
        Ingredient yogurt = new Ingredient("Jogurt Naturalny",20f, 30f, 10f, MeasurementUnits.GRAMS, 100f);
        Ingredient salad = new Ingredient("Sałata",20f, 30f, 10f, MeasurementUnits.GRAMS);


        try {
            // add ingredients to Curry Chicken recipe
            curryChicken.addIngredient(chicken, 200f);
            curryChicken.addIngredient(currySpice);
            curryChicken.addIngredient(olive, 5f);


            // add ingredients to chickenSalat
            chickenSalad.addIngredient(salad);
            chickenSalad.addIngredient(chicken, 180f);
            chickenSalad.addIngredient(olive, 10f);


            // add ingredients to yogurtPancakes
            yogurtPancakes.addIngredient(yogurt, 200f);
            yogurtPancakes.addIngredient(flour, 100f);
            yogurtPancakes.addIngredient(milk, 100f);
            yogurtPancakes.addIngredient(whiteCheese, 50f);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {


            System.out.println("----------------------------------------------");
            System.out.println("--------Printing all RecipeIngredients--------");
            System.out.println("----------------------------------------------");

            ExtensionAnnotationAssociationManager.printExtension(RecipeIngredient.class);

            ExtensionAnnotationAssociationManager.saveExtensionCurrentState();
        }


    }
}
