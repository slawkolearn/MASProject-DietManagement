package com.slawomirlasik.diet_plan_management.model;

import com.slawomirlasik.diet_plan_management.util.ExtensionAnnotationAssociationManager;
import com.slawomirlasik.diet_plan_management.util.ManyToManyAssociation;

import java.io.Serializable;

@ManyToManyAssociation(
        target = Recipe.class,
        role = "concerns recipe",
        middleClass = RecipeDietType.class
)
public class DietType extends ExtensionAnnotationAssociationManager implements Serializable {

    private String name;
    private String description;

    public DietType() {
        super();
    }

    public DietType(String name, String description) {
        super();
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "DietType{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
