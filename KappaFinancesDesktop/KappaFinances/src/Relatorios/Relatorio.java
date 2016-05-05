package Relatorios;

import Financas.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Observable;
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
//    protected ArrayList<Dado> despesas;
    protected double desp[];
  //  protected ArrayList<Dado> receitas;
    protected Chart grafico;

    Relatorio(Contabilidade con,String title){
        this();
        this.contas = con;
        this.titulo = title;
        this.contas.addObserver(this);
    }
    Relatorio(Contabilidade con){
        this();
        
        this.contas = con;
        this.contas.addObserver(this);
    }
    Relatorio(){
      this.desp = new double[CategoriaDespesa.categorias.values().length];
       for(int i=0;i<desp.length;i++){
          this.desp[i]=0;
      }
    }
    public abstract Chart  geraGrafico();
    /**
     * @param financas
     * @return Dado
     */
   protected void mineracao(){
       for(int i=0;i<desp.length;i++){
          this.desp[i]=0;
      }
//       this.despesas = new ArrayList();
       Iterator<Financa> it = this.contas.getFinancas().iterator();  //= financas.iterator();
       //auxiliares
       Financa aux;
       while(it.hasNext()){
           aux = it.next();
           System.out.println(+aux.getCategoria()+":"+desp[aux.getCategoria()]);
           if( (aux instanceof Despesa ) ){
               this.desp[aux.getCategoria()] +=aux.getValue();
               
           }
       }
   }
    public static void main(String args[]) throws Exception{
       
   }
}
