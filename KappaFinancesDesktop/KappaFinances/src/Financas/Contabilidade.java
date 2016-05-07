package Financas;
import XMLHandler.*;
import java.io.IOException;
import java.util.ArrayList;
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
        } catch (IOException ex) {
            Logger.getLogger(Contabilidade.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public Contabilidade(double saldoTotal, double despesasTotais)
    {
        this();
        this.saldoTotal = saldoTotal;
        this.despesasTotais = despesasTotais;
        
    }
    public double getSaldo()
    {
        return this.saldoTotal;
    }
    
    public void addFinanca(Financa financa) throws Exception
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
      fh.saveLastMonth(array);
      setChanged();
      notifyObservers();
     
      
    }
    public double getValorDespesa(){
        return this.despesasTotais;
    }
    public double getValorReceita(){
        return this.receitasTotais;
    }
    public void remVoid(int pos) throws Exception{
        Financa deletavel;
        
        deletavel = this.array.get(pos);
        
        if(deletavel instanceof Receita )
        {
            this.saldoTotal-= deletavel.getValue();
          this.receitasTotais -= deletavel.getValue();
          
      }else{
          if(deletavel.getValue()>0){
              deletavel.setValue(-deletavel.getValue());
          }
          this.saldoTotal -= deletavel.getValue();
          this.despesasTotais -= deletavel.getValue();
        }
        this.array.remove(pos);
        this.fh.removeFinance(pos);
        fh.saveLastMonth(array);
        setChanged();
        notifyObservers();
    }
    public ArrayList<Financa> getFinancas(){
        return this.array;
    }
    public Financa getFinanca(int pos){
        return this.array.get(pos);
    }
    public void setFinanca(int pos, Financa nova){
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
        setChanged();
        notifyObservers();
    }
    
   
    
}
