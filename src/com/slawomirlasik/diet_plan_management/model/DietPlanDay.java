package com.slawomirlasik.diet_plan_management.model;

import com.slawomirlasik.diet_plan_management.util.ExtensionAnnotationAssociationManager;

import java.io.Serializable;
import java.time.LocalDate;

public class DietPlanDay extends ExtensionAnnotationAssociationManager implements Serializable {

    private Integer proteinPerDay;
    private Integer carbohydratesPerDay;
    private Integer fatsPerDay;
    private LocalDate day;

    public DietPlanDay(Integer proteinPerDay, Integer carbohydratesPerDay, Integer fatsPerDay, LocalDate day) {
        super();
        this.proteinPerDay = proteinPerDay;
        this.carbohydratesPerDay = carbohydratesPerDay;
        this.fatsPerDay = fatsPerDay;
        this.day = day;
    }

    @Override
    public String toString() {
        return "DietPlanDay{" +
                "proteinPerDay=" + proteinPerDay +
                ", carbohydratesPerDay=" + carbohydratesPerDay +
                ", fatsPerDay=" + fatsPerDay +
                ", day=" + day +
                '}';
    }
}
