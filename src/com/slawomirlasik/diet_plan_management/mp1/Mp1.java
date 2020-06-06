package com.slawomirlasik.diet_plan_management.mp1;

import com.slawomirlasik.diet_plan_management.model.*;
import com.slawomirlasik.diet_plan_management.mp3.model.abstractpolymorphism.DietAdministratorAbstractPolymorphism;
import com.slawomirlasik.diet_plan_management.mp3.model.abstractpolymorphism.DietUserAbstractPolymorphism;
import com.slawomirlasik.diet_plan_management.util.ExtensionAnnotationAssociationManager;
import com.slawomirlasik.diet_plan_management.util.ExtensionManager;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Mp1 {
    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static void main(String[] args) {


        // TODO:SL atr. złożony
        // esktensja trwałość - wczytywanie
            // próbujemy wczytać ze standarowego pliku zawartość poprzedniej extensji (wszystkich)
        try {
            System.out.println("Próba wczytania poprzedniego statnu...");
            if(ExtensionAnnotationAssociationManager.loadExtensionsFromFile()){
                System.out.println("Wczytano poprzedni stan ekstensji...");
            }else{
                System.out.println("Nie udało się wczytać poprzedniego stanu ekstensji...");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // atrybut opcjonalny
        System.out.println("-----------------------------");
        System.out.println("Przykład atrybutu opcjonalnego");
        RecipeIngredient recipeIngredientPresent = new RecipeIngredient(null, null );
        RecipeIngredient recipeIngredientAbsent = new RecipeIngredient(null, null);


        System.out.println(recipeIngredientPresent);
        System.out.println(recipeIngredientAbsent);
        System.out.println("-----------------------------");
        // atrybut powtarzalny
        DietType muscleTrainingDietType = new DietType("Dieta treningowa",
                "Dieta przeznaczona dla osób, którzy chcą uczęszczać na siłownię i zbudować masę mięśniową");
        DietType fatReductionDietType = new DietType("Dieta na obniżenie tkanki tłuszczowej",
                "Dieta przeznaczona dla osób które chcą obniżyć swoją wagę, i zredukować tkankę tłuszczową");
        DietType glutenFreeDietType = new DietType("Dieta bezglutenowa",
                "Dieta przeznaczona dla osób nie tolerująca glutenu");

        System.out.println("Poniżej wyświetlamy obiekty klasy Recipe z powtarzalnym atrybutem dietTypes");


        System.out.println("-----------------------------");

        // atrybut klasowy
        System.out.println("Poniżej przedstawiamy przykład użycia atrybutu klasowego z klasy Person MINMAL_AGE...");
        System.out.println("Osoba posiadająca powyżej 18 lat:");
        DietAdministratorAbstractPolymorphism dietAdmin1 = null;
        try {
            dietAdmin1 = new DietAdministratorAbstractPolymorphism(
                    "Pablo", "Picasso", LocalDate.parse("11-10-1977", dateFormatter)
            );
            System.out.println(dietAdmin1);

            System.out.println("Próba stworzenia osoby poniżej 18 roku życia:");
            DietAdministratorAbstractPolymorphism dietAdmin2 = new DietAdministratorAbstractPolymorphism(
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
        System.out.println("Przykład zastosowania metody klsowej getAllDietPlans: ");
        // Tutaj symulujemy działanie metody z klasy DietAdministrator, która tworzy dla konkretnego
            // użytkownika diety obiekt klasy DietPLan, który odpowiada planowi diety użytkownika. W niej
                // będą przechowywane DietPlanDays odpowiadające odpowiednim dniom w diecie
        // Tworzymy Listę z konkretnymi dniami. dla ułatwienia wszyskie dni mają taką samą wartość odżywczą
/*        List<DietPlanDay> dietPlanDayList1 = generateDietPlanDayListStub(10);
        List<DietPlanDay> dietPlanDayList2 = generateDietPlanDayListStub(20);*/
        // Tworzymy nową dietę dla jakiegoś użytkownika (każda dieta będzie indywidualna dla użytkonika)
//        DietPlan dietPlan1 = new DietPlan(dietPlanDayList1);
//        DietPlan dietPlan2 = new DietPlan(dietPlanDayList1);
        // wyświetlamy wszyskie dostępne diety jakie mamy dostępne w systemie
        System.out.println("Oto wszystkie dostępne diety w systemie wszystkich użytkowników:");
//        DietPlan.getAllDietPlans().forEach(System.out::println);
        System.out.println("-----------------------------");
        // atrybut złożony
         // atrybut diploma w klasie DietAdministrator jest to klasa opisująca, w tym przypadku, dyplom
            // administratora diety
        System.out.println("Przykład użycia atrybutu złożonego:");
        printSkillsOfDietManager(dietAdmin1);
        System.out.println("-----------------------------");
        // przesłonięcie (overriding)
         // metoda toString() w klasach DietAdministrator i DietUser
            // stwórzmy bądź weźmy jakąś obiekt klasy DietAdministrator i DietUser
        DietUserAbstractPolymorphism dietUser1 = null;
        DietAdministratorAbstractPolymorphism dietAdministrator1 = null;
        if(ExtensionManager.hasExtensionType(DietUserAbstractPolymorphism.class)){
            dietUser1 = ExtensionManager.getExtension(DietUserAbstractPolymorphism.class).iterator().next();
        }else{
            try {
                dietUser1 = new DietUserAbstractPolymorphism("Paweł", "Kłos", LocalDate.parse("14-11-1989", dateFormatter));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if(ExtensionManager.hasExtensionType(DietAdministratorAbstractPolymorphism.class)){
            dietAdmin1 = ExtensionManager.getExtension(DietAdministratorAbstractPolymorphism.class).iterator().next();
        }else{
            try {
                dietAdmin1 = new DietAdministratorAbstractPolymorphism("Grzegorz", "Nowak",
                        LocalDate.parse("11-01-2012", dateFormatter)
                );

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
            ExtensionAnnotationAssociationManager.saveExtensionCurrentState();
            System.out.println("Zapisano pomyślnie do pliku  " + ExtensionManager.EXTENSION_FILE + " !");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void printSkillsOfDietManager(DietAdministratorAbstractPolymorphism dietAdmin1) {
        System.out.printf("Administrator diety %s posiada umiejętności: [ ",
                dietAdmin1.getName() + " " +  dietAdmin1.getLastName());
        System.out.println("].");
    }

    private static Diploma generateDiploma(String schoolName, String date, List<String> asList) {
        Set<String> skillSet = new HashSet<>();
        asList.stream().forEach(element -> skillSet.add(element));
        return new Diploma(schoolName, LocalDate.parse(date, dateFormatter), skillSet);
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
/*

    private static List<DietPlanDay> generateDietPlanDayListStub(int startAmount) {
        List<DietPlanDay> resultList = new ArrayList<>();
        for(int i = 0; i < 90; i++){
            resultList.add(new DietPlanDay(
                    startAmount - 1, startAmount - 2, startAmount - 3,
                    LocalDate.now().plusDays(i)
            ));
        }
        return resultList;
    }*/

    public static String getYearStringPolish(Integer age) {
        return
                (age == 1 ? "rok" :
                        age % 100 >= 12 && age % 100 <= 14 ? "lat" :
                            age % 10 >= 2 && age % 10 <= 4 ? "lata" : "lat"
                );
    }
}
