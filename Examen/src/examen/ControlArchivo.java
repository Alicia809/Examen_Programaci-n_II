/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 *
 * @author Alicia Ulloa
 */
public class ControlArchivo {
    public static int BuscarPalabra(String texto, String palabraBuscada){
        int posicionInicial = 0;
        int longitud = 0;
        int cantidadPalabra = 0;
        char[] cadenaPorCaracter=texto.toCharArray();

        for (int i = 0; i < texto.length(); i++)
        {
            longitud++;
            if (cadenaPorCaracter[i] == ' ' || i == texto.length() - 1)
            {
                if ( texto.substring(posicionInicial, longitud).trim().toUpperCase().equals(palabraBuscada.trim().toUpperCase()))
                {
                    cantidadPalabra++;
                }
                posicionInicial = i;
            }
        }

        return cantidadPalabra;
    }

    public static void buscarPalabraEnArchivo(String direccionArchivo, String palabraBuscada, JTextArea textArea){
        boolean existe = false;
        int nroLinea=0;
        int totalPalabrasEncontradas = 0;
      
        File archivo;
        FileReader leerArchivo=null;
            
        try
        {
            archivo = new File (direccionArchivo);
        
            leerArchivo = new FileReader (archivo);
            BufferedReader memoriaParaLectura = new BufferedReader(leerArchivo);
        
            String textoLinea=null;
        
            textArea.setText("");
            while((textoLinea = memoriaParaLectura.readLine())!=null)
            {
                nroLinea++;
                int cantidadPalabra = BuscarPalabra(textoLinea, palabraBuscada);
              
                if (cantidadPalabra > 0)
                {
                    textArea.append("Cantidad de palabras: [" + cantidadPalabra + "]\n");
                    textArea.append("En La línea: [" + nroLinea + "]\n");
                    textArea.append("Texto de la Línea: [" + textoLinea + "]\n\n");

                    totalPalabrasEncontradas += cantidadPalabra;
                    existe = true;
                }
            }
            if (!existe)
            {
                JOptionPane.showMessageDialog(null, "Palabra no encontrada");
            }
            else
            {
                textArea.append("Cantidad total de palabras encontradas: [" + totalPalabrasEncontradas + "]\n");
            }
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        finally
        {
            try
            {
                if (null != leerArchivo)
                {
                    leerArchivo.close();
                }
            }
            catch (Exception ex1)
            {
                JOptionPane.showMessageDialog(null, ex1.getMessage());
            }
        }
    }
}
