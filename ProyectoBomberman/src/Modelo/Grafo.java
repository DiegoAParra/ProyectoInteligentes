package Modelo;

import java.util.LinkedList;

/**
 * Proyecto Sistemas Inteligentes I
 * Clase Grafo
 * @author Diego Alejandro Parra Medina
 * @author Juan Bernardo Henao Orozco
 * Universidad de Caldas, 2023-1
 * @version 0.1
 */
public class Grafo {
    
    //**Atributos**
    int numeroNodos;
    static int maxNodos = 20;
    Nodo[] tablaAdyacencia;
    
    //**Constructor**
    public Grafo(int mn){
        numeroNodos = 0;
        maxNodos = mn;
        tablaAdyacencia = new Nodo[mn];
    }
    
    //**Métodos**
    /**
     * Devuelve la lista de adyacencia de un nodo
     * @param n la posición del nodo en la tabla de adyacencia
     * @return la lista de adyacencia de un nodo
     * @throws Exception nodo fuera de rango
     */
    public LinkedList listaAdyacencia(int n) throws Exception{
        if(n < 0 || n >= numeroNodos){
            throw new Exception("Nodo fuera de rango");
        }
        return tablaAdyacencia[n].listaAdyacencia;
    }

    /**
     * Busca y devuelve la posición del nodo en la tabla de adyacencia, si no regresa -1
     * @param id del nodo
     * @return la posición del nodo en la tabla de adyacencia, si no regresa -1
     */
    public int numNodo(String id){
        Nodo n = new Nodo(id);
        boolean encontrado = false;
        int i = 0;
        for(;(i < numeroNodos) && !encontrado;){
            encontrado = tablaAdyacencia[i].equals(n);
            if(!encontrado){
                i++;
            }
        }
        return (i < numeroNodos) ? i : -1;
    }

    /**
     * Crea un nuevo Nodo
     * @param id identificador del nodo
     */
    public void nuevoNodo(String id){
        boolean existe = numNodo(id) >= 0;
        if(!existe){
            Nodo n = new Nodo(id);
            n.setValor(numeroNodos);
            tablaAdyacencia[numeroNodos++] = n;
        }
    }
    
    //Comprueba si dos nodos son adyacentes
    boolean adyacente(String a, String b) throws Exception{
        int n1, n2;
        n1 = numNodo(a);
        n2 = numNodo(b);
        if(n1 < 0 || n2 < 0){
            throw new Exception("El nodo no existe");
        }
        if(tablaAdyacencia[n1].listaAdyacencia.contains(new Arista(n2))){
            return true;
        } else {
            return false;
        }
    }
    
    //Comprueba si dos nodos son adyacentes por el número de nodo
    boolean adyacente(int n1, int n2) throws Exception{
        if(tablaAdyacencia[n1].listaAdyacencia.contains(new Arista(n2))){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Crea una nueva arista
     * @param a id del nodo origen
     * @param b id del nodo destino
     * @throws Exception el nodo no existe
     */
    public void nuevaArista(String a, String b) throws Exception{
        if(!adyacente(a, b)){
            int n1 = numNodo(a);
            int n2 = numNodo(b);
            if(n1 < 0 || n2 < 0){
                throw new Exception("El nodo no existe");
            }
            Arista ab = new Arista(n2);
            tablaAdyacencia[n1].listaAdyacencia.addFirst(ab);
        }
    }

    /**
     * Borra una arista
     * @param a id del nodo origen
     * @param b id del nodo destino
     * @throws Exception el nodo no existe
     */
    public void borrarArco(String a, String b) throws Exception{
        int n1 = numNodo(a);
        int n2 = numNodo(b);
        if(n1 < 0 || n2 < 0){
            throw new Exception("El nodo no existe");
        }
        Arista ab = new Arista(n2);
        tablaAdyacencia[n1].listaAdyacencia.remove(ab);
    }
    
    /**
     * Devuelve la tabla de adyacencia
     * @return la tabla de adyacencia
     */
    public Nodo[] getTablaAdyacencia(){
        return tablaAdyacencia;
    } 
}