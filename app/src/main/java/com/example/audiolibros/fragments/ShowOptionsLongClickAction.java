package com.example.audiolibros.fragments;

/**
 * Created by miguel on 22/1/17.
 */

public class ShowOptionsLongClickAction implements ClickAction {

    public ShowOptionsLongClickAction(SelectorFragment selectorFragment) {
        this.selectorFragment = selectorFragment;
    }

    private SelectorFragment selectorFragment;
    @Override
    public void execute(int position) {
        selectorFragment.showOptionsLongClick(position,selectorFragment.getView());
    }
}
