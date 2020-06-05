package com.slawomirlasik.diet_plan_management.model;

import com.slawomirlasik.diet_plan_management.util.ExtensionAnnotationAssociationManager;

import java.io.Serializable;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class DietPlan extends ExtensionAnnotationAssociationManager implements Serializable {

    List<DietPlanDay> dietPlanDaysList;

    public DietPlan(List<DietPlanDay> dietPLanDaysList) {
        super();
        this.dietPlanDaysList = dietPLanDaysList;
    }

    private List<DietPlanDay> getDietPlanDays(Period period) {
        List<DietPlanDay> dietPlanDayPeriod = new ArrayList<>();
        // TODO:SL implement method of getting days of diet plan within a period
        return dietPlanDayPeriod;
    }

    public static Iterable<DietPlan> getAllDietPlans() {
        // TODO:SL implement this method getting all current diet plans in the system
        return getExtension(DietPlan.class);
    }

    @Override
    public String toString() {
        return "DietPlan{" +
                "dietPlanDaysList Size=" + dietPlanDaysList.size() +
                '}';
    }
}
