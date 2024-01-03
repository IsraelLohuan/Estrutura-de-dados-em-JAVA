/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.estudo.estrutura_de_dados.fila;

/**
 *
 * @author israel.lohuan
 */
public class TadFila {
    
   int ini;
   int fim;
   int operacao;
   final int capacidade;
   final int[] fila;
   
   public TadFila(int tam) {
       this.ini = 0;
       this.fim = 0;
       this.operacao = 0;
       this.capacidade = tam;
       fila = new int[tam];
   } 
   
   public boolean isEmpty() {
       return this.ini == this.fim && this.operacao <= 0;
   }
   
   public boolean isFull() {
       return this.ini == this.fim && this.operacao == 1;
   }
   
   public Integer peek() {
       if(this.isEmpty()) {
           return null;
       }
       
       return this.fila[this.ini];
   }
   
   public String enqueue(int elem) {
       if(this.isFull()) {
           return "ERRO: a fila está cheia";
       }
       
       this.fila[this.fim] = elem;
       this.fim = (this.fim + 1) % this.capacidade;
       this.operacao = 1;
       return "Elemento inserido com sucesso!";
   }
   
   public Integer dequeue() {
       if(this.isEmpty()) {
           return null;
       }
       
       Integer elem = this.peek();
       this.ini = (this.ini + 1) % this.capacidade;
       this.operacao = -1;
       return elem;
   }
   
   public int size() {
       int size = 0;
       
       if(this.ini < this.fim) {
           size = this.fim - this.ini;
       } else if(this.ini == this.fim) {
           if(this.isEmpty()){
               size = 0;
           } else {
               size = this.capacidade;
           }
       } else if(this.ini > this.fim) {
           size = this.capacidade - this.ini + this.fim;
       }
       
       return size;
   }
   
   public String print() {
       String ret = "";
       
       if(this.isEmpty()) {
           return "A fila está vazia";
       }
       
       if(this.ini < this.fim) {
           for(int i = this.ini; i < this.fim; i++) {
               ret += this.fila[i] + " ";
           }
       } else if(this.isFull() || this.ini > this.fim) {
           for(int i = this.ini; i < this.capacidade; i++) {
               ret += this.fila[i] + " ";
           }
           if(this.ini > 0) {
               for(int i = 0; i < this.fim; i++) {
                   ret += this.fila[i] + " ";
               }
           }
       }
       
       return ret;
   }
}
