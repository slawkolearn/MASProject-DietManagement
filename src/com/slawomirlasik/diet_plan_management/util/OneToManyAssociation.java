package com.slawomirlasik.diet_plan_management.util;

import java.lang.annotation.*;

@Repeatable(OneToManyAssociations.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface OneToManyAssociation {
    int cardinality() default Integer.MAX_VALUE;
    String role();
    Class<? extends ExtensionAnnotationAssociationManager> target();
    // if one gives a qualifier this association is association with qualifier type
    boolean qualified() default false; // if it is true in the class must be a annotated field for qualifier

}
