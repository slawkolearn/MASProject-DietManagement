package com.slawomirlasik.diet_plan_management.util;

import java.lang.annotation.*;

@Repeatable(CompositionsWhole.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CompositionWhole {

    Class<? extends ExtensionAnnotationAssociationManager> partTarget();
}
