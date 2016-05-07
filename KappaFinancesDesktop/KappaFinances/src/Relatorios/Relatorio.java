package Relatorios;

import Financas.*;
import java.util.Iterator;
import java.util.Observer;
import javafx.scene.chart.Chart;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * @author Winny S
 */
public abstract class Relatorio /*extends Application*/ implements Observer  {
    protected String titulo ="Despesas";
    protected Contabilidade contas;
    protected double desp[];
    protected double rec[];
    protected Chart grafico;
    protected int tipo = 0;
    public static  int RECEITA=1;
    public static  int DESPESA=0;

    Relatorio(Contabilidade con,int tipo){
        this();
        this.contas = con;
        this.contas.addObserver(this);
        if(tipo== Relatorio.DESPESA){
            this.titulo = "Despesas";
        }else{
            this.titulo = "Receita";
        }
        
        
        mineracao();
    }
    Relatorio(Contabilidade con){
        this();
        this.contas = con;
        this.contas.addObserver(this);
         mineracao();
    }
    Relatorio(){
      this.desp = new double[CategoriaDespesa.categorias.values().length];
      this.rec = new double[CategoriaDespesa.categorias.values().length];
      for(int i=0;i<desp.length;i++){
          this.desp[i]=0;
      }
      for(int i=0;i<desp.length;i++){
          this.rec[i]=0;
      }
      
    }
    public abstract Chart  geraGrafico();
    /**
     * Atualiza os valores totais das categorias
     * 
     */
   protected void mineracao(){
      
       for(int i=0;i<desp.length;i++){
          this.desp[i]=0;
      }
        for(int i=0;i<desp.length;i++){
          this.rec[i]=0;
      }
       Iterator<Financa> it = this.contas.getFinancas().iterator();  //= financas.iterator();
       Financa aux;
       
       // percorre todo vetor de financas, separando os valores por categoria
       while(it.hasNext()){
           aux = it.next();
           if( (aux instanceof Despesa ) ){
               this.desp[aux.getCategoria()] += -aux.getValue();
           }
           else {
               this.rec[aux.getCategoria()] += aux.getValue();
           }
       }
   }
}
