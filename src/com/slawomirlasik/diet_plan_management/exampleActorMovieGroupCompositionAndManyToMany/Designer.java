package com.slawomirlasik.diet_plan_management.exampleActorMovieGroupCompositionAndManyToMany;

import com.slawomirlasik.diet_plan_management.util.CompositionPart;
import com.slawomirlasik.diet_plan_management.util.ExtensionAnnotationAssociationManager;
import com.slawomirlasik.diet_plan_management.util.ManyToOneAssociation;

@CompositionPart(wholeTarget = Group.class)
@ManyToOneAssociation(
        target = Group.class,
        role = "group"
)
public class Designer extends ExtensionAnnotationAssociationManager {

    private String role;

    public Designer(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Designer{" +
                "role='" + role + '\'' +
                '}';
    }
}
