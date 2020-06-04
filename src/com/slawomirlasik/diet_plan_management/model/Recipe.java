package com.slawomirlasik.diet_plan_management.model;

import com.slawomirlasik.diet_plan_management.util.ExtensionAnnotationAssociationManager;
import com.slawomirlasik.diet_plan_management.util.ManyToManyAssociation;

import java.io.Serializable;


@ManyToManyAssociation(
        target = DietType.class,
        middleClass = RecipeDietType.class,
        role = "is of a type"
)
public class Recipe extends ExtensionAnnotationAssociationManager implements Serializable {

    private String recipeName;

    public Recipe() {
        super();
    }

    public Recipe(String recipeName) {
        super();
        this.recipeName = recipeName;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "recipeName='" + recipeName + '\'' +
                '}';
    }

    public void addDietType(DietType muscleTrainingDietType) throws Exception {

        addManyToManyLink(muscleTrainingDietType);

    }
}
