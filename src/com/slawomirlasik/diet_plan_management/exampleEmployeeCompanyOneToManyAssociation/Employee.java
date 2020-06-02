package com.slawomirlasik.diet_plan_management.exampleEmployeeCompanyOneToManyAssociation;

import com.slawomirlasik.diet_plan_management.exampleEmployeeCompanyAttribute.WorksIn;
import com.slawomirlasik.diet_plan_management.util.ExtensionAnnotationAssociationManager;
import com.slawomirlasik.diet_plan_management.util.ManyToManyAssociationWithAttribute;

@ManyToManyAssociationWithAttribute(
        role = "Works in",
        target = Company.class,
        attributeClass = WorksIn.class,
        cardinality = Integer.MAX_VALUE
)
public class Employee extends ExtensionAnnotationAssociationManager {

    private String firstName;

    private String lastName;

    public Employee(String firstName, String lastName) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Employee() {
        super();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
