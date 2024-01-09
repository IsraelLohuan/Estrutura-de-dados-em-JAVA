package com.estudo.estrutura_de_dados.lista_duplamente_ligada_circular;


/**
 * classe ListaLigada
 * teste
 *
 * @author Marcio Porto Feitosa - 22/12/2022 - 17:31:28
 */
public class L2Lcirc {

    private Elemento inicio;
    private int qtd;

    public void _imprimeElemento(Elemento e) {  // Ex1
        System.out.println(e.getId());
    }

    /**
     * Faz a conexao circular em um mesmo elemento.
     *
     * @param c
     */
    private void _autoConex(Elemento e) {
        e.setProximo(e);
        e.setAnterior(e);
    }

    /**
     * Insere um elemento quando a lista esta' vazia.
     *
     * @param e
     * @return <i>true</i> se a inclusao for bem sucedida ou <i>false</i> se a
     * lista nao estiver vazia. Não icrementa a quantidade.
     */
    private boolean _insereEmVazio(Elemento e) {
        if (this.qtd > 0) {
            return false;
        }
        inicio = e;
        return true;
    }

    /**
     * Remove quando ha' um unico elemento.
     *
     * @return O elemento da lista ou <i>nulo</> se a lista estiver vazia ou se
     * tiver mais de 1 elemento. Zera a quantidade.
     */
    private Elemento _removeUnico() {
        if (isEmpty() || qtd > 1) {
            return null;
        }
        Elemento e = inicio;
        inicio = null;
        qtd = 0;
        return e;
    }

    /**
     * Insere um elemento no inicio da lista.
     * <br>
     * O novo elemento entra no inicio e desloca o inicio atual para a direita
     * (sentido horario). Incrementa quantidade.
     *
     * @param e Elemento a ser inserido.
     */
    public void insereInicio(Elemento e) {
        if (isEmpty()) {
            _insereEmVazio(e);
        } else {
            e.setProximo(inicio);
            e.setAnterior(inicio.getAnterior());
            inicio.setAnterior(e);
            e.getAnterior().setProximo(e);
            inicio = e;
        }
        qtd++;
    }

