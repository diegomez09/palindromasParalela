/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package palindroma;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;
import static palindroma.Palindroma.SecuenT;
import static palindroma.Palindroma.contadorPalabras;

/**
 *
 * @author User
 */
public final class ForkPalindroma {

    //Palindroma p = new Palindroma();
    public String[] palabras;
    public static String texto;
    public Integer contadorPalabras = 0;
    Long inicio, total;   
    

    public ForkPalindroma(String[] palabrasLlegan) {
        this.palabras = palabrasLlegan;
        List<String> aux = new ArrayList<String>();
        inicio = System.currentTimeMillis();
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(new Palindroma(palabras));
        total = System.currentTimeMillis();
        //contadorPalabras = aux.size();
        for (int i = 0; i < palabrasLlegan.length; i++) {
            if (palabras[i]!="") {
                contadorPalabras++;
            }
        }
        //System.out.println(contadorPalabras);        
        //System.out.println(total - inicio + " milisegundos // " + contadorPalabras + " palabras Fork/Join");
        this.texto = (total - inicio + " milisegundos // " + contadorPalabras + " palabras Fork/Join");
    }
    
    private class Palindroma extends RecursiveAction {
        private String[] palabras;
        private String palabra;
        private int contador;
        private int contadorPalabras;
        ForkPalindroma f;
        private List<String> lista = new ArrayList<String>();

        Palindroma(String[] palabrasLlegan) {
            this(palabrasLlegan, "", -1);
        }
        
        Palindroma(String[] palabrasLlegan, String palabraLlega, int contador) {
            this.palabras = palabrasLlegan;
            //this.contadorPalabras = contadorPalabras;
            this.palabra = palabraLlega;
            this.contador = contador;
        }

        @Override
        public void compute() {
            if (contador == -1) {
                List<Palindroma> tasks = new ArrayList<>();
                for (int i = 0; i < palabras.length; i++) {
                    tasks.add(new Palindroma(palabras, palabras[i], i));
                }
                invokeAll(tasks);
            } else {
                palindromas(palabras, contador);
            }
            //System.out.println(contadorPalabras);
            //return contadorPalabras;
        }

        public void palindromas(String[] lista, int pos) {
            int inc = 0;
            int des = palabra.length() - 1;
            boolean bError = false;

            while ((inc < des) && (!bError)) {
                if (palabra.charAt(inc) == palabra.charAt(des)) {
                    inc++;
                    des--;
                } else {
                    bError = true;
                }
            }
            
            //this.contadorPalabras++;
            //return !bError;
            if (bError) {
                palabras[pos] = "";
            }
                //aux.add(palabra);                
//            else
//                return false;
        } 
    }
}
