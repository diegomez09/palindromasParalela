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
public class ExecutorPalindroma {

    public String[] palabras;
    public static String texto;
    Integer contadorPalabras = 0;
    Long inicio, total;
    ExecutorService executor = Executors.newFixedThreadPool(10);

    ExecutorPalindroma(String[] palabrasLlegan) throws InterruptedException, ExecutionException {
        this.palabras = palabrasLlegan;
        //System.out.println(Runtime.getRuntime().availableProcessors());
        ExecutorService es = Executors.newFixedThreadPool(12);
        Future<String> totalPalabras;        
        for (int i = 0; i < palabrasLlegan.length; i++) {
            inicio = System.currentTimeMillis();
            totalPalabras = es.submit(new Palindromo(palabras[i]));
            total = System.currentTimeMillis();
            if (!"".equals(totalPalabras.get())) {
                contadorPalabras++;
            }            
        }
        es.shutdown();
        es.awaitTermination(1, TimeUnit.DAYS);
        this.texto = (total - inicio + " milisegundos // " + contadorPalabras + " palabras Executor");
    }

    private class Palindromo implements Callable<String> {
        String palabra;
        Palindromo(String palabra) {
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
            if (!bError) {
                return palabra;
            } else {
                return "";
            }
        }
    }
}
