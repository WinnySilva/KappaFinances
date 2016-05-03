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
      if(financa instanceof Receita){
          this.saldoTotal+= financa.getValue();
          this.receitasTotais+=financa.getValue();
          
      }else{
          if(financa.getValue()>0){
              financa.setValue(-financa.getValue());
          }
          this.saldoTotal+= financa.getValue();
          this.despesasTotais+=financa.getValue();
          
      }
      this.array.add(financa);
      this.fh.addFinance(financa);
      notifyObservers();
    }
    public double getValorDespesa(){
        return this.despesasTotais;
    }
    public double getValorReceita(){
        return this.receitasTotais;
    }
    public void remVoid(int pos) throws Exception{
        this.array.remove(pos);
        this.fh.removeFinance(pos);
        notifyObservers();
    }
    public ArrayList<Financa> getFinancas(){
        return this.array;
    }
    public Financa getFinanca(int pos){
        return this.array.get(pos);
    }
    public void setFinanca(int pos, Financa nova){
        this.array.set(pos, nova);
        notifyObservers();
    }
    
   
    
}
