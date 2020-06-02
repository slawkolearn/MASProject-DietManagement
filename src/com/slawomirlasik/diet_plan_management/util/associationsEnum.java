package com.slawomirlasik.diet_plan_management.util;


/**
 * Stores all association in DietManagementSystem that are possible in the system.
 *
 * Naming Convention:
 *
 *    <starting_class>_<role_name_of_this_class>
 */

/**
 * HERE YOU MUST FINISH ADDING ALL APRIOPIATE associations roles (including specific roles for association with
 * with attribute and composition)
 */
public enum associationsEnum {


    DIETSTATISTICS_BELONGSTO,

    DIETUSER_HAS,

    DIETPLAN_BELONGS_TO,
    DIETPLAN_HAS_DURATION_COMPOSITION_PART, // composition part
    DIETPLAN_EXCERCISE,
    DIETPLAN_CONTAINS_COMPOSITION_PART, // composition part


    DURATION_BELONGS_TO,   // composition part

    EXERCISE_PART_OF,

    DIETPLANDAY_BELONGS_TO, // composition part


    DIETPLANDAY_CONSISTS,

    MEAL_CONCERNS,           // composition part
    MEAL_CONTAINS,

    RECIPE_IS_PART_OF,
    RECIPE_BEST_SUITED,
    RECIPE_IS_TYPE_OF,
    RECIPE_CONTAINS,

    TIME_OF_DAY_PREFERENCE_CONCERNS,

    // association with attribute
    DIET_TYPE_CONCERNS,
    RECIPE_INGREDIENT_RECIPE,
    RECIPE_INGREDIENT_INGREDIENT,
    INGREDIENT_RECIPE



}
