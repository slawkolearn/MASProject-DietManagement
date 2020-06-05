package com.slawomirlasik.diet_plan_management.model;

import com.slawomirlasik.diet_plan_management.util.ExtensionAnnotationAssociationManager;
import com.slawomirlasik.diet_plan_management.util.ManyToManyAssociation;

import java.io.Serializable;


@ManyToManyAssociation(
        target = DietType.class,
        middleClass = RecipeDietType.class,
        role = "is of a type"
)
@ManyToManyAssociation(
        target = Ingredient.class,
        middleClass = RecipeIngredient.class,
        role = "contains"
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


    public void addIngredient(Ingredient ingredient) throws Exception {
        addIngredient(ingredient, null);
    }


    public void addIngredient(Ingredient ingredient, Float amount) throws Exception {

        ExtensionAnnotationAssociationManager attributeClassObject = addManyToManyLinkWithAttributeClass(ingredient);


        // we know what kind of attribute class it is but we may as well check
        if (amount != null && isValidAttributeClass(this, attributeClassObject)) {
            System.out.println("Setting amount " + amount + " for " + ingredient.getName());
            // ok se we know what kind of class is attributeClassObject and we cast it
            RecipeIngredient recipeIngredientAttributeClassObject =
                    (RecipeIngredient) attributeClassObject;
            System.out.println("for " + recipeIngredientAttributeClassObject);

            // then  we set tup amount if it not null
            recipeIngredientAttributeClassObject.setAmountOfIngredient(amount);

        }

    }
}
