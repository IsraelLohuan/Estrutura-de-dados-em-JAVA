/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author israel.lohuan
 */
abstract class ListaLigada<T extends Elemento> {
    T inicio;
    
    abstract public boolean push(T e);
    
    public T pop() {
        if(inicio == null) {
            return null;
        }
        
        T e = inicio;
        inicio = (T) e.getProximo();
        e.setProximo(null);
        return e;
    }
}
