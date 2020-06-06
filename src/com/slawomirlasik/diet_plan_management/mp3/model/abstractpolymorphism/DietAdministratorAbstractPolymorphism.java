package com.slawomirlasik.diet_plan_management.mp3.model.abstractpolymorphism;

import com.slawomirlasik.diet_plan_management.util.ExtensionAssociationManager;
import com.slawomirlasik.diet_plan_management.util.OneToManyAssociation;

import java.io.Serializable;
import java.time.LocalDate;

@OneToManyAssociation(
        target = DiplomaAbstractPolymorphism.class,
        role = "has"
)
@OneToManyAssociation(
        target = DietUserAbstractPolymorphism.class,
        role = "mentors"
)
public class DietAdministratorAbstractPolymorphism extends PersonAbstractPolymorphism implements Serializable {


    public DietAdministratorAbstractPolymorphism(String name, String lastName, LocalDate birthDate ) throws Exception {
        super(name, lastName, birthDate);

    }

    /**
     * returns info about DietAdministrator
     *
     * his personal data
     * , current diet users that he is supervising their diet
     * , number of past students that he supervised
     * , his diplomas
     *
     * @return
     */
    @Override
    public String getInfo() throws Exception {

        String dietAdminInfo = "-----------------------------------\n";

        dietAdminInfo += this + "\n";

        // add current diet users (toString) that he have
        ExtensionAssociationManager[] usersMentored = getLinks("mentors");

        for(ExtensionAssociationManager userMentored : usersMentored){
            dietAdminInfo += "     " + userMentored + "\n";
        }


        // add to string method of all diplomas that this dietAdmin have
        ExtensionAssociationManager[] diplomas = getLinks("has");

        for(ExtensionAssociationManager diploma : diplomas){
            dietAdminInfo += "     " + diploma + "\n";
        }
        dietAdminInfo +=  "-----------------------------------\n\n";

        return dietAdminInfo;
    }



    @Override
    public String toString() {
        return super.toString() + " DietAdministrator{ }";
    }

}
