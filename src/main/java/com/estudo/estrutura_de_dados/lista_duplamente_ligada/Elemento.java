package com.estudo.estrutura_de_dados.lista_duplamente_ligada;

public class Elemento {

    public Elemento(String nome, int numero) {
        this.nome = nome;
        this.numero = numero;
        this.proximo = null;
        this.anterior = null;
    }

    private String nome;
    private int numero;
    private Elemento proximo;
    private Elemento anterior;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Elemento getProximo() {
        return proximo;
    }

    public void setProximo(Elemento proximo) {
        this.proximo = proximo;
    }

    public Elemento getAnterior() {
        return anterior;
    }

    public void setAnterior(Elemento anterior) {
        this.anterior = anterior;
    }
    
    

}
