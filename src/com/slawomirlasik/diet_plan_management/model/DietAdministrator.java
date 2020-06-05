package com.slawomirlasik.diet_plan_management.model;

import com.slawomirlasik.diet_plan_management.util.OneToManyAssociation;

import java.io.Serializable;
import java.time.LocalDate;

@OneToManyAssociation(
        target = DietUser.class,
        role = "mentors"
)
public class DietAdministrator extends Person implements Serializable {


    public DietAdministrator(String name, String lastName, LocalDate birthDate ) throws Exception {
        super(name, lastName, birthDate);

    }

    @Override
    public String toString() {
        return super.toString() + " DietAdministrator{ }";
    }

}
