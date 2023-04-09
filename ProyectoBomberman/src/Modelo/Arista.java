package Modelo;

/**
 * Proyecto Sistemas Inteligentes I
 * Clase Arista
 * @author Diego Alejandro Parra Medina
 * @author Juan Bernardo Henao Orozco
 * Universidad de Caldas, 2023-1
 * @version 0.1
 */
public class Arista {
    
    //**Atributos**
    int destino;
    int peso;
    
    //**Constructor**
    public Arista(int destino){
        this.destino = destino;
    }
    
    public Arista(int destino, int peso){
        this.destino = destino;
        this.peso = peso;
    }
    
    //**Métodos**
    /**
     * Devuelve verdadero si dos aristas son iguales
     * @param o objeto que es una arista
     * @return falso si no son iguales, verdadero en caso de que si
     */
    @Override
    public boolean equals(Object o){
        Arista a = (Arista) o;
        return destino == a.destino;
    }
    
    /**
     * Devuelve el valor del nodo destino
     * @return el valor del nodo destino
     */
    public int getDestino(){
        return destino;
    }
    
    /**
     * Devuelve el peso del nodo destino
     * @return el peso del nodo destino
     */
    public int getPeso(){
        return peso;
    }
}
