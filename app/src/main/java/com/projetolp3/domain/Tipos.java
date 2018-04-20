package com.projetolp3.domain;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IntDef({Tipos.banho, Tipos.passeio, Tipos.comida, Tipos.brincar, Tipos.tosa, Tipos.vacina,
        Tipos.remedio, Tipos.veterinario, Tipos.cio, Tipos.outro})
@Retention(RetentionPolicy.SOURCE)
public @interface Tipos{
    int banho = 1;
    int passeio = 2;
    int comida = 3;
    int brincar = 4;
    int tosa = 5;
    int vacina = 6;
    int remedio = 7;
    int veterinario = 8;
    int cio = 9;
    int outro = 10;
}