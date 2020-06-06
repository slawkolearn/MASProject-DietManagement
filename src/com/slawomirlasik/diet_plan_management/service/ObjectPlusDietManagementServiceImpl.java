package com.slawomirlasik.diet_plan_management.service;

import com.slawomirlasik.diet_plan_management.mp3.model.abstractpolymorphism.PersonAbstractPolymorphism;
import com.slawomirlasik.diet_plan_management.util.ExtensionAnnotationAssociationManager;

public class ObjectPlusDietManagementServiceImpl implements DietManagementService {


    @Override
    public void addStatisticToUser(ExtensionAnnotationAssociationManager user, ExtensionAnnotationAssociationManager statistic) throws Exception {
        System.out.printf("Adding %s to %s\n", statistic, user);

        statistic.addManyToOneLink(user);

    }

    @Override
    public void assignMentorToUser(ExtensionAnnotationAssociationManager mentor, ExtensionAnnotationAssociationManager user) throws Exception {
        System.out.printf("Assigning mentor %s to a user %s\n", mentor, user);

        mentor.addOneToManyLink(user);
    }

    @Override
    public void printInfoAboutPerson(PersonAbstractPolymorphism person) throws Exception {
        System.out.println("Printing info about " + person.getClass().getSimpleName());
        System.out.println(person.getInfo());
    }

    @Override
    public void setDiplomaToDietAdministrator(ExtensionAnnotationAssociationManager diploma,
                                              ExtensionAnnotationAssociationManager dietAdmin) throws Exception {

        diploma.addManyToOneLink(dietAdmin);

    }

    @Override
    public void assignDietPlanToUser(ExtensionAnnotationAssociationManager dietPlan, ExtensionAnnotationAssociationManager user) throws Exception {
        dietPlan.addManyToOneLink(user);
    }
}
