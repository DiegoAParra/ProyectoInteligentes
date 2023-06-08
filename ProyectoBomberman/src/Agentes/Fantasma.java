package Agentes;

import Comportamientos.ComportamientoFantasma;
import Vista.Principal;
import jade.core.Agent;

/**
 * Proyecto Sistemas Inteligentes I
 * Clase Fantasma
 * @author Diego Alejandro Parra Medina
 * @author Juan Bernardo Henao Orozco
 * Universidad de Caldas, 2023-1
 * @version 0.1
 */
public class Fantasma extends Agent{
    
    public void setup(){
        addBehaviour(new ComportamientoFantasma(this));
    }
}