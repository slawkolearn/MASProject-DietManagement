package com.slawomirlasik.diet_plan_management.model;

import com.slawomirlasik.diet_plan_management.util.ExtensionAssociationManager;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

public class Diploma extends ExtensionAssociationManager implements Serializable {

    private String schoolName;
    private LocalDate finishDate;
    private Set<String> skills;

    public Diploma(String schoolName, LocalDate finishDate, Set<String> skillSet) {
        this.schoolName = schoolName;
        this.finishDate = finishDate;
        this.skills = skillSet;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }

    public Set<String> getSkills() {
        return skills;
    }

    public void setSkills(Set<String> skills) {
        this.skills = skills;
    }
}
