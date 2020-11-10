/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package palindroma;

import static java.lang.Compiler.command;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author User
 */
public class ExecutorPalindroma{

    public String[] palabras;
    public static String texto;
    Integer contadorPalabras = 0;
    Long inicio, total;
    ExecutorService executor = Executors.newFixedThreadPool(10);

    ExecutorPalindroma(String[] palabrasLlegan) throws InterruptedException, ExecutionException{
        this.palabras = palabrasLlegan;        
        //System.out.println(Runtime.getRuntime().availableProcessors());
        ExecutorService es = Executors.newFixedThreadPool(4);
        Future<String> totalPalabras;
        inicio = System.currentTimeMillis();
        for (int i = 0; i < palabrasLlegan.length; i++) {
            totalPalabras = es.submit(new Palindromo(palabras[i]));
            if (!"".equals(totalPalabras.get())) {
                contadorPalabras++;
            }
        }
        total = System.currentTimeMillis();
        es.shutdown();
        es.awaitTermination(1, TimeUnit.DAYS);        
        this.texto = (total - inicio + " milisegundos // " + contadorPalabras + " palabras Fork/Join");
    }

    private class Palindromo implements Callable<String>{
        String palabra;
        Palindromo(String palabra){
            this.palabra = palabra;
        }

        @Override
        public String call() throws Exception {
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
            if (!bError) {
                return palabra;
            }
            else{
                return "";
            }
        }       
    }
}
