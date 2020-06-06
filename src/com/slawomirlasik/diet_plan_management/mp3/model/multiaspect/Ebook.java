package com.slawomirlasik.diet_plan_management.mp3.model.multiaspect;

import java.io.Serializable;

public class Ebook extends BookType implements Serializable {

    public Ebook(){
        super();
    }


    @Override
    public void continueWith() {
        readBook();
    }

    public void readBook(){
        System.out.println("Reading the book as ebook");
    }
}
