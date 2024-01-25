package com.estudo.estrutura_de_dados.arvore_binaria_avl;



import java.util.Scanner;

public class ArvoreBinariaAvlRun {

    private static ArvoreBinariaAvl abb1;

    public static void main() {

        abb1 = new ArvoreBinariaAvl();

        Scanner scn = new Scanner(System.in);

        while (true) {

            System.out.println("=======================================================");
            System.out.println("  PROGRAMA PARA ESTUDO DO TAD ARVORE BINARIA DE BUSCA");
            System.out.println("       Quantidade de elementos na arvore -> " + abb1.getQtd());
            System.out.println("=======================================================");
            System.out.println("0 - encerrar");
            System.out.println("1 - inserir no'");
            System.out.println("2 - ler no'");
            System.out.println("3 - excluir no'");
            System.out.println("=================== IMPRESSAO POR METODOS ITERATIVOS ==");
            System.out.println("4 - imprimir em ordem");
            System.out.println("5 - imprimir em pre-ordem");
            System.out.println("6 - imprimir em pos-ordem");
            System.out.println("=================== IMPRESSAO POR METODOS RECURSIVOS ==");
            System.out.println("7 - imprimir em ordem");
            System.out.println("8 - imprimir em pre-ordem");
            System.out.println("9 - imprimir em pos-ordem");
            System.out.println("=======================================================");
            System.out.println("*** OPCOES AUXILIARES ");
            System.out.println("10 - imprimir o no' raiz");
            System.out.println("11 - inserir no' por metodo recursivo.");
            System.out.println("99 - carregar valores (100, 50, 200, 25, 75, 150, 300)");
            System.out.println("111 - carregar valores (100, 50, 200, 25, 75, 150, 300, 12, 30, 60, 120, 5, 15, 27, 35)");
            System.out.println("-1 - zerar a arvore");

            System.out.print("\nOpcao -> ");

            int opc = scn.nextByte();

            if (opc == 0) {
                break;
            } else if (opc == 1) {
                Scanner scn1I = new Scanner(System.in);
                System.out.print("Informe o ID do elemento ---> ");
                int id = scn1I.nextInt();
                Scanner scn1S = new Scanner(System.in);
                System.out.print("Informe o nome do elemento -> ");
                String nm = scn1S.next();

                Elemento elem = new Elemento(id, nm);
                No_AVL<Elemento> noa = new No_AVL<>(id, elem);

                if (abb1.inserir(noa)) {
                    System.out.println("\nInclusao bem sucedida.");
                } else {
                    System.out.println("\n*** ERRO: Inclusao negada.");
                }

            } else if (opc == 2) {
                Scanner scn2I = new Scanner(System.in);
                System.out.print("Informe o ID do elemento a ser localizado ---> ");
                int id = scn2I.nextInt();
                No_AVL[] no = abb1.localizarNo(id);
                if (no[1] == null) {
                    System.out.println("ERRO: nao consta na arvore.");
                } else {
                    System.out.println("\nElemento localizado:");
                    imprimeElem(no[1]);
                }

            } else if (opc == 3) {

                Scanner scn3I = new Scanner(System.in);
                System.out.print("Informe o ID do elemento a ser extraido ---> ");
                int id = scn3I.nextInt();
                No_AVL no = abb1.extrair(id);
                if (no == null) {
                    System.out.println("ERRO: nao consta na arvore.");
                } else {
                    System.out.println("\nElemento extraido:");
                    imprimeElem(no);
                }

            } else if (opc == 4) {
                abb1.imprimirEmOrdem_ITE();
            } else if (opc == 5) {
                abb1.imprimirPreOrdem_ITE();
            } else if (opc == 6) {
                abb1.imprimirPosOrdem_ITE();
            } else if (opc == 7) {
                abb1.imprimeEmOrdem_REC(abb1.getRaiz());
            } else if (opc == 8) {
                abb1.imprimePreOrdem_REC(abb1.getRaiz());
            } else if (opc == 9) {
                abb1.imprimePosOrdem_REC(abb1.getRaiz());
            } else if (opc == 10) {
                if (abb1.getQtd() == 0) {
                    System.out.println("*** A arvore esta' vazia ***");
                } else {
                    System.out.print("\nElemento do no' raiz -> ");
                    imprimeElem(abb1.getRaiz());
                }
            } else if (opc == 11) {

                Scanner scn11I = new Scanner(System.in);
                System.out.print("Informe o ID do elemento ---> ");
                int id = scn11I.nextInt();
                Scanner scn11S = new Scanner(System.in);
                System.out.print("Informe o nome do elemento -> ");
                String nm = scn11S.next();

                Elemento elem = new Elemento(id, nm);
                No_AVL<Elemento> noa = new No_AVL<>(id, elem);

                if (abb1.insert(abb1.getRaiz(), noa)) {
                    System.out.println("\nInsercao bem sucedida.");
                } else {
                    System.out.println("\n*** ERRO: insercao falhou (ver. se ja' nao consta este ID na arvore).");
                }

            } else if (opc == 99) {
                carregarDados();
            } else if (opc == 111) {
                __carregarDados();
            } else if (opc == -1) {
                abb1.zerarArvore();
            }

        }

    }

    private static void carregarDados() {

        abb1.inserir(new No_AVL<>(100, new Elemento(100, "Cem")));
        abb1.inserir(new No_AVL<>(50, new Elemento(50, "Cinquenta")));
        abb1.inserir(new No_AVL<>(200, new Elemento(200, "Duzentos")));
        abb1.inserir(new No_AVL<>(25, new Elemento(25, "VinteECinco")));
        abb1.inserir(new No_AVL<>(75, new Elemento(75, "SetentaECinco")));
        abb1.inserir(new No_AVL<>(150, new Elemento(150, "Cento e Cinquenta")));
        abb1.inserir(new No_AVL<>(300, new Elemento(300, "Trezentos")));

    }

    private static void imprimeElem(No_AVL no) {
        Elemento elem = (Elemento) no.getDados();
        System.out.println("Numero -> " + elem.getNumero() + " | Nome -> " + elem.getNome());
    }

    private static void __carregarDados() {

        abb1.inserir(new No_AVL<>(100, new Elemento(100, "Cem")));
        abb1.inserir(new No_AVL<>(50, new Elemento(50, "Cinquenta")));
        abb1.inserir(new No_AVL<>(200, new Elemento(200, "Duzentos")));
        abb1.inserir(new No_AVL<>(25, new Elemento(25, "VinteECinco")));
        abb1.inserir(new No_AVL<>(75, new Elemento(75, "SetentaECinco")));
        abb1.inserir(new No_AVL<>(150, new Elemento(150, "Cento e Cinquenta")));
        abb1.inserir(new No_AVL<>(300, new Elemento(300, "Trezentos")));

        abb1.inserir(new No_AVL<>(12, new Elemento(12, "12")));
        abb1.inserir(new No_AVL<>(30, new Elemento(30, "30")));
        abb1.inserir(new No_AVL<>(60, new Elemento(60, "60")));
        abb1.inserir(new No_AVL<>(120, new Elemento(120, "120")));
        abb1.inserir(new No_AVL<>(5, new Elemento(5, "5")));
        abb1.inserir(new No_AVL<>(15, new Elemento(15, "15")));
        abb1.inserir(new No_AVL<>(27, new Elemento(27, "27")));
        abb1.inserir(new No_AVL<>(35, new Elemento(35, "35")));

    }

}
