package com.slawomirlasik.diet_plan_management.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ManyToOneAssociation {
    int cardinality() default 1;
    String role();
    Class<? extends ExtensionAnnotationAssociationManager> target();

}
