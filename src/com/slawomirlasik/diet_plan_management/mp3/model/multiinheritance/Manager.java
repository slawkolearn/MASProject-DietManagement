package com.slawomirlasik.diet_plan_management.mp3.model.multiinheritance;

public class Manager extends Employee implements IManager {

    private double salary;

    public Manager(String name, double salary){
        super(name);
        this.salary = salary;

    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public void doWork() {

        System.out.println("---------------------------");
        System.out.println("Doing work as an Manager");
        System.out.println("---------------------------");
    }

    @Override
    public double getIncome() {
        return getSalary();
    }

    @Override
    public String toString() {
        return "Manager{" +
                "salary=" + salary +
                '}';
    }
}
