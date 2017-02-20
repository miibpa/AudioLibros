package com.example.audiolibros;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import java.util.Vector;

/**
 * Created by miguel on 24/1/17.
 */

public class LibrosSingleton {

    private static  LibrosSingleton ourInstance;
    private Vector<Libro> libros;
    private AdaptadorLibrosFiltro adaptador;

    public static LibrosSingleton getInstance(Context context) {
        if(ourInstance == null) {
            ourInstance = new LibrosSingleton(context);
        }
        return ourInstance;
    }

    private LibrosSingleton(Context context) {
        final String SERVIDOR =
                "http://www.dcomg.upv.es/~jtomas/android/audiolibros/";
        libros = new Vector<Libro>();
        libros.add(new Libro("Kappa", "Akutagawa",
                SERVIDOR+"kappa.jpg", SERVIDOR+"kappa.mp3",
                Libro.G_S_XIX, false, false));
        libros.add(new Libro("Avecilla", "Alas Clarín, Leopoldo",
                SERVIDOR+"avecilla.jpg", SERVIDOR+"avecilla.mp3",
                Libro.G_S_XIX, true, false));
        libros.add(new Libro("Divina Comedia", "Dante",
                SERVIDOR+"divina_comedia.jpg", SERVIDOR+"divina_comedia.mp3",
                Libro.G_EPICO, true, false));
        libros.add(new Libro("Viejo Pancho, El", "Alonso y Trelles, José",
                SERVIDOR+"viejo_pancho.jpg", SERVIDOR+"viejo_pancho.mp3",
                Libro.G_S_XIX, true, true));
        libros.add(new Libro("Canción de Rolando", "Anónimo",
                SERVIDOR+"cancion_rolando.jpg",	SERVIDOR+"cancion_rolando.mp3",
                Libro.G_EPICO, false, true));
        libros.add(new Libro("Matrimonio de sabuesos", "Agata Christie",
                SERVIDOR+"matrim_sabuesos.jpg",SERVIDOR+"matrim_sabuesos.mp3",
                Libro.G_SUSPENSE, false, true));
        libros.add(new Libro("La iliada", "Homero",
                SERVIDOR+"la_iliada.jpg", SERVIDOR+"la_iliada.mp3",
                Libro.G_EPICO, true, false));

        adaptador = new AdaptadorLibrosFiltro (context, libros);
    }

    public Vector<Libro> getLibros(){
        return libros;
    }



public AdaptadorLibrosFiltro getAdaptador() {
        return adaptador;
        }


}
