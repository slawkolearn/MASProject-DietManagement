package com.slawomirlasik.diet_plan_management.mp2;

import com.slawomirlasik.diet_plan_management.model.DietPlan;
import com.slawomirlasik.diet_plan_management.model.DietPlanDay;
import com.slawomirlasik.diet_plan_management.util.ExtensionAnnotationAssociationManager;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MP2Composition {

    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");


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

        int flag = 0;
        if(flag == 0){
            try {
                generateData();
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

    private static void printData() {
    }

    private static void generateData() {


        // create sample DietPlan objects
        DietPlan dietPlan = new DietPlan();
        dietPlan.setFrom(LocalDate.parse("14-09-2019",dateFormatter));
        dietPlan.setTo(LocalDate.parse("14-12-2019", dateFormatter));

        // create sample DietPlanDays
        try {
            DietPlanDay day1 = DietPlanDay.createDietPlanDay(dietPlan,100f, 100f, 100f, dietPlan.getFrom().plusDays(0) );
            DietPlanDay day2 = DietPlanDay.createDietPlanDay(dietPlan,100f, 100f, 100f, dietPlan.getFrom().plusDays(1) );
            DietPlanDay day3 = DietPlanDay.createDietPlanDay(dietPlan,100f, 100f, 100f, dietPlan.getFrom().plusDays(2) );
            DietPlanDay day4 = DietPlanDay.createDietPlanDay(dietPlan,100f, 100f, 100f, dietPlan.getFrom().plusDays(3) );


            ExtensionAnnotationAssociationManager.printExtension(DietPlan.class);
            ExtensionAnnotationAssociationManager.printExtension(DietPlanDay.class);

            dietPlan.printAssociations(System.out);

            day1.printAssociations(System.out);

        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
