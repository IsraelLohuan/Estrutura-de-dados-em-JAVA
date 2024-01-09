package com.estudo.estrutura_de_dados.lista_duplamente_ligada;


/**
 * As alteracoes no codigo para atender ao exercicio 1 estao com comentarios iniciados por "Ex1".
 */
import java.util.Scanner;

/**
 * classe TadL1LJava_Exercicio1
 *
 * @author Marcio Porto Feitosa - 23/12/2022 - 10:48:35
 */
public class TadL2LJava_IRun {

    static ListaLigada ll;

    public static void main() {

        Scanner scn = new Scanner(System.in);

        ll = new ListaLigada();

        while (true) {

            System.out.println("\n\n================================================");
            System.out.println("             LISTA DUPLAMENTE LIGADA");
            System.out.println("       Quantidade de elementos na lista -> " + ll.getQtd());  // Ex1
            System.out.println("================================================");
            System.out.println(" 0 - encerrar");
            System.out.println(" 1 - inserir elemento no inicio");
            System.out.println(" 2 - inserir elemento no final");
            System.out.println(" 3 - extrair elemento do inicio");
            System.out.println(" 4 - extrair elemento do final");
            System.out.println(" 5 - imprimir elementos da lista (sentido inicio -> fim)");
            System.out.println(" 6 - imprimir elementos da lista (sentido fim -> inicio");
            System.out.println(" 7 - retornar elemento de uma determinada posicao (sentido inicio -> fim)");
            System.out.println(" 8 - retornar elemento de uma determinada posicao (sentido fim -> inicio)");
            System.out.println(" 9 - localizar a primeira ocorrencia um elemento dado seu numero (sentido inicio -> fim)");
            System.out.println("10 - localizar a primeira ocorrencia um elemento dado seu numero (sentido fim -> inicio)");
            System.out.println("11 - inserir um elemento em determinada posicao (sentido inicio -> fim)");
            System.out.println("12 - inserir um elemento em determinada posicao (sentido fim -> inicio)");
            System.out.println("13 - remover um elemento de determinada posicao (sentido inicio -> fim)");
            System.out.println("14 - remover um elemento de determinada posicao (sentido fim -> inicio)");

            System.out.println("98 - inicializa a lista com algum elementos");
            System.out.println("99 - destroi a lista");

            System.out.println("");
            System.out.print("Opcao -> ");

            int opc;
            try {
                opc = scn.nextInt();
            } catch (Exception ex) {
                System.out.println("ERRO: Opcao invalida.");
                scn.next();
                continue;
            }

            if (opc == 0) {
                break;
            } else if (opc == 1) {  //* insere no inicio

                Elemento e = criaElem();
                ll.insereInicio(e);
                System.out.println("Elemento inserido no inicio com sucesso!!");

            } else if (opc == 2) {  //* insere no final

                Elemento e = criaElem();
                ll.insereUltimo(e);
                System.out.println("Elemento inserido no final com sucesso!!");

            } else if (opc == 3) {  //* remove do inicio
                if (vazia()) {
                    continue;
                }
                Elemento e = ll.removeInicio();
                System.out.println("Elemento removido do inicio com sucesso.");
                printElem(e);
            } else if (opc == 4) {  //* remove do final
                if (vazia()) {
                    continue;
                }
                Elemento e = ll.removeUltimo();
                System.out.println("Elemento removido do final com sucesso.");
                printElem(e);
            } else if (opc == 5) {
                ll.imprimeParaFim();
            } else if (opc == 6) {
                ll.imprimeParaInicio();
            } else if (opc == 7 || opc == 8) {
                Scanner scn7 = new Scanner(System.in);
                System.out.println("A posicao desejada nao pode ser maior que a quantidade de elementos.");
                System.out.print("Informe a posicao -> ");
                int pos = scn7.nextInt();
                Elemento e = null;
                if (opc == 7) {
                    e = ll.getPosDoInicio(pos);
                } else if (opc == 8) {
                    e = ll.getPosDoFim(pos);
                }
                printElem(e);
            } else if (opc == 9 || opc == 10) {
                Scanner scn9 = new Scanner(System.in);
                System.out.println("A posicao desejada nao pode ser maior que a quantidade de elementos.");
                System.out.print("Informe a posicao -> ");
                int pos = scn9.nextInt();
                Elemento e = null;
                if (opc == 9) {
                    e = ll.getElemDoInicio(pos);
                } else if (opc == 8) {
                    e = ll.getElemDoFim(pos);
                }
                printElem(e);
            } else if (opc == 11 || opc == 12) {

                //=== REMOVER ESTE TRECHO APOS IMPLEMENTAR A OPCAO 12
                if (opc == 12) {
                    System.out.println("ERRO: opcao ainda nao implementada.");
                    continue;
                }
                //===================================================
                Scanner scn11 = new Scanner(System.in);
                System.out.println("A posicao desejada nao pode ser maior que a quantidade de elementos.");
                System.out.print("Informe a posicao -> ");
                int pos = scn11.nextInt();
                Elemento e = criaElem();
                String s = "";
                if (opc == 11) {
                    s = ll.inserePosParaFim(pos, e);
                } else if (opc == 12) {

                }
                if (s.equals("")) {
                    System.out.println("Inclusao bem sucedida.");
                } else {
                    System.out.println(s);
                }
            } else if (opc == 13 || opc == 14) {

                //=== REMOVER ESTE TRECHO APOS IMPLEMENTAR A OPCAO 13
                if (opc == 13) {
                    System.out.println("ERRO: opcao ainda nao implementada.");
                    continue;
                }
                //===================================================
                Scanner scn13 = new Scanner(System.in);
                System.out.println("A posicao desejada nao pode ser maior que a quantidade de elementos.");
                System.out.print("Informe a posicao -> ");
                int pos = scn13.nextInt();
                Elemento e = ll.removePosParaInicio(pos);
                if (e != null) {
                    System.out.println("Elemento removido");
                    printElem(e);
                } else {
                    System.out.println("ERRO: lista vazia ou parametros incorretos.");
                }

            } else if (opc == 98) {
                inicializa();
                System.out.println("Lista inicializada com valores na ordem do inicio para o final.");
            } else if (opc == 99) {
                ll.destroi();
                System.out.println("A lista foi reinicializada.");
            }

        }

    }

    /**
     * Imprime um elemento no formato -> [ numero | nome ]
     *
     * @param e
     */
    private static void printElem(Elemento e) {  // Ex1
        if (e == null) {
            System.out.println("ERRO: Elemento nulo.");
        } else {
            System.out.println(e.getNumero() + " | " + e.getNome());
        }
    }

    private static Elemento criaElem() {  // Ex1

        Scanner scn = new Scanner(System.in);
        String nm;
        int nr;
        while (true) {
            try {

                System.out.print("\n\nNome do elemento -> ");
                nm = scn.next();
                System.out.print("Numero do elemento -> ");
                nr = scn.nextInt();
                break;
            } catch (Exception ex) {
                scn.next();
                System.out.println("ERRO: entrada invalida.");
            }
        }
        return new Elemento(nm, nr);
    }

    private static boolean vazia() {
        if (ll.isEmpty()) {
            System.out.println("A lista esta' vazia.");
            return true;
        }
        return false;
    }

    private static void inicializa() {

        for (int i = 100; i <= 300; i = i + 10) {
            Elemento e = new Elemento("Elemento_" + String.valueOf(i), i);
            ll.insereUltimo(e);
        }

    }

}
