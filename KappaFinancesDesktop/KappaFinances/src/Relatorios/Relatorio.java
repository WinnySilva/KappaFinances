package Relatorios;

import Financas.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observer;
import java.util.Random;
import javafx.application.Application;
import javafx.scene.chart.Chart;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Winny S
 */
public abstract class Relatorio extends Application implements Observer  {
    protected String titulo ="Despesas";
    protected Contabilidade contas;
    protected ArrayList<Dado> dados;
    protected class Dado{
        public String categoria_s;
        public int categeria_i;
        public double total;
        Dado(String s, int i, double t){
            this.categeria_i=i;
            this.categoria_s=s;
            this.total=t;
        }
    }
        
    protected enum despesas {
        vestuario, energia   
    }
    Relatorio(Contabilidade con){
        this.contas = con;
    }
    Relatorio(){
        Random r = new Random();
      dados = new ArrayList();
      for(int i=0;i<10;i++){
        
          dados.add(new Dado("Tenso"+r.nextInt(200),8,r.nextInt(700) ));
      }
    }
    public abstract Chart  geraGrafico();
    /**
     * @param financas
     * @return Dado
     */
   protected ArrayList<Dado> mineracao(ArrayList<Financa> financas){
       this.dados = new ArrayList();
       Iterator<Financa> it = financas.iterator();
       Financa aux;
       while(it.hasNext()){
           aux = it.next();
           if( (aux instanceof Receita ) ){
              
               
           }else{
               
           }
       }
       
       
       
       
       return this.dados;
   }
       
        
       
   
   
    
}
