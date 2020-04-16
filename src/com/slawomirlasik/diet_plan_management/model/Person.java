package com.slawomirlasik.diet_plan_management.model;

import com.slawomirlasik.diet_plan_management.util.ExtensionManager;

import java.time.LocalDate;

public class Person extends ExtensionManager {
    private static Integer MINIMAL_AGE = 18;

    private String name;
    private String lastName;

    private LocalDate birthDate;

    public Person(String name, String lastName, LocalDate birthDate) {
        super();
        this.name = name;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public Integer getAge(){
        return LocalDate.now().getYear() - birthDate.getYear();
    }

    public static Integer getMinimalAge() {
        return MINIMAL_AGE;
    }

    public static void setMinimalAge(Integer minimalAge) {
        MINIMAL_AGE = minimalAge;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
