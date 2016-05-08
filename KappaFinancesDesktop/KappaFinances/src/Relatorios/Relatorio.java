package Relatorios;

import Financas.*;
import java.util.Iterator;
import java.util.Observer;
import javafx.scene.chart.Chart;
/**
 * @author Winny S
 */
public abstract class Relatorio /*extends Application*/ implements Observer  {
    protected String titulo ="Despesas";
    protected Contabilidade contas;
    protected double desp[];
    protected double rec[];
    protected Chart grafico;
    protected int tipo;
    public static  int DESPESA=0;
    public static  int RECEITA=1;

    Relatorio(Contabilidade con,int tipo){
        this();
        this.contas = con;
        this.contas.addObserver(this);
        if(tipo== Relatorio.DESPESA){
            this.titulo = "Despesas";
        }else{
            this.titulo = "Receita";
        }
        this.tipo=tipo;
        mineracao();
    }
    Relatorio(Contabilidade con){
        this();
        this.contas = con;
        this.contas.addObserver(this);
        mineracao();
    }
    Relatorio(){
        tipo = 0;  
        this.desp = new double[CategoriaDespesa.categorias.values().length];
        this.rec = new double[CategoriaReceita.categorias.values().length];
        for(int i=0;i<desp.length;i++){
            this.desp[i]=0;
        }
        for(int i=0;i<rec.length;i++){
            this.rec[i]=0;
        }
          System.out.println(desp.length+" "+rec.length);
    
    }
    public abstract Chart  geraGrafico();
    /**
     * Atualiza os valores totais das categorias
     * 
     */
   protected void mineracao(){
      
       for(int i=0;i<desp.length;i++){
          this.desp[i]=0;
      }
        for(int i=0;i<rec.length;i++){
          this.rec[i]=0;
      }
       Iterator<Financa> it = this.contas.getFinancas().iterator();  //= financas.iterator();
       Financa aux;
       
       // percorre todo vetor de financas, separando os valores por categoria
       while(it.hasNext()){
           aux = it.next();
           if( (aux instanceof Despesa ) ){
               this.desp[aux.getCategoria()] += -aux.getValue();
           }
           else {
               this.rec[aux.getCategoria()] += aux.getValue();
           }
       }
   }
}
