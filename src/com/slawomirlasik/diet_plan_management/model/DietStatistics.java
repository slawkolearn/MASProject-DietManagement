package com.slawomirlasik.diet_plan_management.model;

import com.slawomirlasik.diet_plan_management.util.ExtensionAssociationManager;

import java.io.Serializable;
import java.time.LocalDate;

public class DietStatistics extends ExtensionAssociationManager implements Serializable {

    private Float height;
    private Float weight;
    private Float waistCircumference;
    private LocalDate dateOfMeasurement;

    public DietStatistics(Float height, Float weight, Float waistCircumference, LocalDate dateOfMeasurement) {
        super();
        this.height = height;
        this.weight = weight;
        this.waistCircumference = waistCircumference;
        this.dateOfMeasurement = dateOfMeasurement;
    }

    public Float getHeight() {
        return height;
    }

    public Float getWeight() {
        return weight;
    }

    public Float getWaistCircumference() {
        return waistCircumference;
    }

    public LocalDate getDateOfMeasurement() {
        return dateOfMeasurement;
    }

    @Override
    public String toString() {
        return "DietStatistics{" +
                "height=" + height +
                ", weight=" + weight +
                ", waistCircumference=" + waistCircumference +
                ", dateOfMeasurement=" + dateOfMeasurement +
                '}';
    }
}
