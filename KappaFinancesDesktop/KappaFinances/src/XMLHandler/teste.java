/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XMLHandler;

import Financas.*;
import java.util.Calendar;
import java.util.Random;

/**
 *
 * @author Winny S
 */
public class teste {
    
    public static void main(String args[]) throws Exception{
        Contabilidade x = new Contabilidade();
        Calendar c = Calendar.getInstance();
        Despesa d = new Despesa(c,19.8,11);
        Receita r = new Receita (c,20.99,11);
        Random ran = new Random();
        int it=0;
        for(int i=0;i<1000; i++){
            d = new Despesa(c,ran.nextInt(100),ran.nextInt(12));
            r = new Receita (c,ran.nextInt(100), ran.nextInt(1));
            
            System.out.println(it++);
        //    x.addFinanca(r);
            x.addFinanca(d);
            
            
        }
    }
}
