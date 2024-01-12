package com.estudo.estrutura_de_dados.tabela_hash;


import java.util.Scanner;

public class TabHash_Run {

    public static void main() {

        System.out.print("Tamanho da tabela -> ");
        Scanner scn = new Scanner(System.in);
        int tam = scn.nextInt();

        System.out.print("Possibilita repeticao (0 = nao / qqr. outro = sim) -> ");
        boolean repete = false;
        int rep = scn.nextByte();
        if (rep != 0) {
            repete = true;
        }

        TabelaHash th = new TabelaHash(tam, repete);

        while (true) {

            System.out.println("================================================");
            System.out.println("           ESTUDO DA TABELA HASH");
            System.out.println("             (sem encadeamento)");
            System.out.println("================================================");
            System.out.println("0 - encerrar.");
            System.out.println("1 - inserir elemento.");
            System.out.println("2 - localiza elemento (nao extrai da tabela).");
            System.out.println("3 - extrai elemento da tabela.");
            System.out.println("4 - imprime a tabela.");
            System.out.println("================================================");

            System.out.println("");
            System.out.print("Opcao -> ");

            int opc = scn.nextByte();

            if (opc == 0) {
                break;
            } else if (opc == 1) {
                Elemento e = _criaElem();
                if (e == null) {
                    System.out.println("Elemento invalido.");
                    continue;
                }
                int ret = th.insere(e);
                switch (ret) {
                    case 1:
                        System.out.println("Inclusao bem sucedida.");
                        break;
                    case 2:
                        System.out.println("ERRO: identificador ja' inserido na tabela.");
                        break;
                    case 3:
                        System.out.println("ERRO: a tabela esta' cheia.");
                        break;
                    default:
                        break;
                }
            } else if (opc == 2) {
                System.out.print("Informe o numero identificador do elemento -> ");
                Scanner scn2 = new Scanner(System.in);
                int nr = scn2.nextInt();
                int i = th.localiza(nr);
                if (i == -1) {
                    System.out.println("Elemento nao localizado.");
                } else {
                    System.out.println("Localizado na posicao " + i);
                }
            } else if (opc == 3) {
                System.out.print("Informe o numero identificador do elemento -> ");
                Scanner scn3 = new Scanner(System.in);
                int nr = scn3.nextInt();
                Elemento e = th.remove(nr);
                if (e == null){
                    System.out.println("ERRO: elemento nao localizado.");
                }
                else{
                    System.out.println("Removido o elemento:");
                    System.out.println(th.imprimeElem(e, true));
                }

            } else if (opc == 4) {
                System.out.println(th.imprimeTab(true));
            }

        }

    }

    private static Elemento _criaElem() {

        try {
            System.out.print("Informe o numero identificador -> ");
            Scanner scnN = new Scanner(System.in);
            int nr = scnN.nextInt();

            System.out.print("Informe o nome -> ");
            Scanner scnS = new Scanner(System.in);
            String nm = scnS.nextLine();

            System.out.print("Informe os dados -> ");
            String dd = scnS.nextLine();

            Elemento e = new Elemento(nr, nm);
            e.setInfos(dd);

            return e;
        } catch (Exception ex) {
            return null;
        }

    }

}
