package com.slawomirlasik.diet_plan_management.mp3;

import com.slawomirlasik.diet_plan_management.mp3.model.multiaspect.*;

public class MP3MultiAspect {

    private static String ADVENTURE_DESC = "Adventure has been a common theme since the earliest days of written fiction. Indeed, the standard plot of Medieval romances was a series of adventures. Following a plot framework as old as Heliodorus, and so durable as to be still alive in Hollywood movies, a hero would undergo a first set of adventures before he met his lady. A separation would follow, with a second set of adventures leading to a final reunion.";
    private static String SCI_FI_DESC = "is a self-written account of the life of oneself. The word autobiography was first used deprecatingly by William Taylor in 1797 in the English periodical The Monthly Review, when he suggested the word as a hybrid, but condemned it as pedantic";
    private static String BIO_DESC = "dealing principally with the impact of actual or imagined science on society or individuals or having a scientific factor as an essential orienting component";

    private static String ADVENTURE_TYPE_NAME = "Adventure";
    private static String SCI_FI_TYPE_NAME = "Science Fiction";
    private static String BIO_TYPE_NAME = "Autobiography";

    public static void main(String[] args) {

        // create sample books
        Book goodAdventureBook = new Adventure("Witcher", ADVENTURE_TYPE_NAME,  ADVENTURE_DESC);
        Book niceSciFiBook = new ScienceFiction("Star Trek", SCI_FI_TYPE_NAME,  SCI_FI_DESC);
        Book dissentAutobiographyBook = new Autobiography("Autobiography : Di Caprio", BIO_TYPE_NAME,  BIO_DESC);

        // add book types to each book
        try {
            goodAdventureBook.setBookType(new Ebook());
            niceSciFiBook.setBookType(new PaperBook());
            dissentAutobiographyBook.setBookType(new AudioBook());

            Book[] books = {
                    goodAdventureBook, niceSciFiBook, dissentAutobiographyBook
            };

            // get desc of each book

            System.out.println("Printing books descriptions");
            for(Book book: books){
                System.out.println("====================================");
                System.out.printf("Book Title : %s \n" , book.getTitle());
                System.out.printf("Genre Description :\n %s \n",((GenreType) book).getDesc());
            }

            // continue the book
            System.out.println("\n\n");
            System.out.println("Continue with the book");

            for(Book book: books){
                System.out.println("====================================");
                System.out.printf("Book Title : %s \n" , book.getTitle());
                book.continueWith();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
