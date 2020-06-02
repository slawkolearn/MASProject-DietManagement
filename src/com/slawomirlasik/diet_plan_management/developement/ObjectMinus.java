package com.slawomirlasik.diet_plan_management.developement;

public class ObjectMinus {

    private String name;

    public ObjectMinus() {
    }

    public ObjectMinus(String name) {
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
        return "ObjectMinus{" +
                "name='" + name + '\'' +
                '}';
    }
}
