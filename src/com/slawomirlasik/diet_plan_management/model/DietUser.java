package com.slawomirlasik.diet_plan_management.model;

import com.slawomirlasik.diet_plan_management.util.ManyToOneAssociation;

import java.io.Serializable;
import java.time.LocalDate;

@ManyToOneAssociation(
        target = DietAdministrator.class,
        role = "mentor"
)
public class DietUser extends Person implements Serializable {


    public DietUser(String name, String lastName, LocalDate birthDate) throws Exception {
        super(name, lastName, birthDate);
    }


    @Override
    public String toString() {
        return super.toString() + " DietUser{ }";
    }
}
