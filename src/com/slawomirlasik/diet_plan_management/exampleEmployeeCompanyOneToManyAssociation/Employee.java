package com.slawomirlasik.diet_plan_management.exampleEmployeeCompanyOneToManyAssociation;


import com.slawomirlasik.diet_plan_management.util.ExtensionAnnotationAssociationManager;

@ManyToOneAssociation(
        role = "Works in",
        target = Company.class
)
public class Employee extends ExtensionAnnotationAssociationManager {

    private String firstName;

    private String lastName;

    private float salary;

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

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", salary=" + salary +
                '}';
    }
}
