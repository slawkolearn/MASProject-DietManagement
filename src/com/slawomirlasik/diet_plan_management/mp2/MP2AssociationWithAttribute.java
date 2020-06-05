package com.slawomirlasik.diet_plan_management.mp2;

import com.slawomirlasik.diet_plan_management.model.Ingredient;
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
        Ingredient chicken = new Ingredient(10f, 10f, 20f);
    }
}
