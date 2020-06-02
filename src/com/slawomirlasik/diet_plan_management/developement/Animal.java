package com.slawomirlasik.diet_plan_management.developement;

@ManyToManyAssociation(role = "owner", target = Human.class, middleClass = AnimalHuman.class)
public class Animal extends ExtensionAnnotationAssociationManager {

    private String species;

    public Animal(String species) {
        super();
        this.species = species;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "species='" + species + '\'' +
                '}';
    }
}
