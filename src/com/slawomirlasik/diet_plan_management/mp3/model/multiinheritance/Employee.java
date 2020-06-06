package com.slawomirlasik.diet_plan_management.mp3.model.multiinheritance;

import com.slawomirlasik.diet_plan_management.util.ExtensionAnnotationAssociationManager;

import java.io.Serializable;

public abstract class Employee extends ExtensionAnnotationAssociationManager   implements Serializable {

    private String name;

    public Employee(String name){
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract double getIncome();

    public void doWork(){
        System.out.println("---------------------------");
        System.out.println("Doing work as an Employee");
        System.out.println("---------------------------");
    }
}
