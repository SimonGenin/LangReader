package be.simongenin.langreader.tests;


import java.util.ArrayList;

import be.simongenin.langreader.models.Book;

public class DummyData {

    static {

        Book b1 = new Book("Harry Potter et la chambre des secrets", "J.K Rowling");
        Book b2 = new Book("The Lord of the Rings", "R.G Tolkien");
        Book b3 = new Book("Eragon", "Some one");
        Book b4 = new Book("Meto", "Quelqu'un");

        ArrayList<Book> books = new ArrayList<>();
        books.add(b1);
        books.add(b2);
        books.add(b3);
        books.add(b4);

    }

}
