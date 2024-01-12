package com.estudo.estrutura_de_dados.arvore_binaria;
import java.util.Stack;


public class ArvoreBinaria {

    private No_A raiz;

    private int qtd;

    public No_A getRaiz() {
        return raiz;
    }

    public int getQtd() {
        return qtd;
    }

    /**
     * Insere um no' na arvore
     *
     * @param no
     * @return <i>true</i> = insercao bem sucedida; <i>false</i> = insercao
     * negada (unica possibilidade e' de identificador ja' incluido).
     */
    public boolean inserir(No_A no) {

        if (raiz == null) {
            raiz = no;
            qtd = 1;
            return true;
        }

        No_A cursor = raiz;

        while (true) {

            // CASO DE IDENTIFICADOR JA' INCLUIDO -> IMPEDE A INSERCAO.
            if (no.getId() == cursor.getId()) {
                return false;
            }

            // CASO ID MENOR QUE NO' CORRENTE -> CAMINHO A ESQUERDA.
            if (no.getId() < cursor.getId()) {
                if (cursor.getEsquerda() == null) {
                    cursor.setEsquerda(no);
                    qtd++;
                    return true;
                }
                cursor = cursor.getEsquerda();
                continue;
            }

            // CASO ID MAIOR QUE NO' CORRENTE -> CAMINHO A DIREITA.
            if (no.getId() > cursor.getId()) {
                if (cursor.getDireita() == null) {
                    cursor.setDireita(no);
                    qtd++;
                    return true;
                }
                cursor = cursor.getDireita();
            }

        }

    }

    /**
     * Extrai um no' da arvore.
     *
     * @param id
     * @return O no' especificado pelo ID ou <i>null</i> se nao constar na
     * arvore.
     */
    public No_A extrair(int id) {

        //* CASO ARVORE VAZIA
        if (qtd == 0) {
            return null;
        }

        //* CASO O UNICO NO' E' O RAIZ
        if (qtd == 1 && id == raiz.getId()) {
            No_A no = raiz;
            raiz = null;
            qtd = 0;
            return no;
        }

        No_A[] noa = localizarNo(id);

        if (noa[1] == null) {
            return null;
        }

        No_A noPai = noa[0];  // no' pai do no' a ser extraido
        No_A noId = noa[1];   // no' a ser extraido

        //=== FILHOS DO NO' A SER EXTRAIDO =====================================
        No_A filhoIdEsq = noId.getEsquerda();
        No_A filhoIdDir = noId.getDireita();
        //----------------------------------------------------------------------

        //======================================================================
        // CASO 1 - O NO' NAO TEM FILHOS (E' UM NO' FOLHA).
        //======================================================================
        if (filhoIdEsq == null && filhoIdDir == null) {
            if (noId.getId() < noPai.getId()) {  // caso no' a esquerda do pai
                noPai.setEsquerda(null);
            } else {
                noPai.setDireita(null);
            }
        } //
        //======================================================================
        // CASO 2 - O NO' TEM APENAS 1 FILHO.
        //======================================================================
        else if (filhoIdEsq == null && filhoIdDir != null
                || filhoIdEsq != null && filhoIdDir == null) {

            // CASO DO NO' REMOVIDO SER O RAIZ (NAO TEM PAI)
            if (noId == raiz) {
                if (filhoIdEsq != null) {
                    raiz = filhoIdEsq;
                } else {
                    raiz = filhoIdDir;
                }
            } //
            // NO' REMOVIDO NAO E' O NO' RAIZ
            else {
                if (noId.getId() > noPai.getId()) {  // o no' extraido e' filho a direita
                    if (filhoIdEsq != null) {
                        noPai.setDireita(filhoIdEsq);
                    } else {
                        noPai.setDireita(filhoIdDir);
                    }
                } else {  // o no' extraido e' filho a esquerda
                    if (filhoIdEsq != null) {
                        noPai.setEsquerda(filhoIdEsq);
                    } else {
                        noPai.setEsquerda(filhoIdDir);
                    }
                }
            }

        } //
        //======================================================================
        // CASO 2 - O NO' TEM OS 2 FILHOS.
        //======================================================================
        else if (filhoIdEsq != null && filhoIdDir != null) {

            // CASO SEJA O NO' RAIZ
            if (noId == raiz) {
                No_A[] noaE = maiorValorSubEsq(raiz);
                No_A[] noaD = menorValorSubDir(raiz);
//                No_A noaMaior = maiorValor();
//                No_A noaMenor = menorValor();

                No_A[] noaSubst;  // no' substituto; vou eleger o no' do lado em minimo ou maximo estao mais distantes da raiz
                if (Math.abs(raiz.getId() - noaE[1].getId()) > Math.abs(raiz.getId() - noaD[1].getId())) {
                    noaSubst = noaE;
                } else {
                    noaSubst = noaD;
                }

                extrair(noaSubst[1].getId());

                noaSubst[1].setEsquerda(raiz.getEsquerda());
                noaSubst[1].setDireita(raiz.getDireita());
                raiz.setEsquerda(null);
                raiz.setDireita(null);
                No_A ret = raiz;
                raiz = noaSubst[1];
                qtd--;
                return ret;

            }

            /*
             * Pelo material teoricco, estabelecemos a seguinte correspondencia:
             * noId = E 
             * noPai = pE
             * maiorEsq[1], menorDir[1] = S
             * maiorEsq[0], menorDir[0] = pS
             *
             */
            if (noId.getId() > noPai.getId()) {  // o no' extraido e' filho a direita

                // SEGUINDO A NUMERACAO DO MATERIAL TEORICO
                //=========================================
                //
                /*2*/
                No_A[] maiorEsq = maiorValorSubEsq(noId);

                /*3*/
                noPai.setDireita(maiorEsq[1]);

                /*4*/
                maiorEsq[1].setDireita(noId.getDireita());

                /*5*/
                if (maiorEsq[0] != noId) {
                    maiorEsq[1].setEsquerda(noId.getEsquerda());
                    maiorEsq[0].setDireita(null);
                }

            } //
            //
            else {  // o no' extraido e' filho a esquerda

                // SEGUINDO A NUMERACAO DO MATERIAL TEORICO
                //=========================================
                //
                /*2*/
                No_A[] menorDir = menorValorSubDir(noId);

                /*3*/
                noPai.setEsquerda(menorDir[1]);

                /*4*/
                menorDir[1].setEsquerda(noId.getEsquerda());

                /*5*/
                if (menorDir[0] != noId) {
                    menorDir[1].setDireita(noId.getDireita());
                    menorDir[0].setEsquerda(null);
                }

            }

        }

        qtd--;
        return noId;

    }

