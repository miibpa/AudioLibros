package com.example.audiolibros;

/**
 * Created by miguel on 5/2/17.
 */

public class BooksRepository {
    private final LibroStorage libroStorage;

    public BooksRepository(LibroStorage libroStorage) {
        this.libroStorage = libroStorage;
    }
    public int getLastBook(){
        return libroStorage.getLastBook();
    }
    public boolean hasLastBook(){
        return libroStorage.hasLastBook();
    }

    public void saveLastBook(int id){
        libroStorage.saveLastBook(id);
    }
}
