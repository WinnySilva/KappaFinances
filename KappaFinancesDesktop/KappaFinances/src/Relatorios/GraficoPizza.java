package Relatorios;
import Financas.CategoriaDespesa;
import Financas.CategoriaReceita;
import Financas.Contabilidade;
import java.util.Observable;
import javafx.application.Platform;
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
    
    public GraficoPizza(Contabilidade con,int tipo){
        super(con, tipo);
    }
    
    public GraficoPizza(Contabilidade con){
        super(con);
    }
    public GraficoPizza(){
        super();
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
        if(super.tipo== Relatorio.DESPESA){
            for(int i=0;i<this.desp.length;i++){
                System.out.println("%%:"+CategoriaDespesa.categorias.values()[i]+"::"+this.desp[i]);
                this.pieChartData.add(new PieChart.Data(
                    ""+CategoriaDespesa.categorias.values()[i] ,this.desp[i]));
            
           }
        }else{
            for(int i=0;i<this.rec.length;i++){
                System.out.println("%%"+CategoriaReceita.categorias.values()[i]+"::"+this.rec[i]);
                this.pieChartData.add( new PieChart.Data(
                        ""+CategoriaReceita.categorias.values()[i] ,this.rec[i]));
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
        
        
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if(tipo== Relatorio.DESPESA){
                    for(int i=0;i<desp.length;i++){
                    pieChartData.set(i, new PieChart.Data(""+CategoriaDespesa.categorias.values()[i] ,desp[i]));
                   }
                }else{
                    for(int i=0;i<rec.length;i++){
                    pieChartData.set(i, new PieChart.Data(""+CategoriaReceita.categorias.values()[i] ,rec[i]));
                    }
                }
            }
        });
        
            
        
        
        

   
        
    }

    
}
