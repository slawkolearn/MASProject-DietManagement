package com.slawomirlasik.diet_plan_management.model;

import com.slawomirlasik.diet_plan_management.util.ExtensionManager;

import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class DietPlan extends ExtensionManager {

    public DietPlan() {
        super();
    }

    private static List<DietPlanDay> getDietPlanDays(Period period) {
        List<DietPlanDay> dietPlanDayPeriod = new ArrayList<>();
        // TODO:SL implement method of getting days of diet plan within a period
        return dietPlanDayPeriod;
    }


}
