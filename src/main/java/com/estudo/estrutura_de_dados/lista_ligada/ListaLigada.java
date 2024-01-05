/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.estudo.estrutura_de_dados.lista_ligada;

/**
 *
 * @author israel.lohuan
 * @param <T>
 */
public abstract class ListaLigada<T extends Elemento> {
    T inicio;
    
    abstract public boolean push(T e);
    
    public T pop() {
        if(inicio == null) {
            return null;
        }
        
        T e = inicio;
        inicio = (T) e.getProximo();
        e.setProximo(null);
        return e;
    }
    
    public boolean isEmpty() {
        return inicio == null;
    }
    
    public T getInicio() {
        return inicio;
    }
}
