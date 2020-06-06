package com.slawomirlasik.diet_plan_management.mp3.model.multiaspect;

import java.io.Serializable;

public abstract class GenreType extends Book implements Serializable {

    private String type;

    public GenreType(String title, String type) {
        super(title);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public abstract String getDesc();

    @Override
    public String toString() {
        return "GenreType{" +
                "type='" + type + '\'' +
                '}';
    }
}
