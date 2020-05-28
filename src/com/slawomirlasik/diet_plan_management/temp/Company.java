package com.slawomirlasik.diet_plan_management.temp;

import com.slawomirlasik.diet_plan_management.util.ExtensionAssociationManager;

public class Company extends ExtensionAssociationManager {

    private String name;

    public Company() {
        super();
    }

    public Company(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                '}';
    }
}
