package Relatorios;

import Financas.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observer;
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
    protected Contabilidade contas;
    protected ArrayList<Dado> dados;
    protected class Dado{
        public String categoria_s;
        public int categeria_i;
        public int total;
    }
        
    protected enum despesas {
        vestuario, energia   
    }
    Relatorio(Contabilidade con){
        this.contas = con;
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
