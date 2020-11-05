/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package palindroma;

/**
 *
 * @author User
 */
public class ExecutorPalindroma implements Runnable {

    public String[] palabras = new String[50];
    public static String texto;
    Integer contadorPalabras = 0;
    Long inicio, total;

    public ExecutorPalindroma(String[] palabrasLllegan) {
        this.palabras = palabrasLllegan;
        inicio = System.currentTimeMillis();
        run();
        total = System.currentTimeMillis();
        System.out.println(total - inicio + " milisegundos // " + contadorPalabras + " palabras Fork/Join");
        texto = (total - inicio + " milisegundos // " + contadorPalabras + " palabras Fork/Join");
        contadorPalabras = 0;
    }

    public String setTextFork() {
        return texto;
    }

    @Override
    public void run() {
        palindromas();
    }

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

}
