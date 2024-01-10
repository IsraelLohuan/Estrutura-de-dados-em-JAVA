package com.estudo.estrutura_de_dados.tabela_hash_com_lista_ligada;


import java.util.LinkedList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * classe TabelaHash_2
 *
 * @author Marcio Porto Feitosa - 05/04/2023 - 11:17:48
 */
public class TabelaHash_2 {

    public TabelaHash_2(int tam, boolean rep) {
        this.tabHash2 = new LinkedList[tam];
        this.repete = rep;
        this.qtd = 0;
        for (int i = 0; i < tam; i++) {
            this.tabHash2[i] = new LinkedList<>();
        }
    }

    private final LinkedList<Elemento>[] tabHash2;
    private final boolean repete;
    private int qtd;

    /**
     * Funcao de hashing.
     *
     * @param nr
     * @return Resto da divisao do numero (nr) pelo tamanho da tabela.
     */
    public int hash(int nr) {
        return nr % this.tabHash2.length;
    }

    /**
     * Verifica se um elemento ja' se encontra na tabela.
     *
     * @param nr
     * @return # <b>-1</b> -> elemento nao esta' na tabela
     * <br># <b>maior ou igual a zero</b> -> elemento esta' na tabela (retorna o
     * indice na LinkedList da posicao correspondente ao resultado da sua funcao
     * hash em que esta' o elemento).
     */
    public int localiza(int nr) {

        int h = hash(nr);

        if (this.tabHash2[h].isEmpty()) {
            return -1;
        }

        for (int i = 0; i < this.tabHash2[h].size(); i++) {
            if (this.tabHash2[h].get(i).getNumero() == nr) {
                return i;
            }
        }

        return -1;

    }

    /**
     * Insere um novo elemento na tabela.
     *
     * @param e
     * @return # <b>1</b> -> Insercao bem sucedida.
     * <br># <b>2</b> -> Elemento ja' consta da tabela.
     */
    public int insere(Elemento e) {

        //**********************************************************************
        /* ESTE METODO NAO E' MAIS NECESSARIO
        ------------------------------------------------------------------------
        // VERIFICAR SE A TABELA ESTA' CHEIA.
        if (qtd == tabHash.length) {
            return 3;
        }
        ------------------------------------------------------------------------
        ------------------------------------------------------------------------
         */
        int nr = e.getNumero();  // numero identificador do elemento (salvar para diminuir a quantidade de chamadas ao metodo e.getNumero()).
        int h = hash(nr);

        //=== CASO DE NAO PERMITIR REPETICAO ===================================
        if (!repete) {
            if (this.localiza(nr) >= 0) {
                return 2;
            }
        }
        //----------------------------------------------------------------------

        //=== INSERIR O NOVO ELEMENTO ==========================================
        this.tabHash2[h].add(e);
        //----------------------------------------------------------------------

        qtd++;
        return 1;
        //----------------------------------------------------------------------
    }

    /**
     * Remove um elemento da tabela dado o seu numero identificador.
     *
     * @param nr
     * @return O elemento, caso seja localizado; caso contrario retorna
     * <i>null</i>.
     */
    public Elemento remove(int nr) {
        int i = localiza(nr);
        if (i == -1) {
            return null;
        } else {
            int h = hash(nr);
            Elemento e = tabHash2[h].remove(i);
            qtd--;
            return e;
        }
    }

    /**
     * Gera uma String com a impressao da tabela.
     *
     * @param info
     */
    public void imprimeTab(boolean info) {

        for (int i = 0; i < this.tabHash2.length; i++) {

            System.out.println("Indice " + i + " ------------------------------------------------");

            if (this.tabHash2[i].isEmpty()) {
                System.out.println("-- vazia --");
                continue;
            }

            for (int j = 0; j < this.tabHash2[i].size(); j++) {
                Elemento e = this.tabHash2[i].get(j);
                System.out.println("[" + i + "] " + "[" + j + "] " + imprimeElem(e, info));

            }

        }

    }

    /**
     * Retorna uma String com a impressao do elemento.
     *
     * @param e
     * @param info
     * @return String no seguinte formato: <b>[numero] | [nome]</b>
     * <br>
     * Se "info" == true, acrescenta <b>| [infos]</b>
     */
    public String imprimeElem(Elemento e, boolean info) {
        String ret = e.getNumero() + " | " + e.getNome();
        if (info) {
            ret += " | " + e.getInfos();
        }
        return ret;
    }

}
