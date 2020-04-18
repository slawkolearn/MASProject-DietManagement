package com.slawomirlasik.diet_plan_management.model;

import com.slawomirlasik.diet_plan_management.util.ExtensionManager;

import java.io.Serializable;

public class Ingredient extends ExtensionManager implements Serializable {

    private Float numberOfProtein;
    private Float numberOfCarbohydrates;
    private Float numberOfFat;

    public Ingredient(Float numberOfProtein, Float numberOfCarbohydrates, Float numberOfFat) {
        super();
        this.numberOfProtein = numberOfProtein;
        this.numberOfCarbohydrates = numberOfCarbohydrates;
        this.numberOfFat = numberOfFat;
    }

    public Float getNumberOfProtein() {
        return numberOfProtein;
    }

    public void setNumberOfProtein(Float numberOfProtein) {
        this.numberOfProtein = numberOfProtein;
    }

    public Float getNumberOfCarbohydrates() {
        return numberOfCarbohydrates;
    }

    public void setNumberOfCarbohydrates(Float numberOfCarbohydrates) {
        this.numberOfCarbohydrates = numberOfCarbohydrates;
    }

    public Float getNumberOfFat() {
        return numberOfFat;
    }

    public void setNumberOfFat(Float numberOfFat) {
        this.numberOfFat = numberOfFat;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "numberOfProtein=" + numberOfProtein +
                ", numberOfCarbohydrates=" + numberOfCarbohydrates +
                ", numberOfFat=" + numberOfFat +
                '}';
    }
}
