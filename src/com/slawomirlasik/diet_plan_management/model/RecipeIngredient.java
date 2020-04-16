package com.slawomirlasik.diet_plan_management.model;

import com.slawomirlasik.diet_plan_management.util.ExtensionManager;

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

    public void setAmountOfIngredient(Float amountOfIngredient) {
        this.amountOfIngredient = Optional.ofNullable(amountOfIngredient);
    }
}
