package com.slawomirlasik.diet_plan_management.mp1;

import com.slawomirlasik.diet_plan_management.model.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
        // Tutaj symulujemy działanie metody z klasy DietAdministrator, która tworzy dla konkretnego
            // użytkownika diety obiekt klasy DietPLan, który odpowiada planowi diety użytkownika. W niej
                // będą przechowywane DietPlanDays odpowiadające odpowiednim dniom w diecie
        // Tworzymy Listę z konkretnymi dniami. dla ułatwienia wszyskie dni mają taką samą wartość odżywczą
        List<DietPlanDay> dietPlanDayList1 = generateDietPlanDayListStub(10);
        List<DietPlanDay> dietPlanDayList2 = generateDietPlanDayListStub(20);
        // Tworzymy nową dietę dla jakiegoś użytkownika (każda dieta będzie indywidualna dla użytkonika)
        DietPlan dietPlan1 = new DietPlan(dietPlanDayList1);
        DietPlan dietPlan2 = new DietPlan(dietPlanDayList1);
        // wyświetlamy wszyskie dostępne diety jakie mamy dostępne w systemie
        DietPlan.getAllDietPlans().forEach(System.out::println);
        System.out.println("-----------------------------");
        // przesłonięcie (overriding)
        // przeciążenie (overload)

    }

    private static List<DietPlanDay> generateDietPlanDayListStub(int startAmount) {
        List<DietPlanDay> resultList = new ArrayList<>();
        for(int i = 0; i < 90; i++){
            resultList.add(new DietPlanDay(
                    startAmount - 1, startAmount - 2, startAmount - 3,
                    LocalDate.now().plusDays(i)
            ));
        }
        return resultList;
    }

    public static String getYearStringPolish(Integer age) {
        return
                (age == 1 ? "rok" :
                        age % 100 >= 12 && age % 100 <= 14 ? "lat" :
                            age % 10 >= 2 && age % 10 <= 4 ? "lata" : "lat"
                );
    }
}
