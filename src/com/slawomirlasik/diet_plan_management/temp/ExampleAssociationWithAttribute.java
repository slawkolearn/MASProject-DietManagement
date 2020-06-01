package com.slawomirlasik.diet_plan_management.temp;

public class ExampleAssociationWithAttribute {

    // TODO:SL implement association with attribute example (ManyToMany) using Recipe -- Ingredient with attribute RecipeIngredient

    // again in our system we must implement this association in our system diagram as two associations
    //

    // TODO:SL create sample association with attribute from lecture MAS


    public static void main(String[] args) {

        // create sample employees
        Employee gregory = new Employee("Grzegorz", "Nowak");
        Employee tadeo = new Employee("Tadeusz", "Zbigniweski");
        Employee cristofer = new Employee("Krzysztof", "Krzystowski");

        // create sample companies
        Company google = new Company("Google INC");
        Company asseco = new Company("Assaco Poland S.A.");

        // associate them using association with attribute
        google.hireEmployee(gregory);
        google.hireEmployee(tadeo);

        asseco.hireEmployee(cristofer);

        // print assocations for role "employs"
        try {
            ExtensionAssociationManagerEmployment[] employments =
                    asseco.getLinks(employmentAssociations.COMPANY_EMPLOYS);

            System.out.println("Oto zatrudnienia dla firmy google:");
            for(ExtensionAssociationManagerEmployment worksIn : employments ){
                System.out.println(worksIn);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
