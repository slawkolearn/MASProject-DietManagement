package com.slawomirlasik.diet_plan_management.model;

import com.slawomirlasik.diet_plan_management.util.ExtensionAssociationManager;

import java.io.Serializable;

public class Recipe extends ExtensionAssociationManager implements Serializable {

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
}
