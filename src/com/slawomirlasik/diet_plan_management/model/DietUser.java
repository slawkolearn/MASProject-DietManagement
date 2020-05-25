package com.slawomirlasik.diet_plan_management.model;

import java.io.Serializable;
import java.time.LocalDate;

public class DietUser extends Person implements Serializable {


    public DietUser(String name, String lastName, LocalDate birthDate) throws Exception {
        super(name, lastName, birthDate);
    }


    @Override
    public String toString() {
        return super.toString() + " DietUser{ }";
    }
}