    /**
     * Localiza um no' dado o seu ID.
     *
     * @param id
     * @return Um vetor de 2 posicoes com o no' pai (indice 0) e o no'
     * correspondente ao ID fornecido ou <i>null</i> se nao for localizado na
     * arvore (indice 1).
     * <br>Se na posicao pai (indice 0) constar <i>null</i> significa que o no'
     * e' raiz. Se na posicao do no' (indice 1) constar <i>null</i>, significa
     * que o no' procurado nao consta na arvore.
     */
    public No_A[] localizarNo(int id) {

        No_A[] noa = new No_A[2];  // vetor de retorno

        noa[1] = raiz;

        while (true) {

            if (noa[1] == null || noa[1].getId() == id) {
                return noa;
            }

            noa[0] = noa[1];  // no' analisado passa a condicao de pai

            if (id < noa[0].getId()) {
                noa[1] = noa[0].getEsquerda();
            } else {
                noa[1] = noa[0].getDireita();
            }

        }

    }

    /**
     * Obtem o maior ID da subarvore esquerda.
     *
     * @param no
     * @return
     */
    private No_A[] maiorValorSubEsq(No_A no) {

        No_A[] noa = new No_A[2];

        noa[0] = no;
        noa[1] = no.getEsquerda();  // primeiro no' da subarvore esquerda

        // o no' maior esta' no ramo mais a direita da subarvore
        while (noa[1].getDireita() != null) {
            noa[0] = noa[1];
            noa[1] = noa[1].getDireita();
        }

        return noa;

    }

    /**
     * Obtem o menor ID da subarvore direita.
     *
     * @param no
     * @return
     */
    private No_A[] menorValorSubDir(No_A no) {

        No_A[] noa = new No_A[2];

        noa[0] = no;
        noa[1] = no.getDireita();  // primeiro no' da subarvore direita

        // o no' menor esta' no ramo mais a esquerda da subarvore
        while (noa[1].getEsquerda() != null) {
            noa[0] = noa[1];
            noa[1] = noa[1].getEsquerda();
        }

        return noa;

    }

