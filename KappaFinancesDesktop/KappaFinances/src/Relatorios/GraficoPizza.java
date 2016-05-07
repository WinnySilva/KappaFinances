package Relatorios;
import Financas.CategoriaDespesa;
import Financas.CategoriaReceita;
import Financas.Contabilidade;
import java.util.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    ObservableList<PieChart.Data> pieChartData;
    
    GraficoPizza(Contabilidade con,int tipo){
        super(con, tipo);
    }
    
    GraficoPizza(Contabilidade con){
        super(con);
    }
    GraficoPizza(){
        super();
    }
    public void  start(Stage stage) { 
        Scene scene = new Scene(new Group());
        stage.setTitle("Test");
        stage.setWidth(500);
        stage.setHeight(500);
        ((Group) scene.getRoot()).getChildren().add(this.geraGrafico());
        stage.setScene(scene);
        stage.show();
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
        
       /* for(int i=0;i<this.desp.length;i++){
            pieChartData.add(new PieChart.Data(""+CategoriaDespesa.categorias.values()[i] ,this.desp[i]));
        }*/
        if(this.tipo== Relatorio.DESPESA){
            for(int i=0;i<this.desp.length;i++){
            this.pieChartData.set(i, new PieChart.Data(""+CategoriaDespesa.categorias.values()[i] ,this.desp[i]));
           }
        }else{
            for(int i=0;i<this.desp.length;i++){
            this.pieChartData.set(i, new PieChart.Data(""+CategoriaReceita.categorias.values()[i] ,this.rec[i]));
            }
        }
       
        PieChart chart = new PieChart(pieChartData); 
        chart.setTitle(super.titulo);
        this.grafico = chart;
        return chart;
    }

    @Override
    public void update(Observable o, Object arg) {
        mineracao();
        
        if(this.tipo== Relatorio.DESPESA){
            for(int i=0;i<this.desp.length;i++){
            this.pieChartData.set(i, new PieChart.Data(""+CategoriaDespesa.categorias.values()[i] ,this.desp[i]));
           }
        }else{
            for(int i=0;i<this.desp.length;i++){
            this.pieChartData.set(i, new PieChart.Data(""+CategoriaReceita.categorias.values()[i] ,this.rec[i]));
            }
        }
            
            
        
        
        

   
        
    }

    
}
