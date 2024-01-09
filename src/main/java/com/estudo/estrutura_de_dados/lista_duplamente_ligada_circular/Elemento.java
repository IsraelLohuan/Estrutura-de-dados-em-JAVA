package com.estudo.estrutura_de_dados.lista_duplamente_ligada_circular;


/**
 * class Elemento
 *
 * @author Marcio Porto Feitosa - 22/12/2022 - 17:31:51
 */
public class Elemento {

    public Elemento() {
        this._id = 0;
        this.posicao = 0;
        this.status = -1;
        this.proximo = this;  //* a casa é instanciada conectada a ela mesma
        this.anterior = this;
    }

    private int _id;
    private int posicao;
    private int status;

    private Elemento proximo;
    private Elemento anterior;

    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    //=== game
    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    //---
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
