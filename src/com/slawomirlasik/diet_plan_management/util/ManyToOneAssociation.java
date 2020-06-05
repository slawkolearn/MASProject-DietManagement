package com.slawomirlasik.diet_plan_management.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ManyToOneAssociation {

    // here we do not have cardinality
    // this annotation enforce of using cardinality of 1 later in Util class on the target side
    // with the given role

    String role();
    Class<? extends ExtensionAnnotationAssociationManager> target();
}
