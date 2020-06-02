package com.slawomirlasik.diet_plan_management.developement;

import java.lang.annotation.Annotation;

public class AnnotationTest {

    public static void main(String[] args) {
        System.out.println("Testing annotations...");

        Animal dog = new Animal("bleee", "dog");

        Human human1 = new Human("ahhhh", 100;

        if(dog.getClass().isAnnotationPresent(ManyToManyAssociation.class)){
            System.out.println("Yes manyToMany annotation present...");

            ManyToManyAssociation annotation = dog.getClass().getAnnotation(ManyToManyAssociation.class);

            System.out.println("Role : " + annotation.role());
            System.out.println("Target: " + annotation.target());
        }
    }
}
