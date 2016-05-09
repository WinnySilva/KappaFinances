package Financas;
import XMLHandler.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Winny S
 */
public class Contabilidade extends Observable
{
    private double saldoTotal;      // equivalente a valor entrada do diagrama de classes
    private double despesasTotais;   // Equivalente a valor saida do diagrama de classes
    private double receitasTotais;
    private ArrayList<Financa> array;
    private FileHandler fh;
    
    
    public Contabilidade()
    {
        saldoTotal = 0;
        despesasTotais = 0;
        receitasTotais = 0;
        this.array = new ArrayList();
        
        try {
            fh = new FileHandler();
            this.array = fh.loadCurrentMonth();
            carregarValores();
        } catch (Exception ex) {
            Logger.getLogger(Contabilidade.class.getName()).log(Level.SEVERE, null, ex);
            this.array = new ArrayList();
        } 
    
    }
   public void setFinancas(ArrayList<Financa> al){
       this.array= al;
       carregarValores();
       setChanged();
       notifyObservers();
   }
    
    public Contabilidade(double saldoTotal, double despesasTotais)
    {
        this();
        this.saldoTotal = saldoTotal;
        this.despesasTotais = despesasTotais;
        
    }
    /**
     * Funcao para pegar da propria classe percorrer e atribuir os valores de saldo, total
     * de despesa e total de receitas.
     * OBS: este deve ser a primeira coisa a ser feita, pois assume que o vetor está vazio
     * @param fin 
     */
    private synchronized  void carregarValores(){
        Iterator<Financa> it= this.array.iterator();
        Financa aux;
        while(it.hasNext()){
            aux = it.next();
            this.saldoTotal += aux.getValue();
            if(aux instanceof Receita){
                this.receitasTotais += aux.getValue();
            }else{
                this.despesasTotais +=aux.getValue();
            }
            
        }
    }
    
    public double getSaldo()
    {
        return this.saldoTotal;
    }
    
    public synchronized  void addFinanca(Financa financa) throws Exception
    {
        double financaValor;
        financaValor = financa.getValue();
        
      if(financa instanceof Receita){
          this.saldoTotal+= financaValor;
          this.receitasTotais+= financaValor;
          
      }else{
          if(financa.getValue()>0){
              financa.setValue(-(financaValor));
          }
          this.saldoTotal+= -(financaValor);
          this.despesasTotais+= -(financaValor);
          
      }
      this.array.add(financa);
      this.fh.addFinance(financa);
      setChanged();
      notifyObservers();
    }
    
    public double getValorDespesa(){
        return this.despesasTotais;
    }
    
    public double getValorReceita(){
        return this.receitasTotais;
    }
    
    public synchronized void remVoid(int pos) throws Exception{
        Financa deletavel;
        
        deletavel = this.array.get(pos);
        
        if(deletavel instanceof Receita )
        {
            this.saldoTotal-= deletavel.getValue();
          this.receitasTotais -= deletavel.getValue();
          
        }else{
          this.saldoTotal -= deletavel.getValue();
          this.despesasTotais -= deletavel.getValue();
        }
        this.array.remove(pos);
        this.fh.removeFinance(pos);
        setChanged();
        notifyObservers();
    }
    
    public ArrayList<Financa> getFinancas(){
        return this.array;
    }
    
    public synchronized  Financa getFinanca(int pos){
        return this.array.get(pos);
    }
    
    public synchronized  void setFinanca(int pos, Financa nova){
        Financa editavel;
        
        editavel = this.array.get(pos);
        
        if(nova instanceof Receita){
          this.saldoTotal -= editavel.getValue();
          this.saldoTotal+= nova.getValue();
          this.receitasTotais += nova.getValue();
          
        }else{
          if(nova.getValue()>0){
              nova.setValue(-nova .getValue());
        }
          this.saldoTotal -= editavel.getValue();
          this.despesasTotais -= editavel.getValue();
          this.saldoTotal += nova.getValue();
          this.despesasTotais += nova.getValue();
          
        }
         
        this.array.set(pos, nova);
        this.fh.removeFinance(pos);
        this.fh.addFinance(nova);
        setChanged();
        notifyObservers();
    }
    
   
    
}
