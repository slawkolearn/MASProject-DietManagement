package com.slawomirlasik.diet_plan_management.temp;

import com.slawomirlasik.diet_plan_management.util.ExtensionAssociationManager;

import java.time.LocalDate;

public class WorksIn extends ExtensionAssociationManager {

    private LocalDate dateFrom;

    private LocalDate dateTo;

    private float salary;

    public WorksIn(LocalDate dateFrom, LocalDate dateTo, float salary) {
        super();
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.salary = salary;
    }

    public WorksIn() {
        super();
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "WorksIn{" +
                "dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", salary=" + salary +
                '}';
    }
}
