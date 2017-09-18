package com.example.audiolibros.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.audiolibros.AdaptadorLibrosFiltro;
import com.example.audiolibros.AppHelper;
import com.example.audiolibros.Book;
import com.example.audiolibros.BooksSingleton;
import com.example.audiolibros.MainActivity;
import com.example.audiolibros.R;
import com.example.audiolibros.SearchObserveble;

import java.util.Vector;

public class SelectorFragment extends Fragment {
    private Activity actividad;
    private RecyclerView recyclerView;
    private AdaptadorLibrosFiltro adaptador;
    private Vector<Book> vectorBooks;
    private SearchObserveble searchObserveble;

    @Override public void onAttach(Activity actividad) {
        super.onAttach(actividad);
        this.actividad = actividad;
        AppHelper app = (AppHelper) actividad.getApplication();
        adaptador =  BooksSingleton.getInstance(getActivity().getApplicationContext()).getAdaptador();
        vectorBooks = BooksSingleton.getInstance(getActivity().getApplicationContext()).getBooks();
    }

    @Override public View onCreateView(LayoutInflater inflador, ViewGroup
            contenedor, Bundle savedInstanceState) {
        View vista = inflador.inflate(R.layout.fragment_selector,
                contenedor, false);
        recyclerView = (RecyclerView) vista.findViewById(
                R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(actividad,2));
        recyclerView.setAdapter(adaptador);
        adaptador.setClickAction(new OpenDetailClickAction(((MainActivity)getActivity()).getMainPresenter()));
        adaptador.setLongClickAction(new ShowOptionsLongClickAction(this));
        setHasOptionsMenu(true);
        return vista;
    }

    public void showOptionsLongClick(final int id,final View v){
        AlertDialog.Builder menu = new AlertDialog.Builder(actividad);
        CharSequence[] options = { "Compartir", "Borrar ", "Insertar" };
        menu.setItems(options, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int option) {
                switch (option) {
                    case 0: //Share
                        Book book = vectorBooks.elementAt(id);
                        Intent i = new Intent(Intent.ACTION_SEND);
                        i.setType("text/plain");
                        i.putExtra(Intent.EXTRA_SUBJECT, book.titulo);
                        i.putExtra(Intent.EXTRA_TEXT, book.urlAudio);
                        startActivity(Intent.createChooser(i, "Compartir"));
                        break;
                    case 1: //Delete
                        Snackbar.make(v,"¿Estás seguro?", Snackbar.LENGTH_LONG)
                                .setAction("SI", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        adaptador.borrar(id);
                                        adaptador.notifyDataSetChanged();
                                    }
                                })
                                .show();

                    case 2: //Insert
                        int posicion = recyclerView.getChildLayoutPosition(v);
                        adaptador.insertar((Book) adaptador.getItem(posicion));
                        adaptador.notifyDataSetChanged();
                        Snackbar.make(v,"Libro insertado", Snackbar.LENGTH_INDEFINITE)
                                .setAction("OK", new View.OnClickListener() {
                                    @Override public void onClick(View view) { }
                                })
                                .show();
                        break;
                }
            }
        });
        menu.create().show();
    }

    @Override public void onResume(){
        ((MainActivity) getActivity()).mostrarElementos(true);
        super.onResume();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_selector, menu);
        MenuItem searchItem = menu.findItem(R.id.menu_buscar);
        SearchView searchView= (SearchView) searchItem.getActionView();

        //Make adapter observe changes in SearchView
        SearchObserveble searchObservable = new SearchObserveble();
        searchObservable.addObserver(adaptador);
        searchView.setOnQueryTextListener(searchObservable);

        MenuItemCompat.setOnActionExpandListener(searchItem,
                new MenuItemCompat.OnActionExpandListener() {
                    @Override
                    public boolean onMenuItemActionCollapse(MenuItem item) {
                        adaptador.setBusqueda("");
                        adaptador.notifyDataSetChanged();
                        return true;  //Allow closing
                    }
                    @Override
                    public boolean onMenuItemActionExpand(MenuItem item) {
                        return true;  // Allow expansion
                    }
                });
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_ultimo) {
            ((MainActivity)getActivity()).getMainPresenter().clickFavouriteButton();
            return true;
        } else if (id == R.id.menu_buscar) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}