    /**
     * Insere um elemento no final da lista.
     * <br>
     * O novo elemento entra no sentido antihorario ao lado do inicio.
     * Incrementa a quantidade.
     *
     * @param e
     */
    public void insereUltimo(Elemento e) {
        if (isEmpty()) {
            _insereEmVazio(e);
        } else {
            e.setProximo(inicio);
            e.setAnterior(inicio.getAnterior());
            inicio.getAnterior().setProximo(e);
            inicio.setAnterior(e);
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
        inicio.getAnterior().setProximo(inicio.getProximo());
        inicio.getProximo().setAnterior(inicio.getAnterior());
        inicio = inicio.getProximo();
        e.setProximo(e);
        e.setAnterior(e);
        qtd--;
        return e;
    }

    /**
     * Remove um elemento do fim; decrementa a quantidade.
     *
     * @return
     */
    public Elemento removeFim() {
        if (isEmpty()) {
            return null;
        }
        if (qtd == 1) {
            return _removeUnico();
        }
        Elemento e = inicio.getAnterior();
        inicio.setAnterior(e.getAnterior());
        e.getAnterior().setProximo(inicio);
        e.setProximo(e);
        e.setAnterior(e);
        qtd--;
        return e;
    }

    /**
     * Retorna o ponteiro do elemento que esta' N posicoes no <b>sentido
     * horario</b> a partir de uma posicao especifica.
     *
     * @param e
     * @param pos Posicao desejada
     * @return O ponteiro para o objeto ou <i>null</i> se a posicao desejada for
     * maior que a quantidade atual de objetos na lista ou menor ou igual a
     * zero.
     */
    public Elemento getPosHorario(Elemento e, int pos) {
        if (pos > qtd || pos <= 0) {
            return null;
        }
        int posAtu = 1;
        Elemento eAux = e;  //* criar ponteiro de deslocamento
        while (posAtu < pos) {
            eAux = eAux.getProximo();
            posAtu++;
        }
        return eAux;
    }

    /**
     * Retorna o ponteiro do elemento que esta' N posicoes no <b>sentido
     * antihorario</b> a partir de uma posicao especifica.
     *
     * @param e
     * @param pos Posicao desejada
     * @return O ponteiro para o objeto ou <i>null</i> se a posicao desejada for
     * maior que a quantidade atual de objetos na lista ou menor ou igual a
     * zero.
     */
    public Elemento getPosAntiHorario(Elemento e, int pos) {
        if (pos > qtd || pos <= 0) {
            return null;
        }
        int posAtu = 1;
        Elemento eAux = e;
        while (posAtu < pos) {
            eAux = eAux.getAnterior();
            posAtu++;
        }
        return eAux;
    }

    /**
     * Remove um elemento.
     * <br>
     * Se o removido for o inicio -> o proximo no sentido horario passara' a ser
     * o inicio.
     *
     * @param e Elemento alvo da remocao.
     * @return
     */
    public boolean removeElemento(Elemento e) {
        if (isEmpty() || e == null) {
            return false;
        }
        if (qtd == 1) {
            _removeUnico();
        } else {
            if (e == inicio) {
                inicio = e.getProximo();
            }
            e.getAnterior().setProximo((e.getProximo()));
            e.getProximo().setAnterior((e.getAnterior()));
            _autoConex(e);
            qtd--;
        }
        return true;
    }

    /**
     * Insere um elemento na posicao de outro, empurrando o primeiro para frente.
     *
     * @param eNovo O novo elemento a ser inserido
     * @param eAtual O elemento atual, cuja posicao será ocupada pelo novo.
     * @return
     */
    public boolean insereHorario(Elemento eNovo, Elemento eAtual) {
        if (eNovo == null) {
            return false;
        }
        if (qtd <= 1 || eAtual == inicio) {
            insereInicio(eNovo);
        } else if (eAtual == inicio.getAnterior()) {
            insereUltimo(eNovo);
        } else {
            eNovo.setProximo(eAtual);
            eNovo.setAnterior(eAtual.getAnterior());
            eAtual.getAnterior().setProximo(eNovo);
            eAtual.setAnterior(eNovo);
            qtd++;
        }
        return true;
    }

    public boolean insereAntihorario(Elemento eNovo, Elemento eAtual) {

        if (eNovo == null) {
            return false;
        }
        if (isEmpty()) {
            insereInicio(eNovo);
        } else {
            eNovo.setProximo(eAtual.getProximo());
            eNovo.setAnterior(eAtual);
            eAtual.getProximo().setAnterior(eNovo);
            eAtual.setProximo(eNovo);
            if (eAtual == inicio) {  //* se o atual for o inicio, deslocando-se no anti-horario -> o novo passa a ser o inicio.
                inicio = eNovo;
            }
            qtd++;
        }
        return true;
    }

    /**
     * Insere um elemento em determinada posicao no sentido horario
     *
     * @param pos Posicao desejada para inserçao
     * @param e Casa a inserir
     * @return String vazia em caso de sucesso ou mensagem de erro
     */
    /*
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
                Elemento eursor = getPosDoInicio(pos - 1);
                e.setProximo(cursor.getProximo());
                e.setAnterior(cursor);
                cursor.getProximo().setAnterior(e);
                cursor.setProximo(e);
                qtd++;
            }
        }
        return "";

    }
     */
    /**
     * Remove um elemento de determinada posicao (sentido fim -> inicio).
     *
     * @param pos
     * @return
     */
    /*
    public Elemento removePosParaInicio(int pos) {
        if (isEmpty() || pos > qtd + 1 || pos <= 0) {
            return null;
        }
        if (pos == 1) {
            return removeUltimo();
        } else if (pos == qtd) {
            return removeInicio();
        }
        Elemento eursor = getPosDoFim(pos);
        cursor.getAnterior().setProximo(cursor.getProximo());
        cursor.getProximo().setAnterior(cursor.getAnterior());
        cursor.setProximo(null);
        cursor.setAnterior(null);
        qtd--;
        return cursor;
    }
     */
    /**
     * Imprime os elementos da lista no sentido horario (utiliza o metodo
     * getProximo())
     */
    public void imprimeHorario() {
        if (isEmpty()) {
            System.out.println("A lista esta' vazia");
        } else {
            Elemento e = getInicio();
            int posAtu = 1;
            while (posAtu <= getQtd()) {
                _imprimeElemento(e);
                e = e.getProximo();
                posAtu++;
            }
        }
    }

    /**
     * Imprime os elementos da lista no sentido antihorario (utiliza o metodo
     * getAnterior())
     */
    public void imprimeAntiHorario() {
        if (isEmpty()) {
            System.out.println("A lista esta' vazia");
        } else {
            Elemento e = getInicio();
            int posAtu = 1;
            while (posAtu <= getQtd()) {
                _imprimeElemento(e);
                e = e.getAnterior();
                posAtu++;
            }
        }
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
        if (isEmpty()) {
            return null;
        }
        return inicio.getAnterior();
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

    public void destroi() {
        inicio = null;
        qtd = 0;
    }

}  // class >
