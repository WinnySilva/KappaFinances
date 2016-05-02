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
    protected ArrayList<Dado> despesas;
    protected ArrayList<Dado> receitas;
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
    Relatorio(Contabilidade con,String title){
        this.contas = con;
        this.titulo = title;
    }
    
    
    Relatorio(Contabilidade con){
        this.contas = con;
       
    }
    
    
    Relatorio(){
        Random r = new Random();
      despesas = new ArrayList();
      for(int i=0;i<10;i++){
        
          despesas.add(new Dado("Tenso"+r.nextInt(200),8,r.nextInt(700) ));
      }
    }
    public abstract Chart  geraGrafico();
    /**
     * @param financas
     * @return Dado
     */
   protected void mineracao(ArrayList<Financa> financas){
       this.despesas = new ArrayList();
       Iterator<Financa> it = financas.iterator();
       //auxiliares
       Financa aux;Dado categorias; String cat;
       while(it.hasNext()){
           aux = it.next();
           if( (aux instanceof Receita ) ){
               cat = CategoriaReceita.categorias.values()[aux.getCategoria()].name();
               categorias = new Dado(cat,aux.getCategoria(),aux.getValue());
                this.receitas.add(categorias);
           }else{
               cat = CategoriaDespesa.categorias.values()[aux.getCategoria()].name();
               categorias = new Dado(cat ,aux.getCategoria(),aux.getValue());
                this.despesas.add(categorias);
           }
       }
    
   }
       
        
       
   
   
    
}
