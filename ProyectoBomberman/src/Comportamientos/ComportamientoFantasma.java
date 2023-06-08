package Comportamientos;

import Vista.Principal;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

/**
 * Proyecto Sistemas Inteligentes I
 * Clase ComportamientoFantasma
 * @author Diego Alejandro Parra Medina
 * @author Juan Bernardo Henao Orozco
 * Universidad de Caldas, 2023-1
 * @version 0.1
 */
public class ComportamientoFantasma extends CyclicBehaviour{ //CyclicBehaviour para el pin pon
    Agent miAgente;

    public ComportamientoFantasma(Agent miAgente) {
        this.miAgente = miAgente;
    }
    
    @Override
    public void action(){
        ACLMessage mensajeRecibido = this.miAgente.blockingReceive();
        if(mensajeRecibido != null){
            try{
                Principal principal = (Principal)mensajeRecibido.getContentObject();
                ACLMessage respuesta = mensajeRecibido.createReply();
                respuesta.setContentObject(principal);
                this.miAgente.send(respuesta);
            } catch (Exception e){}
        } else {}
    }
}
