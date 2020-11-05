/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package palindroma;

import java.util.concurrent.ForkJoinPool;

/**
 *
 * @author User
 */
public class ForkPalindroma {
    public void count(String[] palabras){
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.invoke(new Palindroma());
    }
    
}
