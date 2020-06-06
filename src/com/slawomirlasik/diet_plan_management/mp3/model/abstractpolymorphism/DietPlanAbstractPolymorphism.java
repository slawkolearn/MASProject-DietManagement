package com.slawomirlasik.diet_plan_management.mp3.model.abstractpolymorphism;

import com.slawomirlasik.diet_plan_management.util.ExtensionAnnotationAssociationManager;
import com.slawomirlasik.diet_plan_management.util.ManyToOneAssociation;

import java.io.Serializable;
import java.time.LocalDate;

@ManyToOneAssociation(
        target = DietUserAbstractPolymorphism.class,
        role = "belongs to"
)
public class DietPlanAbstractPolymorphism extends ExtensionAnnotationAssociationManager implements Serializable {

    private LocalDate from;

    private LocalDate to;

    public DietPlanAbstractPolymorphism() {
        super();
    }

    public DietPlanAbstractPolymorphism(LocalDate startingDate) {
        this.from = startingDate;
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
