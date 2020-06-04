package com.slawomirlasik.diet_plan_management.model;

import com.slawomirlasik.diet_plan_management.util.AssociationClass;
import com.slawomirlasik.diet_plan_management.util.ExtensionAnnotationAssociationManager;
import com.slawomirlasik.diet_plan_management.util.Target1Getter;
import com.slawomirlasik.diet_plan_management.util.Target2Getter;

import java.io.Serializable;

@AssociationClass(
        target1 = Recipe.class,
        role1 = "recipe middle",
        target2 = DietType.class,
        role2 = "diet type middle"
)
public class RecipeDietType extends ExtensionAnnotationAssociationManager implements Serializable {

    private Recipe recipe;

    private DietType dietType;

    public RecipeDietType(Recipe recipe, DietType dietType) {
        super();
        this.recipe = recipe;
        this.dietType = dietType;
    }

    @Target1Getter
    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    @Target2Getter
    public DietType getDietType() {
        return dietType;
    }

    public void setDietType(DietType dietType) {
        this.dietType = dietType;
    }

    @Override
    public String toString() {
        return "RecipeDietType{" +
                "recipe=" + recipe +
                ", dietType=" + dietType +
                '}';
    }
}
