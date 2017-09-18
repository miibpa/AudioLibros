package com.example.audiolibros;

import android.content.Context;

import java.util.Vector;

/**
 *
 * Created by miguel on 24/1/17.
 *
 */

public class BooksSingleton {

    private static BooksSingleton ourInstance;
    private Vector<Book> books;
    private AdaptadorLibrosFiltro adaptador;

    public static BooksSingleton getInstance(Context context) {
        if(ourInstance == null) {
            ourInstance = new BooksSingleton(context);
        }
        return ourInstance;
    }

    private BooksSingleton(Context context) {
        final String SERVIDOR =
                "http://www.dcomg.upv.es/~jtomas/android/audiolibros/";

        //Mock array
        books = new Vector<Book>();
        books.add(new Book("Kappa", "Akutagawa",
                SERVIDOR+"kappa.jpg", SERVIDOR+"kappa.mp3",
                Book.G_S_XIX, false, false));
        books.add(new Book("Avecilla", "Alas Clarín, Leopoldo",
                SERVIDOR+"avecilla.jpg", SERVIDOR+"avecilla.mp3",
                Book.G_S_XIX, true, false));
        books.add(new Book("Divina Comedia", "Dante",
                SERVIDOR+"divina_comedia.jpg", SERVIDOR+"divina_comedia.mp3",
                Book.G_EPICO, true, false));
        books.add(new Book("Viejo Pancho, El", "Alonso y Trelles, José",
                SERVIDOR+"viejo_pancho.jpg", SERVIDOR+"viejo_pancho.mp3",
                Book.G_S_XIX, true, true));
        books.add(new Book("Canción de Rolando", "Anónimo",
                SERVIDOR+"cancion_rolando.jpg",	SERVIDOR+"cancion_rolando.mp3",
                Book.G_EPICO, false, true));
        books.add(new Book("Matrimonio de sabuesos", "Agata Christie",
                SERVIDOR+"matrim_sabuesos.jpg",SERVIDOR+"matrim_sabuesos.mp3",
                Book.G_SUSPENSE, false, true));
        books.add(new Book("La iliada", "Homero",
                SERVIDOR+"la_iliada.jpg", SERVIDOR+"la_iliada.mp3",
                Book.G_EPICO, true, false));

        adaptador = new AdaptadorLibrosFiltro (context, books);
    }

    public Vector<Book> getBooks(){
        return books;
    }



public AdaptadorLibrosFiltro getAdaptador() {
        return adaptador;
        }


}
