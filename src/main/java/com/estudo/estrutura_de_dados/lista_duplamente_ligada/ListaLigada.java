package com.estudo.estrutura_de_dados.lista_duplamente_ligada;


/**
 * classe ListaLigada
 * teste
 *
 * @author Marcio Porto Feitosa - 22/12/2022 - 17:31:28
 */
public class ListaLigada {

    private Elemento inicio;
    private Elemento ultimo;
    private int qtd;

    private void _printElem(Elemento e) {  // Ex1
//        System.out.print("\nNumero -> " + e.getNumero());
//        System.out.print("\nNome ---> " + e.getNome());
        System.out.println(e.getNumero() + " | " + e.getNome());
    }

    /**
     * Insere um elemento quando a lista esta' vazia.
     * <br>
     * Este metodo nao verifica se a lista de fato esta' vazia. Caso nao esteja,
     * sera' criada uma nova lista e a original sera' descartada.
     *
     * @param e
     */
    private void _insereEmVazio(Elemento e) {
        ultimo = e;
        inicio = e;
    }

    /**
     * Remove quando ha' um unico elemento.
     * <br>
     * Este metodo nao verifica se realmente ha' apenas um elemento na lista.
     * Caso haja mais elementos, serao desconectados e disponibilizados para o
     * Garbage Collector.
     *
     * @return
     */
    private Elemento _removeUnico() {
        Elemento e = inicio;
        inicio = null;
        ultimo = null;
        qtd = 0;
        return e;
    }

    /**
     * Insere um elemento no inicio
     *
     * @param e
     */
    public void insereInicio(Elemento e) {

        if (isEmpty()) {
            _insereEmVazio(e);
        } else {
            e.setProximo(inicio);
            inicio.setAnterior(e);
            inicio = e;
        }
        qtd++;

    }

    /**
     * Insere um elemento do final
     *
     * @param e
     */
    public void insereUltimo(Elemento e) {

        if (isEmpty()) {
            _insereEmVazio(e);
        } else {
            ultimo.setProximo(e);
            e.setAnterior(ultimo);
            ultimo = e;
        }
        qtd++;

    }

    /**
     * Remove um elemento do inicio; decrementa quantidade.
     *
     * @return
     */
    public Elemento removeInicio() {
        if (isEmpty()) {
            return null;
        }
        if (qtd == 1) {
            return _removeUnico();
        }
        Elemento e = inicio;
        inicio = e.getProximo();
        inicio.setProximo(null);
        e.setProximo(null);
        qtd--;
        return e;
    }

    /**
     * Remove um elemento do final; decrementa quantidade.
     *
     * @return
     */
    public Elemento removeUltimo() {
        if (isEmpty()) {
            return null;
        }
        if (qtd == 1) {
            return _removeUnico();
        }
        Elemento e = ultimo;
        ultimo = e.getAnterior();
        e.setAnterior(null);
        qtd--;
        return e;
    }

    /**
     * Imprime os elementos da lista no sentido do inicio ao final
     */
    public void imprimeParaFim() {
        if (isEmpty()) {
            System.out.println("A lista esta' vazia");
        } else {
            Elemento e = inicio;
            while (e != null) {
                _printElem(e);
                e = e.getProximo();
            }
        }
    }

    /**
     * Imprime os elementos da lista no sentido do final ao inicio
     */
    public void imprimeParaInicio() {
        if (isEmpty()) {
            System.out.println("A lista esta' vazia");
        } else {
            Elemento e = ultimo;
            while (e != null) {
                _printElem(e);
                e = e.getAnterior();
            }
        }
    }

    /**
     * Retorna o ponteiro do elemento em uma determinada posicao no sentido do
     * inicio ao fim.
     *
     * @param pos Posicao desejada
     * @return
     */
    public Elemento getPosDoInicio(int pos) {
        if (pos > qtd || pos <= 0) {
            return null;
        }
        int posAtu = 1;
        Elemento e = inicio;
        while (posAtu < pos) {
            e = e.getProximo();
            posAtu++;
        }
        return e;
    }

    /**
     * Retorna o ponteiro do elemento em uma determinada posicao no sentido do
     * fim ao inicio.
     *
     * @param pos Posicao desejada
     * @return
     */
    public Elemento getPosDoFim(int pos) {
        if (pos > qtd || pos <= 0) {
            return null;
        }
        int posAtu = 1;
        Elemento e = ultimo;
        while (posAtu < pos) {
            e = e.getAnterior();
            posAtu++;
        }
        return e;
    }

    /**
     * Retorna o ponteiro do elemento que esta' em determinada posicao contada a
     * partir do inicio.
     *
     * @param num
     * @return
     */
    public Elemento getElemDoInicio(int num) {
        if (isEmpty()) {
            return null;
        }
        Elemento e = inicio;
        while (e.getNumero() != num) {
            e = e.getProximo();
        }
        return e;

    }

    /**
     * Retorna o ponteiro do elemento que esta' em determinada posicao contada a
     * partir do final.
     *
     * @param num
     * @return
     */
    public Elemento getElemDoFim(int num) {
        if (isEmpty()) {
            return null;
        }
        Elemento e = ultimo;
        while (e.getNumero() != num) {
            e = e.getAnterior();
        }
        return e;

    }

    /**
     * Insere um elemento em determinada posicao (sentido inicio -> fim)
     *
     * @param pos Posicao desejada para inserçao
     * @param e Elemento a inserir
     * @return String vazia em caso de sucesso ou mensagem de erro
     */
    public String inserePosParaFim(int pos, Elemento e) {
        if (pos > qtd + 1 || pos <= 0 || e == null) {
            return "ERRO: um ou mais parametros com problemas!!";
        }
        if (pos == 1) {
            insereInicio(e);
        } else {
            if (pos == qtd + 1) {
                insereUltimo(e);
            } else {
                Elemento cursor = getPosDoInicio(pos - 1);
                e.setProximo(cursor.getProximo());
                e.setAnterior(cursor);
                cursor.getProximo().setAnterior(e);
                cursor.setProximo(e);
                qtd++;
            }
        }
        return "";

    }

    /**
     * Remove um elemento de determinada posicao (sentido fim -> inicio).
     *
     * @param pos
     * @return
     */
    public Elemento removePosParaInicio(int pos) {
        if (isEmpty() || pos > qtd + 1 || pos <= 0) {
            return null;
        }
        if (pos == 1) {
            return removeUltimo();
        } else if (pos == qtd) {
            return removeInicio();
        }
        Elemento cursor = getPosDoFim(pos);
        cursor.getAnterior().setProximo(cursor.getProximo());
        cursor.getProximo().setAnterior(cursor.getAnterior());
        cursor.setProximo(null);
        cursor.setAnterior(null);
        qtd--;
        return cursor;
    }

    /**
     * Retorna o primeiro elemento.
     *
     * @return
     */
    public Elemento getInicio() {
        return inicio;
    }

    /**
     * Retorna o ultimo elemento.
     *
     * @return
     */
    public Elemento getUltimo() {
        return ultimo;
    }

    public boolean isEmpty() {
        return inicio == null;
    }

    /**
     * Retorna a quantidade de elementos na lista.
     *
     * @return
     */
    public int getQtd() {
        return qtd;
    }
    
    public void destroi(){
        inicio = null;
        ultimo = null;
        qtd = 0;
    }

}  // class >
