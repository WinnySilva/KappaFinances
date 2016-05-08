package Relatorios;


import Financas.CategoriaDespesa;
import Financas.CategoriaReceita;
import Financas.Contabilidade;
import java.util.Observable;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.Chart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

/**
 *
 * @author Winny S
 */
public class GraficoBarra extends Relatorio {
    private final String  labelX  = "Categorias", labelY = "Valor(R$)",label = "Valor";
    XYChart.Series series1;

    public GraficoBarra(Contabilidade con,int tipo){
        super(con, tipo);
    }
    public GraficoBarra(Contabilidade con){
        super(con);
    }
    public GraficoBarra(){
        super();
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
 
        series1 = new XYChart.Series();
       // series1.setName(label);       
        if(super.tipo== Relatorio.DESPESA){
            for(int i=0;i<this.desp.length;i++){
            series1.getData().add(new XYChart.Data(
                    ""+CategoriaDespesa.categorias.values()[i]  ,   this.desp[i] ));
            }
        }else{
           for(int i=0;i<this.rec.length;i++){
            series1.getData().add(new XYChart.Data(
                    ""+CategoriaReceita.categorias.values()[i]  ,   this.rec[i] ));
            } 
        }
        bc.setLegendVisible(false);
        bc.getData().addAll(series1);
        return bc;
    }
    @Override
    public void update(Observable o, Object arg) {
        mineracao();
        if(series1==null){
            series1= new XYChart.Series();
        }
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if(tipo== Relatorio.DESPESA){
                    for(int i=0;i<desp.length;i++){
                    series1.getData().add(new XYChart.Data(
                            ""+CategoriaDespesa.categorias.values()[i]  ,  desp[i] ));
                    }
                }else{
                   for(int i=0;i<rec.length;i++){
                    series1.getData().add(new XYChart.Data(
                            ""+CategoriaReceita.categorias.values()[i]  , rec[i] ));
                    } 
                }
            }
        
        });
        
   }
}
