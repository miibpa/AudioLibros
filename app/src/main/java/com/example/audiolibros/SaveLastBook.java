package com.example.audiolibros;

/**
 *
 * Created by miguel on 5/2/17.
 *
 */

public class SaveLastBook {
    private final BooksRepository booksRepository;

    public SaveLastBook(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public void execute(int id) {
        booksRepository.saveLastBook(id);
    }

}
