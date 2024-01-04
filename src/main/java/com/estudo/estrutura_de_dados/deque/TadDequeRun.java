/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.estudo.estrutura_de_dados.deque;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author israel.lohuan
 */
public class TadDequeRun {
   static Deque<Integer> deque;
   static Scanner scn;
   static int maxTam;
   
   public static void main() {
       deque = new ArrayDeque<>(3);
       
       scn = new Scanner(System.in);
       
       System.out.println("Tamanho máximo do Deque -> ");
       maxTam = scn.nextInt();
       
       if(maxTam <= 0) {
           System.out.println("Tamanhos permitidos >= 1");
           return;
       }
       
       while(true) {
          System.out.println("\n\n================================================");
          System.out.println("            ESTUDO DO TAD DEQUE");
          System.out.println("================================================");
          System.out.println("0 - sair");
          System.out.println("1 - Inserir elemento no inicio");
          System.out.println("2 - Inserir elemento no final");
          System.out.println("3 - Remover elemento do inicio");
          System.out.println("4 - Remover elemento do final");
          System.out.println("5 - Ler elemento de uma determinada posicao");
          System.out.println("6 - Imprimir elementos do Deque");

          System.out.print("\n\nOpcao -> "); 
          
          int opc = scn.nextInt();
          
          if(opc == 0) {
              break;
          } else if(opc == 1) {
              if(cheio()) {
                  continue;
              }
              deque.addFirst(getValor());
          } else if(opc == 2) {
              if(cheio()) {
                  continue;
              }
              
              deque.addLast(getValor());
          } else if(opc == 3) {
              if(vazio()) {
                  continue;
              }
              System.out.println("Elemento removido do início -> " + deque.removeFirst());
          } else if(opc == 4) {
              if(vazio()) {
                  continue;
              }
              
              System.out.println("Elemento removido do final -> " + deque.removeLast());
          } else if(opc == 5) {
              int posDesejada = getValor();
              if(posDesejada > deque.size() || posDesejada <= 0) {
                  System.out.println("Não há elemento nesta posição " + posDesejada);
                  continue;
              }
              
              Iterator<Integer> it = deque.iterator();
              int posAtual = 0;
              boolean loc = false;
              while(it.hasNext()) {
                  posAtual ++;
                  if(posAtual == posDesejada) {
                      System.out.println("O elemento da posição " + posDesejada + " é -> " + it.next());
                      loc = true;
                      break;
                  }
                  it.next();
              }
              if(!loc) {
                  System.out.println("Elemento não localizado");
              }
          } else if(opc == 6) {
              Iterator<Integer> it = deque.iterator();
              while(it.hasNext()) {
                  System.out.println(it.next() + ", ");
              }
          } else if(opc == 99) {
              deque.add(getValor());
          }
       }
   }
   
   private static boolean vazio() {
       if(deque.isEmpty()) {
           System.out.println("O deque está vazio!");
           return true;
       }
       
       return false;
   }
   
   private static boolean cheio() {
       if(deque.size() == maxTam) {
           System.out.println("O Deque está cheio");
           return true;
       }
       
       return false;
   }
   
   private static int getValor() {
       System.out.println("Informe o valor do elemento -> ");
       return scn.nextInt();
   }
}
