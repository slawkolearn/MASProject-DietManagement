package com.slawomirlasik.diet_plan_management.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ManyToManyAssociationWithAttribute {
    int cardinality();
    String role();
    Class<? extends ExtensionAnnotationAssociationManager> target();
    Class<? extends ExtensionAnnotationAssociationManager> attributeClass();

}
