package com.slawomirlasik.diet_plan_management.exampleEmployeeCompanyAttribute;

import com.slawomirlasik.diet_plan_management.util.AttributeClass;
import com.slawomirlasik.diet_plan_management.util.ExtensionAnnotationAssociationManager;

import java.time.LocalDate;

@AttributeClass(
        role1 = "Concerns employee", target1 = Employee.class,
        role2 = "Concerns company", target2 = Company.class
)
public class WorksIn extends ExtensionAnnotationAssociationManager {

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
