package com.slawomirlasik.diet_plan_management.mp3.model.overlapping;

import com.slawomirlasik.diet_plan_management.util.ExtensionAnnotationAssociationManager;
import com.slawomirlasik.diet_plan_management.util.ManyToOneAssociation;

import java.io.Serializable;
import java.time.LocalDate;

@ManyToOneAssociation(
        target = DietUserOverlapping.class,
        role = "belongs to"
)
public class DietStatisticsOverlapping extends ExtensionAnnotationAssociationManager implements Serializable {

    private Float height;
    private Float weight;
    private Float waistCircumference;
    private LocalDate dateOfMeasurement;

    public DietStatisticsOverlapping(Float height, Float weight, Float waistCircumference, LocalDate dateOfMeasurement) {
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
