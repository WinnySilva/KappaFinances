package Relatorios;
import Financas.CategoriaDespesa;
import Financas.Contabilidade;
import Financas.Despesa;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.Chart;
import javafx.stage.Stage;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Winny S
 */
    public class GraficoPizza extends Relatorio{
    ObservableList<PieChart.Data> pieChartData;
    
    
    
    
    @Override
    public void  start(Stage stage) { 
        Scene scene = new Scene(new Group());
        stage.setTitle("Test");
        stage.setWidth(500);
        stage.setHeight(500);
        ((Group) scene.getRoot()).getChildren().add(this.geraGrafico());
        stage.setScene(scene);
        stage.show();
    }
    public static void main (String args[]) throws Exception{
      Contabilidade x = new Contabilidade();
      GraficoPizza gb = new GraficoPizza();
      gb.contas=x;
      x.addObserver(gb);
       Despesa d;
       int aux =CategoriaDespesa.categorias.values().length;
       Calendar c = Calendar.getInstance();
       double dl;
       for(int i=0; i<10; i++){  
           dl = (i+i*0.333);
           d = new Despesa(c,dl, CategoriaDespesa.categorias.values()[i%aux].ordinal());
           x.addFinanca(d);
       }

     //  x.addObserver(gb);
       launch(args);
       
    }

    /**
     *um grafico de pizza do tipo Chart, ele deve ser inserido na gui atraves de um
     * FXPanel ou Fx conteneiner
     * @return 
     */
    //
    @Override
    public Chart geraGrafico() {
       pieChartData=FXCollections.observableArrayList();
        
        for(int i=0;i<this.desp.length;i++){
            pieChartData.add(new PieChart.Data(""+CategoriaDespesa.categorias.values()[i] ,this.desp[i]));
        }
        PieChart chart = new PieChart(pieChartData); 
        chart.setTitle(super.titulo);
      //  chart.setScaleX(0.7);
        //chart.setScaleY(0.7);
        this.grafico = chart; 
        return chart;
    }

    @Override
    public void update(Observable o, Object arg) {
        
        /*if(pieChartData == null){
            pieChartData=FXCollections.observableArrayList();
            for(int i=0;i<this.desp.length;i++){
            pieChartData.add(new PieChart.Data(""+CategoriaDespesa.categorias.values()[i] ,this.desp[i]));
        }
        }
        
        mineracao();
        for(int i=0;i<this.desp.length;i++){
            System.out.println("$$$"+desp[i]);
            pieChartData.set(i, new PieChart.Data(""+CategoriaDespesa.categorias.values()[i] ,this.desp[i]));
        }*/
        mineracao();
        for(int i=0;i<this.desp.length;i++){
            this.pieChartData.set(i, new PieChart.Data(""+CategoriaDespesa.categorias.values()[i] ,this.desp[i]));
            //novo_val.add(new PieChart.Data(""+CategoriaDespesa.categorias.values()[i] ,this.desp[i]));
        }
        

   
        
    }

    
}
