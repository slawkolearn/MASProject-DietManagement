package com.slawomirlasik.diet_plan_management.model;

import com.slawomirlasik.diet_plan_management.util.ExtensionManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Recipe extends ExtensionManager implements Serializable {

    private List<DietType> dietPlans;

    public Recipe() {
        super();
        this.dietPlans = new ArrayList<>();
    }

    public Recipe(DietType ...dietTypes){
        super();
        this.dietPlans = new ArrayList<>();
        for (DietType dietType : dietTypes){
            this.dietPlans.add(dietType);
        }
    }

    public void addTypeOfDietForRecipe(DietType dietType){
        if(!checkIfRecipeIsOfDietType(dietType))
            this.dietPlans.add(dietType);
    }

    private boolean checkIfRecipeIsOfDietType(DietType dietType) {
        return this.dietPlans.contains(dietType);
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "dietPlans=" + String.join(", ", getAllDietPlans()
                .stream().map(DietType::toString).collect(Collectors.joining(", "))) +
                '}';
    }

    private List<DietType> getAllDietPlans() {
        return this.dietPlans;
    }
}
