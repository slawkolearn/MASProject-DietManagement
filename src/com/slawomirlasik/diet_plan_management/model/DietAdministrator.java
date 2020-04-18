package com.slawomirlasik.diet_plan_management.model;

import java.time.LocalDate;

public class DietAdministrator extends Person {

    // field stub for storing diploma (later may be implemented differently)
    private String diploma;

    public DietAdministrator(String name, String lastName, LocalDate birthDate, String diploma) throws Exception {
        super(name, lastName, birthDate);
        this.diploma = diploma;
    }

    @Override
    public String toString() {
        return super.toString() + " DietAdministrator{" +
                "diploma='" + diploma + '\'' +
                '}';
    }
}
