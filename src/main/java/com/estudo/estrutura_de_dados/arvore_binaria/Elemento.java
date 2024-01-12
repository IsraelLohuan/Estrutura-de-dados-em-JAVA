/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.estudo.estrutura_de_dados.arvore_binaria;

public class Elemento {
   private int numero;
   private String nome;
   
   public Elemento(int numero, String nome) {
      this.numero = numero;
      this.nome = nome;
   } 
   
   public int getNumero() {
       return numero;
   }
   
   public void setNumero(int numero) {
       this.numero = numero;
   }
   
   public String getNome() {
       return nome;
   }
   
   public void setNome(String nome) {
       this.nome = nome;
   }
}
