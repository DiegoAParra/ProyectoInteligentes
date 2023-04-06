package Modelo;

import java.util.LinkedList;

/**
 * Proyecto Sistemas Inteligentes I
 * Clase Nodo
 * @author Diego Alejandro Parra Medina
 * @author Juan Bernardo Henao Orozco
 * Universidad de Caldas, 2023-1
 * @version 0.1
 */
public class Nodo {
    
    //**Atributos**
    String id;
    int valor;
    String estado;
    int coordenadaX;
    int coordenadaY;
    LinkedList<Object> listaAdyacencia;
    
    //**Constructor**
    public Nodo(String id, String estado, int coordenadaX, int coordenadaY){
        this.id = id;
        valor = -1;
        this.estado = estado;
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
        listaAdyacencia = new LinkedList();
    }
    
    public Nodo(String id){
        this.id = id;
        valor = -1;
        listaAdyacencia = new LinkedList();
    }
    
    //**Métodos**
    /**
     * Devuelve verdadero si dos nodos tienen la misma id
     * @param o objeto que es un Nodo
     * @return falso si no son iguales, verdadero en caso de que si
     */
    @Override
    public boolean equals(Object o){
        Nodo nodoNos = (Nodo) o;
        return id.equals(nodoNos.id);
    }
    
    /**
     * Indica el valor que identificará este nodo
     * @param valor el nuevo valor
     */
    public void setValor(int valor){
        this.valor = valor;
    }
    
    /**
     * Devuelve el id del nodo
     * @return id del nodo
     */
    public String getId(){
        return id;
    }
    
    /**
     * Método toString para imprimir un nodo
     * @return el id y valor del nodo
     */
    @Override
    public String toString(){
        return id + "(" + estado + " - " + valor + ")";
    }
}
