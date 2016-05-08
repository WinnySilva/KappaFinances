package Relatorios;

import Financas.CategoriaDespesa;
import Financas.Contabilidade;
import Financas.Despesa;
import Financas.Receita;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaChartDemo {
  public static void main ( String[] args){
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        ChartFrame mainFrame = new ChartFrame();
        mainFrame.setVisible(true);
        }
      });
    }
  }

class ChartFrame extends JFrame {
   
      Contabilidade x ;
      GraficoPizza gb ; //new GraficoBarra();
    
  JFXPanel fxPanel;
  public ChartFrame(){
    x = new Contabilidade();
    gb = new GraficoPizza(x,1);
      initSwingComponents();
      initFxComponents();
       
    new Thread( new Runnable() {
         public void run() {
            // final Despesa d;
             int aux ;
             Calendar c = Calendar.getInstance();
             double dl;
             Scanner sc = new Scanner(System.in);
             for(int i=0; i<5; i++){
                 //dl = sc.nextDouble();
                 //aux = sc.nextInt();
                System.out.println("iteracao:: "+i);
                 final Receita d = new Receita(c,i,i%2);
                Platform.runLater(new Runnable() {
                     @Override
                     public void run() {
                         try {
                             x.addFinanca(d);
                             System.out.println(x);
                      //      Thread.sleep(100);
                         } catch (Exception ex) {
                             
                             Logger.getLogger(ChartFrame.class.getName()).log(Level.SEVERE, null, ex);
                         }
                     }
                 });
             }}
     }).start();
     //  this.validateTree();
    System.out.println("FIM DO CONSTRUTOR");
  }

  private void initSwingComponents(){
    JPanel mainPanel = new JPanel(new BorderLayout());
    fxPanel = new JFXPanel();
    mainPanel.add(fxPanel, BorderLayout.CENTER);
    this.add(mainPanel);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(400,400);
    
  }

  private void initFxComponents(){

    Platform.runLater(new Runnable() {
      @Override
      public void run() {
          GridPane grid = new GridPane();
          Scene scene = new Scene(grid, 400, 400);
          grid.add(gb.geraGrafico(), 0, 0);
          fxPanel.setScene(scene);
        }
      });

  }
}
