package com.slawomirlasik.diet_plan_management.mp3;

import com.slawomirlasik.diet_plan_management.mp3.model.abstractpolymorphism.*;
import com.slawomirlasik.diet_plan_management.service.DietManagementService;
import com.slawomirlasik.diet_plan_management.service.ObjectPlusDietManagementServiceImpl;
import com.slawomirlasik.diet_plan_management.util.ExtensionAnnotationAssociationManager;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class MP3AbstractClassPolymorphism {

    public static DietManagementService dietManagementService = new ObjectPlusDietManagementServiceImpl();

    public static void main(String[] args) {

        try {
            // create sample DietUsers

            DietUserAbstractPolymorphism robertUser = new DietUserAbstractPolymorphism(
                    "Robert"
                    , "Downie",
                    LocalDate.parse("14-02-1988", ExtensionAnnotationAssociationManager.DATE_TIME_FORMATTER)
            );


            DietUserAbstractPolymorphism christofferUser = new DietUserAbstractPolymorphism(
                    "Christoffer"
                    , "Smith",
                    LocalDate.parse("24-09-1999", ExtensionAnnotationAssociationManager.DATE_TIME_FORMATTER)
            );


            DietUserAbstractPolymorphism joanneUser = new DietUserAbstractPolymorphism(
                    "Joanne"
                    , "Killisth",
                    LocalDate.parse("22-03-2000", ExtensionAnnotationAssociationManager.DATE_TIME_FORMATTER)
            );

            // create sample statistics for them as well
            addRandomStatisticsAndDietPlanToUser(robertUser, 3);
            addRandomStatisticsAndDietPlanToUser(christofferUser, 2);
            addRandomStatisticsAndDietPlanToUser(joanneUser, 4);

            // create sample dietplans to each user


            // create sample DietAdministrators
            DietAdministratorAbstractPolymorphism mark = new DietAdministratorAbstractPolymorphism(
                    "Mark",
                    "Dolly",
                    LocalDate.parse("28-05-1987", ExtensionAnnotationAssociationManager.DATE_TIME_FORMATTER)
            );

            DietAdministratorAbstractPolymorphism bob = new DietAdministratorAbstractPolymorphism(
                    "Bob",
                    "Dyllan",
                    LocalDate.parse("09-09-1964", ExtensionAnnotationAssociationManager.DATE_TIME_FORMATTER)
            );


            // create sample diplomas for them as well
            Set<String>  skills = new HashSet<>();
            skills.add("Exercise");
            skills.add("Diet Master");
            DiplomaAbstractPolymorphism diploma1Mark = new DiplomaAbstractPolymorphism(
                    "Go Now!!",
                    LocalDate.now().minusDays((long) (Math.random() - (900))),
                    skills
            );

            skills = new HashSet<>();
            skills.add("Diet Master");
            DiplomaAbstractPolymorphism diploma2Mark = new DiplomaAbstractPolymorphism(
                    "Revelation",
                    LocalDate.now().minusDays((long) (Math.random() - (900))),
                    skills
            );


            skills = new HashSet<>();
            skills.add("Exercise");
            DiplomaAbstractPolymorphism diploma3Mark = new DiplomaAbstractPolymorphism(
                    "Simple",
                    LocalDate.now().minusDays((long) (Math.random() - (900))),
                    skills
            );


            skills = new HashSet<>();
            skills.add("Exercise");
            skills.add("Diet Master");
            DiplomaAbstractPolymorphism diploma1Bob = new DiplomaAbstractPolymorphism(
                    "Charish",
                    LocalDate.now().minusDays((long) (Math.random() - (900))),
                    skills
            );

            dietManagementService.setDiplomaToDietAdministrator(diploma1Mark, mark);
            dietManagementService.setDiplomaToDietAdministrator(diploma2Mark, mark);
            dietManagementService.setDiplomaToDietAdministrator(diploma3Mark, mark);

            dietManagementService.setDiplomaToDietAdministrator(diploma1Bob, bob);


            // assign sample mentors for diet users

            dietManagementService.assignMentorToUser(bob, robertUser);
            dietManagementService.assignMentorToUser(bob, christofferUser);
            dietManagementService.assignMentorToUser(mark, joanneUser);

            // printing sample info about random user or mentor
            PersonAbstractPolymorphism[] peopleManagedInDietManagementSystem = {
                    bob, mark, robertUser, christofferUser, joanneUser
            };

            for (int i = 0; i < 3; i++) {
                dietManagementService.printInfoAboutPerson(
                        peopleManagedInDietManagementSystem[
                                new Random().nextInt(peopleManagedInDietManagementSystem.length)
                                ]
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addRandomStatisticsAndDietPlanToUser(DietUserAbstractPolymorphism user, int numberOfStatisticsToAdd) throws Exception {

        // generate weight
        int maxWeight = 200;
        int minWeight = 40;

        float currentRandomWeight = (float) (Math.random() * (maxWeight - minWeight + 1) + minWeight);

        int alphaWeight = 3;

        // generate waist
        int maxWaist = 120;
        int minWaist = 60;

        float currentRandomWaist = (float) (Math.random() * (maxWaist - minWaist + 1) + minWaist);

        int alphaWaist = 2;

        // generate starting date
        LocalDate startingDate = LocalDate.now().minusDays((long) (Math.random() * (900 - 0 + 1)));

        while (numberOfStatisticsToAdd-- > 0) {
            dietManagementService.addUserToStatistic(
                    user,
                    new DietStatisticsAbstractPolymorphism(
                            (float) Math.random() * (220 - 140 + 1) + 140,
                            (float) Math.random() * (currentRandomWeight - alphaWeight + 1) + alphaWeight,
                            (float) Math.random() * (currentRandomWaist - alphaWaist + 1) + alphaWaist,
                            startingDate.plusDays((long) (Math.random() * (14 - 7 + 1) + 7))

                    )
            );
        }

        // add sample dietplan to user
        dietManagementService.assignDietPlanToUser(new DietPlanAbstractPolymorphism(startingDate), user);

    }
}
