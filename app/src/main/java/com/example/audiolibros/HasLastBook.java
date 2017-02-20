package com.example.audiolibros;

/**
 * Created by miguel on 5/2/17.
 */

public class HasLastBook {
    private final BooksRepository booksRepository;

    public HasLastBook(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public boolean execute() {
       return booksRepository.hasLastBook();
    }
}
