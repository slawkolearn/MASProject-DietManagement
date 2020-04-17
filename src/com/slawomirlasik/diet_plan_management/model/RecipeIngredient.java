package com.slawomirlasik.diet_plan_management.model;

import com.slawomirlasik.diet_plan_management.util.ExtensionManager;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.Optional;

public class RecipeIngredient extends ExtensionManager {

    private Optional<Float> amountOfIngredient;

    private RecipeIngredient(Optional<Float> amountOfIngredient){
        super();
        this.amountOfIngredient = amountOfIngredient;
    }

    public RecipeIngredient(Float amountOfIngredientFloat) {
        this(Optional.ofNullable(amountOfIngredientFloat));
    }

    public RecipeIngredient() {
        this(Optional.empty());
    }

    public Float getAmountOfIngredient() {
        return amountOfIngredient.orElse(0f);
    }

    public Boolean checkIfAmountOfIngredientPresent(){
        return amountOfIngredient.isPresent();
    }

    public void setAmountOfIngredient(Float amountOfIngredient) {
        this.amountOfIngredient = Optional.ofNullable(amountOfIngredient);
    }

    @Override
    public String toString() {
        return "RecipeIngredient{" +
                "amountOfIngredient=" + (amountOfIngredient.isPresent() ? getAmountOfIngredient() : "ilość dowolna") +
                '}';
    }
}
