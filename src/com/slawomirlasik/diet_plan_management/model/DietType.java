package com.slawomirlasik.diet_plan_management.model;

import com.slawomirlasik.diet_plan_management.util.ExtensionManager;

public class DietType extends ExtensionManager {

    private String name;
    private String description;

    public DietType(String name, String description) {
        super();
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "DietType{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
