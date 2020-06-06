package com.slawomirlasik.diet_plan_management.mp3.model.multiinheritance;

import java.io.Serializable;

public class Consultant extends Employee  implements Serializable {

    private double salary;

    private double commission;

    public Consultant(String name, double salary, double commision){
        super(name);
        this.salary = salary;
        this.commission = commision;
    }

    public Consultant(String name, double salary){
        this(name, salary, 0);
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

    public double getIncome(){
        return getSalary() + getCommission();
    }

    public void addCommission(double amount){
        this.commission += amount;
    }

    @Override
    public void doWork(){
        System.out.println("---------------------------");
        System.out.println("Doing work as an Consultant");
        System.out.println("---------------------------");
    }
}
