package com.estudo.estrutura_de_dados.algoritmos_ordenacao;


import java.util.Scanner;

public class Bubble_JavaRun {

    /**
     * @param args the command line arguments
     */
    public static void run() {

        Scanner scn = new Scanner(System.in);

        System.out.print("Tamanho do vetor -> ");
        int tam = scn.nextInt();

        int[] dados = vetor(tam);

        while (true) {
            System.out.println("====================================================");
            System.out.println("         ALGORITMO DE ORDENACAO BUBBLE SORT");
            System.out.println("====================================================");
            System.out.println("0 - encerrar");
            System.out.println("1 - imprimir o vetor");
            System.out.println("2 - ordenar com Bubble");
            System.out.println("3 - ordenar com Insertion");
            System.out.println("4 - ordenar com Selection");
            System.out.println("====================================================");
            System.out.print("\nOpcao -> ");
            int opc = scn.nextInt();

            if (opc == 0) {
                break;
            } else if (opc == 1) {
                for (int i = 0; i < tam; i++) {
                    System.out.println(i + " - " + dados[i]);
                }
            } else if (opc == 2) {
                dados = bubble(dados);
            } else if (opc == 3) {
                dados = insertion(dados);
            } else if (opc == 4) {
                dados = selection(dados);
            }

        }

    }

    /**
     * Retorna um vetor de numeros inteiros.
     *
     * @param tam Tamanho do vetor a ser retornado.
     * @return
     */
    private static int[] vetor(int tam) {

        System.out.println("Gerando vetor ....");

        int[] ret = new int[tam];

        for (int i = 0; i < tam; i++) {

            ret[i] = (int) (Math.random() * tam * 10);

        }

        System.out.println("Pronto.");

        return ret;

    }

    /**
     * Algoritmo de ordenacao Bubble Sort.
     *
     * @param dados Vetor com o conjunto de dados a ordenar.
     * @return O mesmo vetor de entrada, porem com os dados ordenados.
     */
    private static int[] bubble(int[] dados) {

        boolean trocou = false;
        int temp;

        long inicio = System.nanoTime();

        do {

            trocou = false;

            for (int i = 0; i < dados.length - 1; i++) {

                if (dados[i] > dados[i + 1]) {
                    temp = dados[i];
                    dados[i] = dados[i + 1];
                    dados[i + 1] = temp;
                    trocou = true;
                }

            }

        } while (trocou);

        long fim = System.nanoTime();

        System.out.println("Tempo de execucao = " + (double) ((fim - inicio) / 1000000) + " milisegundos");

        return dados;

    }

    /**
     * Algoritmo de ordenacao Insertion Sort.
     *
     * @param dados
     * @return
     */
    private static int[] insertion(int[] dados) {

        long inicio = System.nanoTime();

        for (int i = 1; i < dados.length; i++) {

            int atual = dados[i];

            for (int j = i - 1; j >= 0; j--) {
                if (atual >= dados[j]) {
                    break;
                } else {
                    dados[j + 1] = dados[j];
                    dados[j] = atual;
                }

            }

        }

        long fim = System.nanoTime();

        System.out.println("Tempo de execucao = " + (double) ((fim - inicio) / 1000000) + " milisegundos");

        return dados;
    }

    private static int[] selection(int[] dados) {

        int valMenor;
        int idxValMenor;
        
        long inicio = System.nanoTime();

        for (int i = 0; i < dados.length; i++) {
            valMenor = dados[i];
            idxValMenor = i;
            for (int j = i + 1; j < dados.length; j++) {
                if (dados[j] < valMenor){
                    valMenor = dados[j];
                    idxValMenor = j;
                }
            }
            dados[idxValMenor] = dados[i];
            dados[i] = valMenor;
        }
        
        long fim = System.nanoTime();
        
        System.out.println("Tempo de execucao = " + (double) ((fim - inicio) / 1000000) + " milisegundos");

        return dados;

    }

}
