package com.estudo.estrutura_de_dados.lista_ligada;

import com.estudo.estrutura_de_dados.lista_ligada.ListaLigadaFila;
import com.estudo.estrutura_de_dados.lista_ligada.Pessoa;
import com.estudo.estrutura_de_dados.lista_ligada.ListaLigada;
import java.util.Scanner;

public class TadListaLigadaRun {

    public static void main() {
       int opt = 1;
       
       Scanner scn = new Scanner(System.in);

       ListaLigada<Pessoa> lista;
       
       System.out.println("Selecione a opção:");
       System.out.println("Opções: tipo Pilha (1) | tipo Fila (2)");
      
       opt = scn.nextInt();
       
       if(opt == 1) {
           lista = new ListaLigadaPilha<Pessoa>();
       } else {
           lista = new ListaLigadaFila<Pessoa>();
       }
       
       while (true) {

            System.out.println("\n\n================================================");
            System.out.println("   LISTA LIGADA ");
            System.out.println("================================================");
            System.out.println("0 - encerrar");
            System.out.println("1 - inserir elemento");
            System.out.println("2 - extrair elemento");
            System.out.println("3 - imprimir elementos da lista (sentido inicio -> fim)");
            System.out.println("");
            System.out.print("Opcao -> ");
            int opc = scn.nextInt();

            if (opc == 0) {
                break;
            } else if (opc == 1) {
                System.out.print("Nome do elemento -> ");
                String nm = scn.next();
                System.out.print("Numero do elemento -> ");
                int nr = scn.nextInt();
                if (lista.push(new Pessoa(nm, nr))) {
                    System.out.println("Elemento inserido com sucesso!!");
                } else {
                    System.out.println("*** Falha na insercao do elemento!! ***");
                }
            } else if (opc == 2) {
                Pessoa e = lista.pop();
                if (e == null) {
                    System.out.println("A Lista esta' vazia!!");
                } else {
                    System.out.println("O elemento foi removido com sucesso.");
                    System.out.print("\nNome ---> " + e.getNome());
                    System.out.print("\nNumero -> " + e.getNumero());
                }
            } else if (opc == 3 && opt == 2) {
                System.out.println("Elementos da fila:");
                System.out.println("--------------------------------------------");
                if (lista.isEmpty()) {
                    System.out.println("A fila esta' vazia.");
                } else {
                    Pessoa e = lista.getInicio();
                    while (e != null) {
                        System.out.println(e.getNumero() + " | " + e.getNome());
                        e = (Pessoa) e.getProximo();
                    }
                }

            }
        }

    }

}
