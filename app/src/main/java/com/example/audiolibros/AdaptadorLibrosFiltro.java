package com.example.audiolibros;

import android.content.Context;

import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

public class AdaptadorLibrosFiltro extends AdaptadorLibros implements Observer{
    private Vector<Book> vectorSinFiltro;// Vector con todos los libros
    private Vector<Integer> indiceFiltro; // Índice en vectorSinFiltro de
    // Cada elemento de vectorLibros
    private String busqueda = "";         // Búsqueda sobre autor o título
    private String genero = "";           // Género seleccionado
    private boolean novedad = false;      // Si queremos ver solo novedades
    private boolean leido = false;        // Si queremos ver solo leidos

    public AdaptadorLibrosFiltro(Context contexto,
                                 Vector<Book> vectorBooks) {
        super(contexto, vectorBooks);
        vectorSinFiltro = vectorBooks;
        recalculaFiltro();
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda.toLowerCase();
        recalculaFiltro();
    }

    public void setGenero(String genero) {
        this.genero = genero;
        recalculaFiltro();
    }

    public void setNovedad(boolean novedad) {
        this.novedad = novedad;
        recalculaFiltro();
    }

    public void setLeido(boolean leido) {
        this.leido = leido;
        recalculaFiltro();
    }

    public void recalculaFiltro() {
        vectorBooks = new Vector<Book>();
        indiceFiltro = new Vector<Integer>();
        for (int i = 0; i < vectorSinFiltro.size(); i++) {
            Book book = vectorSinFiltro.elementAt(i);
            if ((book.titulo.toLowerCase().contains(busqueda) ||
                    book.autor.toLowerCase().contains(busqueda))
                    && (book.genero.startsWith(genero))
                    && (!novedad || (novedad && book.novedad))
                    && (!leido || (leido && book.leido))) {
                vectorBooks.add(book);
                indiceFiltro.add(i);
            }
        }
    }

    public Book getItem(int posicion) {
        return vectorSinFiltro.elementAt(indiceFiltro.elementAt(posicion));
    }

    public long getItemId(int posicion) {
        return indiceFiltro.elementAt(posicion);
    }

    public void borrar(int posicion){
        vectorSinFiltro.remove((int)getItemId(posicion));
        recalculaFiltro();
    }

    public void insertar(Book book){
        vectorSinFiltro.add(book);
        recalculaFiltro();
    }

    @Override
    public void update(Observable observable, Object data) {
        setBusqueda((String) data);
        notifyDataSetChanged();
    }
}
