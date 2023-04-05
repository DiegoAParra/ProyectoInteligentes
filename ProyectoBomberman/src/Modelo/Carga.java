/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Usuario
 */
public class Carga {

    public Carga() {

    }

    public void presentar() {
        //Creamos el objeto JFileChooser
        JFileChooser fc = new JFileChooser();
        fc.showOpenDialog(fc);
        //Creamos el filtro
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.TXT", "txt");

        //Le indicamos el filtro
        fc.setFileFilter(filtro);
        //Seleccionamos el fichero
        File fichero = fc.getSelectedFile();

        try (FileReader fr = new FileReader(fichero)) {
            Scanner scanner;
            scanner = new Scanner(fr);

            while (scanner.hasNextLine()) {

                //aqui tenemos la linea del texto a leer NODOS
                String linea = scanner.nextLine();
                String[] hola = linea.split(",");
                for (int i = 0; i < hola.length; i++) {
                    System.out.println(hola[i]);

                }

                Scanner delimitar = new Scanner(linea);
                delimitar.useDelimiter("\n");

                delimitar.close();

            }

        } catch (IOException e1) {
            System.out.println("El archivo no se encontró");
        } catch (NumberFormatException e2) {
            System.out.println("El archivo no puede contener letras");

        }
    }
}