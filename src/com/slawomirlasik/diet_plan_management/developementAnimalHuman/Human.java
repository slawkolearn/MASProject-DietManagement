package com.slawomirlasik.diet_plan_management.developementAnimalHuman;

import com.slawomirlasik.diet_plan_management.util.ExtensionAnnotationAssociationManager;
import com.slawomirlasik.diet_plan_management.util.ManyToManyAssociation;

@ManyToManyAssociation(role = "pupil", target = Animal.class, middleClass = AnimalHuman.class)
public class Human extends ExtensionAnnotationAssociationManager {

    private int salary;

    public Human( int salary) {
        super();
        this.salary = salary;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Human{" +
                "salary=" + salary +
                '}';
    }
}
