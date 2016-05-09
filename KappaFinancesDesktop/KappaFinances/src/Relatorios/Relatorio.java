package Relatorios;

import Financas.*;
import java.util.Iterator;
import java.util.Observer;
import javafx.scene.chart.Chart;
/**
 * @author Winny S
 */
public abstract class Relatorio  implements Observer  {
    protected String titulo ="Despesas";
    protected Contabilidade contas;
    protected double desp[];
    protected double rec[];
    protected Chart grafico;
    protected int tipo;
    public static  int DESPESA=0;
    public static  int RECEITA=1;
/**
 * recebe uma contabilidade para ser observada e 
 * atualizar as estruturas de dado e um inteiro
 * para especificar se o relatorio gerado é de despesas
 * ou receitas
 * @param con
 * @param tipo 
 */
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
    /**
     * recebe uma contabilidade para ser observada
     * e gera relatorio para despesas como padrão
     * @param con 
     */
    Relatorio(Contabilidade con){
        this();
        this.contas = con;
        this.contas.addObserver(this);
        mineracao();
    }
    /**
     * construtor interno ao pacote, inicializa os vetores de despesa e receita
     * e os zera
     */
    protected Relatorio(){
        tipo = 0;  
        this.desp = new double[CategoriaDespesa.categorias.values().length];
        this.rec = new double[CategoriaReceita.categorias.values().length];
        for(int i=0;i<desp.length;i++){
            this.desp[i]=0;
        }
        for(int i=0;i<rec.length;i++){
            this.rec[i]=0;
        }
    }
    /**
     * utiliza os vetores com os valores de totais das categorias de receita e 
     * despesa para gerar o gráfico do tipo da classe especializada.
     * Pode ser chamada apenas uma vez, pois o gráfico se atualiza de acordo com
     * a classe observada
     * @return grafico - retorna um grafico da classe especializada 
     */
    public abstract Chart  geraGrafico();
    /**
     * Atualiza os valores dos vetores de valores totais das categorias de acordo
     * com o array de financas do objeto observado
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
