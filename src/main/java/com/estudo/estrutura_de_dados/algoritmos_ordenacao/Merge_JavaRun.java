package com.estudo.estrutura_de_dados.algoritmos_ordenacao;


import java.util.Scanner;

public class Merge_JavaRun {

    public static void run() {

        Scanner scn = new Scanner(System.in);
        System.out.print("Tamanho do Vetor -> ");
        int tam = scn.nextInt();

        int[] dados = vetor(tam);
        System.out.println("Dados aleatorios gerados.");
        
        System.out.print("Imprime o vetor? (1 = sim, 0 = nao) -> ");
        int opc = scn.nextByte();
        if (opc == 1) {
            for (int i = 0; i < tam; i++) {
                System.out.println(i + " - " + dados[i]);
            }
        }

        GFG gfg = new GFG();

        System.out.println("Iniciando Merge Sort...");
        long inicio = System.nanoTime();

        gfg.mergeSort(dados, tam);

        long fim = System.nanoTime();

        System.out.println("Tempo de execucao = " + (double) ((fim - inicio) / 1000000) + " milisegundos");

        System.out.print("Imprime o vetor ordenado? (1 = sim, 0 = nao) -> ");
        opc = scn.nextByte();
        if (opc == 1) {
            for (int i = 0; i < tam; i++) {
                System.out.println(i + " - " + dados[i]);
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

}


