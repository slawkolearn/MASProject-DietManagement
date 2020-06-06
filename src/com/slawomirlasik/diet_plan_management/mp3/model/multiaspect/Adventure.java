package com.slawomirlasik.diet_plan_management.mp3.model.multiaspect;

import java.io.Serializable;

public class Adventure extends GenreType implements Serializable {

    private String descAdventure;

    public Adventure(String title, String type, String descAdventure) {
        super(title, type);
        this.descAdventure = descAdventure;
    }

    public String getDescAdventure() {
        return descAdventure;
    }

    public void setDescAdventure(String descAdventure) {
        this.descAdventure = descAdventure;
    }

    @Override
    public String getDesc() {
        return descAdventure;
    }

    @Override
    public String toString() {
        return "Adventure{" +
                "descAdventure='" + descAdventure + '\'' +
                '}';
    }
}