    //==========================================================================
    //     M E T O D O S   D E   I M P R E S S A O   I T E R A T I V O S
    //==========================================================================
    public void imprimirEmOrdem_ITE() {

        if (qtd == 0) {
            System.out.println("*** Arvore vazia ***");
        } else {
            Stack<No_A> pilha = new Stack<>();
            No_A cursor = this.raiz;
            cursor.lado = 'X';
            pilha.push(cursor);
            boolean verLadoDir = true;

            boolean doWhile1 = true;
            while1:
            while (doWhile1) {

                //* caminhar tudo `a esquerda e imprimir o ultimo
                //* (se nao houver filho esquerdo, imprime o proprio)
                if (cursor.getEsquerda() != null) {
                    while (cursor.getEsquerda() != null) {
                        cursor = cursor.getEsquerda();
                        cursor.lado = 'E';
                        pilha.push(cursor);
                    }
                }
                System.out.println(cursor.getId() + " - ");

//                while2:
                while (true) {

                    //* verif se tem ramo a direita
                    if (verLadoDir && cursor.getDireita() != null) {
                        cursor = cursor.getDireita();
                        cursor.lado = 'D';
                        pilha.push(cursor);
                        verLadoDir = true;
                        //* volta ao inicio do laco para processar todo o lado esquerdo desse novo no´
                        //continue;
                        continue while1;
                    }

                    //* voltar - se volta de filho esquerdo . imprime o pai	
                    //* retirar a si mesmo da pilha
                    char filhoLado = pilha.pop().lado;
                    if (pilha.empty()) {
                        doWhile1 = false;
                        break while1;
                    }

                    //* pai
                    cursor = pilha.peek();
                    if (filhoLado == 'E') { //* voltou do filho esquerdo . imprime o pai
                        System.out.println(cursor.getId() + " - ");
                        verLadoDir = true;
                    } else {
                        verLadoDir = false;
                    }
                }

            }

            System.out.println("FIM");

        }

    }

    public void imprimirPreOrdem_ITE() {

        if (qtd == 0) {
            System.out.println("*** Arvore vazia ***");
        } else {

            Stack<No_A> pilha = new Stack<>();
            No_A cursor = this.raiz;
            cursor.lado = 'X';
            pilha.push(cursor);
            System.out.println(cursor.getId() + " - ");

            while (true) {

                //* caminhar tudo `a esquerda imprimindo
                while (cursor.getEsquerda() != null) {
                    cursor = cursor.getEsquerda();
                    cursor.lado = 'E';
                    pilha.push(cursor);
                    System.out.println(cursor.getId() + " - ");
                }

                //* verif se ultimo tem ramo a direita
                if (cursor.getDireita() != null) {
                    cursor = cursor.getDireita();
                    cursor.lado = 'D';
                    pilha.push(cursor);
                    System.out.println(cursor.getId() + " - ");
                    //* volta ao inicio do laco para imprimir todo o lado esquerdo desse novo no´
                    continue;
                }

                //* voltar acima e, se voltou de filho esquerdo, verificar se tem filho direito
                while (!pilha.empty()) {
                    //* retirar a si mesmo da pilha
                    char filhoLado = pilha.pop().lado;
                    if (pilha.empty()) {
                        break;
                    }

                    //* pai
                    cursor = pilha.peek();

                    //* se volta do filho direito, ja´ cumpriu o codigo seguinte
                    if (filhoLado == 'D') {
                        continue;
                    }

                    //* ver se o pai tem filho direito
                    if (cursor.getDireita() != null) {
                        cursor = cursor.getDireita();
                        cursor.lado = 'D';
                        pilha.push(cursor);
                        System.out.println(cursor.getId() + " - ");
                        //* voltar a imprimir todo o ramo esquerdo desta nova raiz
                        break;
                    } else {
                        continue;
                    }
                }

                if (pilha.empty()) {
                    break;
                }
            }
            System.out.println("FIM");

        }

    }

