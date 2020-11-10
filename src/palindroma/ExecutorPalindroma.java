/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package palindroma;

import static java.lang.Compiler.command;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author User
 */
public class ExecutorPalindroma implements Runnable {

    public String[] palabras = new String[50];
    public static String texto;
    Integer contadorPalabras = 0;
    Long inicio, total;
    ExecutorService executor = Executors.newFixedThreadPool(10);

    public ExecutorPalindroma(String[] palabrasLllegan) {
        inicio = System.currentTimeMillis();
        this.palabras = palabrasLllegan;
        //executor.execute(command);
        run();
        total = System.currentTimeMillis();
        //System.out.println(total - inicio + " milisegundos // " + contadorPalabras + " palabras Fork/Join");
        texto = (total - inicio + " milisegundos // " + contadorPalabras + " palabras Executor");
        contadorPalabras = 0;
    }

    public String setTextExe() {
        return texto;
    }

    Runnable command = new Runnable() {
        @Override
        public void run() {
            for (int i = 0; i < palabras.length; i++) {
                int inc = 0;
                int des = palabras[i].length() - 1;
                boolean bError = false;

                while ((inc < des) && (!bError)) {

                    if (palabras[i].charAt(inc) == palabras[i].charAt(des)) {
                        inc++;
                        des--;
                    } else {
                        bError = true;
                    }
                }

                if (!bError) {
                    contadorPalabras++;
                    //System.out.println(contadorPalabras);
                    // System.out.println(palabras[i] + " forkJoin");
                } //else {
                //System.out.println(palabras[i] + " NO es un palindromo");
                //}
            }
        }
    };

    public void palindromas() {
        for (int i = 0; i < palabras.length; i++) {
            int inc = 0;
            int des = palabras[i].length() - 1;
            boolean bError = false;

            while ((inc < des) && (!bError)) {

                if (palabras[i].charAt(inc) == palabras[i].charAt(des)) {
                    inc++;
                    des--;
                } else {
                    bError = true;
                }
            }

            if (!bError) {
                contadorPalabras++;
                //System.out.println(contadorPalabras);
                // System.out.println(palabras[i] + " forkJoin");
            } //else {
            //System.out.println(palabras[i] + " NO es un palindromo");
            //}
        }
    }

    @Override
    public void run() {
        palindromas();
    }

}
