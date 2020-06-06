package com.slawomirlasik.diet_plan_management.mp3.model.multiinheritance;

import java.io.Serializable;

public class ConsultantManager extends Consultant implements Serializable, IManager {

    private double managerSalary;

    public ConsultantManager(String name, double consultantSalary, double managerSalary, double commission) {
        super(name, consultantSalary, commission);
        this.managerSalary = managerSalary;
    }

    @Override
    public double getSalary() {
        return super.getSalary() + managerSalary;
    }

    @Override
    public double getIncome() {
        return super.getIncome() + managerSalary;
    }

    @Override
    public void doWork() {
        super.doWork();
        System.out.println("---------------------------");
        System.out.println("Doing work as an Manager");
        System.out.println("---------------------------");

    }
}
