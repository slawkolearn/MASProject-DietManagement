package com.slawomirlasik.diet_plan_management.exampleEmployeeCompanyOneToManyAssociation;

import com.slawomirlasik.diet_plan_management.exampleEmployeeCompanyAttribute.WorksIn;
import com.slawomirlasik.diet_plan_management.util.ExtensionAnnotationAssociationManager;
import com.slawomirlasik.diet_plan_management.util.OneToManyAssociation;

import java.time.LocalDate;

@OneToManyAssociation(
        role = "Employs",
        target = Employee.class
)
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

    public void hireEmployee(Employee employee, float salary) {

        WorksIn employment = new WorksIn();

        employment.setSalary(10000f);
        employment.setDateFrom(LocalDate.now());

        // add link to employee in employment


        // add link to company in employment;


    }
}
