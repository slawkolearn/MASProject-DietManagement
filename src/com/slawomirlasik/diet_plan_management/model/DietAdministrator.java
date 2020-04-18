package com.slawomirlasik.diet_plan_management.model;

import java.time.LocalDate;

public class DietAdministrator extends Person {
    public DietAdministrator(String name, String lastName, LocalDate birthDate) throws Exception {
        super(name, lastName, birthDate);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
