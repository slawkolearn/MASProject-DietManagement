package com.slawomirlasik.diet_plan_management.exampleEmployeeCompanyOneToManyAssociation;

public class TestOneToManyAssociationAnnotations {

    public static void main(String[] args) {

        // create sample employees
        //
        Employee gregory = new Employee("Grzegorz", "Nowak");
        Employee tadeo = new Employee("Tadeusz", "Zbigniweski");
        Employee cristofer = new Employee("Krzysztof", "Krzystowski");

        // create sample companies
        //
        Company google = new Company("Google INC");
        Company asseco = new Company("Assaco Poland S.A.");

        // add some employees to company
        // one employee can work in one company at the time
        //
        google.hireEmployee(gregory, 4000);
        google.hireEmployee(cristofer, 10000);

        asseco.hireEmployee(tadeo, 2000);

    }
}
