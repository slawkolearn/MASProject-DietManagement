package com.slawomirlasik.diet_plan_management.mp2;

import com.slawomirlasik.diet_plan_management.model.DietAdministrator;
import com.slawomirlasik.diet_plan_management.model.DietUser;
import com.slawomirlasik.diet_plan_management.util.ExtensionAssociationManager;
import com.slawomirlasik.diet_plan_management.util.ExtensionManager;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class MP2ManyToManyAssociation {

    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private static final short flag = 1;

    public static void main(String[] args) {

        // load extension
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

        // generate association
        if (flag == 0) {

            // generate OneToMany assocations
            generateManyToManyLinks();

        // show association
        } else {

            // show links
            showOneToManyLinks();


        }

    }

    private static void showOneToManyLinks() {

        System.out.println("\n========================================");
        System.out.println("   Printing DietUsers And DietAdministartors Association (OneToMany) ");
        System.out.println("\n========================================");
        // ===========================================
        // show links for DietUsers

        // get DietUsers
        Iterable<DietUser> dietUsers = ExtensionAssociationManager.getExtension(DietUser.class);

        // Print links for each DietUser
        for (DietUser dietUser : dietUsers) {
            try {
                dietUser.showLinks("mentor", System.out);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("\n========================================");
        // ===========================================
        // show links for DietAdmins

        // get DietUsers
        Iterable<DietAdministrator> dietAdmins = ExtensionAssociationManager.getExtension(DietAdministrator.class);


        // Print links for each DietUser
        for (DietAdministrator dietAdmin : dietAdmins) {
            try {
                dietAdmin.showLinks("mentors", System.out);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void generateManyToManyLinks() {
        // ===========================================



    }
}
