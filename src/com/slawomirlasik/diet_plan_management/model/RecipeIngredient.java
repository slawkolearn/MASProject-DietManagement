package com.slawomirlasik.diet_plan_management.model;

import com.slawomirlasik.diet_plan_management.util.ExtensionAssociationManager;

import java.io.Serializable;

public class RecipeIngredient extends ExtensionAssociationManager implements Serializable {

    private Float amountOfIngredient;


    public RecipeIngredient(Float amountOfIngredientFloat) {
        super();
        this.amountOfIngredient = amountOfIngredientFloat;
    }

    public RecipeIngredient() {
        this(null);
    }

    public Float getAmountOfIngredient() {
        return checkIfAmountOfIngredientPresent() ? amountOfIngredient : 0f;
    }

    public Boolean checkIfAmountOfIngredientPresent() {
        return this.amountOfIngredient == null ? false : true;
    }

    public void setAmountOfIngredient(Float amountOfIngredient) {
        this.amountOfIngredient = amountOfIngredient;
    }

    @Override
    public String toString() {
        return "RecipeIngredient{" +
                "amountOfIngredient=" + (checkIfAmountOfIngredientPresent() ? getAmountOfIngredient() : "ilość dowolna") +
                '}';
    }
}
