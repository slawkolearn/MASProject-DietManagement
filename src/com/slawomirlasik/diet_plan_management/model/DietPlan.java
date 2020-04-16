package com.slawomirlasik.diet_plan_management.model;

import com.slawomirlasik.diet_plan_management.util.ExtensionManager;

import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class DietPlan extends ExtensionManager {

    private Integer proteinPerDay;
    private Integer carbohydratesPerDay;
    private Integer fatsPerDay;


    public DietPlan(Integer proteinPerDay, Integer carbohydratesPerDay, Integer fatsPerDay) {
        super();
        this.proteinPerDay = proteinPerDay;
        this.carbohydratesPerDay = carbohydratesPerDay;
        this.fatsPerDay = fatsPerDay;
    }

    private static List<DietPlanDay> getDietPlanDays(Period period) {
        List<DietPlanDay> dietPlanDayPeriod = new ArrayList<>();
        // TODO:SL implement method of getting days of diet plan within a period
        return dietPlanDayPeriod;
    }

    public Integer getProteinPerDay() {
        return proteinPerDay;
    }

    public Integer getCarbohydratesPerDay() {
        return carbohydratesPerDay;
    }

    public Integer getFatsPerDay() {
        return fatsPerDay;
    }
}
