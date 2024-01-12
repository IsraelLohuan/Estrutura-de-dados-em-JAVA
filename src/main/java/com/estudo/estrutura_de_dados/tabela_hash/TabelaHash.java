package com.estudo.estrutura_de_dados.tabela_hash;

public class TabelaHash {

    public TabelaHash(int tam, boolean rep) {
        this.tabHash = new Elemento[tam];
        this.repete = rep;
        this.qtd = 0;
    }

    private final Elemento[] tabHash;
    private final boolean repete;
    private int qtd;

    /**
     * Funcao de hashing.
     *
     * @param nr
     * @return Resto da divisao do numero (nr) pelo tamanho da tabela.
     */
    private int hash(int nr) {
        return nr % tabHash.length;
    }

    /**
     * Insere um elemento na tabela. Este metodo nao e' <i>thread safe</i>.
     *
     * @param e
     * @return 1 = inclusao bem sucedida.<br>2 = elemento com identificador ja'
     * exixtente;<br>3 = tabela cheia.
     */
    public int insere(Elemento e) {

        // VERIFICAR SE A TABELA ESTA' CHEIA.
        if (qtd == tabHash.length) {
            return 3;
        }

        int nr = e.getNumero();  // numero identificador do elemento (salvar para diminuir a quantidade de chamadas ao metodo e.getNumero()).
        int h = hash(nr);

        //=== CASO DE NAO PERMITIR REPETICAO ===================================
        if (!repete) {
            // parte da tabela de h ao final.
            for (int i = h; i < tabHash.length; i++) {
                if (tabHash[i] != null && tabHash[i].getNumero() == nr) {
                    return 2;
                }
            }
            for (int i = 0; i < h; i++) {
                if (tabHash[i] != null && tabHash[i].getNumero() == nr) {
                    return 2;
                }
            }
        }
        //----------------------------------------------------------------------

        //=== LOCALIZAR UMA POSICAO ============================================
        boolean inserido = false;
        for (int i = h; i < tabHash.length; i++) {
            if (tabHash[i] == null) {
                tabHash[i] = e;
                inserido = true;
                break;
            }
        }
        if (!inserido) {
            for (int i = 0; i < h; i++) {
                if (tabHash[i] == null) {
                    tabHash[i] = e;
                    break;
                }
            }
        }
        qtd++;
        return 1;
        //----------------------------------------------------------------------
    }

    /**
     * Localiza um elemento dado seu identificador.
     *
     * @param nr
     * @return A posicao no vetor ou -1 caso nao seja localizado.
     */
    public int localiza(int nr) {

        int h = hash(nr);

        for (int i = h; i < tabHash.length; i++) {
            if (tabHash[i] != null && tabHash[i].getNumero() == nr) {
                return i;
            }
        }
        for (int i = 0; i < h; i++) {
            if (tabHash[i] != null && tabHash[i].getNumero() == nr) {
                return i;
            }
        }

        return -1;

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
            Elemento e = tabHash[i];
            tabHash[i] = null;
            qtd--;
            return e;
        }
    }

    /**
     * Gera uma String com a impressao da tabela.
     *
     * @param info
     * @return
     */
    public String imprimeTab(boolean info) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < tabHash.length; i++) {
            Elemento e = tabHash[i];
            if (e == null) {
                sb.append("\n").append(i).append(" | -- vago --");
            } else {
                sb.append("\n").append(i).append(" | ").append(imprimeElem(e, info));
            }
        }
        return sb.toString();

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