    public void imprimirPosOrdem_ITE() {

        if (qtd == 0) {
            System.out.println("*** Arvore vazia ***");
        } else {
            Stack<No_A> pilha = new Stack<>();
            No_A cursor = this.raiz;
            cursor.lado = 'X';
            pilha.push(cursor);
            boolean verLadoDir = true;

            while1:
            while (true) {

                //* caminhar tudo `a esquerda
                if (cursor.getEsquerda() != null) {
                    while (cursor.getEsquerda() != null) {
                        cursor = cursor.getEsquerda();
                        cursor.lado = 'E';
                        pilha.push(cursor);
                    }
                }

                while (true) {
                    //* verif se tem ramo a direita
                    if (verLadoDir) {
                        if (cursor.getDireita() != null) {
                            cursor = cursor.getDireita();
                            cursor.lado = 'D';
                            pilha.push(cursor);
                            verLadoDir = true;
                            //* volta ao inicio do laco para processar todo o lado esquerdo desse novo no´
                            continue while1;
                        } else {
                            System.out.println(cursor.getId() + " - ");
                        }
                    }

                    //* voltar - se volta de filho direito.imprime o pai	
                    //* retirar a si mesmo da pilha
                    char filhoLado = pilha.pop().lado;
                    if (pilha.empty()) {
                        break while1;
                    }

                    //* pai
                    cursor = pilha.peek();
                    if (filhoLado == 'D') { //* voltou do filho direito.imprime o pai
                        System.out.println(cursor.getId() + " - ");
                        verLadoDir = false;
                    } else {
                        verLadoDir = true;
                    }
                }

            }
            System.out.println("FIM");

        }

    }

    //==========================================================================
    //     M E T O D O S   D E   I M P R E S S A O   R E C U R S I V O S
    //==========================================================================
    public void imprimeEmOrdem_REC(No_A no) {

        if (no != null) {
            imprimeEmOrdem_REC(no.getEsquerda());
            System.out.println(no.getId() + " ");
            imprimeEmOrdem_REC(no.getDireita());
        }

    }

    public void imprimePreOrdem_REC(No_A no) {

        if (no != null) {
            System.out.println(no.getId() + " ");
            imprimePreOrdem_REC(no.getEsquerda());
            imprimePreOrdem_REC(no.getDireita());
        }

    }

    public void imprimePosOrdem_REC(No_A no) {

        if (no != null) {
            imprimePosOrdem_REC(no.getEsquerda());
            imprimePosOrdem_REC(no.getDireita());
            System.out.println(no.getId() + " ");
        }

    }

    /**
     * Retorna o no' de maior ID na arvore.
     *
     * @return
     */
    public No_A maiorValor() {
        if (qtd == 0) {
            return null;
        }
        No_A cursor = raiz;
        while (cursor.getDireita() != null) {
            cursor = cursor.getDireita();
        }
        return cursor;
    }

    /**
     * Retorna o no' de menor ID na arvore.
     *
     * @return
     */
    public No_A menorValor() {
        if (qtd == 0) {
            return null;
        }
        No_A cursor = raiz;
        while (cursor.getEsquerda() != null) {
            cursor = cursor.getEsquerda();
        }
        return cursor;
    }

    //==========================================================================
    //  METODO DE INSERCAO RECURSIVO
    //==========================================================================
    /**
     * METODO DE INSERCAO RECURSIVO.
     *
     * metodo baseado no codigo aa pagina
     * https://www.scaler.com/topics/binary-tree-implementation-in-java/
     * <br><br>
     * (o codigo original esta' abaixo)
     *
     * @param raizRamo No raiz de um determinado (sub)ramo da arvore. Para o
     * caso da insercao na arvore, este metodo deve ser invocado com o no' raiz
     * principal.
     * @param noId No candidato a ser inserido na arvore.
     * @return <i>true</i> no caso de sucesso ou <i>false</i> em caso de ja'
     * haver o ID presente na arvore.
     */
    public boolean insert(No_A raizRamo, No_A noId) {
        if (raizRamo == null) {
            raiz = noId;
            qtd++;
            return true;
        }
        if (noId.getId() < raizRamo.getId()) {
            if (raizRamo.getEsquerda() != null) {
                insert(raizRamo.getEsquerda(), noId);
            } else {
                raizRamo.setEsquerda(noId);
                qtd++;
                return true;
            }
        } else if (noId.getId() > raizRamo.getId()) {
            if (raizRamo.getDireita() != null) {
                insert(raizRamo.getDireita(), noId);
            } else {
                raizRamo.setDireita(noId);
                qtd++;
                return true;
            }
        }
        return false;
    }


    /* CODIGO ORIGINAL
    public void insert(node node, int value) {
        if (value < node.value) {
            if (node.left != null) {
                insert(node.left, value);
            } else {
                System.out.println(" Inserted " + value + " to left of " + node.value);
                node.left = new Node(value);
            }
        } else if (value > node.value) {
            if (node.right != null) {
                insert(node.right, value);
            } else {
                System.out.println("  Inserted " + value + " to right of "
                        + node.value);
                node.right = new Node(value);
            }
        }
    }
     */
}
