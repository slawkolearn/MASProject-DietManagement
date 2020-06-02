package com.slawomirlasik.diet_plan_management.developement;

public class AnimalHuman extends ExtensionAnnotationAssociationManager {

    private Animal animal;

    private Human human;

    public AnimalHuman(Animal animal, Human human) {
        super();
        this.animal = animal;
        this.human = human;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Human getHuman() {
        return human;
    }

    public void setHuman(Human human) {
        this.human = human;
    }
}
