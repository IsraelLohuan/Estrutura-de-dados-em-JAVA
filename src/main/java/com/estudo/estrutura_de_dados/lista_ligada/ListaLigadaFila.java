/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.estudo.estrutura_de_dados.lista_ligada;

public class ListaLigadaFila<T extends Elemento> extends ListaLigada<T> {
    @Override
    public boolean push(T e) {
       if (isEmpty()) {
            inicio = e;
        } else {
            Elemento fim = getFim();
            fim.setProximo(e);
        }
       
       return true;
    }
    
    private T getFim() {
        if (isEmpty()) {
            return inicio;
        }
        
        T e = inicio;
        while (e.getProximo() != null) {
            e = (T) e.getProximo();
        }
        return e;
    }
}
