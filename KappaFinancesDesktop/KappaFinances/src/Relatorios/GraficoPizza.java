package Relatorios;
import Financas.Contabilidade;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.Chart;
import javafx.stage.Stage;
import javafx.scene.chart.PieChart;

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
    public static void main (String args[]){
        
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
        ObservableList<PieChart.Data> pieChartData=FXCollections.observableArrayList();
        Iterator<Dado> it = super.dados.iterator();
        Dado aux;
        while(it.hasNext()){
            aux = it.next();
            pieChartData.add(new PieChart.Data(aux.categoria_s,aux.total ));
        }
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle(super.titulo);
      //  chart.setScaleX(0.7);
        //chart.setScaleY(0.7);
        return chart;
    }

    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  

    
}
