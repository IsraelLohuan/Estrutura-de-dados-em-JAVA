/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.estudo.estrutura_de_dados.arvore_binaria;

public class No_A<T> {
    private final int id;
    private final T dados; 
    private No_A esquerda;
    private No_A direita; 
    public char lado;
    
    public No_A(int id, T dados) {
        this.id = id;
        this.dados = dados;
    }
    
    public No_A getEsquerda() {
        return this.esquerda;
    }
    
    public No_A getDireita() {
        return this.direita;
    }
    
    public void setDireita(No_A dir) {
        this.direita = dir;
    }
    
    public void setEsquerda(No_A dir) {
        this.esquerda = dir;
    }
    
    public int getId() {
        return id;
    }
    
    public T getDados() {
        return dados;
    }
}
