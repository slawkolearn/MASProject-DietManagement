package com.slawomirlasik.diet_plan_management.mp3.model.multiaspect;

import com.slawomirlasik.diet_plan_management.util.ExtensionAnnotationAssociationManager;

import java.io.Serializable;

public abstract class BookType extends ExtensionAnnotationAssociationManager implements Serializable {

    public BookType(){
        super();
    }

    public abstract void continueWith();


}
