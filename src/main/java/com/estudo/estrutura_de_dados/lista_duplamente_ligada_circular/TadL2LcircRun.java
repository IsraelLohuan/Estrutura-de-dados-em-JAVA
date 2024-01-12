package com.estudo.estrutura_de_dados.lista_duplamente_ligada_circular;
import java.util.Scanner;

public class TadL2LcircRun {

    static L2Lcirc l2lc;

    public static void main() {

        l2lc = new L2Lcirc();

        String msg_or = "OPERACAO REALIZADA COM SUCESSO.";
        String msg_of = "*** OPERACAO FALHOU!!! ***";
        String msg_er = "Elemento removido -> ";

        OUTER:
        while (true) {
            System.out.println("\n\n======================================================");
            System.out.println("          TAD LISTA DUPLAMENTE LIGADA CIRCULAR");
            System.out.println("             Quantidade de elementos -> " + l2lc.getQtd());
            System.out.println("======================================================");
            System.out.println(" 0 - encerrar");
            System.out.println(" 1 - inserir um elemento no inicio");
            System.out.println(" 2 - inserir um elemento no final");
            System.out.println(" 3 - inserir um elemento em uma posicao generica no sentido horario");
            System.out.println(" 4 - inserir um elemento em uma posicao generica no sentido anti-horario");
            System.out.println(" 5 - remover um elemento do inicio");
            System.out.println(" 6 - remover um elemento do final");
            System.out.println(" 7 - remover um elemento de uma posicao generica no sentido horario");
            System.out.println(" 8 - remover um elemento de uma posicao generica no sentido anti-horario");
            System.out.println(" 9 - imprimir no sentido horário");
            System.out.println("10 - imprimir no sentido anti-horario");
            System.out.println("99 - imprimir 15 elementos.");
            System.out.print("\nSua opcao -> ");
            Scanner scn = new Scanner(System.in);

            try {
                int opc = scn.nextByte();
                if (opc == 0) {
                    break;
                } else if (opc == 1) {
                    Elemento e = _criaElem();
                    l2lc.insereInicio(e);
                    System.out.println(msg_or);
                } else if (opc == 2) {
                    Elemento e = _criaElem();
                    l2lc.insereUltimo(e);
                    System.out.println(msg_or);
                } else if (opc == 3) {
                    int pos = _posicao();
                    Elemento eNovo = _criaElem();
                    Elemento eAtual = l2lc.getPosHorario(l2lc.getInicio(), pos);
                    if (l2lc.insereHorario(eNovo, eAtual)) {
                        System.out.println(msg_or);
                    } else {
                        System.out.println(msg_of);
                    }
                } else if (opc == 4) {
                    int pos = _posicao();
                    Elemento eNovo = _criaElem();
                    Elemento eAtual = l2lc.getPosAntiHorario(l2lc.getInicio(), pos);
                    if (l2lc.insereAntihorario(eNovo, eAtual)) {
                        System.out.println(msg_or);
                    } else {
                        System.out.println(msg_of);
                    }
                } else if (opc == 5 || opc == 6) {
                    Elemento e = null;
                    if (opc == 5) {
                        e = l2lc.removeInicio();
                    } else if (opc == 6) {
                        e = l2lc.removeFim();
                    }
                    if (e != null) {
                        System.out.println(msg_er);
                        System.out.print(msg_er);
                        l2lc._imprimeElemento(e);
                    } else {
                        System.out.println(msg_of);
                    }
                } else if (opc == 7 || opc == 8) {
                    int pos = _posicao();
                    Elemento e = null;
                    if (opc == 7) {
                        e = l2lc.getPosHorario(l2lc.getInicio(), pos);
                    } else if (opc == 8) {
                        e = l2lc.getPosAntiHorario(l2lc.getInicio(), pos);
                    }
                    l2lc.removeElemento(e);
                    if (e != null) {
                        System.out.println(msg_er);
                        System.out.print(msg_er);
                        l2lc._imprimeElemento(e);
                    } else {
                        System.out.println(msg_of);
                    }

                } else if (opc == 9) {
                    l2lc.imprimeHorario();
                } else if (opc == 10) {
                    l2lc.imprimeAntiHorario();
                } else if (opc == 99) {
                    Elemento e = l2lc.getInicio();
                    for (int i = 1; i <= 15; i++) {
                        l2lc._imprimeElemento(e);
                        e = e.getProximo();
                    }
                }
            } catch (Exception ex) {
                System.out.println(msg_of + "\n(mensagem do sistema >> " + ex.getMessage() + ")");
            }
        }

    }

    private static Elemento _criaElem() {
        Scanner scn = new Scanner(System.in);
        System.out.print("Informe o ID do elemento -> ");
        int id = scn.nextInt();
        Elemento e = new Elemento();
        e.setId(id);
        return e;
    }

    private static int _posicao() {
        Scanner scn = new Scanner(System.in);
        System.out.println("AVISO: a posicao nao pode ser maior que a quantidade de elementos.");
        System.out.print("Informe a posicao desejada -> ");
        return scn.nextInt();
    }

}
