package Agentes;

import Comportamientos.ComportamientoBomberman;
import Vista.Principal;
import jade.core.Agent;

/**
 * Proyecto Sistemas Inteligentes I
 * Clase Bomberman
 * @author Diego Alejandro Parra Medina
 * @author Juan Bernardo Henao Orozco
 * Universidad de Caldas, 2023-1
 * @version 0.1
 */
public class Bomberman extends Agent{
    
    public Principal principal;
    
    public Bomberman(Principal principal){
        this.principal = principal;
    }
    
    public void setup(){
        addBehaviour(new ComportamientoBomberman(this));
    }
}
