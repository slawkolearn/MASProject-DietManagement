package com.slawomirlasik.diet_plan_management.model;

import com.slawomirlasik.diet_plan_management.util.ExtensionManager;

import java.util.ArrayList;
import java.util.List;

public class Recipe extends ExtensionManager {

    private List<DietType> dietPlans;

    public Recipe() {
        super();
        this.dietPlans = new ArrayList<>();
    }

    public void addTypeOfDietForRecipe(DietType dietType){
        if(!checkIfRecipeIsOfDietType(dietType))
            this.dietPlans.add(dietType);
    }

    private boolean checkIfRecipeIsOfDietType(DietType dietType) {
        return this.dietPlans.contains(dietType);
    }
}
