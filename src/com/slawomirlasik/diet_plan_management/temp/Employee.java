package com.slawomirlasik.diet_plan_management.temp;

public class Employee extends ExtensionAssociationManagerEmployment {

    // roles that coming from Emplyee to objects
    // role of employee works in
    public static final String ASSOCIATION_WITH_ATTRIBUTE_EMPLOYEE_WORKS_IN = "employee_works_in";


    public enum associations {
        EMPLOYEE_WORKS_IN
    };

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
