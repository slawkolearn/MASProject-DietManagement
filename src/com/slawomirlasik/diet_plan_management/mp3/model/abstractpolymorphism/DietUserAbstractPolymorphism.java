package com.slawomirlasik.diet_plan_management.mp3.model.abstractpolymorphism;

import com.slawomirlasik.diet_plan_management.model.DietAdministrator;
import com.slawomirlasik.diet_plan_management.util.ExtensionAssociationManager;
import com.slawomirlasik.diet_plan_management.util.ManyToOneAssociation;
import com.slawomirlasik.diet_plan_management.util.OneToManyAssociation;

import java.io.Serializable;
import java.time.LocalDate;

@OneToManyAssociation(
        target = DietStatisticsAbstractPolymorphism.class,
        role = "has statistics"
)
@ManyToOneAssociation(
        target = DietAdministratorAbstractPolymorphism.class,
        role = "mentor"
)
@OneToManyAssociation(
        target = DietPlanAbstractPolymorphism.class,
        role = "has diet plans"
)
public class DietUserAbstractPolymorphism extends PersonAbstractPolymorphism implements Serializable {


    public DietUserAbstractPolymorphism(String name, String lastName, LocalDate birthDate) throws Exception {
        super(name, lastName, birthDate);
    }

    /**
     * returns info about DietUser
     *
     * his personal data
     * , his  diets
     * , his current DietAdministrator
     * , his diet statistic array (asc by date of statistic taken)
     *
     * @return
     */
    @Override
    public String getInfo() throws Exception {

        String userInfo = "-----------------------------------\n";

        // add personal data
        userInfo += this + "\n";

        // add his diets
        ExtensionAssociationManager[] dietPlans = getLinks("has diet plans");

        for(ExtensionAssociationManager dietPlan : dietPlans){
            userInfo += "     "  + dietPlan + "\n";
        }

        //  his current mentor
        ExtensionAssociationManager[] mentors = getLinks("mentor");

        if(mentors.length > 1){
            throw new Exception(String.format("%s can have only 1 %s -> have %d",
                    this.getClass().getSimpleName(),
                    DietAdministrator.class.getSimpleName(),
                    mentors.length));
        }

        userInfo += "     "  + mentors[0] + "\n";


        // his diet statistics
        ExtensionAssociationManager[] userStatistics = getLinks("has statistics");

        for(ExtensionAssociationManager userStatistic : userStatistics){
            userInfo += "     "  + userStatistic + "\n";
        }


        userInfo += "-----------------------------------\n\n";

        return userInfo;
    }


    @Override
    public String toString() {
        return super.toString() + " DietUser{ }";
    }
}
