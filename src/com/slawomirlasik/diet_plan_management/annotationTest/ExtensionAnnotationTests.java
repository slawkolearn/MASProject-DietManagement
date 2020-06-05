package com.slawomirlasik.diet_plan_management.annotationTest;

import com.slawomirlasik.diet_plan_management.model.DietType;
import com.slawomirlasik.diet_plan_management.model.Recipe;

public class ExtensionAnnotationTests {

    public static void main(String[] args) {

        Recipe recipe = new Recipe();

        DietType dietType = new DietType();

        try {
            recipe.addDietType(dietType);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
