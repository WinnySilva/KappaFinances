package Relatorios;


import Financas.CategoriaDespesa;
import Financas.CategoriaReceita;
import Financas.Contabilidade;
import Financas.Despesa;
import java.util.Calendar;
import java.util.Observable;
import static javafx.application.Application.launch;
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
    private String labelX = "Categorias", labelY = "Valor(R$)",label = "Valor";
    XYChart.Series series1;

    GraficoBarra(Contabilidade con,int tipo){
        super(con, tipo);
    }
    GraficoBarra(Contabilidade con){
        super(con);
    }
    GraficoBarra(){
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
        series1.setName(label);       
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
        
        bc.getData().addAll(series1);
        return bc;
    }

    public void start(Stage stage) throws Exception {
        stage.setTitle("Bar Chart Sample");
        Scene scene  = new Scene(this.geraGrafico(),800,600);
       // bc.getData().addAll(series1);
        stage.setScene(scene);
        stage.show();
    }
    @Override
    public void update(Observable o, Object arg) {
        mineracao();
        if(series1==null){
            series1= new XYChart.Series();
        }
        
        if(super.tipo== Relatorio.DESPESA){
            for(int i=0;i<this.desp.length;i++){
            series1.getData().add(new XYChart.Data(
                    ""+CategoriaDespesa.categorias.values()[i]  ,   this.desp[i] ));
                System.out.println(""+this.desp[i]);
            }
        }else{
           for(int i=0;i<this.rec.length;i++){
            series1.getData().add(new XYChart.Data(
                    ""+CategoriaReceita.categorias.values()[i]  ,   this.rec[i] ));
            System.out.println(""+this.rec[i]);
            } 
        }
        
        
       
        
        
        
    }
}
