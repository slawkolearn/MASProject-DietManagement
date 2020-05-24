package com.slawomirlasik.diet_plan_management.model;

import com.slawomirlasik.diet_plan_management.util.ExtensionAssociationManager;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class Person extends ExtensionAssociationManager implements Serializable {
    private static Integer MINIMAL_AGE = 18;

    private String name;
    private String lastName;

    private LocalDate birthDate;

    public Person(String name, String lastName, LocalDate birthDate) throws Exception {
        super();
        this.name = name;
        this.lastName = lastName;
        setBirthDate(birthDate);
    }

    public Integer getAge() {
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

    /**
     * the method sets the users current @param birthdate field.
     * <p>
     * used only when some to update incorrect data, and when creating first Person.
     * <p>
     * Throws exception when users age is under 18.
     *
     * @param birthDate
     * @throws Exception
     */

    public void setBirthDate(LocalDate birthDate) throws Exception {
        if (LocalDate.now().getYear() - birthDate.getYear() < 18) {
            throw new Exception(
                    String.format("%s : The %s must have at least %d years old.",
                            ERROR_LABEL, name, MINIMAL_AGE)
            );
        } else {
            this.birthDate = birthDate;
        }
    }


    @Override
    public String toString() {
        return "Personal Data={" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}

