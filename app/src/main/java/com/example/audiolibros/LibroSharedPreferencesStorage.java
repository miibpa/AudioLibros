package com.example.audiolibros;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by miguel on 22/1/17.
 */

public class LibroSharedPreferencesStorage implements LibroStorage {

    public static final String PREF_AUDIOLIBROS =
            "com.example.audiolibros_internal";
    public static final String KEY_ULTIMO_LIBRO = "ultimo";
    private final Context context;

    private static LibroSharedPreferencesStorage instance;

    public static LibroStorage getInstance(Context context) {
        if(instance == null) {

            instance = new LibroSharedPreferencesStorage(context);
        }
        return instance;
    }

    private LibroSharedPreferencesStorage(Context context) {
        this.context = context;
    }

    @Override
    public boolean hasLastBook() {
        return getPreference().contains(KEY_ULTIMO_LIBRO);
    }

    private SharedPreferences getPreference() {
        return context.getSharedPreferences(
                PREF_AUDIOLIBROS, Context.MODE_PRIVATE);

    }
    @Override
    public int getLastBook() {
        return getPreference().getInt(KEY_ULTIMO_LIBRO, -1);
    }

    @Override
    public void setLastBook(int id) {
        SharedPreferences.Editor editor = getPreference().edit();
        editor.putInt("ultimo", id);
        editor.apply();
    }

    @Override
    public void saveLastBook(int id) {
        SharedPreferences.Editor editor = getPreference().edit();
        editor.putInt("ultimo", id);
        editor.apply();
    }
}
