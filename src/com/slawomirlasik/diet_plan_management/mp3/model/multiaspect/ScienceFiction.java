package com.slawomirlasik.diet_plan_management.mp3.model.multiaspect;

import java.io.Serializable;

public class ScienceFiction extends GenreType implements Serializable {

    private String descScienceFiction;

    public ScienceFiction(String title, String type, String descScienceFiction) {
        super(title, type);
        this.descScienceFiction = descScienceFiction;
    }

    public String getDescScienceFiction() {
        return descScienceFiction;
    }

    public void setDescScienceFiction(String descScienceFiction) {
        this.descScienceFiction = descScienceFiction;
    }

    @Override
    public String getDesc() {
        return descScienceFiction;
    }
}
