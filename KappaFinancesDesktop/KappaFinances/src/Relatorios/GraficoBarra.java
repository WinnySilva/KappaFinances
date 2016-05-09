package Relatorios;


import Financas.CategoriaDespesa;
import Financas.CategoriaReceita;
import Financas.Contabilidade;
import java.util.Observable;
import javafx.application.Platform;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.Chart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
/**
 *
 * @author Winny S
 */
public class GraficoBarra extends Relatorio {
    private final String  labelX  = "Categorias", labelY = "Valor(R$)";
    private XYChart.Series series1;
    private BarChart<String,Number> gb;
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
        //cria o eixo X do gráfico
        final CategoryAxis xAxis = new CategoryAxis();
        // cria o eixo Y do gráfico
        final NumberAxis yAxis = new NumberAxis();
        //cria o gráfico de barra do JavaFX
        final BarChart<String,Number> bc = 
            new BarChart<String,Number>(xAxis,yAxis);
        // seta o titulo do gráfico
        bc.setTitle(super.titulo);
        //seta os eixos do gráfico
        xAxis.setLabel(labelX);       
        yAxis.setLabel(labelY);
        //instacia a lista de dados do gráfico
        series1 = new XYChart.Series();
       //teste qual dados o gráfico deve mostrar
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
        //torna a legenda de cores não visivel
        bc.setLegendVisible(false);
        //adiciona os dados ao gráfico para ser exibido
        bc.getData().addAll(series1);
        //guarda a referencia ao gráfico no atributo da classe
        gb = bc;
        return bc;
    }
    /**
     * chamado quando há alterações na classe observada. Este método gera uma 
     * thread que atualiza os dados do grafico de acordo com o tipo do gráfico
     * @param o
     * @param arg 
     */
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
