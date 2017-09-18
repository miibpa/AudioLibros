package com.example.audiolibros;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.example.audiolibros.fragments.ClickAction;
import com.example.audiolibros.fragments.EmptyClickAction;

import java.util.Vector;

public class AdaptadorLibros extends
        RecyclerView.Adapter<AdaptadorLibros.ViewHolder> {
    private LayoutInflater inflador;      //Crea Layouts a partir del XML
    protected Vector<Book> vectorBooks; //Vector con libros a visualizar
    private Context contexto;
    private ClickAction clickAction = new EmptyClickAction();
    private ClickAction longClickAction = new EmptyClickAction();

    public AdaptadorLibros(Context contexto, Vector<Book> vectorBooks) {
        inflador = (LayoutInflater) contexto
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.vectorBooks = vectorBooks;
        this.contexto = contexto;
    }

    public void setClickAction(ClickAction clickAction) {
        this.clickAction = clickAction;
    }

    public void setLongClickAction(ClickAction longClickAction) {
        this.longClickAction = longClickAction;
    }

    //Creamos nuestro ViewHolder, con los tipos de elementos a modificar
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView portada;
        public TextView titulo;

        public ViewHolder(View itemView) {
            super(itemView);
            portada = (ImageView) itemView.findViewById(R.id.portada);
            //portada.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            titulo = (TextView) itemView.findViewById(R.id.titulo);
        }
    }

    // Creamos el ViewHolder con las vista de un elemento sin personalizar
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflamos la vista desde el xml
        View v = inflador.inflate(R.layout.elemento_selector, null);
        return new ViewHolder(v);
    }

    // Usando como base el ViewHolder y lo personalizamos
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int posicion) {
        Book book = vectorBooks.elementAt(posicion);
        //holder.portada.setImageResource(libro.recursoImagen);
        holder.titulo.setText(book.titulo);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickAction.execute(posicion);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                longClickAction.execute(posicion);
                return true;
            }
        });
        VolleySingleton volleySingleton = VolleySingleton.getInstance(contexto);
        volleySingleton.getLectorImagenes().get(book.urlImagen,
                new ImageLoader.ImageListener() {
                    @Override public void onResponse(ImageLoader.ImageContainer
                                                             response, boolean isImmediate) {
                        Bitmap bitmap = response.getBitmap();
                        if (bitmap != null) {

                            holder.portada.setImageBitmap(bitmap);

                            //Extraemos el color principal de un bitmap
                            Palette palette = Palette.from(bitmap).generate();
                            holder.itemView.setBackgroundColor(palette.getLightMutedColor(0));
                            holder.titulo.setBackgroundColor(palette.getLightVibrantColor(0));

                            holder.portada.invalidate();
                        }

                    }
                    @Override public void onErrorResponse(VolleyError error) {
                        holder.portada.setImageResource(R.drawable.books);
                    }
                });

    }

    // Indicamos el número de elementos de la lista
    @Override public int getItemCount() {
        return vectorBooks.size();
    }


}
