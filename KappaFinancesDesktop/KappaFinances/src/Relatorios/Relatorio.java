package Relatorios;

import Financas.Contabilidade;
import java.awt.Graphics2D;
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
public abstract class Relatorio extends Application {
    protected Contabilidade contas;
     protected class dados{
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
     * * @return um vetor de dados sobre as financas 
     */
   //protected dados[] mineracao();
       
        
       
   
   
    
}
