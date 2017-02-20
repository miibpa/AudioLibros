package com.example.audiolibros;

import java.util.Vector;

public class Libro {
    public String titulo;
    public String autor;
    //public int recursoImagen;
    public String urlImagen;
    public String urlAudio;
    public String genero;   // Género literario
    public Boolean novedad; // Es una novedad
    public Boolean leido;   // Leído por el usuario
    public final static String G_TODOS = "Todos los géneros";
    public final static String G_EPICO = "Poema épico";
    public final static String G_S_XIX = "Literatura siglo XIX";
    public final static String G_SUSPENSE = "Suspense";
    public final static String[] G_ARRAY = new String[] {G_TODOS, G_EPICO,
            G_S_XIX, G_SUSPENSE };

    public final static Libro LIBRO_EMPTY = new Libro("", "anónimo", "http://www.dcomg.upv.es/~jtomas/android/audiolibros/sin_portada.jpg", "", G_TODOS, true, false);

    public Libro(String titulo, String autor, String urlImagen,
                 String urlAudio, String genero, Boolean novedad, Boolean leido) {
        this.titulo = titulo; this.autor = autor;
        this.urlImagen = urlImagen;	this.urlAudio = urlAudio;
        this.genero = genero; this.novedad = novedad; this.leido = leido;
    }



    public static class LibroBuilder {
        private String titulo = "";
        private String autor = "anónimo";
        private String urlImagen =
                "http://www.dcomg.upv.es/~jtomas/android/audiolibros/sin_portada.jpg";
        private String urlAudio = "";
        private String genero = G_TODOS;
        private boolean nuevo = true;
        private boolean leido = false;

        public LibroBuilder withTitulo(String titulo) {
            this.titulo = titulo;
            return this;
        }

        public LibroBuilder withAutor(String autor) {
            this.autor = autor;
            return this;
        }

        public LibroBuilder withUrlImagen(String urlImagen) {
            this.urlImagen = urlImagen;
            return this;
        }

        public LibroBuilder withUrlAudio(String urlAudio){
            this.urlAudio = urlAudio;
            return this;
        }

        public LibroBuilder withGenero(String genero){
            this.genero = genero;
            return this;
        }

        public LibroBuilder withIsNuevo(boolean nuevo){
            this.nuevo = nuevo;
            return this;
        }

        public LibroBuilder withIsLeido(boolean leido){
            this.leido = leido;
            return this;
        }

        public Libro build() {
            return new Libro(titulo, autor, urlImagen, urlAudio, genero,
                    nuevo, leido);
        }

    }



}