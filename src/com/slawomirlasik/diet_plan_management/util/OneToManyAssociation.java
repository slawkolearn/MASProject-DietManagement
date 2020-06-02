package com.slawomirlasik.diet_plan_management.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface OneToManyAssociation {
    int cardinality() default Integer.MAX_VALUE;
    String role();
    Class<? extends ExtensionAnnotationAssociationManager> target();

}
