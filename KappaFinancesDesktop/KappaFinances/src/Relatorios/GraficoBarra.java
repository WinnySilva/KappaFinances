package Relatorios;


import Financas.Contabilidade;
import Relatorios.Relatorio;
import java.awt.Graphics2D;
import java.util.Iterator;
import java.util.Observable;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.Chart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Winny S
 */
public class GraficoBarra extends Relatorio {
    private String labelX = "Categorias", labelY = "Valor(R$)",label = "Valor";
    
    
    
    final static String austria = "Austria";
    final static String brazil = "Brazil";
    final static String france = "France";
    final static String italy = "Italy";
    final static String usa = "USA";
  
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    @Override
    public Chart geraGrafico() {
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc = 
            new BarChart<String,Number>(xAxis,yAxis);
        bc.setTitle(super.titulo);
        xAxis.setLabel(labelX);       
        yAxis.setLabel(labelY);
 
        XYChart.Series series1 = new XYChart.Series();
        series1.setName(label);       
        /*
        series1.getData().add(new XYChart.Data(austria, 25601.34));
        series1.getData().add(new XYChart.Data(brazil, 20148.82));
        series1.getData().add(new XYChart.Data(france, 10000));
        series1.getData().add(new XYChart.Data(italy, 35407.15));
        series1.getData().add(new XYChart.Data(usa, 12000));      
        */
        Iterator<Dado> it = super.despesas.iterator();
        Dado aux;
        while(it.hasNext()){
            aux = it.next();
            series1.getData().add(new XYChart.Data(aux.categoria_s,aux.total ));
        }
        
        
        bc.getData().addAll(series1);
        return bc;
    
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Bar Chart Sample");
       
        Scene scene  = new Scene(this.geraGrafico(),800,600);
       // bc.getData().addAll(series1);
        stage.setScene(scene);
        stage.show();
    }
     public static void main (String args[]){
        
        launch(args);
    }
}
