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
import java.util.Random;
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
    gb = new GraficoPizza(x,0);
      initSwingComponents();
      initFxComponents();
      
       Calendar c = Calendar.getInstance();
    new Thread( new Runnable() {
         public void run() {
            // final Despesa d;
             int aux ;
             double dl;
             Random r = new Random();
             for(int i=0; i<5; i++){
                 //dl = sc.nextDouble();
                 //aux = sc.nextInt();
                 final Despesa d = new Despesa(c,r.nextDouble()+300,i%2);
                Platform.runLater(new Runnable() {
                     @Override
                     public void run() {
                         try {
                             x.addFinanca(d);
                             
                         } catch (Exception ex) {
                             
                             Logger.getLogger(ChartFrame.class.getName()).log(Level.SEVERE, null, ex);
                         }
                     }
                 });
             }}
     }).start();
     //  this.validateTree();
  }

  private void initSwingComponents(){
    JPanel mainPanel = new JPanel(new BorderLayout());
    fxPanel = new JFXPanel();
    
    this.add(mainPanel);
    
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(400,400);
    mainPanel.add(fxPanel, BorderLayout.CENTER);
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
