package com.projetolp3.presentation.view;

import com.projetolp3.presentation.presenter.ApresentadorBase;

public interface ViewBase<T extends ApresentadorBase> {
    void setApresentador(T apresentador);
}
