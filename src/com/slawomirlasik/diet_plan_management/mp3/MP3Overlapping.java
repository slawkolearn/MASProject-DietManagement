package com.slawomirlasik.diet_plan_management.mp3;

import com.slawomirlasik.diet_plan_management.mp3.model.overlapping.DietStatisticsOverlapping;
import com.slawomirlasik.diet_plan_management.mp3.model.overlapping.DiplomaOverlapping;
import com.slawomirlasik.diet_plan_management.mp3.model.overlapping.PersonOverlapping;
import com.slawomirlasik.diet_plan_management.service.DietManagementService;
import com.slawomirlasik.diet_plan_management.service.ObjectPlusDietManagementServiceImpl;
import com.slawomirlasik.diet_plan_management.util.ExtensionAnnotationAssociationManager;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class MP3Overlapping {

    public static DietManagementService dietManagementService = new ObjectPlusDietManagementServiceImpl();

    public static void main(String[] args) {

        try {

            // create sample Persons
            PersonOverlapping robertUser = new PersonOverlapping(
                    "Robert"
                    , "Downie",
                    LocalDate.parse("14-02-1988", ExtensionAnnotationAssociationManager.DATE_TIME_FORMATTER)
            );


            PersonOverlapping christofferUser = new PersonOverlapping(
                    "Christoffer"
                    , "Smith",
                    LocalDate.parse("24-09-1999", ExtensionAnnotationAssociationManager.DATE_TIME_FORMATTER)
            );


            PersonOverlapping joanneUser = new PersonOverlapping(
                    "Joanne"
                    , "Killisth",
                    LocalDate.parse("22-03-2000", ExtensionAnnotationAssociationManager.DATE_TIME_FORMATTER)
            );

            PersonOverlapping markAdminUser = new PersonOverlapping(
                    "Mark",
                    "Dolly",
                    LocalDate.parse("28-05-1987", ExtensionAnnotationAssociationManager.DATE_TIME_FORMATTER)
            );

            PersonOverlapping bobAdmin = new PersonOverlapping(
                    "Bob",
                    "Dyllan",
                    LocalDate.parse("09-09-1964", ExtensionAnnotationAssociationManager.DATE_TIME_FORMATTER)
            );

            // create sample diplomas for them as well
            Set<String> skills = new HashSet<>();
            skills.add("Exercise");
            skills.add("Diet Master");
            DiplomaOverlapping diploma1Mark = new DiplomaOverlapping(
                    "Go Now!!",
                    LocalDate.now().minusDays((long) (Math.random() - (900))),
                    skills
            );

            skills = new HashSet<>();
            skills.add("Diet Master");
            DiplomaOverlapping diploma2Mark = new DiplomaOverlapping(
                    "Revelation",
                    LocalDate.now().minusDays((long) (Math.random() - (900))),
                    skills
            );


            skills = new HashSet<>();
            skills.add("Exercise");
            DiplomaOverlapping diploma3Mark = new DiplomaOverlapping(
                    "Simple",
                    LocalDate.now().minusDays((long) (Math.random() - (900))),
                    skills
            );


            skills = new HashSet<>();
            skills.add("Exercise");
            skills.add("Diet Master");
            DiplomaOverlapping diploma1Bob = new DiplomaOverlapping(
                    "Charish",
                    LocalDate.now().minusDays((long) (Math.random() - (900))),
                    skills
            );

            // add type of person - diet administrator
            markAdminUser.addDietAdministrator(new DiplomaOverlapping[]{diploma1Mark, diploma2Mark, diploma3Mark}, dietManagementService);
            bobAdmin.addDietAdministrator(new DiplomaOverlapping[]{diploma1Bob}, dietManagementService);


            // add type of person - diet user
            joanneUser.addDietUser(getRandomStatistics(5), dietManagementService );
            markAdminUser.addDietUser(getRandomStatistics(2), dietManagementService);
            christofferUser.addDietUser(getRandomStatistics(5), dietManagementService );
            robertUser.addDietUser(getRandomStatistics(5), dietManagementService );

            // show dietPlans for mark and bob (try for robert - exception)
            markAdminUser.showDiplomas();
//            bobAdmin.showDiplomas();

            // show statistics for users
            markAdminUser.showStatistics();
//            robertUser.showStatistics();;
//            joanneUser.showStatistics();
//            robertUser.showStatistics();

            // error
//            robertUser.showDiplomas();


            // assign sample mentors for diet users

//            dietManagementService.assignMentorToUser(bobAdmin.getAdminRole(), robertUser.getUserRole());
//            dietManagementService.assignMentorToUser(bobAdmin.getAdminRole(), christofferUser.getUserRole());
//            dietManagementService.assignMentorToUser(markAdminUser.getAdminRole(), joanneUser.getUserRole());

            // printing sample info about random user or mentor


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static DietStatisticsOverlapping[] getRandomStatistics(int noOfStatsToGenerate) throws Exception {

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

        // create array to return
        DietStatisticsOverlapping[] stats = new DietStatisticsOverlapping[noOfStatsToGenerate];

        for(int i = 0; i < noOfStatsToGenerate; i++){

            stats[i] =  new DietStatisticsOverlapping(
                    (float) Math.random() * (220 - 140 + 1) + 140,
                    (float) Math.random() * (currentRandomWeight - alphaWeight + 1) + alphaWeight,
                    (float) Math.random() * (currentRandomWaist - alphaWaist + 1) + alphaWaist,
                    startingDate.plusDays((long) (Math.random() * (14 - 7 + 1) + 7)));
        }

        return stats;

   }
}
