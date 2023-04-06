package Modelo;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Proyecto Sistemas Inteligentes I
 * Clase Carga
 * @author Diego Alejandro Parra Medina
 * @author Juan Bernardo Henao Orozco
 * Universidad de Caldas, 2023-1
 * @version 0.1
 */
public class Carga {

    static File fichero;
            
    public Carga() {
    
    }

    public Grafo cargarTXT() {
        //Creamos el objeto JFileChooser
        JFileChooser fc = new JFileChooser();
        fc.showOpenDialog(fc);
        //Creamos el filtro
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.TXT", "txt");
        //Le indicamos el filtro
        fc.setFileFilter(filtro);
        //Seleccionamos el fichero
        this.fichero = fc.getSelectedFile();

        int contador = 0; //contador para saber cuantos nodos tendra el grafo
        
        try (FileReader fr = new FileReader(fichero)) {
            Scanner scanner;
            scanner = new Scanner(fr);
            
            while (scanner.hasNextLine()) {

                //aqui tenemos la linea del texto a leer Nodos
                String linea = scanner.nextLine();
                String[] listaLinea = linea.split(",");
                for (int i = 0; i < listaLinea.length; i++) {
                    contador++;
                }

                Scanner delimitar = new Scanner(linea);
                delimitar.useDelimiter("\n");
                delimitar.close();
            }
        } catch (IOException e1) {
            System.out.println("El archivo no se encontró");
            return null;
        } catch (NumberFormatException e2) {
            System.out.println("El archivo no puede contener letras");
            return null;
        }
        
        Grafo grafo = new Grafo(contador);
        
        try (FileReader fr = new FileReader(fichero)) {
            Scanner scanner;
            scanner = new Scanner(fr);
            
            
            int coorX = 0;
            int coorY = 0;
            
            while (scanner.hasNextLine()) {

                //Aqui tenemos la linea del texto a leer Nodos
                String linea = scanner.nextLine();
                String[] listaLinea = linea.split(",");
                for (int i = 0; i < listaLinea.length; i++) {
                    grafo.nuevoNodo(coorX+","+coorY, listaLinea[i], coorX, coorY);
                    
                    //Conectar con los de arriba
                    try{
                        grafo.nuevaArista(coorX+","+coorY, coorX+","+(coorY-1));
                        grafo.nuevaArista(coorX+","+(coorY-1), coorX+","+coorY);
                    } catch(Exception e){
                        //No imprimir nada, pero si salen excepciones
                    }
                    //Conectar con los de la izquierda
                    try{                        
                        grafo.nuevaArista((coorX-1)+","+coorY, coorX+","+coorY);
                        grafo.nuevaArista(coorX+","+coorY, (coorX-1)+","+coorY);
                    } catch(Exception e){
                        //No imprimir nada, pero si salen excepciones
                    }
                    
                    coorX++;
                }
                coorX = 0;
                coorY++;
                Scanner delimitar = new Scanner(linea);
                delimitar.useDelimiter("\n");
                delimitar.close();
            }
        return grafo;
        } catch (IOException e1) {
            System.out.println("El archivo no se encontró");
            return null;
        } catch (NumberFormatException e2) {
            System.out.println("El archivo no puede contener letras");
            return null;
        }
    }
    
    public int calculoN(){
        int n = 0; //contador para saber cuantos nodos tendra el grafo
        
        try (FileReader fr = new FileReader(fichero)) {
            Scanner scanner;
            scanner = new Scanner(fr);
            
            while (scanner.hasNextLine()) {

                //aqui tenemos la linea del texto a leer Nodos
                String linea = scanner.nextLine();
                String[] listaLinea = linea.split(",");
                for (int i = 0; i < listaLinea.length; i++) {
                    n++;
                }
                return n;
            }
        return n;
        } catch (IOException e1) {
            System.out.println("El archivo no se encontró");
            return 0;
        } catch (NumberFormatException e2) {
            System.out.println("El archivo no puede contener letras");
            return 0;
        }
    }
    
    public int calculoM(){
        int m = 0; //contador para saber cuantos nodos tendra el grafo
        
        try (FileReader fr = new FileReader(fichero)) {
            Scanner scanner;
            scanner = new Scanner(fr);
            
            while (scanner.hasNextLine()) {
                //aqui tenemos la linea del texto a leer Nodos
                String linea = scanner.nextLine();
                Scanner delimitar = new Scanner(linea);
                delimitar.useDelimiter("\n");
                delimitar.close();
                m++;
            }
        return m;
        } catch (IOException e1) {
            System.out.println("El archivo no se encontró");
            return 0;
        } catch (NumberFormatException e2) {
            System.out.println("El archivo no puede contener letras");
            return 0;
        }
    }
}