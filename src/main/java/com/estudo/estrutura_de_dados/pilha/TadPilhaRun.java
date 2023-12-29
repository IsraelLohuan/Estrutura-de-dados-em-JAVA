/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.estudo.estrutura_de_dados.pilha;

import java.util.Scanner;

/**
 *
 * @author israel.lohuan
 */
public class TadPilhaRun {
    public static void main() {
        Scanner scn = new Scanner(System.in);
        
        System.out.println("-----------------------------------------------------");
        System.out.println("PROGRAMA PARA ESTUDO DO TAD PILHA - VERSÃO 1");
        System.out.println("-----------------------------------------------------");
        System.out.println("");
        System.out.println("Tamanho máximo da pilha: ");
        int qtd = scn.nextInt();
        
        TadPilha pilha = new TadPilha(qtd);
        
        while(true) {
           System.out.println("\n\n--------------------------------------------");
            System.out.println("PROGRAMA PARA ESTUDO DO TAD PILHA - VERSÃO 1");
            System.out.println("      Conteudo: numeros inteiros");
            System.out.println("      Capacidade:" + qtd + " elementos");
            System.out.println("--------------------------------------------");
            System.out.println("");
            System.out.println("0 - Encerrar");
            System.out.println("1 - Inserir um elemento");
            System.out.println("2 - Extrair um elemento");
            System.out.println("3 - Imprimir a pilha");
            System.out.println("");
            
            System.out.print("Opcao: ");
            int opc = scn.nextInt();
            
            if(opc == 0) break;
            
            switch(opc) {
                case 1: {
                   Scanner scn1 = new Scanner(System.in);
                   System.out.print("Valor do elemento a inserir: ");
                   int opc1 = scn1.nextInt();
                   
                   if(pilha.push(opc1)) {
                       System.out.println("Insercao bem sucedida.");
                   } else {
                       System.out.println("Insercao falhou!");
                   }
                   continue;
                }
                case 2: {
                    if(pilha.pop()) {
                        System.out.println("Extração bem sucedidade. Valor do elemento = " + pilha.getRetorno());
                    } else {
                        System.out.println("Extração falhou");
                    }
                    continue;
                }
                case 3: {
                    System.out.println(pilha.print());
                    continue;
                }
                default: {
                    System.out.println("opção inválida!");
                }
            }
        }
    }
}
