package com.slawomirlasik.diet_plan_management.model;

import com.slawomirlasik.diet_plan_management.util.ExtensionAnnotationAssociationManager;

import java.io.Serializable;

public class RecipeIngredient extends ExtensionAnnotationAssociationManager implements Serializable {

    private Float amountOfIngredient;

    private MeasurementUnits unitType;


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

    public MeasurementUnits getUnitType() {
        return unitType;
    }

    public void setUnitType(MeasurementUnits unitType) {
        this.unitType = unitType;
    }

    @Override
    public String toString() {
        return "RecipeIngredient{" +
                "amountOfIngredient=" + (checkIfAmountOfIngredientPresent() ? getAmountOfIngredient() + " " + getUnitType() : "ilość dowolna") +
                '}';
    }
}
