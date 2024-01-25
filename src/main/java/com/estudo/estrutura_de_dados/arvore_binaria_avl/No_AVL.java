package com.estudo.estrutura_de_dados.arvore_binaria_avl;

public class No_AVL<T> {

    public No_AVL(int id, T dados) {
        this.id = id;
        this.dados = dados;
        this.lado = 'X';
    }

    private No_AVL esq;        // no' a esquerda (valores menores)
    private No_AVL dir;        // no' a direita (valores maiores)
    private final int id;      // identificador numerico do no' (a ser fornecido pelo metodo usuario)
    private final T dados;     // carga util
    public char lado;          // flag auxiliar para indicar o lado adotado pelo filho no percurso (impressao e inclusao)

    //AVL===
    private int pE;           // profundidade maxima do ramo esquerdo do no'
    private int pD;           // profundidade maxima do ramo direito do no'
    //---

    public No_AVL getEsq() {
        return esq;
    }

    public void setEsq(No_AVL esq) {
        this.esq = esq;
    }

    public No_AVL getDir() {
        return dir;
    }

    public void setDir(No_AVL dir) {
        this.dir = dir;
    }

    public int getId() {
        return id;
    }

    public T getDados() {
        return dados;
    }

    //AVL=======================================================================
    public int getpE() {
        return pE;
    }

    public void setpE(int pE) {
        this.pE = pE;
    }

    public int getpD() {
        return pD;
    }

    public void setpD(int pD) {
        this.pD = pD;
    }

    public int fatorBalanco() {
        return pD - pE;
    }

    /**
     * Retorna a profundidade maxima da subarvore em que este no' e' o raiz.
     *
     * @return
     */
    public int maxProf() {
        if (pD > pE) {
            return pD;
        } else {
            return pE;
        }

    }
    //--------------------------------------------------------------------------

}
