package Comportamientos;

import Agentes.Bomberman;
import Modelo.Nodo;
import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import java.util.ArrayList;
import java.util.List;

/**
 * Proyecto Sistemas Inteligentes I Clase ComportamientoBomberman
 *
 * @author Diego Alejandro Parra Medina
 * @author Juan Bernardo Henao Orozco Universidad de Caldas, 2023-1
 * @version 0.1
 */
public class ComportamientoBomberman extends CyclicBehaviour {

    Bomberman miAgente;

    public ComportamientoBomberman(Bomberman miAgente) {
        this.miAgente = miAgente;
    }

    @Override
    public void action() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        ACLMessage mensaje = new ACLMessage(ACLMessage.INFORM);
        mensaje.addReceiver(new AID("fantasma", AID.ISLOCALNAME));
        Nodo actual = miAgente.principal.getGrafo().getInicio();
        Nodo destino = miAgente.principal.getGrafo().getSalida();
        List<Nodo> lista = new ArrayList<>();
        lista = miAgente.principal.getGrafo().hillClimbing(actual, destino, "1");
        if (actual.equals(destino)) {
            //Termina
        } else if (lista.size() > 1) {
            miAgente.principal.getGrafo().setInicio(lista.get(1));
            miAgente.principal.repaint();
        }
        /*
        try {
            mensaje.setContentObject(miAgente.principal);
        } catch (Exception e) {
        }
        this.miAgente.send(mensaje);
        ACLMessage mensajeRespuesta = this.miAgente.blockingReceive();
        if (mensajeRespuesta != null) {
            try {
                miAgente.principal = (Principal) mensaje.getContentObject();
            } catch (Exception e) {
            }
        }
*/
    }
}
