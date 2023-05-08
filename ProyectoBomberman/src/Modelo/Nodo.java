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
    String letra;
    String estado;
    int coordenadaX;
    int coordenadaY;
    LinkedList<Object> listaAdyacencia;
    
    //**Constructor**
    public Nodo(String id, String estado, int coordenadaX, int coordenadaY){
        this.id = id;
        valor = -1;
        letra = "";
        this.estado = estado;
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
        listaAdyacencia = new LinkedList();
    }
    
    public Nodo(String id){
        this.id = id;
        valor = -1;
        letra = "";
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
        Nodo nodoDos = (Nodo) o;
        return id.equals(nodoDos.id);
    }
    
    /**
     * Indica el valor que identificará este nodo
     * @param valor el nuevo valor
     */
    public void setValor(int valor){
        this.valor = valor;
    }
    
    public void setLetra(String letra){
        this.letra = letra;
    }
    
    public String getLetra(){
        return letra;
    }
    
    /**
     * Devuelve el valor del nodo
     * @return valor del nodo
     */
    public int getValor(){
        return valor;
    }
    
    /**
     * Devuelve el id del nodo
     * @return id del nodo
     */
    public String getId(){
        return id;
    }

    /**
     * Devuelve el estado del nodo
     * @return estado C,M o R
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Devuelve la coordenada x del nodo
     * @return coordenada x
     */
    public int getCoordenadaX() {
        return coordenadaX;
    }

    /**
     * Devuelve la coordenada y del nodo
     * @retur coordenada y
     */
    public int getCoordenadaY() {
        return coordenadaY;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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
