package com.slawomirlasik.diet_plan_management.service;

import com.slawomirlasik.diet_plan_management.mp3.model.abstractpolymorphism.PersonAbstractPolymorphism;
import com.slawomirlasik.diet_plan_management.util.ExtensionAnnotationAssociationManager;

public interface DietManagementService {

    void addUserToStatistic(
            ExtensionAnnotationAssociationManager user,
            ExtensionAnnotationAssociationManager statistic
            ) throws Exception;


    void assignMentorToUser(ExtensionAnnotationAssociationManager mentor,
                            ExtensionAnnotationAssociationManager user
    ) throws Exception;

    void printInfoAboutPerson(PersonAbstractPolymorphism person) throws Exception;

    void setDiplomaToDietAdministrator(ExtensionAnnotationAssociationManager diploma,
                                       ExtensionAnnotationAssociationManager dietAdmin) throws Exception;

    void assignDietPlanToUser(ExtensionAnnotationAssociationManager dietPlan,
                              ExtensionAnnotationAssociationManager user) throws Exception;
}
