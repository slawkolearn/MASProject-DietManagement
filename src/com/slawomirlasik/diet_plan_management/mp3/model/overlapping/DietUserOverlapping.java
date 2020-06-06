package com.slawomirlasik.diet_plan_management.mp3.model.overlapping;

import com.slawomirlasik.diet_plan_management.util.ExtensionAnnotationAssociationManager;
import com.slawomirlasik.diet_plan_management.util.ExtensionAssociationManager;
import com.slawomirlasik.diet_plan_management.util.ManyToOneAssociation;
import com.slawomirlasik.diet_plan_management.util.OneToManyAssociation;

import java.io.Serializable;

@OneToManyAssociation(
        target = DietStatisticsOverlapping.class,
        role = "has statistics"
)
@ManyToOneAssociation(
        target = DietAdministratorOverlapping.class,
        role = "mentor"
)
@OneToManyAssociation(
        target = DietPlanOverlapping.class,
        role = "has diet plans"
)
public class DietUserOverlapping  extends ExtensionAnnotationAssociationManager implements Serializable {


    public DietUserOverlapping() throws Exception {
        super();
    }

    @Override
    public String toString() {
        return super.toString() + " DietUser{ }";
    }

    public ExtensionAssociationManager[] getUserDietStatistics() throws Exception {

        return getLinks("has statistics");
    }

    public String getInfo() throws Exception {

        String dietUserInfo = "";

        ExtensionAssociationManager[] userDietStats = getLinks("has statistics");

        for(ExtensionAssociationManager stat : userDietStats){
            dietUserInfo += "      " + stat.toString() + "\n";
        }
        return dietUserInfo;
    }
}
