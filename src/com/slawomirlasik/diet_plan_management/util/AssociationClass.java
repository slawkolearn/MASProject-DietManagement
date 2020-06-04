package com.slawomirlasik.diet_plan_management.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface AssociationClass {

    // add roles for each sides
    String role1();
    String role2();

    // add target object for each side (which object this attribute class concerns)
    Class<? extends ExtensionAnnotationAssociationManager> target1();
    Class<? extends ExtensionAnnotationAssociationManager> target2();


}

// HOW TO ANNOTATE THE FIELDS IN THE CLASS
