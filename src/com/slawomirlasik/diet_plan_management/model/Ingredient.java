package com.slawomirlasik.diet_plan_management.model;

import com.slawomirlasik.diet_plan_management.util.ExtensionAnnotationAssociationManager;

import java.io.Serializable;

public class Ingredient extends ExtensionAnnotationAssociationManager implements Serializable {

    private String name;

    private MeasurementUnits unitType;

    private Float onOneUnit;

    private Float numberOfProtein;
    private Float numberOfCarbohydrates;
    private Float numberOfFat;

    public Ingredient(
            String name,
            Float numberOfProtein,
            Float numberOfCarbohydrates,
            Float numberOfFat,
            MeasurementUnits unitType,
            Float oneUnit
            ) {
        super();
        this.name = name;
        this.numberOfProtein = numberOfProtein;
        this.numberOfCarbohydrates = numberOfCarbohydrates;
        this.numberOfFat = numberOfFat;
        this.unitType = unitType;
        this.onOneUnit = oneUnit;
    }

    public Ingredient(

            String name,
            Float numberOfProtein,
            Float numberOfCarbohydrates,
            Float numberOfFat,
            MeasurementUnits unitType
    ){
        this(name, numberOfProtein, numberOfCarbohydrates, numberOfFat, unitType, 1f);
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MeasurementUnits getUnitType() {
        return unitType;
    }

    public void setUnitType(MeasurementUnits unitType) {
        this.unitType = unitType;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "name='" + name + '\'' +
                '}';
    }
}
