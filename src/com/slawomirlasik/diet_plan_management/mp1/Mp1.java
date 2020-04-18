package com.slawomirlasik.diet_plan_management.mp1;

import com.slawomirlasik.diet_plan_management.model.DietAdministrator;
import com.slawomirlasik.diet_plan_management.model.DietType;
import com.slawomirlasik.diet_plan_management.model.Recipe;
import com.slawomirlasik.diet_plan_management.model.RecipeIngredient;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Mp1 {
    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static void main(String[] args) {


        // TODO:SL ekstensja
        // TODO:SL ekstensja trwałość
        // TODO:SL atr. złożony
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

        System.out.println("-----------------------------");

        // atrybut klasowy
        System.out.println("Poniżej przedstawiamy przykład użycia atrybutu klasowego z klasy Person MINMAL_AGE...");
        System.out.println("Osoba posiadająca powyżej 18 lat:");
        DietAdministrator dietAdmin1 = null;
        try {
            dietAdmin1 = new DietAdministrator(
                    "Pablo", "Picasso", LocalDate.parse("11-10-1977", dateFormatter)
            );
            System.out.println(dietAdmin1);

            System.out.println("Próba stworzenia osoby poniżej 18 roku życia:");
            DietAdministrator dietAdmin2 = new DietAdministrator(
                    "Krzysiu", "Laskowski", LocalDate.parse("31-12-2006", dateFormatter)
            );
            // wyjątek -> osoba Krzysiu będzie miała poniżej 18 lat dla daty 31-12-2006
            System.out.println(dietAdmin2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("-----------------------------");
        // atrybut pochodny
        System.out.println("przykład użycia atrybutu pochodnego...");
        System.out.println("Wiek, który jest obliczany na bierząco z daty urodzenia i obecnej daty: ");
        System.out.printf("%s ma obecnie %d %s%n",
                dietAdmin1.getName(), dietAdmin1.getAge(), getYearStringPolish(dietAdmin1.getAge()));
        System.out.println("-----------------------------");
        // Metoda klasowa
        // przesłonięcie (overriding)
        // przeciążenie (overload)

    }

    public static String getYearStringPolish(Integer age) {
        return
                (age == 1 ? "rok" :
                        age % 100 >= 12 && age % 100 <= 14 ? "lat" :
                            age % 10 >= 2 && age % 10 <= 4 ? "lata" : "lat"
                );
    }
}
