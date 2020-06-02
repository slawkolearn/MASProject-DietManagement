package com.slawomirlasik.diet_plan_management.test;

import com.slawomirlasik.diet_plan_management.developementAnimalHuman.Animal;
import com.slawomirlasik.diet_plan_management.developementAnimalHuman.Human;
import com.slawomirlasik.diet_plan_management.util.ManyToManyAssociation;

public class AnnotationTest {

    public static void main(String[] args) {
        System.out.println("Testing annotations...");

        Animal dog = new Animal("dog");

        Human human1 = new Human(100);

        if(dog.getClass().isAnnotationPresent(ManyToManyAssociation.class)){
            System.out.println("Yes manyToMany annotation present...");

            ManyToManyAssociation annotation = dog.getClass().getAnnotation(ManyToManyAssociation.class);

            System.out.println("Role : " + annotation.role());
            System.out.println("Target: " + annotation.target());
        }
    }
}
