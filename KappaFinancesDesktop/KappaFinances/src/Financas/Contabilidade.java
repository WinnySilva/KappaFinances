package Financas;
import XMLHandler.*;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Winny S
 */
public class Contabilidade 
{
    private double saldoTotal;      // equivalente a valor entrada do diagrama de classes
    private double despesasTotais;   // Equivalente a valor saida do diagrama de classes
    private ArrayList<Financa> array;
    
    
    public Contabilidade()
    {
        saldoTotal = 0;
        despesasTotais = 0;
    }
    
    public Contabilidade(double saldoTotal, double despesasTotais)
    {
        this.saldoTotal = saldoTotal;
        this.despesasTotais = despesasTotais;
        
    }
    
    public double getSaldo()
    {
        return this.saldoTotal;
    }
    
    public void addFinance(Financa Financa)
    {
      
    }
    
}
