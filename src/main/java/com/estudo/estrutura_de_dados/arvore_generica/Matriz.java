package com.estudo.estrutura_de_dados.arvore_generica;


import java.util.ArrayList;

public class Matriz {

    public Matriz() {
        al_pai = new ArrayList<>();
        al_filho = new ArrayList<>();
        al_impressao = new ArrayList<>();
    }

    private ArrayList<Elemento> al_pai;
    private ArrayList<Elemento> al_filho;
    private ArrayList<Ponteiro> al_impressao;

    /**
     * Localizar [e remover] o par pai/filho.
     *
     * @param pai Codigo do Elemento pai.
     * @param filho Codigo do Elemento filho.
     * @param remove true = remove o par da matriz caso seja localizado; false =
     * nao remove.
     * @return Vetor de Elementos de 2 posicoes: [0] = Elemento pai, [1] =
     * Elemento filho ou null caso nao seja encontrado.
     */
    public Elemento[] localizar(String pai, String filho, boolean remove) {
        if (al_pai.isEmpty()) {
            return null;
        }
        for (int i = 0; i < al_pai.size(); i++) {
            if (al_pai.get(i).getCodigo().equals(pai) && al_filho.get(i).getCodigo().equals(filho)) {
                Elemento[] ret = new Elemento[2];
                ret[0] = al_pai.get(i);
                ret[1] = al_filho.get(i);
                if (remove) {
                    al_pai.remove(i);
                    al_filho.remove(i);
                }
                return ret;
            }
        }
        return null;
    }

    /**
     * Inserir um novo par pai-filho no array.
     *
     * @param pai
     * @param filho
     * @return
     */
    public boolean inserir(Elemento pai, Elemento filho) {
        if (localizar(pai.getCodigo(), filho.getCodigo(), false) == null) {
            return false;
        }
        al_pai.add(pai);
        al_filho.add(filho);
        return true;
    }

    /**
     * Imprime a lista de impressao.
     *
     * @param refazLista true = refaz a lista de impressao; false = imprime a
     * lista atualmente presente.
     * @param codigo Codigo do elemento inicial; se for null parte do no' raiz.
     * Este parametro e' ignorado caso "refazLista" seja false.
     */
    public void imprimir(boolean refazLista, String codigo) {
        if (refazLista) {
            refazLista(codigo);
        }

    }

    /**
     * Identifica, na array de elementos, qual o elemento raiz da arvore. Este
     * elemento nao estara' na array de filhos.
     *
     * @return
     */
//    private Elemento identificaRaiz() {
//        if (al_pai.isEmpty()) {
//            return null;
//        }
//        Elemento ePai = null;
//        Elemento ePaiAux = null;
//        for (int i = 0; i < al_pai.size(); i++) {
//            ePai = al_pai.get(i);
//            if (ePai)
//        }
//
//    }

    /**
     * Refaz a lista de impressao.
     *
     * @param codigo Codigo do elemento inicial.
     * @see #imprimir(boolean, java.lang.String)
     */
    private void refazLista(String codigo) {
        al_impressao.clear();

    }

    /**
     * Classe utilizada para composicao da lista de impressao.
     */
    private class Ponteiro {

        public Ponteiro(Elemento elem, int nivel) {
            this.elem = elem;
            this.nivel = nivel;
        }

        Elemento elem;
        int nivel;

    }

}
