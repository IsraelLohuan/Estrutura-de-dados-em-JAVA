/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.estudo.estrutura_de_dados.lista_ligada;

public class ListaLigadaPilha<T extends Elemento> extends ListaLigada<T> {
    @Override
    public boolean push(T e) {
       e.setProximo(e);
       inicio = e;
       return true;
    }
}
