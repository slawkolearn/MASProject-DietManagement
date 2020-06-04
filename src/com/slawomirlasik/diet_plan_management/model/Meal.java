package com.slawomirlasik.diet_plan_management.model;

import com.slawomirlasik.diet_plan_management.util.ExtensionAnnotationAssociationManager;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Meal extends ExtensionAnnotationAssociationManager implements Serializable {

    private LocalDateTime timeConsumed;

    public Meal() {
        super();
    }

    public Meal(LocalDateTime timeConsumed) {
        super();
        this.timeConsumed = timeConsumed;
    }

    public LocalDateTime getTimeConsumed() {
        return timeConsumed;
    }

    public void setTimeConsumed(LocalDateTime timeConsumed) {
        this.timeConsumed = timeConsumed;
    }
}
