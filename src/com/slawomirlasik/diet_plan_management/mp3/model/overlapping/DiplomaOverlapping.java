package com.slawomirlasik.diet_plan_management.mp3.model.overlapping;

import com.slawomirlasik.diet_plan_management.util.ExtensionAnnotationAssociationManager;
import com.slawomirlasik.diet_plan_management.util.ManyToOneAssociation;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@ManyToOneAssociation(
        target = DietAdministratorOverlapping.class,
        role = "belongs to"
)
public class DiplomaOverlapping extends ExtensionAnnotationAssociationManager implements Serializable {

    private String schoolName;
    private LocalDate finishDate;
    private Set<String> skills;

    public DiplomaOverlapping(String schoolName, LocalDate finishDate, Set<String> skillSet) {
        super();
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

    @Override
    public String toString() {
        return "DiplomaOverlapping{" +
                "schoolName='" + schoolName + '\'' +
                ", finishDate=" + finishDate +
                ", skills=" + skills +
                '}';
    }
}
