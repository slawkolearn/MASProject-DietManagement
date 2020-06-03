package com.slawomirlasik.diet_plan_management.exampleEmployeeCompanyAttribute;

import com.slawomirlasik.diet_plan_management.exampleEmployeeCompanyOneToManyAssociation.Company;
import com.slawomirlasik.diet_plan_management.exampleEmployeeCompanyOneToManyAssociation.Employee;

public class ExampleAssociationWithAttribute {

    // TODO:SL implement association with attribute example (ManyToMany) using Recipe -- Ingredient with attribute RecipeIngredient

    // again in our system we must implement this association in our system diagram as two associations
    //

    // TODO:SL create sample association with attribute from lecture MAS


    public static void main(String[] args) throws Exception {

        // create sample employees
        Employee gregory = new Employee("Grzegorz", "Nowak");
        Employee tadeo = new Employee("Tadeusz", "Zbigniweski");
        Employee cristofer = new Employee("Krzysztof", "Krzystowski");

        // create sample companies
        Company google = new Company("Google INC");
        Company asseco = new Company("Assaco Poland S.A.");

        // associate them using association with attribute
        google.hireEmployee(gregory, 10000);

        // print assocations for role "employs"


    }
}
