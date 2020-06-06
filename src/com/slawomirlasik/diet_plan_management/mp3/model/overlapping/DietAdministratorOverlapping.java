package com.slawomirlasik.diet_plan_management.mp3.model.overlapping;

import com.slawomirlasik.diet_plan_management.util.ExtensionAnnotationAssociationManager;
import com.slawomirlasik.diet_plan_management.util.ExtensionAssociationManager;
import com.slawomirlasik.diet_plan_management.util.OneToManyAssociation;

import java.io.Serializable;

@OneToManyAssociation(
        target = DiplomaOverlapping.class,
        role = "has"
)
@OneToManyAssociation(
        target = DietUserOverlapping.class,
        role = "mentors"
)
public class DietAdministratorOverlapping extends ExtensionAnnotationAssociationManager implements Serializable {


    public DietAdministratorOverlapping() throws Exception {
        super();

    }


    @Override
    public String toString() {
        return super.toString() + " DietAdministrator{ }";
    }

    public ExtensionAssociationManager[] getDietAdministratorDiplomas() throws Exception {

        return getLinks("has");

    }
}
