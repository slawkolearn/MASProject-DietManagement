package com.slawomirlasik.diet_plan_management.model;

import com.slawomirlasik.diet_plan_management.util.ExtensionAnnotationAssociationManager;

import java.io.Serializable;

public class RecipeIngredient extends ExtensionAnnotationAssociationManager implements Serializable {

    private Float amountOfIngredient;

    public RecipeIngredient(Float amountOfIngredientFloat, MeasurementUnits unitType) {
        super();
        this.amountOfIngredient = amountOfIngredientFloat;
    }

    public RecipeIngredient(MeasurementUnits unitType){
        this(null, unitType);
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
        // TODO:SL add unit type in printing of amount the ingredient from ingredient class
        return "RecipeIngredient{" +
                "amountOfIngredient=" + (checkIfAmountOfIngredientPresent() ? getAmountOfIngredient()  : "ilość dowolna") +
                '}';
    }
}
