package com.slawomirlasik.diet_plan_management.developement;

@ManyToManyAssociation(role = "owner", target = Human.class)
public class Animal extends ObjectMinus {

    private String species;

    public Animal(String species) {
        this.species = species;
    }

    public Animal(String name, String species) {
        super(name);
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
