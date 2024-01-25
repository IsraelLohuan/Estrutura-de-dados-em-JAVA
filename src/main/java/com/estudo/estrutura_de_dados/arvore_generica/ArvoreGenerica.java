package com.estudo.estrutura_de_dados.arvore_generica;


import java.util.ArrayList;

public class ArvoreGenerica {

    public ArvoreGenerica(Elemento raiz) {
        raiz.setNivel(0);
        this.arvore = new ArrayList<>();
        arvore.add(raiz);
        qtd = 1;
    }

    ArrayList<Elemento> arvore;
    int qtd;
    
    public int getQtd(){
        return this.qtd;
    }

    /**
     * Insere um novo elemento na arvore.
     *
     * @param codigoPai Codigo do pai do novo elemento. Este novo elemento entra
     * na lista apos o pai com nivel do pai + 1.
     * @param elem Novo elemento a ser inserido.
     * @return true se localizou o pai e inseriu o elemento a seguir; false se
     * nao localizou o pai.
     */
    public boolean inserir(String codigoPai, Elemento elem) {

        Elemento cursorPai;
        for (int i = 0; i < arvore.size(); i++) {

            cursorPai = arvore.get(i);

            if (cursorPai.getCodigo().equals(codigoPai)) {  // localizou o pai
                elem.setNivel(cursorPai.getNivel() + 1);  // nivel do novo elemento.
                if (i == arvore.size() - 1) {  // se o elemento pai e' o ultimo da arvore -> acrescentar no final
                    arvore.add(elem);
                } else {  // caso contrario -> inserir no indice seguinte
                    arvore.add(i + 1, elem);
                }
                qtd++;
                return true;

            }

        }
        return false;
    }

    /**
     * (uso interno) Localiza um elemento na arvore.
     *
     * @param codigo Codigo do elemento procurado.
     * @return O indice na lista em que esta' o elemento ou -1 caso o elemento
     * nao seja encontrado.
     */
    private int _localizar(String codigo) {

        for (int i = 0; i < arvore.size(); i++) {
            if (arvore.get(i).getCodigo().equals(codigo)) {
                return i;
            }
        }
        return -1;

    }

    /**
     * Localiza um elemento na arvore.
     *
     * @param codigo Codigo do elemento procurado.
     * @return a instancia do elemento procurado ou null caso nao seja
     * localizado ou a arvore esteja vazia.
     */
    public Elemento localizar(String codigo) {

        if (arvore.isEmpty()) {
            System.out.println("*** Arvore vazia ***");
        }

        int idx = _localizar(codigo);
        if (idx >= 0) {
            return arvore.get(idx);
        } else {
            System.out.println("### NAO LOCALIZADO ###");
        }

        return null;

    }

    /**
     * Imprime a arvore a partir do no' raiz ou o ramo a partir do no'
     * especificado.
     *
     * @param codigo Codigo do elemento de partida da impressao. Se for null
     * imprime a partir do inicio da lista (no' raiz).
     */
    public void imprimir(String codigo) {

        if (arvore.isEmpty()) {
            System.out.println("*** Arvore vazia ***");
            return;
        }

        String esp = "          ";  // espacamento em relacao a margem esquerda para cada nivel (espacamento = nivel * esp)
        //int niv = 0;  // nivel de partida (raiz = 0, mas pode-se partir de niveis inferiores)
        int idx = 0;  // indice da lista
        
        

        if (codigo != null) {
            idx = _localizar(codigo);
            if (idx == -1) {
                System.out.println("### CODIGO NA LOCALIZADO ###");
                return;
            }
        }
        
        System.out.println("\n\n--- IMPRESSAO DA ARVORE ---");

        int nivelInicial = arvore.get(idx).getNivel();
        System.out.println("--- iniciando por -> " + arvore.get(idx).getCodigo() + " ---");
        System.out.println("\n");

        for (int i = idx; i < arvore.size(); i++) {
            Elemento e = arvore.get(i);
            if (e.getNivel() < nivelInicial) {
                System.out.println("\n--- FIM DA ARVORE ---");
                return;
            }
            for (int j = 0; j <= e.getNivel() - nivelInicial; j++) {
                System.out.print(esp);
            }
            imprimirElem(e, false);
        }

    }

    /**
     * Imprime um elemento.
     *
     * @param e Elemento a ser impresso.
     * @param descr Se true imprime a descricao do elemento, caso contrario
     * omite a descricao.
     */
    public void imprimirElem(Elemento e, boolean descr) {
        String descricao = "";
        if (descr) {
            descricao = e.getDescricao();
        }

        System.out.println(e.getCodigo() + " | " + e.getNome() + " | " + descricao);
    }

}
