package com.slawomirlasik.diet_plan_management.exampleEmployeeCompanyAttribute;

import com.slawomirlasik.diet_plan_management.util.*;

import java.time.LocalDate;

@AttributeClass
@AssociationClass(
        role1 = "Concerns employee",
        target1 = Employee.class,
        role2 = "Concerns company",
        target2 = Company.class
)
public class WorksIn extends ExtensionAnnotationAssociationManager {

    private Employee target1;

    private Company target2;

    private LocalDate dateFrom;

    private LocalDate dateTo;

    private float salary;

    public WorksIn(Employee employee, Company company) {
        super();
        this.target1 = employee;
        this.target2 = company;
    }

    public WorksIn() {
        super();
    }

    @Attribute
    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    @Attribute
    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    @Attribute
    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    @Target1Getter
    public Employee getTarget1() {
        return target1;
    }

    public void setTarget1(Employee target1) {
        this.target1 = target1;
    }

    @Target2Getter
    public Company getTarget2() {
        return target2;
    }

    public void setTarget2(Company target2) {
        this.target2 = target2;
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
