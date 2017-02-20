package com.example.audiolibros.fragments;

import com.example.audiolibros.MainActivity;
import com.example.audiolibros.MainPresenter;

/**
 * Created by miguel on 22/1/17.
 */

public class OpenDetailClickAction implements ClickAction {

    private final MainPresenter mainPresenter;

    public OpenDetailClickAction(MainPresenter mainPresenter) {
        this.mainPresenter = mainPresenter;
    }

    @Override
    public void execute(int position) {
        mainPresenter.openDetalle(position);
    }
}
