package com.slawomirlasik.diet_plan_management.mp2;

import com.slawomirlasik.diet_plan_management.model.DietAdministrator;
import com.slawomirlasik.diet_plan_management.model.DietUser;
import com.slawomirlasik.diet_plan_management.util.ExtensionAnnotationAssociationManager;
import com.slawomirlasik.diet_plan_management.util.ExtensionAssociationManager;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MP2OneToManyAssociation {

    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private static final short flag = 1;

    public static void main(String[] args) {

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

        if (flag == 0) {

            // generate OneToMany assocations
            generateOneToManyLinks();


        } else {
            System.out.println("\n========================================");
            System.out.println("   Printing DietUsers And DietAdministartors   ");
            try {
                // ===========================================
                // show DietUser
                System.out.println("\n========================================");
                ExtensionAssociationManager.printExtension(DietUser.class);

                // ===========================================
                // show DietAdministrator
                System.out.println("\n========================================");
                ExtensionAssociationManager.printExtension(DietAdministrator.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
            } catch (Exception e) {
                e.printStackTrace();
            }


            // show links
            try {
                showOneToManyLinks();
            } catch (Exception exception) {
                exception.printStackTrace();
            }


        }

    }

    private static void showOneToManyLinks() throws Exception {

        System.out.println("\n========================================");
        System.out.println("   Printing DietUsers And DietAdministartors Association (OneToMany) ");
        System.out.println("\n========================================");
        // ===========================================
        // show links for DietUsers
        System.out.println("--------- Links for DietUsers ------------");

        Iterable<DietUser> dietUsers = ExtensionAnnotationAssociationManager.getExtension(DietUser.class);

        for(DietUser dietUser : dietUsers){
            dietUser.printAssociations(System.out);
        }

        // ===========================================
        // show links for DietAdmins
        System.out.println("---------- Links for dietAdmins --------");

        Iterable<DietAdministrator> dietAdministrators = ExtensionAnnotationAssociationManager.getExtension(DietAdministrator.class);

        for(DietAdministrator dietAdministrator : dietAdministrators){
            dietAdministrator.printAssociations(System.out);
        }

    }

    private static void generateOneToManyLinks() {
        // ===========================================
        // create sample DietUsers
        try {
            DietUser dietUser1 = new DietUser(
                    "Jan",
                    "Kowalski",
                    LocalDate.parse("21-02-1995", dateFormatter)
            );
            DietUser dietUser2 = new DietUser(
                    "Paweł",
                    "Nowak",
                    LocalDate.parse("15-04-2001", dateFormatter)
            );
            DietUser dietUser3 = new DietUser(
                    "Filip",
                    "Blok",
                    LocalDate.parse("15-06-1988", dateFormatter)
            );

            // ===========================================
            // create sample DietAdmins
            DietAdministrator dietAdministrator1 = new DietAdministrator(
                    "Maciej",
                    "Sroka",
                    LocalDate.parse("15-06-1978", dateFormatter)
            );

            DietAdministrator dietAdministrator2 = new DietAdministrator(
                    "Stanisław",
                    "Stog",
                    LocalDate.parse("29-09-1968", dateFormatter)
            );

            // ===========================================
            // ad links between them
            dietUser1.addManyToOneLink(dietAdministrator1);
            dietAdministrator1.addOneToManyLink(dietUser2);
            dietUser3.addManyToOneLink(dietAdministrator2);


            // save extensions
            ExtensionAssociationManager.saveExtensionCurrentState();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
