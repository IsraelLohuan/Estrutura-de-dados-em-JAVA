/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.estudo.estrutura_de_dados.lista_ligada;

/**
 *
 * @author israel.lohuan
 */
public class Pessoa extends Elemento {
   String nome;
   int numero;
   
   public Pessoa(String nome, int numero) {
        this.nome = nome;
        this.numero = numero;
   }
   
   public String getNome() {
       return nome;
   }

   public void setNome(String nome) {
       this.nome = nome;
   }

   public int getNumero() {
       return numero;
   }

   public void setNumero(int numero) {
       this.numero = numero;
   }
}
