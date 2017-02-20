package com.example.audiolibros;

/**
 * Created by miguel on 22/1/17.
 */

public interface LibroStorage {
    boolean hasLastBook();
    int getLastBook();
    void setLastBook(int id);
    void saveLastBook(int id);
}
