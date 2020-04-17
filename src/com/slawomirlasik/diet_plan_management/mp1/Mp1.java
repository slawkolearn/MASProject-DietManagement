package com.slawomirlasik.diet_plan_management.mp1;

import com.slawomirlasik.diet_plan_management.model.RecipeIngredient;

public class Mp1 {
    public static void main(String[] args) {

        // atrybut opcjonalny
        System.out.println("Przykład atrybuty opcjonalnego");
        RecipeIngredient recipeIngredientPresent = new RecipeIngredient(10f);
        RecipeIngredient recipeIngredientAbsent = new RecipeIngredient();


        System.out.println(recipeIngredientAbsent);
        System.out.println(recipeIngredientPresent);
        System.out.println("-----------------------------");
        // atrybut złożony

        // atrybut powtarzalny
        // atrybut klasowy
        // atrybut pochodny
        // Metoda klasowa
        // przesłonięcie (overriding)
        // przeciążenie (overload)

    }
}
