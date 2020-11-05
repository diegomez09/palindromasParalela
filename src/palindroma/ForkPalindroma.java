/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package palindroma;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import static palindroma.Palindroma.SecuenT;
import static palindroma.Palindroma.contadorPalabras;

/**
 *
 * @author User
 */
public final class ForkPalindroma extends RecursiveAction {

    Palindroma p = new Palindroma();
    public String[] palabras = new String[50];
    Integer contadorPalabras = 0;
    Long inicio,total;

    public ForkPalindroma(String[] palabrasLllegan) {
        System.out.println(palabrasLllegan.length);
        this.palabras = palabrasLllegan;
        inicio = System.currentTimeMillis();
        compute();
        total = System.currentTimeMillis();
        System.out.println(total - inicio + " milisegundos // " + contadorPalabras + " palabras Fork/Join");
        setTextFork();
        contadorPalabras = 0;
    }
    
    public void setTextFork(){
        SecuenT.setText(total - inicio + " milisegundos // " + contadorPalabras + " palabras Fork/Join");
    }

    protected void compute() {
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
