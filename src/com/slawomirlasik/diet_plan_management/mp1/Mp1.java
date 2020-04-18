package com.slawomirlasik.diet_plan_management.mp1;

import com.slawomirlasik.diet_plan_management.model.DietType;
import com.slawomirlasik.diet_plan_management.model.Recipe;
import com.slawomirlasik.diet_plan_management.model.RecipeIngredient;

public class Mp1 {
    public static void main(String[] args) {

        // TODO:SL ekstensja
        // TODO:SL ekstensja trwałość
        // TODO:SL atr. złożony
        // TODO:SL atr. powtarzalny
        // TODO:SL atr.klasowy
        // TODO:SL atr.pochodny
        // TODO:SL metoda klasowa
        // TODO:SL override (przesłonięcie)
        // TODO:SL overload (przeciążenie)

        // atrybut opcjonalny
        System.out.println("-----------------------------");
        System.out.println("Przykład atrybuty opcjonalnego");
        RecipeIngredient recipeIngredientPresent = new RecipeIngredient(10f);
        RecipeIngredient recipeIngredientAbsent = new RecipeIngredient();


        System.out.println(recipeIngredientAbsent);
        System.out.println(recipeIngredientPresent);
        System.out.println("-----------------------------");
        // atrybut złożony

        // atrybut powtarzalny
        DietType muscleTrainingDietType = new DietType("Dieta treningowa",
                "Dieta przeznaczona dla osób, którzy chcą uczęszczać na siłownię i zbudować masę mięśniową");
        DietType fatReductionDietType = new DietType("Dieta na obniżenie tkanki tłuszczowej",
                "Dieta przeznaczona dla osób które chcą obniżyć swoją wagę, i zredukować tkankę tłuszczową");
        DietType glutenFreeDietType = new DietType("Dieta bezglutenowa",
                "Dieta przeznaczona dla osób nie tolerująca glutenu");

        Recipe chickenSalat = new Recipe(muscleTrainingDietType, fatReductionDietType);
        Recipe fetaCheeseSalat = new Recipe(glutenFreeDietType);

        System.out.println("Poniżej wyświetlamy obiekty klasy Recipe z powtarzalnym atrybutem dietTypes");
        System.out.println(chickenSalat);
        System.out.println(fetaCheeseSalat);

        // atrybut klasowy

        // atrybut pochodny
        // Metoda klasowa
        // przesłonięcie (overriding)
        // przeciążenie (overload)

    }
}
