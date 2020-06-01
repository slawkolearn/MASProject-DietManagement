package com.slawomirlasik.diet_plan_management.temp;

import java.time.LocalDate;

public class Company extends ExtensionAssociationManagerEmployment {

    // roles that coming from Comany to objects
    // role of company employes
    public static final String ASSOCIATION_WITH_ATTRIBUTE_COMPANY_EMPLOYS = "company_employs";


    public enum associations {
        COMPANY_EMPLOYS
    };


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
        employment.addLink(
                employmentAssociations.WORKSIN_CONCERNS_EMPLOYEE,
                employmentAssociations.EMPLOYEE_WORKS_IN,
                gregory);

        // add link to company in employment;
        employment.addLink(
                employmentAssociations.WORKISIN_CONCERNS_COMPANY,
                employmentAssociations.COMPANY_EMPLOYS,
                this
        );

    }
}
