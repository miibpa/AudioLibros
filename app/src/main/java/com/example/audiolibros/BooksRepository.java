package com.example.audiolibros;

/**
 * Created by miguel on 5/2/17.
 */

public class BooksRepository {
    private final BookStorage bookStorage;

    public BooksRepository(BookStorage bookStorage) {
        this.bookStorage = bookStorage;
    }
    public int getLastBook(){
        return bookStorage.getLastBook();
    }
    public boolean hasLastBook(){
        return bookStorage.hasLastBook();
    }

    public void saveLastBook(int id){
        bookStorage.saveLastBook(id);
    }
}
