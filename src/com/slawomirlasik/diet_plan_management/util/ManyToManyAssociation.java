package com.slawomirlasik.diet_plan_management.util;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ManyToManyAssociation {
    int cardinality() default Integer.MAX_VALUE;
    String role();
    Class<? extends ExtensionAnnotationAssociationManager> target();
    Class<? extends ExtensionAnnotationAssociationManager> middleClass();

}
