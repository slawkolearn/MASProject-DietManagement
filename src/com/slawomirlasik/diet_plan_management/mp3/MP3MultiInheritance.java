package com.slawomirlasik.diet_plan_management.mp3;

import com.slawomirlasik.diet_plan_management.mp3.model.multiinheritance.Consultant;
import com.slawomirlasik.diet_plan_management.mp3.model.multiinheritance.ConsultantManager;
import com.slawomirlasik.diet_plan_management.mp3.model.multiinheritance.Employee;
import com.slawomirlasik.diet_plan_management.mp3.model.multiinheritance.Manager;

public class MP3MultiInheritance {

    public static void main(String[] args) {

        Manager managerBob = new Manager("Bob", 10000);

        Consultant consultantRick = new Consultant("Rick", 2000, 300);

        ConsultantManager consultantManagerTom = new ConsultantManager("Tom", 1500, 8000, 200);

        Employee[] employees = {
                managerBob, consultantRick, consultantManagerTom
        };

        // doWork for each of the type of employee
        for (Employee employee : employees) {
            System.out.printf("========   %s goes to work =============\n", employee.getName());
            employee.doWork();
            System.out.printf("              Finished\n");
            System.out.println("=========================================\n\n");
        }

        // print income for each employe


        for (Employee employee : employees) {
            System.out.printf("Dochody dla %s : %f\n",
                    employee.getName(),
                    employee.getIncome());
        }
    }
}
