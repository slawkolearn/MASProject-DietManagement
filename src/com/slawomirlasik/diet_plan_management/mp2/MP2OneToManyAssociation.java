package com.slawomirlasik.diet_plan_management.mp2;

import com.slawomirlasik.diet_plan_management.model.DietPlan;
import com.slawomirlasik.diet_plan_management.model.DietPlanDay;
import com.slawomirlasik.diet_plan_management.model.DietUser;
import com.slawomirlasik.diet_plan_management.model.Meal;
import com.slawomirlasik.diet_plan_management.util.ExtensionManager;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MP2OneToManyAssociation {

    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy H:m");

    private static final short flag = 0;


    public static void main(String[] args) {

        if ( flag == 0 ) {
            generateOneToManyLinks();
        } else {
            showOneToManyLinks();
        }

    }

    private static void showOneToManyLinks() {
    }

    private static void generateOneToManyLinks() {
        try {
            System.out.println("Próba wczytania poprzedniego statnu...");
            if (ExtensionManager.loadExtensionsFromFile()) {
                System.out.println("Wczytano poprzedni stan ekstensji...");
            } else {
                System.out.println("Nie udało się wczytać poprzedniego stanu ekstensji...");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {

//            Meal meal1 = new Meal(LocalDate.parse("11-10-1977", dateFormatter));

                LocalDateTime mealDate = LocalDateTime.now();
            System.out.println(mealDate.format(dateFormatter));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
