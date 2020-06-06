package com.slawomirlasik.diet_plan_management.mp3.model.multiaspect;

import java.io.Serializable;

public class AudioBook extends BookType implements Serializable {

    public AudioBook() {
        super();
    }

    @Override
    public void continueWith() {
        listenBook();
    }

    public void listenBook() {
        System.out.println("Listening the book as audio book" );
    }
}
