package com.slawomirlasik.diet_plan_management.mp3.model.multiaspect;

import java.io.Serializable;

public class Autobiography extends GenreType implements Serializable {

    private String descAutobiography;

    public Autobiography(String title, String type, String descAutobiography) {
        super(title, type);
        this.descAutobiography = descAutobiography;
    }

    public String getDescAutobiography() {
        return descAutobiography;
    }

    public void setDescAutobiography(String descAutobiography) {
        this.descAutobiography = descAutobiography;
    }

    @Override
    public String getDesc() {
        return descAutobiography;
    }

    @Override
    public String toString() {
        return "Autobiography{" +
                "descAutobiography='" + descAutobiography + '\'' +
                '}';
    }
}
