package com.slawomirlasik.diet_plan_management.model;

import java.io.Serializable;
import java.time.LocalDate;

public class DietAdministrator extends Person implements Serializable {

    // field for storing diploma (later may be implemented differently)
    private Diploma diploma;

    public DietAdministrator(String name, String lastName, LocalDate birthDate, Diploma diploma) throws Exception {
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
