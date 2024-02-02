package com.estudo.estrutura_de_dados.algoritmos_ordenacao;


import java.util.Scanner;

public class Merge_Quick_JavaRun {

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

        //Q ====================================================================
        Scanner scnS = new Scanner(System.in);
        System.out.print("Merge (m) ou Quick (q) -> ");
        String alg = scnS.nextLine();
        String nome = "Merge";
        String recursivo = "n";
        System.out.println(alg);
        if (alg.equals("q")) {
            nome = "Quick";
            System.out.print("Utilizar o Quick Sort recursivo? (s/n)");
            recursivo = scnS.nextLine();
        }
        //Q --------------------------------------------------------------------

        GFG gfg = new GFG();

        //Q ====================================================================
        //  vou deixar todos os objetos instanciados (somente um sera' utilizado)
        QuickSort_ITE qsi = new QuickSort_ITE();
        QuickSort_REC qsr = new QuickSort_REC();
        //----------------------------------------------------------------------

        System.out.println("Iniciando " + nome + " Sort...");  //Q
        long inicio = System.nanoTime();

        if (alg.equals("m")) {
            System.out.println("Utilizando metodo Merge Sort iterativo...");
            gfg.mergeSort(dados, tam);
        } else {
            if (recursivo.equals("s")) {
                System.out.println("Utilizando metodo Quick Sort recursivo.");
                qsr.qSort(dados, 0, dados.length - 1);
            } else {
                System.out.println("Utilizando metodo Quick Sort iterativo...");
                qsi.quickSortIterative(dados, 0, dados.length - 1);
            }

        }

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

class QuickSort_ITE {  // prof Marcio acrescentou o sufixo _ITE para diferenciar da classe recursiva

    /* This function takes last element as pivot,
	places the pivot element at its correct
	position in sorted array, and places all
	smaller (smaller than pivot) to left of
	pivot and all greater elements to right
	of pivot */
    int partition(int arr[], int low, int high) {  // prof. Marcio -> este metodo estava como static
        int pivot = arr[high];

        // index of smaller element
        int i = (low - 1);
        for (int j = low; j <= high - 1; j++) {
            // If current element is smaller than or
            // equal to pivot
            if (arr[j] <= pivot) {
                i++;

                // swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    /* A[] --> Array to be sorted,
l --> Starting index,
h --> Ending index */
    void quickSortIterative(int arr[], int l, int h) {  // prof. Marcio -> este metodo estava como static
        // Create an auxiliary stack
        int[] stack = new int[h - l + 1];

        // initialize top of stack
        int top = -1;

        // push initial values of l and h to stack
        stack[++top] = l;
        stack[++top] = h;

        // Keep popping from stack while is not empty
        while (top >= 0) {
            // Pop h and l
            h = stack[top--];
            l = stack[top--];

            // Set pivot element at its correct position
            // in sorted array
            int p = partition(arr, l, h);

            // If there are elements on left side of pivot,
            // then push left side to stack
            if (p - 1 > l) {
                stack[++top] = l;
                stack[++top] = p - 1;
            }

            // If there are elements on right side of pivot,
            // then push right side to stack
            if (p + 1 < h) {
                stack[++top] = p + 1;
                stack[++top] = h;
            }
        }
    }
//    // Driver code
//
//    public static void main(String args[]) {
//        int arr[] = {4, 3, 5, 2, 1, 3, 2, 3};
//        int n = 8;
//
//        // Function calling
//        quickSortIterative(arr, 0, n - 1);
//
//        for (int i = 0; i < n; i++) {
//            System.out.print(arr[i] + " ");
//        }
//    }
}
//
//
//

// Java program for implementation of QuickSort in recursive method
class QuickSort_REC {

    /* This function takes last element as pivot,
	places the pivot element at its correct
	position in sorted array, and places all
	smaller (smaller than pivot) to left of
	pivot and all greater elements to right
	of pivot */
    int partition(int arr[], int low, int high) {  // prof. Marcio -> este metodo estava como static
        int pivot = arr[high];
        int i = (low - 1); // index of smaller element
        for (int j = low; j <= high - 1; j++) {
            // If current element is smaller than or
            // equal to pivot
            if (arr[j] <= pivot) {
                i++;

                // swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    /* The main function that implements QuickSort()
	arr[] --> Array to be sorted,
	low --> Starting index,
	high --> Ending index */
    void qSort(int arr[], int low, int high) {  // prof. Marcio -> este metodo estava como static
        if (low < high) {
            /* pi is partitioning index, arr[pi] is
			now at right place */
            
            //int pi = 1000;
            int pi = partition(arr, low, high);

            // Recursively sort elements before
            // partition and after partition
            qSort(arr, low, pi - 1);
            qSort(arr, pi + 1, high);
        }
    }

//    // Driver code
//    public static void main(String args[]) {
//
//        int n = 5;
//        int arr[] = {4, 2, 6, 9, 2};
//
//        qSort(arr, 0, n - 1);
//
//        for (int i = 0; i < n; i++) {
//            System.out.print(arr[i] + " ");
//        }
//    }
}


