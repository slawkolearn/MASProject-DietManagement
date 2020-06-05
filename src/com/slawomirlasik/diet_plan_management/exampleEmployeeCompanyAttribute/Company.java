package com.slawomirlasik.diet_plan_management.exampleEmployeeCompanyAttribute;

import com.slawomirlasik.diet_plan_management.util.ExtensionAnnotationAssociationManager;
import com.slawomirlasik.diet_plan_management.util.ManyToManyAssociation;

import java.time.LocalDate;

@ManyToManyAssociation(
        role = "Employs",
        middleClass = WorksIn.class,
        target = Employee.class,
        cardinality = Integer.MAX_VALUE
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

    public void hireEmployee(Employee employee, float salary) throws Exception {
        // add link many to many between them
        ExtensionAnnotationAssociationManager associationAttributeClass =  addManyToManyLinkWithAttributeClass(employee);
//
//        // get association class created between THIS object and employee
//        ExtensionAssociationManager associationAttributeClass = getAssociationAttributeClass("Employs", employee);
//
//        System.out.println(associationAttributeClass);

        // we know what kind of attribute class it is but we may as well check
        if(isValidAttributeClass(this,associationAttributeClass )){

            WorksIn associationClass = (WorksIn) associationAttributeClass;

            // set salary for this attribute class
            associationClass.setSalary(salary);

            // set hire date for this class
            associationClass.setDateFrom(LocalDate.now());

        }else {
            // throw exceptio. soemthiing gone horribly wrong. You have worng association class at this point
            throw new Exception(String.format("Worng association class {%s} for the class {%s}",
                    associationAttributeClass.getClass().getSimpleName(),
                    this.getClass().getSimpleName()));
        }



    }
}
