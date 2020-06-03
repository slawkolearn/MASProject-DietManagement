package com.slawomirlasik.diet_plan_management.exampleEmployeeCompanyOneToManyAssociation;

import com.slawomirlasik.diet_plan_management.util.ExtensionAnnotationAssociationManager;

import java.io.IOException;

public class TestOneToManyAssociationAnnotations {

    private static boolean readFromFile = true;

    public static void main(String[] args) {

        if (readFromFile) {
            try {
                ExtensionAnnotationAssociationManager.loadExtensionsFromFile();


                ExtensionAnnotationAssociationManager.printExtension(Company.class);

                ExtensionAnnotationAssociationManager.printExtension(Employee.class);

                Iterable<Company> companies = ExtensionAnnotationAssociationManager.getExtension(Company.class);


                Iterable<Employee> employees = ExtensionAnnotationAssociationManager.getExtension(Employee.class);

                for (Employee employee : employees) {
                    employee.printRoles();

                    employee.showLinks(
                            ExtensionAnnotationAssociationManager.getRoleNameForTarget(employee, Company.class),
                            System.out)
                    ;
                }

                for (Company company : companies) {
                    company.printRoles();

                    company.showLinks(
                            ExtensionAnnotationAssociationManager.getRoleNameForTarget(company, Employee.class),
                            System.out)
                    ;
                }


            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception exception) {
                exception.printStackTrace();
            }


        } else {


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
            try {
                google.hireEmployee(gregory, 4000);
                google.hireEmployee(tadeo, 10000);
                asseco.hireEmployee(cristofer, 20000);
                google.hireEmployee(cristofer, 10000);
            } catch (Exception exception) {
                exception.printStackTrace();
            }

            google.printRoles();
            gregory.printRoles();
            cristofer.printRoles();

            try {
                google.showLinks("Employs", System.out);
                cristofer.showLinks("Works in", System.out);
            } catch (Exception exception) {
                exception.printStackTrace();
            }

            try {
                ExtensionAnnotationAssociationManager.saveExtensionCurrentState();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
