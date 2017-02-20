package com.example.audiolibros;

/**
 * Created by miguel on 5/2/17.
 */

public class GetLastBook {
    private final BooksRepository booksRepository;

    public GetLastBook(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public int execute() {
        return booksRepository.getLastBook();
    }
}
