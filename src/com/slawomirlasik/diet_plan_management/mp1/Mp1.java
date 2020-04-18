package com.slawomirlasik.diet_plan_management.mp1;

import com.slawomirlasik.diet_plan_management.model.*;
import com.slawomirlasik.diet_plan_management.util.ExtensionManager;
import com.sun.org.glassfish.external.statistics.RangeStatistic;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Mp1 {
    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static void main(String[] args) {


        // TODO:SL ekstensja
        // TODO:SL ekstensja trwałość
        // TODO:SL atr. złożony
        // TODO:SL overload (przeciążenie)
        // esktensja trwałość - wczytywanie
            // próbujemy wczytać ze standarowego pliku zawartość poprzedniej extensji (wszystkich)
        try {
            System.out.println("Próba wczytania poprzedniego statnu...");
            ExtensionManager.loadExtensionsFromFile();
            System.out.println("Wczytano poprzedni stan ekstensji...");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // atrybut opcjonalny
        System.out.println("-----------------------------");
        System.out.println("Przykład atrybuty opcjonalnego");
        RecipeIngredient recipeIngredientPresent = new RecipeIngredient(10f);
        RecipeIngredient recipeIngredientAbsent = new RecipeIngredient();


        System.out.println(recipeIngredientPresent);
        System.out.println(recipeIngredientAbsent);
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
                    "Pablo", "Picasso", LocalDate.parse("11-10-1977", dateFormatter),
                    "Uniwersytet Warszawski"
            );
            System.out.println(dietAdmin1);

            System.out.println("Próba stworzenia osoby poniżej 18 roku życia:");
            DietAdministrator dietAdmin2 = new DietAdministrator(
                    "Krzysiu", "Laskowski", LocalDate.parse("31-12-2006", dateFormatter),
                    "UKSW"
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
         // metoda toString() w klasach DietAdministrator i DietUser
            // stwórzmy bądź weźmy jakąś obiekt klasy DietAdministrator i DietUser
        DietUser dietUser1 = null;
        DietAdministrator dietAdministrator1 = null;
        if(ExtensionManager.hasExtensionType(DietUser.class)){
            dietUser1 = ExtensionManager.getExtension(DietUser.class).iterator().next();
        }else{
            try {
                dietUser1 = new DietUser("Paweł", "Kłos", LocalDate.parse("14-11-1989", dateFormatter));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        dietUser1.addStatistic(generateRandomDietUserStatistic());

        if(ExtensionManager.hasExtensionType(DietAdministrator.class)){
            dietAdmin1 = ExtensionManager.getExtension(DietAdministrator.class).iterator().next();
        }else{
            try {
                dietAdmin1 = new DietAdministrator("Grzegorz", "Nowak",
                        LocalDate.parse("11-01-2012", dateFormatter),
                        "Szkolenie Online \"Zdrowie i TY\"");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("Dane Użytkownika:");
        System.out.println(dietUser1);
        System.out.println("Dane Administratora Diety:");
        System.out.println(dietAdmin1);
        System.out.println("-----------------------------");
        // przeciążenie (overload)
            // wykorzystamy referencję do obiektu klasy DietUser by dodać jedną bądź listę statystyk (np. z kilku poprzednich dni)
            // dodajmy najpierw jedną statystykę
            // wyświetlmy ile ma obecnie statystyk dany user
        System.out.printf("Obecnie %s ma %d  statystyk%n", dietUser1.getName(), dietUser1.getUserStatistics().size());
        dietUser1.addStatistic(generateRandomDietUserStatistic());
            // a teraz listę statystyk
        dietUser1.addStatistic(Arrays.asList(
                generateRandomDietUserStatistic(),
                generateRandomDietUserStatistic()
        ));

        System.out.printf("Po dodaniu statystyk obecnie %s ma %d statystyk%n", dietUser1.getName(), dietUser1.getUserStatistics().size());
        System.out.println(dietUser1);
        System.out.println("-----------------------------");
        // ekstensje
        Class extensionKeyToShow = DietStatistics.class;
        System.out.printf("Obecnie ekstensje typu %s to:%n", extensionKeyToShow.getSimpleName());
        ExtensionManager.getExtension(extensionKeyToShow).forEach(System.out::println);
        System.out.println("-----------------------------");
        // ektstensje trwałość - zapis
        System.out.println("Zapisujemy stan ekstensji - dane mogą być zapisane ponownie te same podczas testowania!!!");
        try {
            ExtensionManager.saveExtensionCurrentState();
            System.out.println("Zapisano pomyślnie do pliku  " + ExtensionManager.EXTENSION_FILE + " !");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static DietStatistics generateRandomDietUserStatistic() {
        Random radomizer = new Random();
        return new DietStatistics(
                140f + radomizer.nextFloat()*100,
                30 + radomizer.nextFloat()*100,
                50 + radomizer.nextFloat()*100,
                LocalDate.now()
        );
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
