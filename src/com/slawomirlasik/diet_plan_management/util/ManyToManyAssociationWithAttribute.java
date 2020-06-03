package com.slawomirlasik.diet_plan_management.util;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ManyToManyAssociationWithAttribute {
    int cardinality();
    String role();
    Class<? extends ExtensionAnnotationAssociationManager> target();
    Class<? extends ExtensionAnnotationAssociationManager> attributeClass();
}
