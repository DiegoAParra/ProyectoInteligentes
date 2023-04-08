package Modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Proyecto Sistemas Inteligentes I Clase Main
 *
 * @author Diego Alejandro Parra Medina
 * @author Juan Bernardo Henao Orozco Universidad de Caldas, 2023-1
 * @version 0.1
 */
public class Main {

    public static void main(String[] args) {

        Scanner entradaEscaner = new Scanner(System.in);

        Carga carga = new Carga();
        Grafo grafo = carga.cargarTXT();

        //Calculo n y m
        int m = carga.calculoM();

        //Imprimir mapa
        for (int i = 0; i < m; i++) {
            for (Nodo nodo : grafo.getTablaAdyacencia()) {
                if (nodo.getCoordenadaY() == i) {
                    System.out.print("| ");
                    System.out.print(nodo.getEstado() + " ");
                }
            }
            System.out.println("|" + "\n");
        }

        /*
        //Imprimir nodos
        for (Nodo nodo : grafo.getTablaAdyacencia()) {
            System.out.println(nodo.toString());
        }
         */
        //Pedimos el nodo inicio y salida del grafo
        String entradaInicio = ""; //id del nodo inicio
        String entradaSalida = ""; //id del nodo salida

        do {
            System.out.println("Id del nodo inicio: ");
            entradaInicio = entradaEscaner.nextLine(); //4,0
        } while (grafo.numNodo(entradaInicio) < 0 && !"C".equals(grafo.tablaAdyacencia[grafo.numNodo(entradaInicio)].estado));

        do {
            System.out.println("Id del nodo salida: "); //6,0
            entradaSalida = entradaEscaner.nextLine();
        } while (grafo.numNodo(entradaSalida) < 0 && !"C".equals(grafo.tablaAdyacencia[grafo.numNodo(entradaSalida)].estado));

        grafo.setInicio(grafo.tablaAdyacencia[grafo.numNodo(entradaInicio)]);
        grafo.setSalida(grafo.tablaAdyacencia[grafo.numNodo(entradaSalida)]);

        //Imprimir la tabla de adyacencia
        /*
        for(int i = 0; i < grafo.tablaAdyacencia.length; i++){
            for(int j = 0; j < grafo.tablaAdyacencia[i].listaAdyacencia.size(); j++){
                Arista a = (Arista) grafo.tablaAdyacencia[i].listaAdyacencia.get(j);
                System.out.println(grafo.tablaAdyacencia[i].toString() + " --> " + grafo.tablaAdyacencia[a.getDestino()].toString());
            }
        }
         */
        //Pedir el algoritmo
        String entradaTipo = ""; //Informada o No informada
        String entradaAlgoritmo = ""; //Algoritmo especifico
        String entradaHeuristica = ""; //En algunos caso la Heurística
        boolean salir = false; //Para finalizar los while

        do {
            System.out.println("Menú Principal");
            System.out.println("1. Búsqueda No informada");
            System.out.println("2. Búsqueda informada");

            entradaTipo = entradaEscaner.nextLine();

            switch (entradaTipo) {
                case "1":
                    do {
                        System.out.println("Búsqueda No informada");
                        System.out.println("1. Anchura ");
                        System.out.println("2. Profundidad");
                        System.out.println("3. Costo Uniforme");

                        entradaAlgoritmo = entradaEscaner.nextLine();

                        switch (entradaAlgoritmo) {
                            case "1":
                                System.out.println("* * * Anchura * * *");
                                List<Nodo> listaVisitadosA = new ArrayList<>();
                                listaVisitadosA = grafo.anchura(grafo.tablaAdyacencia[grafo.numNodo(entradaInicio)], grafo.tablaAdyacencia[grafo.numNodo(entradaSalida)]);
                                grafo.imprimirListaVisitados(listaVisitadosA, m);
                                salir = true;
                                break;
                            case "2":
                                System.out.println("* * * Profundidad * * *");
                                List<Nodo> listaVisitadosP = new ArrayList<>();
                                listaVisitadosP = grafo.profundidad(grafo.tablaAdyacencia[grafo.numNodo(entradaInicio)], grafo.tablaAdyacencia[grafo.numNodo(entradaSalida)], listaVisitadosP);
                                grafo.imprimirListaVisitados(listaVisitadosP, m);
                                salir = true;
                                break;
                            case "3":
                                System.out.println("* * * Costo Uniforme * * *");
                                List<Nodo> listaVisitadosCU = new ArrayList<>();
                                listaVisitadosCU = grafo.costoUniforme(grafo.tablaAdyacencia[grafo.numNodo(entradaInicio)], grafo.tablaAdyacencia[grafo.numNodo(entradaSalida)]);
                                grafo.imprimirListaVisitados(listaVisitadosCU, m);
                                salir = true;
                                break;
                            default:
                                System.out.println("Por favor ingresar una opción valida del menú");
                        }
                    } while (!salir);
                    break;

                case "2":
                    do {
                        System.out.println("Búsqueda informada");
                        System.out.println("1. Beam Search");
                        System.out.println("2. Hill climbing");
                        System.out.println("3. A estrella ");

                        entradaAlgoritmo = entradaEscaner.nextLine();

                        switch (entradaAlgoritmo) {
                            case "1":
                                do {
                                    System.out.println("Seleccionar Heurística");
                                    System.out.println("1. Manhattan");
                                    System.out.println("2. Euclidiana");

                                    entradaHeuristica = entradaEscaner.nextLine();
                                    switch (entradaHeuristica) {
                                        case "1":
                                            System.out.println("* * * Beam Search * * *");
                                            System.out.println("Heurística: Manhattan");
                                            List<Nodo> listaVisitadosBSM = new ArrayList<>();
                                            listaVisitadosBSM = grafo.beamSearch(grafo.tablaAdyacencia[grafo.numNodo(entradaInicio)], grafo.tablaAdyacencia[grafo.numNodo(entradaSalida)], entradaHeuristica);
                                            grafo.imprimirListaVisitados(listaVisitadosBSM, m);
                                            salir = true;
                                            break;

                                        case "2":
                                            System.out.println("* * * Beam Search * * *");
                                            System.out.println("Heurística: Euclidiana");
                                            List<Nodo> listaVisitadosBSE = new ArrayList<>();
                                            listaVisitadosBSE = grafo.beamSearch(grafo.tablaAdyacencia[grafo.numNodo(entradaInicio)], grafo.tablaAdyacencia[grafo.numNodo(entradaSalida)], entradaHeuristica);
                                            grafo.imprimirListaVisitados(listaVisitadosBSE, m);
                                            salir = true;
                                            break;
                                        default:
                                            System.out.println("Por favor ingresar una opción valida del menú");
                                    }
                                } while (!salir);
                                break;

                            case "2":
                                do {
                                    System.out.println("Seleccionar Heurística");
                                    System.out.println("1. Manhattan");
                                    System.out.println("2. Euclidiana");

                                    entradaHeuristica = entradaEscaner.nextLine();
                                    switch (entradaHeuristica) {
                                        case "1":
                                            System.out.println("* * * Hill Climbing * * *");
                                            System.out.println("Heurística: Manhattan");
                                            List<Nodo> listaVisitadosHCM = new ArrayList<>();
                                            listaVisitadosHCM = grafo.hillClimbing(grafo.tablaAdyacencia[grafo.numNodo(entradaInicio)], grafo.tablaAdyacencia[grafo.numNodo(entradaSalida)], entradaHeuristica);
                                            grafo.imprimirListaVisitados(listaVisitadosHCM, m);
                                            salir = true;
                                            break;

                                        case "2":
                                            System.out.println("* * * Hill Climbing * * *");
                                            System.out.println("Heurística: Euclidiana");
                                            List<Nodo> listaVisitadosHCE = new ArrayList<>();
                                            listaVisitadosHCE = grafo.hillClimbing(grafo.tablaAdyacencia[grafo.numNodo(entradaInicio)], grafo.tablaAdyacencia[grafo.numNodo(entradaSalida)], entradaHeuristica);
                                            grafo.imprimirListaVisitados(listaVisitadosHCE, m);
                                            salir = true;
                                            break;
                                        default:
                                            System.out.println("Por favor ingresar una opción valida del menú");
                                    }
                                } while (!salir);
                                break;
                            case "3":
                                do {
                                    System.out.println("Seleccionar Heurística");
                                    System.out.println("1. Manhattan");
                                    System.out.println("2. Euclidiana");

                                    entradaHeuristica = entradaEscaner.nextLine();
                                    switch (entradaHeuristica) {
                                        case "1":
                                            System.out.println("* * * A Estrella * * *");
                                            System.out.println("Heurística: Manhattan");
                                            List<Nodo> listaVisitadosAEM = new ArrayList<>();
                                            listaVisitadosAEM = grafo.aEstrella(grafo.tablaAdyacencia[grafo.numNodo(entradaInicio)], grafo.tablaAdyacencia[grafo.numNodo(entradaSalida)], entradaHeuristica);
                                            grafo.imprimirListaVisitados(listaVisitadosAEM, m);
                                            salir = true;
                                            break;

                                        case "2":
                                            System.out.println("* * * A Estrella * * *");
                                            System.out.println("Heurística: Euclidiana");
                                            List<Nodo> listaVisitadosAEE = new ArrayList<>();
                                            listaVisitadosAEE = grafo.aEstrella(grafo.tablaAdyacencia[grafo.numNodo(entradaInicio)], grafo.tablaAdyacencia[grafo.numNodo(entradaSalida)], entradaHeuristica);
                                            grafo.imprimirListaVisitados(listaVisitadosAEE, m);
                                            salir = true;
                                            break;
                                        default:
                                            System.out.println("Por favor ingresar una opción valida del menú");
                                    }
                                } while (!salir);
                                break;
                            default:
                                System.out.println("Por favor ingresar una opción valida del menú");
                        }
                    } while (!salir);
                    break;

                default:
                    System.out.println("Por favor ingresar una opción valida del menú");
            }
        } while (!salir);
    }
}
