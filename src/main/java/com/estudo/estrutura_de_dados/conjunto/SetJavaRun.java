package com.estudo.estrutura_de_dados.conjunto;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class SetJavaRun {

    public static void run() {

        Set<Integer> conjunto1 = new HashSet<>();
        Set<Integer> conjunto2 = new HashSet<>();
        conjunto2.add(3);
        conjunto2.add(8);
        conjunto2.add(13);
        conjunto2.add(29);

        HashMap mapa1 = new HashMap();
//        Map<String, Integer> mapa1 = new HashMap();
//        mapa1.put("nulo", null);

        Scanner scn = new Scanner(System.in);

        while (true) {
            System.out.println("\n\n");
            System.out.println("===================================================================");
            System.out.println("             E X E R C I C I O   D E   C O N J U N T O");
            System.out.println("                        (numeros inteiros)");
            System.out.println("\nO conteudo do Conjunto 1 e' variavel pelas operacoes abaixo");
            System.out.print("O conjunto 2 e' fixo -> ");
            printSet(conjunto2);
            System.out.println("\n===================================================================");
            System.out.println("0 - encerrar");
            System.out.println("\n---------- operacoes no Conjunto 1");
            System.out.println("1 - inserir");
            System.out.println("2 - remover");
            System.out.println("3 - procurar");
            System.out.println("4 - imprimir");
            System.out.println("-------------------------------------------------------------------");
            System.out.println("5 - Uniao dos Conjuntos 1 e 2");
            System.out.println("6 - Interseccao dos Conjuntos 1 e 2");
            System.out.println("7 - Diferenca: Conjunto 1 menos Conjunto 2");
            System.out.println("\n---------- operacoes com o Dicionario 1");
            System.out.println("8 - imprimir");
            System.out.println("9 - inserir");
            System.out.println("10 - remover");
            System.out.println("11 - ler o valor dada a chave");
            System.out.println("12 - procurar pelo valor");
            System.out.println("===================================================================");

            System.out.print("Opcao -> ");
            int opc = scn.nextInt();

            if (opc == 0) {
                break;
            } else if (opc == 1) {
                System.out.print("Valor a inserir -> ");
                int n1 = scn.nextInt();
                conjunto1.add(n1);
            } else if (opc == 2) {
                System.out.print("Valor a remover -> ");
                int n2 = scn.nextInt();
                conjunto1.remove(n2);
            } else if (opc == 3) {
                System.out.print("Valor a procurar -> ");
                int n3 = scn.nextInt();
                if (conjunto1.contains(n3)) {
                    System.out.println(n3 + " esta' presente no conjunto.");
                } else {
                    System.out.println(n3 + " nao foi localizado.");
                }
            } else if (opc == 4) {
                printSet(conjunto1);
            } else if (opc == 5) {
                Set<Integer> c5 = new HashSet<>();
                c5.addAll(conjunto1);
                c5.addAll(conjunto2);
                System.out.print("C1 uniao C2 -> ");
                printSet(c5);
            } else if (opc == 6) {
                Set<Integer> c6 = new HashSet<>();
                c6.addAll(conjunto1);
                c6.retainAll(conjunto2);
                System.out.print("C1 interseccao C2 -> ");
                printSet(c6);
            } else if (opc == 7) {
                Set<Integer> c7 = new HashSet<>();
                c7.addAll(conjunto1);
                c7.removeAll(conjunto2);
                System.out.print("C1 menos C2 -> ");
                printSet(c7);
            } else if (opc == 8) {
                printMap(mapa1);
            } else if (opc == 9) {
                Scanner scn9 = new Scanner(System.in);
                System.out.print("Chave (String) -> ");
                String k9 = scn9.nextLine();
                System.out.print("Valor a inserir -> ");
                int n9 = scn.nextInt();
                mapa1.put(k9, n9);
            } else if (opc == 10) {
                Scanner scn10 = new Scanner(System.in);
                System.out.print("Chave (String) -> ");
                String k10 = scn10.nextLine();
                if (mapa1.containsKey(k10)) {
                    Integer v10 = (Integer) mapa1.remove(k10);
                    System.out.println("Removido elemento " + v10);
                } else {
                    System.out.println("Chave nao consta no dicionario.");
                }
            } else if (opc == 11) {
                Scanner scn11 = new Scanner(System.in);
                System.out.print("Chave (String) -> ");
                String k11 = scn11.nextLine();
                if (mapa1.containsKey(k11)) {
                    System.out.println(mapa1.get(k11));
                } else {
                    System.out.println("Chave nao consta no dicionario.");
                }
            } else if (opc == 12) {
                Scanner scn12 = new Scanner(System.in);
                System.out.print("Valor -> ");
                int v12 = scn12.nextInt();
                boolean encontrado = false;
                for (Object key : mapa1.keySet()) {
                    if ((Integer) mapa1.get(key) == v12) {
                        System.out.println("Encontrado na chave " + key);
                        encontrado = true;
                    }
                }
                if (!encontrado){
                    System.out.println("Nao localizado");
                }
            }

        }

    }

    private static void printSet(Set<Integer> s) {
        s.forEach((elem) -> {
            System.out.print(" " + elem);
        });
    }

    private static void printMap(HashMap hm) {
        for (Object key : hm.keySet()) {
            System.out.println(key + " = " + hm.get(key));
        }
    }

}
