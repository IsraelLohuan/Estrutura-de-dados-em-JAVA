package com.estudo.estrutura_de_dados.arvore_binaria_avl;


import java.util.Stack;

public class ArvoreBinariaAvl {

    private No_AVL raiz;

    private int qtd;

    public No_AVL getRaiz() {
        return raiz;
    }

    public int getQtd() {
        return qtd;
    }

    public void zerarArvore() {
        this.qtd = 0;
        this.raiz = null;
    }

    /**
     * Insere um no' na arvore
     *
     * @param no
     * @return <i>true</i> = insercao bem sucedida; <i>false</i> = insercao
     * negada (unica possibilidade e' de identificador ja' incluido).
     */
    public boolean inserir(No_AVL no) {

        if (raiz == null) {
            raiz = no;
            qtd = 1;
            return true;
        }

        Stack<No_AVL> pilha = new Stack<>();  //AVL - pilha para armazenamento do caminho

        No_AVL cursor = raiz;
        //no.lado = 'X';  //AVL

        while (true) {

            pilha.add(cursor);  //AVL

            // CASO DE IDENTIFICADOR JA' INCLUIDO -> IMPEDE A INSERCAO.
            if (no.getId() == cursor.getId()) {
                return false;
            }

            // CASO ID MENOR QUE NO' CORRENTE -> CAMINHO A ESQUERDA.
            if (no.getId() < cursor.getId()) {
                cursor.lado = 'E';  //AVL
                if (cursor.getEsq() == null) {
                    cursor.setEsq(no);
                    pilha.add(no);  //AVL
                    qtd++;
                    verifica_IN(pilha);
                    return true;
                }
                cursor = cursor.getEsq();
                continue;
            }

            // CASO ID MAIOR QUE NO' CORRENTE -> CAMINHO A DIREITA.
            if (no.getId() > cursor.getId()) {
                cursor.lado = 'D';  //AVL
                if (cursor.getDir() == null) {
                    cursor.setDir(no);
                    pilha.add(no);  //AVL
                    qtd++;
                    verifica_IN(pilha);
                    return true;
                }
                cursor = cursor.getDir();
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
    public No_AVL extrair(int id) {

        //* CASO ARVORE VAZIA
        if (qtd == 0) {
            return null;
        }

        //* CASO O UNICO NO' E' O RAIZ
        if (qtd == 1 && id == raiz.getId()) {
            No_AVL no = raiz;
            raiz = null;
            qtd = 0;
            return no;
        }

        //======================================================================
        // AVL
        //
        // Obter a pilha de percurso
        Stack<No_AVL> pilha = _getPilhaDePercurso(id);

        int maxIdx = pilha.size() - 1;  // indice do topo

        // se o topo estiver com null -> no' nao localizado
        if (pilha.get(maxIdx) == null) {
            return null;
        }

        No_AVL noPai = pilha.get(maxIdx - 1);
        No_AVL noId = pilha.get(maxIdx);


        /*  --- codigo anterior
        No_AVL[] noa = localizarNo(id);

        if (noa[1] == null) {
            return null;
        }
         
        No_AVL noPai = noa[0];  // no' pai do no' a ser extraido
        No_AVL noId = noa[1];   // no' a ser extraido
         */
        //----------------------------------------------------------------------
        //
        //=== FILHOS DO NO' A SER EXTRAIDO =====================================
        No_AVL filhoIdEsq = noId.getEsq();
        No_AVL filhoIdDir = noId.getDir();
        //----------------------------------------------------------------------

        //======================================================================
        // CASO 1 - O NO' NAO TEM FILHOS (E' UM NO' FOLHA).
        //======================================================================
        if (filhoIdEsq == null && filhoIdDir == null) {
            if (noId.getId() < noPai.getId()) {  // caso no' a esquerda do pai
                noPai.setEsq(null);
            } else {
                noPai.setDir(null);
            }

            verifica_OUT(pilha, 0, null, null);  // AVL

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
                        noPai.setDir(filhoIdEsq);
                    } else {
                        noPai.setDir(filhoIdDir);
                    }
                } else {  // o no' extraido e' filho a esquerda
                    if (filhoIdEsq != null) {
                        noPai.setEsq(filhoIdEsq);
                    } else {
                        noPai.setEsq(filhoIdDir);
                    }
                }
            }

            verifica_OUT(pilha, 1, null, null);  // AVL

        } //
        //======================================================================
        // CASO 3 - O NO' TEM OS 2 FILHOS.
        //======================================================================
        else if (filhoIdEsq != null && filhoIdDir != null) {

            // AVL === no' pai do no' substituto (para o caso do no' excluido ter 
            //         2 filhos); passa a ser a referencia de busca para verificacao
            //         do balanceamento da pilha de percurso
            No_AVL noSubstPai;  // AVL - pai do no' substituto
            //------------------------------------------------------------------

            //=== CASO SEJA O NO' RAIZ =========================================
            if (noId == raiz) {
                No_AVL[] noaE = maiorValorSubEsq(raiz);
                No_AVL[] noaD = menorValorSubDir(raiz);
//                No_AVL noaMaior = maiorValor();
//                No_AVL noaMenor = menorValor();

                No_AVL[] noaSubst;  // no' substituto; vou eleger o no' do lado em minimo ou maximo estao mais distantes da raiz
                if (Math.abs(raiz.getId() - noaE[1].getId()) > Math.abs(raiz.getId() - noaD[1].getId())) {
                    noaSubst = noaE;
                } else {
                    noaSubst = noaD;
                }
                noSubstPai = noaSubst[0];  // AVL

                extrair(noaSubst[1].getId());

                noaSubst[1].setEsq(raiz.getEsq());
                noaSubst[1].setDir(raiz.getDir());
                raiz.setEsq(null);
                raiz.setDir(null);
                No_AVL ret = raiz;
                raiz = noaSubst[1];
                qtd--;

                verifica_OUT(pilha, 2, noSubstPai, noaSubst[1]);  // AVL

                return ret;

            }
            //------------------------------------------------------------------

            /*
             * Pelo material teoricco, estabelecemos a seguinte correspondencia:
             * noId = E 
             * noPai = pE
             * maiorEsq[1], menorDir[1] = S
             * maiorEsq[0], menorDir[0] = pS
             *
             */
            No_AVL noSubst;
            if (noId.getId() > noPai.getId()) {  // o no' extraido e' filho a direita

                // SEGUINDO A NUMERACAO DO MATERIAL TEORICO
                //=========================================
                //
                /*2*/
                No_AVL[] maiorEsq = maiorValorSubEsq(noId);

                noSubstPai = maiorEsq[0];  // AVL
                noSubst = maiorEsq[1];  // AVL

                /*3*/
                noPai.setDir(maiorEsq[1]);

                /*4*/
                maiorEsq[1].setDir(noId.getDir());

                /*5*/
                if (maiorEsq[0] != noId) {
                    maiorEsq[1].setEsq(noId.getEsq());
                    maiorEsq[0].setDir(null);
                }

            } //
            //
            else {  // o no' extraido e' filho a esquerda

                // SEGUINDO A NUMERACAO DO MATERIAL TEORICO
                //=========================================
                //
                /*2*/
                No_AVL[] menorDir = menorValorSubDir(noId);

                noSubstPai = menorDir[0];  // AVL
                noSubst = menorDir[1];

                /*3*/
                noPai.setEsq(menorDir[1]);

                /*4*/
                menorDir[1].setEsq(noId.getEsq());

                /*5*/
                if (menorDir[0] != noId) {
                    menorDir[1].setDir(noId.getDir());
                    menorDir[0].setEsq(null);
                }

            }

            verifica_OUT(pilha, 2, noSubstPai, noSubst);  // AVL

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
    public No_AVL[] localizarNo(int id) {

        No_AVL[] noa = new No_AVL[2];  // vetor de retorno

        noa[1] = raiz;

        while (true) {

            if (noa[1] == null || noa[1].getId() == id) {
                return noa;
            }

            noa[0] = noa[1];  // no' analisado passa a condicao de pai

            if (id < noa[0].getId()) {
                noa[1] = noa[0].getEsq();
            } else {
                noa[1] = noa[0].getDir();
            }

        }

    }

    /**
     * Obtem o maior ID da subarvore esquerda.
     *
     * @param no
     * @return
     */
    private No_AVL[] maiorValorSubEsq(No_AVL no) {

        No_AVL[] noa = new No_AVL[2];

        noa[0] = no;
        noa[1] = no.getEsq();  // primeiro no' da subarvore esquerda

        // o no' maior esta' no ramo mais a direita da subarvore
        while (noa[1].getDir() != null) {
            noa[0] = noa[1];
            noa[1] = noa[1].getDir();
        }

        return noa;

    }

    /**
     * Obtem o menor ID da subarvore direita.
     *
     * @param no
     * @return
     */
    private No_AVL[] menorValorSubDir(No_AVL no) {

        No_AVL[] noa = new No_AVL[2];

        noa[0] = no;
        noa[1] = no.getDir();  // primeiro no' da subarvore direita

        // o no' menor esta' no ramo mais a esquerda da subarvore
        while (noa[1].getEsq() != null) {
            noa[0] = noa[1];
            noa[1] = noa[1].getEsq();
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
            Stack<No_AVL> pilha = new Stack<>();
            No_AVL cursor = this.raiz;
            cursor.lado = 'X';
            pilha.push(cursor);
            boolean verLadoDir = true;

            boolean doWhile1 = true;
            while1:
            while (doWhile1) {

                //* caminhar tudo `a esquerda e imprimir o ultimo
                //* (se nao houver filho esquerdo, imprime o proprio)
                if (cursor.getEsq() != null) {
                    while (cursor.getEsq() != null) {
                        cursor = cursor.getEsq();
                        cursor.lado = 'E';
                        pilha.push(cursor);
                    }
                }
                System.out.println(cursor.getId() + " - ");

//                while2:
                while (true) {

                    //* verif se tem ramo a direita
                    if (verLadoDir && cursor.getDir() != null) {
                        cursor = cursor.getDir();
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

            Stack<No_AVL> pilha = new Stack<>();
            No_AVL cursor = this.raiz;
            cursor.lado = 'X';
            pilha.push(cursor);
            //System.out.println(cursor.getId() + " - ");
            _printNo(cursor);

            while (true) {

                //* caminhar tudo `a esquerda imprimindo
                while (cursor.getEsq() != null) {
                    cursor = cursor.getEsq();
                    cursor.lado = 'E';
                    pilha.push(cursor);
                    //System.out.println(cursor.getId() + " - ");
                    _printNo(cursor);
                }

                //* verif se ultimo tem ramo a direita
                if (cursor.getDir() != null) {
                    cursor = cursor.getDir();
                    cursor.lado = 'D';
                    pilha.push(cursor);
                    //System.out.println(cursor.getId() + " - ");
                    _printNo(cursor);
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
                    if (cursor.getDir() != null) {
                        cursor = cursor.getDir();
                        cursor.lado = 'D';
                        pilha.push(cursor);
                        //System.out.println(cursor.getId() + " - ");
                        _printNo(cursor);
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
            Stack<No_AVL> pilha = new Stack<>();
            No_AVL cursor = this.raiz;
            cursor.lado = 'X';
            pilha.push(cursor);
            boolean verLadoDir = true;

            while1:
            while (true) {

                //* caminhar tudo `a esquerda
                if (cursor.getEsq() != null) {
                    while (cursor.getEsq() != null) {
                        cursor = cursor.getEsq();
                        cursor.lado = 'E';
                        pilha.push(cursor);
                    }
                }

                while (true) {
                    //* verif se tem ramo a direita
                    if (verLadoDir) {
                        if (cursor.getDir() != null) {
                            cursor = cursor.getDir();
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
    public void imprimeEmOrdem_REC(No_AVL no) {

        if (no != null) {
            imprimeEmOrdem_REC(no.getEsq());
            System.out.println(no.getId() + " ");
            imprimeEmOrdem_REC(no.getDir());
        }

    }

    public void imprimePreOrdem_REC(No_AVL no) {

        if (no != null) {
            System.out.println(no.getId() + " ");
            imprimePreOrdem_REC(no.getEsq());
            imprimePreOrdem_REC(no.getDir());
        }

    }

    public void imprimePosOrdem_REC(No_AVL no) {

        if (no != null) {
            imprimePosOrdem_REC(no.getEsq());
            imprimePosOrdem_REC(no.getDir());
            System.out.println(no.getId() + " ");
        }

    }

    /**
     * Retorna o no' de maior ID na arvore.
     *
     * @return
     */
    public No_AVL maiorValor() {
        if (qtd == 0) {
            return null;
        }
        No_AVL cursor = raiz;
        while (cursor.getDir() != null) {
            cursor = cursor.getDir();
        }
        return cursor;
    }

    /**
     * Retorna o no' de menor ID na arvore.
     *
     * @return
     */
    public No_AVL menorValor() {
        if (qtd == 0) {
            return null;
        }
        No_AVL cursor = raiz;
        while (cursor.getEsq() != null) {
            cursor = cursor.getEsq();
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
    public boolean insert(No_AVL raizRamo, No_AVL noId) {
        if (raizRamo == null) {
            raiz = noId;
            qtd++;
            return true;
        }
        if (noId.getId() < raizRamo.getId()) {
            if (raizRamo.getEsq() != null) {
                insert(raizRamo.getEsq(), noId);
            } else {
                raizRamo.setEsq(noId);
                qtd++;
                return true;
            }
        } else if (noId.getId() > raizRamo.getId()) {
            if (raizRamo.getDir() != null) {
                insert(raizRamo.getDir(), noId);
            } else {
                raizRamo.setDir(noId);
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
    //==========================================================================
    //            M E T O D O S   D A   A R V O R E   A V L
    //                      (todos privados)
    //--------------------------------------------------------------------------
    /**
     * Executa a verificacao do balanceamento da arvore apos a inclusao.
     * <br>Atualiza os indicadores de profundidade dos nos da arvore do
     * fundo ao topo.
     *
     * @param pilha Pilha de percurso da raiz ate' o no' incluido.
     *
     */
    private void verifica_IN(Stack<No_AVL> pilha) {

        int tam = pilha.size();

        //================================================================
        //  Correr a pilha do topo ao fundo para atualizar as profundidades
        //  (no fundo esta' o no' raiz)
        //
        int profMax, profE, profD;
        No_AVL cursor, cursorPai;
        for (int i = tam - 1; i >= 0; i--) {
            cursor = pilha.get(i);  // no' atual
            profE = cursor.getpE();  // profundidade da sub-arvore esquerda
            profD = cursor.getpD();  // profundidade da sub-arvore direita
            if (Math.abs(profE - profD) > 1) {  // ja' temos o primeiro desbalanceado -> balancear
                balanceia_IN(pilha, i);
                return;
            }

            profMax = cursor.maxProf();

            //==================================
            //  atualizar indicadores do no' pai
            //
            if (i > 0) {
                cursorPai = pilha.get(i - 1);
                if (cursorPai.lado == 'E') {
                    cursorPai.setpE(cursor.maxProf() + 1);
                } else {
                    cursorPai.setpD(cursor.maxProf() + 1);
                }

            }
            //-----------------------------------

        }
        //------------------------------------------------------------------

    }

    /**
     * Executa o balanceamento apos a inclusao.
     *
     * @param pilha Pilha completa do percurso da inclusao com atualizacao das
     * profundidades ate' o primeiro no' desbalanceado.
     * @param idx Indice do primeiro no' desbalanceado na pilha.
     */
    private void balanceia_IN(Stack<No_AVL> pilha, int idx) {

        if (idx == 0) {  // desbalanceamento no no' raiz

            if (pilha.get(0).fatorBalanco() > 0) {
                if (pilha.get(1).fatorBalanco() >= 0) {
                    rotacaoSimplesEsq(pilha, idx, false);
                } else {
                    rotacaoDuplaEsq(pilha, idx);
                }
            } else {
                if (pilha.get(1).fatorBalanco() < 0) {
                    rotacaoSimplesDir(pilha, idx, false);
                } else {
                    rotacaoDuplaDir(pilha, idx);
                }

            }

            return;
        }

        No_AVL noId = pilha.get(idx);
        No_AVL noIdPai = pilha.get(idx - 1);
        No_AVL noIdFilho = pilha.get(idx + 1);

        //======================================================================
        // desbalanceamento do lado esquerdo
        //
        if (noId.fatorBalanco() < 0) {
            // fator de balanco do no' desbalanceado tem mesmo sinal do fator de balanco do filho -> rotacao simples a direita
            if (noId.fatorBalanco() * noIdFilho.fatorBalanco() >= 0) {
                rotacaoSimplesDir(pilha, idx, false);
            } else {
                rotacaoDuplaDir(pilha, idx);
            }
        } //
        //======================================================================
        // desbalanceamento do lado dreito
        //
        else {
            // fator de balanco do no' desbalanceado tem mesmo sinal do fator de balanco do filho -> rotacao simples a esquerda
            if (noId.fatorBalanco() * noIdFilho.fatorBalanco() >= 0) {
                rotacaoSimplesEsq(pilha, idx, false);
            } else {
                rotacaoDuplaEsq(pilha, idx);
            }
        }

    }

    /**
     * Rotacao Simples a Esquerda.
     *
     * @param pilha
     * @param idx
     * @param somenteRoda <i>true</i> faz somente a rotacao (nao acerta as
     * profundidades); <i>false</i> faz procedimento completo.
     */
    private void rotacaoSimplesEsq(Stack<No_AVL> pilha, int idx, boolean somenteRoda) {

        No_AVL noId = pilha.get(idx);
        No_AVL noIdPai;
        if (idx == 0) {
            noIdPai = null;  // desbalanceamento na raiz
        } else {
            noIdPai = pilha.get(idx - 1);
        }
        No_AVL noIdFilho = pilha.get(idx + 1);

        // filho passa a ser a nova raiz (e filho esquerdo/direito do pai)
        if (noIdPai != null) {
            if (noIdPai.lado == 'E') {
                noIdPai.setEsq(noIdFilho);
            } else {
                noIdPai.setDir(noIdFilho);
            }
        }

        // filho esquerdo do filho ->  passa a ser filho direito da raiz original (se nao existir -> fica null)
        noId.setDir(noIdFilho.getEsq());

        // raiz original passa a ser o filho esquerdo da nova raiz
        noIdFilho.setEsq(noId);

        if (somenteRoda) {
            return;
        }

        //============================================
        // acerto nas profundidades no caso de idx = 0
        if (idx == 0) {
            // lado direito da antiga raiz (esquerdo nao muda)
            if (noId.getDir() != null) {  // somente lado direito
                noId.setpD(noId.getDir().maxProf() + 1);
            } else {
                noId.setpD(0);
            }
            // lado esquerdo da nova raiz (direito nao muda)
            noIdFilho.setpE(noId.maxProf() + 1);
            this.raiz = noIdFilho;
        } else {

            //==========================================
            // acerto das profundidades na raiz original
            //
            // LADO ESQUERDO
            // se nao tiver filho esquerdo -> pE=0
            if (noId.getEsq() == null) {
                noId.setpE(0);
            } //
            // tem filho esquerdo -> pE = (maxProf do filho) + 1
            else {
                noId.setpE(noId.getEsq().maxProf() + 1);
            }

            // LADO DIREITO
            // se nao tiver filho direito -> pD=0
            if (noId.getDir() == null) {
                noId.setpD(0);
            } //
            // tem filho direito -> pD = (maxProf do filho) + 1
            else {
                noId.setpD(noId.getDir().maxProf() + 1);
            }
            //------------------------------------------
            //
            //==========================================
            // acerto no lado esquerdo da nova raiz 
            // (filho esquerdo da raiz original)
            noIdFilho.setpE(noId.maxProf() + 1);
            //------------------------------------------
        }

        System.out.println("RODOU SIMPLES ESQUERDA");

    }

    /**
     * Rotacao Simples a Direita.
     *
     * @param pilha
     * @param idx
     * @param somenteRoda <i>true</i> faz somente a rotacao (nao acerta as
     * profundidades); <i>false</i> faz procedimento completo.
     */
    private void rotacaoSimplesDir(Stack<No_AVL> pilha, int idx, boolean somenteRoda) {

        No_AVL noId = pilha.get(idx);
        No_AVL noIdPai;
        if (idx == 0) {
            noIdPai = null;  // desbalanceamento na raiz
        } else {
            noIdPai = pilha.get(idx - 1);
        }
        No_AVL noIdFilho = pilha.get(idx + 1);

        // filho passa a ser a nova raiz (e filho esquerdo/direito do pai)
        if (noIdPai != null) {
            if (noIdPai.lado == 'E') {
                noIdPai.setEsq(noIdFilho);
            } else {
                noIdPai.setDir(noIdFilho);
            }
        }

        // filho direito do filho ->  passa a ser filho esquerdo da raiz original (se nao existir -> fica null)
        noId.setEsq(noIdFilho.getDir());

        // raiz original passa a ser o filho direito da nova raiz
        noIdFilho.setDir(noId);

        if (somenteRoda) {
            return;
        }

        //============================================
        // acerto nas profundidades no caso de idx = 0
        if (idx == 0) {
            // lado esquerdo da antiga raiz (direito nao muda)
            if (noId.getEsq() != null) {  // somente lado esquerdo
                noId.setpE(noId.getEsq().maxProf() + 1);
            } else {
                noId.setpE(0);
            }
            // lado direito da nova raiz (esquerdo nao muda)
            noIdFilho.setpD(noId.maxProf() + 1);
            this.raiz = noIdFilho;
        } else {
            //==========================================
            // acerto das profundidades na raiz original
            //
            // LADO ESQUERDO
            // se nao tiver filho esquerdo -> pE=0
            if (noId.getEsq() == null) {
                noId.setpE(0);
            } //
            // tem filho esquerdo -> pE = (maxProf do filho) + 1
            else {
                noId.setpE(noId.getEsq().maxProf() + 1);
            }

            // LADO DIREITO
            // se nao tiver filho direito -> pD=0
            if (noId.getDir() == null) {
                noId.setpD(0);
            } //
            // tem filho direito -> pD = (maxProf do filho) + 1
            else {
                noId.setpD(noId.getDir().maxProf() + 1);
            }
            //------------------------------------------
            //
            //==========================================
            // acerto no lado direito da nova raiz 
            // (filho direito da raiz original)
            noIdFilho.setpD(noId.maxProf() + 1);
            //------------------------------------------
        }

        System.out.println("RODOU SIMPLES DIREITA");

    }

    /**
     * Rotacao Dupla a Esquerda.
     *
     * @param pilha
     * @param idx
     */
    private void rotacaoDuplaEsq(Stack<No_AVL> pilha, int idx) {

        rotacaoSimplesDir(pilha, idx + 1, false);
        Stack<No_AVL> pilhaAux = _getPilhaDePercurso(pilha.peek().getId());
        rotacaoSimplesEsq(pilhaAux, idx, false);


    }

    /**
     * Rotacao Dupla a Direita.
     *
     * @param pilha
     * @param idx
     */
    private void rotacaoDuplaDir(Stack<No_AVL> pilha, int idx) {

        rotacaoSimplesEsq(pilha, idx + 1, false);
        Stack<No_AVL> pilhaAux = _getPilhaDePercurso(pilha.peek().getId());
        rotacaoSimplesDir(pilhaAux, idx, false);

    }

    //==========================================================================
    //               M E T O D O S   A U X I L I A R E S
    //
    /**
     * Inprime o no' com os dados das profundidades, fator de balanco e do
     * atributo "lado" caso o no' participe de uma pilha de percurso.
     *
     * @param no
     */
    private void _printNo(No_AVL no) {
        System.out.println(no.getId() + " - " + no.getpE() + " | " + no.getpD() + " | " + no.lado + " | FB = " + (no.getpD() - no.getpE()));
    }

    /**
     * Imprime os dados dos nos de uma pilha de percurso. Utiliza o metodo
     * "_printNo(No_AVL no)".
     *
     * @param pilha
     * @see #_printNo(No_AVL)
     */
    private void _printPilha(Stack<No_AVL> pilha) {
        System.out.println("--- IMPRESSAO DA PILHA DE BUSCA ---");
        if (pilha == null || pilha.isEmpty()) {
            System.out.println("*** PILHA VAZIA ***");
        } else {
            for (int i = 0; i < pilha.size(); i++) {
                _printNo(pilha.get(i));
            }
        }
    }

    /**
     * Faz o swap entre as posicoes idx1 e idx2 de uma pilha de percurso.
     *
     * @param pilha
     * @param idx1
     * @param idx2
     */
    private void _swap(Stack<No_AVL> pilha, int idx1, int idx2) {
        No_AVL noAux1 = pilha.get(idx1);
        No_AVL noAux2 = pilha.get(idx2);
        pilha.set(idx1, noAux2);
        pilha.set(idx2, noAux1);
    }

    /**
     * Obtem a pilha de percurso ate' um determinado valor. E' aplicavel apenas
     * para restabelecer a pilha apos a insercao de um novo valor (que e'
     * folha). Nao verifica/corrige os indicadores de profundidade.
     *
     * @param valor
     * @return A pilha dos nos percorridos com a indicacao, no pai, do lado que
     * segue o filho.
     */
    private Stack<No_AVL> _getPilhaDePercurso(int valor) {

        if (qtd == 0) {
            return null;
        }

        Stack<No_AVL> pilha = new Stack<>();

        No_AVL cursor = raiz;
        pilha.add(raiz);
        while (true) {

            if (valor == cursor.getId()) {
                cursor.lado = 'X';
                return pilha;
            }

            if (valor < cursor.getId()) {
                cursor.lado = 'E';
                cursor = cursor.getEsq();
            } else if (valor > cursor.getId()) {
                cursor.lado = 'D';
                cursor = cursor.getDir();
            }

            if (cursor == null) {
                pilha.add(null);
                return pilha;
            }
            pilha.add(cursor);
        }
    }

    /**
     * Faz a verificacao de desbalanceamento em caso de extracao de um no'.
     *
     * @param pilha
     * @param tipo
     * <br>0 = caso de extracao de no' folha (0 filhos)
     * <br>1 = caso de extracao de no' com um unico filho
     * <br>2 = caso de extracao de no' com dois filhos
     * @param noSubstPai Pai do no' substituto. Utilizado somente no tipo 2, nos
     * tipos 1 e 2 utilizar <i>null</i>.
     * @param noSubst No' substituto. Utilizado somente no tipo 2, nos tipos 1 e
     * 2 utilizar <i>null</i>.
     */
    private void verifica_OUT(Stack<No_AVL> pilha, int tipo, No_AVL noSubstPai, No_AVL noSubst) {

        switch (tipo) {
            case 0:
                preparaPilha_OUT_folha(pilha, true);
                balanceia_OUT_folha(pilha);
                break;
            case 1:
                pilha = preparaPilha_OUT_1filho(pilha, true);
                balanceia_OUT_1filho(pilha);
                break;
            case 2:
                pilha = preparaPilha_OUT_2filhos(null, noSubstPai, noSubst);
                balanceia_OUT_2filhos(pilha);

        }

    }

    /**
     * Primeira etapa apos extracao de um no' folha.
     *
     * @param pilha
     * @param primeiraPassagem
     */
    private void preparaPilha_OUT_folha(Stack<No_AVL> pilha, boolean primeiraPassagem) {

        No_AVL noPai;
        No_AVL noId;

        if (primeiraPassagem) {

            pilha.pop();  // extrai da pilha o que foi extraido da arvore

            //======================================================================
            // acerta a profundidade do pai do extraido
            //
            noPai = pilha.peek();

            if (noPai.lado == 'E') {
                noPai.setpE(0);
                // se o topo ficar desbalanceado -> incluir na pilha o filho direito
                //  para poder utilizar os metodos de rotacao
                if (Math.abs(noPai.fatorBalanco()) > 1) {
                    pilha.add(noPai.getDir());
                    noPai.lado = 'D';
                }
            } else {
                // idem anterior em espelho
                noPai.setpD(0);
                if (Math.abs(noPai.fatorBalanco()) > 1) {
                    pilha.add(noPai.getEsq());
                    noPai.lado = 'E';
                }
            }
            //----------------------------------------------------------------------
        }

        //======================================================================
        // A partir do pai do extraido, ja' com as profundidades acertadas, 
        //  ajustar as profundidades dos no's superiores 
        //  (faz o ajuste sem verificar se ja' esta' ok)
        //
        for (int i = pilha.size() - 1; i > 0; i--) {
            noId = pilha.get(i);
            noPai = pilha.get(i - 1);
            if (noPai.lado == 'E') {
                noPai.setpE(noId.maxProf() + 1);
            } else {
                noPai.setpD(noId.maxProf() + 1);
            }
        }
        //----------------------------------------------------------------------

    }

    private Stack<No_AVL> preparaPilha_OUT_1filho(Stack<No_AVL> pilha, boolean primeiraPassagem) {

        No_AVL noId;
        No_AVL noFilho;

        if (primeiraPassagem) {
            // obter o filho do no' extraido e reconfigurar a pilha de percurso
            noId = pilha.peek();
            noFilho = noId.getEsq();
            if (noFilho == null) {
                noFilho = noId.getDir();
            }

            pilha = _getPilhaDePercurso(noFilho.getId());
        }

        //======================================================================
        // A partir do filho do extraido, com as profundidades inalteradas, 
        //  ajustar as profundidades dos no's superiores 
        //  (faz o ajuste sem verificar se ja' esta' ok)
        // -- trecho copiado da "preparaPilha_OUT_folha" --
        //
        No_AVL noPai;
        for (int i = pilha.size() - 1; i > 0; i--) {
            noId = pilha.get(i);
            noPai = pilha.get(i - 1);
            if (noPai.lado == 'E') {
                noPai.setpE(noId.maxProf() + 1);
            } else {
                noPai.setpD(noId.maxProf() + 1);
            }
        }
        //----------------------------------------------------------------------

        return pilha;

    }

    /**
     * Prepara a pilha de percurso para o caso da exclusao de no' com 2 filhos.
     *
     * @param pilha
     * @param noSubstPai No' pai do no' substituto.
     * @param noSubst No' substituto. So' e' necessario na primeira passagem,
     * nas demais enviar <i>null</i>.
     * @return
     */
    private Stack<No_AVL> preparaPilha_OUT_2filhos(Stack<No_AVL> pilha, No_AVL noSubstPai, No_AVL noSubst) {

        if (pilha == null) {
            pilha = _getPilhaDePercurso(noSubstPai.getId());
            // O fator de balanco pai do substituto se alterou com a saida deste,
            //   portanto, no lado do filho nulo a profundidade deve estar zero.
            //   Caso nao esteja -> zerar.
            No_AVL noTopo = pilha.peek();
            
            
            if (noTopo==null){
                pilha.pop();
                noTopo = pilha.peek();
            }
            
            
            if (noTopo.getEsq() == null) {
                noTopo.setpE(0);
            }
            if (noTopo.getDir() == null) {
                noTopo.setpD(0);
            }
        }

        //======================================================================
        // A partir do pai do substituto, com as profundidades ajustadas, 
        //  ajustar as profundidades dos no's superiores 
        //  (faz o ajuste sem verificar se ja' esta' ok)
        // -- trecho copiado (com pequenos ajustes) da "preparaPilha_OUT_1filho" --
        //
        No_AVL noPai;
        No_AVL noFilho;
        for (int i = pilha.size() - 1; i > 0; i--) {
            noFilho = pilha.get(i);
            noPai = pilha.get(i - 1);
            if (noPai.lado == 'E') {
                noPai.setpE(noFilho.maxProf() + 1);
                if (noPai == noSubst) {
                    if (noPai.getDir() == null) {
                        noPai.setpD(0);
                    } else {
                        noPai.setpD(noPai.getDir().maxProf() + 1);
                    }
                }
            } else {
                noPai.setpD(noFilho.maxProf() + 1);
                if (noPai == noSubst) {
                    if (noPai.getEsq() == null) {
                        noPai.setpE(0);
                    } else {
                        noPai.setpE(noPai.getEsq().maxProf() + 1);
                    }
                }
            }
        }
        //----------------------------------------------------------------------

        return pilha;
    }

    /**
     * Verifica e faz o balanceamento apos a extracao de um no' folha
     *
     * @param pilha
     */
    private void balanceia_OUT_folha(Stack<No_AVL> pilha) {

        No_AVL noTopo = pilha.peek();

        while (true) {
            int idx = verificaBalancos(pilha);
            if (idx == -1) {
                return;
            }
            balanceia_OUT(pilha.get(idx));
            pilha = _getPilhaDePercurso(noTopo.getId());
            preparaPilha_OUT_folha(pilha, false);

        }

    }

    private void balanceia_OUT_1filho(Stack<No_AVL> pilha) {

        No_AVL noTopo = pilha.peek();

        while (true) {
            int idx = verificaBalancos(pilha);
            if (idx == -1) {
                return;
            }
            balanceia_OUT(pilha.get(idx));
            pilha = _getPilhaDePercurso(noTopo.getId());
            preparaPilha_OUT_1filho(pilha, false);

        }

    }

    private void balanceia_OUT_2filhos(Stack<No_AVL> pilha) {

        No_AVL noTopo = pilha.peek();

        while (true) {
            int idx = verificaBalancos(pilha);
            if (idx == -1) {
                return;
            }
            balanceia_OUT(pilha.get(idx));
            pilha = _getPilhaDePercurso(noTopo.getId());
            preparaPilha_OUT_2filhos(pilha, null, null);

        }

    }

    /**
     * Verifica se uma pilha de percurso possui desbalanceamentos.
     *
     * @param pilha
     * @return A posicao do primeiro no' desbalanceado (sentido topo-fundo) ou
     * -1 se estiver totalmente balanceada.
     */
    private int verificaBalancos(Stack<No_AVL> pilha) {
        for (int i = pilha.size() - 1; i >= 0; i--) {
            if (Math.abs(pilha.get(i).fatorBalanco()) > 1) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Executa o balanceamento apos a exclusao.
     *
     * @param noId No' desbalanceado
     
     */
    private void balanceia_OUT(No_AVL noId) {

        Stack<No_AVL> pilha = new Stack<>();

        //======================================================================
        // DESBALANCEAMENTO NO NO' RAIZ 
        //======================================================================
        if (noId == this.raiz) {

            pilha.add(noId);

            //==================================================================
            // desbalanceamento a direita
            //
            if (noId.fatorBalanco() > 0) {

                // acrescentar filho direito na pilha
                noId.lado = 'D';
                pilha.add(noId.getDir());

                // ver se roda simples ou dupla
                if (pilha.get(1).fatorBalanco() >= 0) {
                    rotacaoSimplesEsq(pilha, 0, false);
                } else {
                    rotacaoDuplaEsq(pilha, 0);
                }

            } //
            //------------------------------------------------------------------
            //==================================================================
            // desbalanceamento a esquerda
            //
            else {

                // acrescentar filho esquerdo na pilha
                noId.lado = 'E';
                pilha.add(noId.getEsq());

                // ver se roda simples ou dupla
                if (pilha.get(1).fatorBalanco() < 0) {
                    rotacaoSimplesDir(pilha, 0, false);
                } else {
                    rotacaoDuplaDir(pilha, 0);
                }
            }
            //------------------------------------------------------------------

            return;

        }
        //----------------------------------------------------------------------
        //----------------------------------------------------------------------
        //----------------------------------------------------------------------

        //
        //
        pilha = _getPilhaDePercurso(noId.getId());
        int idx = pilha.size() - 1;  // indice na pilha do no' problematico

        //==================================================================
        // desbalanceamento a direita
        //
        if (noId.fatorBalanco() > 0) {

            // acrescentar filho direito na pilha
            noId.lado = 'D';
            pilha.add(noId.getDir());

            // ver se roda simples ou dupla
            if (pilha.get(1).fatorBalanco() >= 0) {
                rotacaoSimplesEsq(pilha, idx, false);
            } else {
                rotacaoDuplaEsq(pilha, idx);
            }

        } //
        //------------------------------------------------------------------
        //==================================================================
        // desbalanceamento a esquerda
        //
        else {

            // acrescentar filho esquerdo na pilha
            noId.lado = 'E';
            pilha.add(noId.getEsq());

            // ver se roda simples ou dupla
            if (pilha.get(1).fatorBalanco() < 0) {
                rotacaoSimplesDir(pilha, idx, false);
            } else {
                rotacaoDuplaDir(pilha, idx);
            }
        }
        //------------------------------------------------------------------

        
    }

}
