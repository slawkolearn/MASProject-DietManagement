package com.slawomirlasik.diet_plan_management.developement;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ManyToManyAssociation {

    String role();
    Class<? extends ObjectMinus> target();

}
