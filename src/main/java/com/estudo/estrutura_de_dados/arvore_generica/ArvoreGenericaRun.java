package com.estudo.estrutura_de_dados.arvore_generica;


import java.util.Scanner;

public class ArvoreGenericaRun {

//    static ArvoreGenerica arvore;
    public static void main() {

        Scanner scn = new Scanner(System.in);

        //======================================================================
        //                        INSTANCIAR ARVORE
        //======================================================================
        System.out.println("=== ELEMENTO RAIZ ===");
        Elemento raiz = criaElem();
        ArvoreGenerica arvore = new ArvoreGenerica(raiz);
        //----------------------------------------------------------------------

        while (true) {

            System.out.println("\n\n");
            System.out.println("===========================================================");
            System.out.println("  A R V O R E   D E   E S T R U T U R A   G E N E R I C A");
            System.out.println("===========================================================");
            System.out.println("0 - sair");
            System.out.println("1 - inserir elemento");
            System.out.println("2 - localizar elemento");
            System.out.println("3 - imprimir a arvore");
            System.out.println("4 - criar arvore teste");
            System.out.println("-----------------------------------------------------------");
            System.out.print("Opcao -> ");

            int opc = scn.nextInt();

            if (opc == 0) {
                break;
            } else if (opc == 1) {
                Scanner scn1 = new Scanner(System.in);
                System.out.print("Informe o codigo do elemento pai -> ");
                String codigoPai = scn1.nextLine();
                Elemento e = criaElem();
                if (arvore.inserir(codigoPai, e)) {
                    System.out.println("Inclusao bem sucedida");
                } else {
                    System.out.println("### INCLUSAO FALHOU ###");
                }

            } else if (opc == 2) {
                Scanner scn2 = new Scanner(System.in);
                System.out.print("Informe o codigo do elemento procurado -> ");
                String codigo = scn2.nextLine();
                Elemento e = arvore.localizar(codigo);
                arvore.imprimirElem(e, true);
            } else if (opc == 3) {
                Scanner scn3 = new Scanner(System.in);
                System.out.print("Informe o codigo do elemento raiz do ramo a ser impresso ( . parte do raiz da arvore ) -> ");
                String codigo = scn3.nextLine().trim();
                if (".".equals(codigo)) {
                    codigo = null;
                }
                arvore.imprimir(codigo);
            } else if (opc == 4) {
                Elemento e = new Elemento("A", "Elemento A", "Descricao do elemento A");
                arvore = new ArvoreGenerica(e);
                criaArvore(arvore);
            }

        }

    }

    private static Elemento criaElem() {
        Scanner scn = new Scanner(System.in);
        System.out.println("--- NOVO ELEMENTO ---");
        System.out.print("Codigo -> ");
        String codigo = scn.nextLine();
        System.out.print("Nome -> ");
        String nome = scn.nextLine();
        System.out.print("Descricao -> ");
        String descr = scn.nextLine();

        return new Elemento(codigo, nome, descr);
    }

    private static void criaArvore(ArvoreGenerica a) {
        if (a.getQtd() > 1) {
            System.out.println("### A ARVORE JA' CONTEM ELEMENTOS ALEM DO ELEMENTO RAIZ ###");
            return;
        }
        Elemento raiz = a.localizar("A");
        if (raiz == null) {
            System.out.println("### ELEMENTO RAIZ (A) NAO LOCALIZADO ###");
            return;
        }

        a.inserir("A", new Elemento("A.2", "Elemento A.2", "Descricao do elemento A.2"));
        a.inserir("A.2", new Elemento("A.2.5", "Elemento A.2.5", "Descricao do elemento A.2.5"));
        
        a.inserir("A.2", new Elemento("A.2.4", "Elemento A.2.4", "Descricao do elemento A.2.4"));
        a.inserir("A.2.4", new Elemento("A.2.4.2", "Elemento A.2.4.2", "Descricao do elemento A.2.4.2"));
        a.inserir("A.2.4.2", new Elemento("A.2.4.2.2", "Elemento A.2.4.2.2", "Descricao do elemento A.2.4.2.2"));
        a.inserir("A.2.4.2", new Elemento("A.2.4.2.1", "Elemento A.2.4.2.1", "Descricao do elemento A.2.4.2.1"));
        a.inserir("A.2.4", new Elemento("A.2.4.1", "Elemento A.2.4.1", "Descricao do elemento A.2.4.1"));

        a.inserir("A.2", new Elemento("A.2.3", "Elemento A.2.3", "Descricao do elemento A.2.3"));
        a.inserir("A.2", new Elemento("A.2.2", "Elemento A.2.2", "Descricao do elemento A.2.2"));
        a.inserir("A.2", new Elemento("A.2.1", "Elemento A.2.1", "Descricao do elemento A.2.1"));
        a.inserir("A.2.4.2.2", new Elemento("A.2.4.2.2.1", "Elemento A.2.4.2.2.1", "Descricao do elemento A.2.4.2.2.1"));
        a.inserir("A.2.4.2.2", new Elemento("A.2.4.2.2.2", "Elemento A.2.4.2.2.2", "Descricao do elemento A.2.4.2.2.2"));
        a.inserir("A.2.4.2", new Elemento("A.2.4.2.3", "Elemento A.2.4.2.3", "Descricao do elemento A.2.4.2.3"));

        a.inserir("A", new Elemento("A.1", "Elemento A.1", "Descricao do elemento A.1"));
        
        a.inserir("A.1", new Elemento("A.1.2", "Elemento A.1.2", "Descricao do elemento A.1.2"));
        a.inserir("A.1.2", new Elemento("A.1.2.3", "Elemento A.1.2.3", "Descricao do elemento A.1.2.3"));
        a.inserir("A.1.2.3", new Elemento("A.1.2.3.2", "Elemento A.1.2.3.2", "Descricao do elemento A.1.2.3.2"));
        a.inserir("A.1.2.3", new Elemento("A.1.2.3.1", "Elemento A.1.2.3.1", "Descricao do elemento A.1.2.3.1"));
        a.inserir("A.1.2", new Elemento("A.1.2.2", "Elemento A.1.2.2", "Descricao do elemento A.1.2.2"));
        a.inserir("A.1.2", new Elemento("A.1.2.1", "Elemento A.1.2.1", "Descricao do elemento A.1.2.1"));
        
        a.inserir("A.1", new Elemento("A.1.1", "Elemento A.1.1", "Descricao do elemento A.1.1"));
        a.inserir("A.1.1", new Elemento("A.1.1.2", "Elemento A.1.1.2", "Descricao do elemento A.1.1.2"));
        a.inserir("A.1.1", new Elemento("A.1.1.1", "Elemento A.1.1.1", "Descricao do elemento A.1.1.1"));

    }

}
