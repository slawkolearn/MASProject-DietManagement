package com.slawomirlasik.diet_plan_management.mp3.model.multiaspect;

import com.slawomirlasik.diet_plan_management.util.ExtensionAnnotationAssociationManager;

import java.io.Serializable;

public class Book extends ExtensionAnnotationAssociationManager implements Serializable {

    private String title;

    public Book(String title) {
        super();
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                '}';
    }

    public void setBookType(BookType bookType) throws Exception {

        addPart(bookTypeRoleNameSpecializationAspect, generalizationRoleName, bookType);

    }

    public void continueWith() throws Exception {
        ((BookType) getLinks(bookTypeRoleNameSpecializationAspect)[0]).continueWith();
    }

    private static final String bookTypeRoleNameSpecializationAspect = "aspectBookType";
    private static final String generalizationRoleName = "generalizationBook";
}
