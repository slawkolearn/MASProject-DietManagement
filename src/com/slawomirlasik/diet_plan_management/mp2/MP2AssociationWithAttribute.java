package com.slawomirlasik.diet_plan_management.mp2;

import com.slawomirlasik.diet_plan_management.model.Ingredient;
import com.slawomirlasik.diet_plan_management.model.MeasurementUnits;
import com.slawomirlasik.diet_plan_management.model.Recipe;

public class MP2AssociationWithAttribute {

    public static void main(String[] args) {

        generateData();

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

        // add ingredients to Curry Chicken recipe
        try {
            curryChicken.addIngredient(chicken, 200f);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
