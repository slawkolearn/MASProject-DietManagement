package com.slawomirlasik.diet_plan_management.developementEmployeeCompanyAttribute;

import com.slawomirlasik.diet_plan_management.util.ExtensionAnnotationAssociationManager;

import java.time.LocalDate;

public class Company extends ExtensionAnnotationAssociationManager {


    private String name;

    public Company() {
        super();
    }

    public Company(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                '}';
    }

    public void hireEmployee(Employee gregory) {

        WorksIn employment = new WorksIn();

        employment.setSalary(10000f);
        employment.setDateFrom(LocalDate.now());

        // add link to employee in employment


        // add link to company in employment;


    }
}
