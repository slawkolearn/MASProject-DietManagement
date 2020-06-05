package com.slawomirlasik.diet_plan_management.model;

import com.slawomirlasik.diet_plan_management.util.CompositionWhole;
import com.slawomirlasik.diet_plan_management.util.ExtensionAnnotationAssociationManager;
import com.slawomirlasik.diet_plan_management.util.OneToManyAssociation;

import java.io.Serializable;
import java.time.LocalDate;

@CompositionWhole(partTarget = DietPlanDay.class)
@OneToManyAssociation(target = DietPlanDay.class, role = "contains")
public class DietPlan extends ExtensionAnnotationAssociationManager implements Serializable {

    private LocalDate from;

    private LocalDate to;

    public DietPlan() {
        super();
    }

    public LocalDate getFrom() {
        return from;
    }

    public void setFrom(LocalDate from) {
        this.from = from;
    }

    public LocalDate getTo() {
        return to;
    }

    public void setTo(LocalDate to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "DietPlan{" +
                "from=" + from +
                ", to=" + to +
                '}';
    }
}
