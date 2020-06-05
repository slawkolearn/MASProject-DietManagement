package com.slawomirlasik.diet_plan_management.model;

import com.slawomirlasik.diet_plan_management.util.*;

import java.io.Serializable;

@AttributeClass
@AssociationClass(
        role1 = "concerns recipe",
        target1 = Recipe.class,
        role2 = "concerns ingredient",
        target2 = Ingredient.class
)
public class RecipeIngredient extends ExtensionAnnotationAssociationManager implements Serializable {

    private Recipe recipe;

    private Ingredient ingredient;

    private Float amountOfIngredient;

    public RecipeIngredient(Recipe recipe, Ingredient ingredient) {
        super();
        this.recipe = recipe;
        this.ingredient = ingredient;
    }

    @Attribute
    public Float getAmountOfIngredient() {
        return checkIfAmountOfIngredientPresent() ? amountOfIngredient : 0f;
    }

    public Boolean checkIfAmountOfIngredientPresent() {
        return this.amountOfIngredient == null ? false : true;
    }

    public void setAmountOfIngredient(Float amountOfIngredient) {
        this.amountOfIngredient = amountOfIngredient;
    }

    @Target1Getter
    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    @Target2Getter
    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    @Override
    public String toString() {
        // TODO:SL add unit type in printing of amount the ingredient from ingredient class
        return "RecipeIngredient{" +
                "amountOfIngredient=" + (checkIfAmountOfIngredientPresent() ? getAmountOfIngredient()  : "ilość dowolna") +
                '}' + this.ingredient;
    }
}
