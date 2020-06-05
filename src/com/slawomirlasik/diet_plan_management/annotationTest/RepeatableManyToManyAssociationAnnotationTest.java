package com.slawomirlasik.diet_plan_management.annotationTest;

import com.slawomirlasik.diet_plan_management.model.DietPlan;
import com.slawomirlasik.diet_plan_management.model.DietType;
import com.slawomirlasik.diet_plan_management.model.Recipe;
import com.slawomirlasik.diet_plan_management.util.ManyToManyAssociation;
import com.slawomirlasik.diet_plan_management.util.ManyToManyAssociations;

import java.lang.annotation.Annotation;

public class RepeatableManyToManyAssociationAnnotationTest {

    public static void main(String[] args) {
        System.out.println(Recipe.class.isAnnotationPresent(ManyToManyAssociations.class));
        ManyToManyAssociations annotation = Recipe.class.getAnnotation(ManyToManyAssociations.class);

        for(ManyToManyAssociation a : annotation.value()){
            System.out.println(a);
        }

        System.out.println();
        for(Annotation anno : Recipe.class.getAnnotations()) {
            System.out.println(anno);
        }

        System.out.println("By Type");
        System.out.println(Recipe.class.getAnnotationsByType(ManyToManyAssociation.class).length);
        for(Annotation anno : Recipe.class.getAnnotationsByType(ManyToManyAssociation.class)){
            System.out.println(anno);
        }

        System.out.println();
        System.out.println(DietType.class.isAnnotationPresent(ManyToManyAssociation.class));
        System.out.println(DietType.class.getAnnotation(ManyToManyAssociation.class));
        System.out.println(DietType.class.getAnnotationsByType(ManyToManyAssociation.class).length);

        System.out.println();
        System.out.println("No annotation fo type : " +ManyToManyAssociation.class);
        System.out.println(DietPlan.class.getAnnotationsByType(ManyToManyAssociation.class).length);
    }
}
