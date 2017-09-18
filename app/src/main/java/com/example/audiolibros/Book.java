package com.example.audiolibros;

public class Book {
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

    //Create empty instance to avoid NPE
    public final static Book BOOK_EMPTY = new Book("", "anónimo", "http://www.dcomg.upv.es/~jtomas/android/audiolibros/sin_portada.jpg", "", G_TODOS, true, false);

    public Book(String titulo, String autor, String urlImagen,
                String urlAudio, String genero, Boolean novedad, Boolean leido) {
        this.titulo = titulo; this.autor = autor;
        this.urlImagen = urlImagen;	this.urlAudio = urlAudio;
        this.genero = genero; this.novedad = novedad; this.leido = leido;
    }


    public static class BookBuilder {
        private String titulo = "";
        private String autor = "anónimo";
        private String urlImagen =
                "http://www.dcomg.upv.es/~jtomas/android/audiolibros/sin_portada.jpg";
        private String urlAudio = "";
        private String genero = G_TODOS;
        private boolean nuevo = true;
        private boolean leido = false;

        public BookBuilder withTitulo(String titulo) {
            this.titulo = titulo;
            return this;
        }

        public BookBuilder withAutor(String autor) {
            this.autor = autor;
            return this;
        }

        public BookBuilder withUrlImagen(String urlImagen) {
            this.urlImagen = urlImagen;
            return this;
        }

        public BookBuilder withUrlAudio(String urlAudio){
            this.urlAudio = urlAudio;
            return this;
        }

        public BookBuilder withGenero(String genero){
            this.genero = genero;
            return this;
        }

        public BookBuilder withIsNuevo(boolean nuevo){
            this.nuevo = nuevo;
            return this;
        }

        public BookBuilder withIsLeido(boolean leido){
            this.leido = leido;
            return this;
        }

        public Book build() {
            return new Book(titulo, autor, urlImagen, urlAudio, genero,
                    nuevo, leido);
        }

    }



}