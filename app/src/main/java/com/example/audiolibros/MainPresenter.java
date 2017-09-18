package com.example.audiolibros;

/**
 *
 * Created by miguel on 5/2/17.
 *
 */

public class MainPresenter  {

    private final View  view;
    private final SaveLastBook saveLastBook;
    private final GetLastBook getLastBook;
    private final HasLastBook hasLastBook;


    public MainPresenter(SaveLastBook saveLastBook,GetLastBook getLastBook,HasLastBook hasLastBook, View view) {
        this.saveLastBook = saveLastBook;
        this.hasLastBook = hasLastBook;
        this.getLastBook = getLastBook;
        this.view = view;
    }

    public void clickFavouriteButton(){
        if(hasLastBook.execute()){
            view.mostrarDetalle(getLastBook.execute());
        }else{
            view.mostrarNoUltimaVisita();
        }
    }

    public void openDetalle(int id){
        saveLastBook.execute(id);
        view.mostrarDetalle(id);
    }

    public interface View{
        void mostrarDetalle(int lastBook);
        void mostrarNoUltimaVisita();
    }
}
