package com.slawomirlasik.diet_plan_management.model;

import com.slawomirlasik.diet_plan_management.util.CompositionPart;
import com.slawomirlasik.diet_plan_management.util.ExtensionAnnotationAssociationManager;
import com.slawomirlasik.diet_plan_management.util.ManyToOneAssociation;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@CompositionPart(wholeTarget = DietPlan.class)
@ManyToOneAssociation(
        target = DietPlan.class,
        role = "belongs to"
)
public class DietPlanDay extends ExtensionAnnotationAssociationManager implements Serializable {

    private Float proteinPerDay;
    private Float carbohydratesPerDay;
    private Float fatsPerDay;
    private LocalDate day;

    private DietPlanDay(Float proteinPerDay, Float carbohydratesPerDay, Float fatsPerDay, LocalDate day) {
        super();
        this.proteinPerDay = proteinPerDay;
        this.carbohydratesPerDay = carbohydratesPerDay;
        this.fatsPerDay = fatsPerDay;
        this.day = day;
    }

    public static DietPlanDay createDietPlanDay(
            DietPlan whole,
            float proteinPerDay,
            float carbohydratesPerDay,
            float fatsPerDay,
            LocalDate day) throws Exception {

        // check if the whole exists and it is valid for this part type
        if( !wholeIsValid(DietPlanDay.class, whole) ){
            throw new Exception(String.format(
                    "There is no valid composition association between whole %s and part %s",
                    whole.getClass().getSimpleName(),
                    DietPlanDay.class.getSimpleName()

            ));
        }



        // create an dietPlanDay - its safe now
        DietPlanDay dietPlanDay = new DietPlanDay(proteinPerDay, carbohydratesPerDay, fatsPerDay, day);

        // add dietPlanDay to the parts -> throw exception if part already exists
        if(addPart(whole, dietPlanDay)){
            System.out.println();
        };

        // return dietPlanDay
        return dietPlanDay;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DietPlanDay that = (DietPlanDay) o;
        return proteinPerDay.equals(that.proteinPerDay) &&
                carbohydratesPerDay.equals(that.carbohydratesPerDay) &&
                fatsPerDay.equals(that.fatsPerDay) &&
                day.equals(that.day);
    }

    @Override
    public int hashCode() {
        return Objects.hash(proteinPerDay, carbohydratesPerDay, fatsPerDay, day);
    }
}
