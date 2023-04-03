package Modelo;

import java.util.Scanner;

/**
 * Proyecto Sistemas Inteligentes I
 * Clase Main
 * @author Diego Alejandro Parra Medina
 * @author Juan Bernardo Henao Orozco
 * Universidad de Caldas, 2023-1
 * @version 0.1
 */
public class Main {

    public static void main(String[] args) {
        
        Scanner entradaEscaner = new Scanner (System.in);
        
        String entradaTipo = ""; //Informada o No informada
        String entradaAlgoritmo = ""; //Algoritmo especifico
        String entradaHeuristica = ""; //En algunos caso la Heurística
        boolean salir = false; //Para finalizar los while
        
        do{
            System.out.println ("Menú Principal");
            System.out.println ("1. Búsqueda No informada");
            System.out.println ("2. Búsqueda informada");

            entradaTipo = entradaEscaner.nextLine();
            
            switch (entradaTipo) {
                case "1":
                    do{
                        System.out.println ("Búsqueda No informada");
                        System.out.println ("1. Anchura ");
                        System.out.println ("2. Profundidad");
                        System.out.println ("3. Costo Uniforme");
                        
                        entradaAlgoritmo = entradaEscaner.nextLine();
                        
                        switch (entradaAlgoritmo) {
                            case "1":
                                System.out.println("anchu");
                                salir = true;
                                break;
                            case "2":
                                System.out.println("prof");
                                salir = true;
                                break;
                            case "3":
                                System.out.println("cos uni");
                                salir = true;
                                break;
                            default:
                                System.out.println("Por favor ingresar una opción valida del menú");
                        }
                    }while(!salir);
                    break;
                    
                case "2":
                    do{
                        System.out.println ("Búsqueda informada");
                        System.out.println ("1. Beam Search");
                        System.out.println ("2. Hill climbing");
                        System.out.println ("3. A estrella ");
                        
                        entradaAlgoritmo = entradaEscaner.nextLine();
                        
                        switch (entradaAlgoritmo) {
                            case "1":
                                do{
                                    System.out.println ("Seleccionar Heurística");
                                    System.out.println ("1. Manhattan");
                                    System.out.println ("2. Euclidiana");
                                    
                                    entradaHeuristica = entradaEscaner.nextLine();
                                    switch (entradaHeuristica) {
                                        case "1":
                                            System.out.println("beams - manhat");
                                            salir = true;
                                            break;
                                            
                                        case "2":
                                            System.out.println("beams - euc");
                                            salir = true;
                                            break;
                                        default:
                                            System.out.println("Por favor ingresar una opción valida del menú");
                                    }
                                }while(!salir);
                                break;
                                
                            case "2":
                                do{
                                    System.out.println ("Seleccionar Heurística");
                                    System.out.println ("1. Manhattan");
                                    System.out.println ("2. Euclidiana");
                                    
                                    entradaHeuristica = entradaEscaner.nextLine();
                                    switch (entradaHeuristica) {
                                        case "1":
                                            System.out.println("hillc - manhat");
                                            salir = true;
                                            break;
                                            
                                        case "2":
                                            System.out.println("hillc - euc");
                                            salir = true;
                                            break;
                                        default:
                                            System.out.println("Por favor ingresar una opción valida del menú");
                                    }
                                }while(!salir);
                                break;
                            case "3":
                                do{
                                    System.out.println ("Seleccionar Heurística");
                                    System.out.println ("1. Manhattan");
                                    System.out.println ("2. Euclidiana");
                                    
                                    entradaHeuristica = entradaEscaner.nextLine();
                                    switch (entradaHeuristica) {
                                        case "1":
                                            System.out.println("aestr - manhat");
                                            salir = true;
                                            break;
                                            
                                        case "2":
                                            System.out.println("aestr - euc");
                                            salir = true;
                                            break;
                                        default:
                                            System.out.println("Por favor ingresar una opción valida del menú");
                                    }
                                }while(!salir);
                                break;
                            default:
                                System.out.println("Por favor ingresar una opción valida del menú");
                        }
                    }while(!salir);
                    break;
                    
                default:
                    System.out.println("Por favor ingresar una opción valida del menú");
            }
        }while(!salir);
        
        System.out.println(entradaTipo + entradaAlgoritmo + entradaHeuristica);
        
    }
    
}
