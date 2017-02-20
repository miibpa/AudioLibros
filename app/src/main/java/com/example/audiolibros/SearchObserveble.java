package com.example.audiolibros;

import java.util.Observable;

import android.support.v7.widget.SearchView;

/**
 * Created by miguel on 22/1/17.
 */

public class SearchObserveble extends Observable implements SearchView.OnQueryTextListener {
    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        setChanged();
        notifyObservers(newText);
        return true;
    }
}
