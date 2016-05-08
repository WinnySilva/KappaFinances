/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import javax.swing.table.DefaultTableModel;
import Financas.*;
import Relatorios.GraficoBarra;
import Relatorios.GraficoPizza;
import Relatorios.JavaChartDemo;
import Relatorios.Relatorio;
import XMLHandler.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author fabio
 */
public class Interface extends javax.swing.JFrame {
    private Contabilidade ContabilidadeInterface = new Contabilidade();

    //variaveis para mostrar os graficos na na GUI
    private JFXPanel jxfPanelGB;
    private JFXPanel jxfPanelGP;
    private int tipoGrafico = Relatorio.DESPESA; /* variavel para definir o tipo 
    dos graficos, se tipo = Relatorio.DESPESA for passado para o construtor do grafico
    ele ira mostrar um gráfico de todas as despesas*/
    private GraficoBarra gb = new GraficoBarra(ContabilidadeInterface,tipoGrafico);
    private GraficoPizza gp = new GraficoPizza(ContabilidadeInterface,tipoGrafico);

    /**
     * Os proximos dois atributos sao utilizados pela lista txtListaFinancas para
     * que a insercao das categorias possiveis sejam automatica.
     * DefaultComboBoxModel se refere ao conteudo dentro da lista. Entao, dois conteudos
     * sao instanciados um para cada tipo de categoria.
     * O tipo de categoria mostrada no momento, esta relacionada com aquela selecionada
     * pelos JradioButtons da interface.
     */
    private DefaultComboBoxModel cbModelReceita = new DefaultComboBoxModel(CategoriaReceita.categorias.values());
    private DefaultComboBoxModel cbModelDespesa = new DefaultComboBoxModel(CategoriaDespesa.categorias.values());
               

    /**
     * Creates new form Interface
     */
    public Interface() {
        initComponents();
        
        //insere os graficos na GUI
        jxfPanelGB = new JFXPanel();
        jxfPanelGP = new JFXPanel();
        initFxComponents();
        carregarGraficos();        
        //--
        
        // Inicializa o grupo de botoes
        this.btnGrpTipoFinanca.add(this.bdespesa);
        this.btnGrpTipoFinanca.add(this.breceita);
        
        // Inicializa os ComboBoxModels e inicializa txtListaFinancas com Despesas inicialmente
        this.cbModelDespesa = new DefaultComboBoxModel(CategoriaDespesa.categorias.values());
        this.cbModelReceita = new DefaultComboBoxModel(CategoriaReceita.categorias.values());
        this.txtListaFinancas.setModel(this.cbModelDespesa);
        
        // Le do XML as financas e adiciona elas a tabela da interface
        FileHandler fh;
        ArrayList<Financa> al;
        try {
            fh = new FileHandler();
            al = fh.loadMonth(this.intToMonth(Calendar.getInstance().get(Calendar.MONTH)),
                    Calendar.getInstance().get(Calendar.YEAR));
            NumberFormat nf = NumberFormat.getCurrencyInstance();
            for (Financa f: al) {
                DefaultTableModel tabela = (DefaultTableModel) jtabela.getModel();
                if (f instanceof Despesa) {
                    Object[] dados = {CategoriaDespesa.categorias.values()[f.getCategoria()], nf.format(f.getValue()*-1)};
                    tabela.addRow(dados);
                }
                else {
                    Object[] dados = {CategoriaReceita.categorias.values()[f.getCategoria()], nf.format(f.getValue())};
                    tabela.addRow(dados);
                }
            }
        /*} catch (IOException ex) {
            Logger.getLogger(Contabilidade.class.getName()).log(Level.SEVERE, null, ex);*/
        } catch (Exception e) {
            System.out.println("EXCESSAO FATAL DETECTADA");
        }
    }

    
    /**
     * 
     */
    private void carregarGraficos(){
        // isso funciona
        JPanel graficoPanel;
        graficoPanel = new JPanel(new BorderLayout() );
        graficoPanel.add(jxfPanelGB, BorderLayout.CENTER) ;
        graficoPanel.setSize( jPanelGB.getWidth() , jPanelGB.getHeight());
        jPanelGB.add(graficoPanel);
        jPanel2.updateUI();
        
        graficoPanel = new JPanel(new BorderLayout() );
        graficoPanel.add(jxfPanelGP, BorderLayout.CENTER) ;
        graficoPanel.setSize( jPanelGP.getWidth() , jPanelGP.getHeight());
        jPanelGP.add(graficoPanel);
        jPanel2.updateUI();
        
        
    }
    
    private String intToMonth(int m)
    {
        String ret=null;
        if (m==0) ret="JAN";
        else if (m==1) ret="FEB";
        else if (m==2) ret="MAR";
        else if (m==3) ret="APR";
        else if (m==4) ret="MAY";
        else if (m==5) ret="JUN";
        else if (m==6) ret="JUL";
        else if (m==7) ret="AUG";
        else if (m==8) ret="SEP";
        else if (m==9) ret="OCT";
        else if (m==10) ret="NOV";
        else if (m==11) ret="DEC";
        return ret;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGrpTipoFinanca = new javax.swing.ButtonGroup();
        jPanel4 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtabela = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        txtfSaldo = new javax.swing.JFormattedTextField();
        editar = new javax.swing.JLabel();
        deletar = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        bdespesa = new javax.swing.JRadioButton();
        breceita = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        txtListaFinancas = new javax.swing.JComboBox<String>();
        submit = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        txtValor = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<String>();
        jComboBox2 = new javax.swing.JComboBox<String>();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        tablegraficos = new javax.swing.JTabbedPane();
        jPanelGB = new javax.swing.JPanel();
        jPanelGP = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Kappa Finances");

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Informações"));
        jPanel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel8MouseClicked(evt);
            }
        });

        jtabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Despesa ou Receita", "Valor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jtabela);

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Saldo"));

        jLabel18.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel18.setText("Saldo");

        txtfSaldo.setEditable(false);
        txtfSaldo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("¤#,##0.00"))));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(97, 97, 97)
                .addComponent(jLabel18)
                .addGap(18, 18, 18)
                .addComponent(txtfSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(80, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtfSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        editar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/application_edit.png"))); // NOI18N

        deletar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/cross.png"))); // NOI18N
        deletar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deletarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(editar, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deletar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(editar)
                    .addComponent(deletar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(21, 21, 21))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados"));

        bdespesa.setSelected(true);
        bdespesa.setText("Despesa");
        bdespesa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bdespesaActionPerformed(evt);
            }
        });

        breceita.setText("Receita");
        breceita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                breceitaActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel6.setText("Lista");

        jLabel7.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel7.setText("R$");

        jLabel8.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel8.setText("Obs:");

        txtListaFinancas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        submit.setText("Submit");
        submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitActionPerformed(evt);
            }
        });

        jButton4.setText("Data Atual");

        txtValor.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtValor.setInheritsPopupMenu(true);
        txtValor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextField1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(submit)
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addGap(23, 23, 23))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(bdespesa)
                        .addGap(18, 18, 18)
                        .addComponent(breceita))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(txtListaFinancas, 0, 272, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField4)
                            .addComponent(txtValor))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel6, jLabel7, jLabel8});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(39, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bdespesa)
                    .addComponent(breceita))
                .addGap(53, 53, 53)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtListaFinancas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(57, 57, 57)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(35, 35, 35)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(submit)
                    .addComponent(jButton4))
                .addGap(26, 26, 26))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel6, jLabel7, jLabel8});

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/kappa.jpg"))); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Relatórios"));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton1.setText("Visualizar");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Gráficos"));

        javax.swing.GroupLayout jPanelGBLayout = new javax.swing.GroupLayout(jPanelGB);
        jPanelGB.setLayout(jPanelGBLayout);
        jPanelGBLayout.setHorizontalGroup(
            jPanelGBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 391, Short.MAX_VALUE)
        );
        jPanelGBLayout.setVerticalGroup(
            jPanelGBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 402, Short.MAX_VALUE)
        );

        tablegraficos.addTab("tab1", jPanelGB);

        javax.swing.GroupLayout jPanelGPLayout = new javax.swing.GroupLayout(jPanelGP);
        jPanelGP.setLayout(jPanelGPLayout);
        jPanelGPLayout.setHorizontalGroup(
            jPanelGPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 391, Short.MAX_VALUE)
        );
        jPanelGPLayout.setVerticalGroup(
            jPanelGPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 402, Short.MAX_VALUE)
        );

        tablegraficos.addTab("tab2", jPanelGP);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 416, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(tablegraficos)
                    .addContainerGap()))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 460, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addContainerGap(19, Short.MAX_VALUE)
                    .addComponent(tablegraficos, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(34, 34, 34))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        getAccessibleContext().setAccessibleDescription("Kappa Finances");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jFormattedTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField1ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
       
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void submitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitActionPerformed
        DefaultTableModel tabela = (DefaultTableModel) jtabela.getModel();
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        Object[] dados = {this.txtListaFinancas.getSelectedItem(), nf.format(Double.parseDouble(this.txtValor.getText().replaceAll(",", ".")))};
        if (!this.txtValor.getText().isEmpty()) {
            // Insere a financa na tabela interface grafica
            tabela.addRow(dados);
            
            // Insere a financa no XML
            Financa tempFinanca;
            if (this.bdespesa.isSelected()) {
                Despesa tempDespesa = new Despesa(Calendar.getInstance(),
                    Double.parseDouble(this.txtValor.getText().replaceAll(",", ".")),
                    CategoriaDespesa.categorias.valueOf(this.txtListaFinancas.getSelectedItem().toString()).ordinal());
                    tempFinanca = tempDespesa;
            }else {
                Receita tempReceita = new Receita(Calendar.getInstance(),
                    Double.parseDouble(this.txtValor.getText().replaceAll(",", ".")),
                    CategoriaReceita.categorias.valueOf(this.txtListaFinancas.getSelectedItem().toString()).ordinal());
                    tempFinanca = tempReceita;
            }
            
            try {
                this.ContabilidadeInterface.addFinanca(tempFinanca);
                // Atualiza o saldo na Interface
                this.txtfSaldo.setValue(this.ContabilidadeInterface.getSaldo());
            }
            catch (Exception e){
                System.out.println("EXCESSAO FATAL DETECTADA");
            }
                        
        }else{
            JOptionPane.showMessageDialog(null, "Insira um valor.");
            }
    }//GEN-LAST:event_submitActionPerformed

    private void bdespesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bdespesaActionPerformed
        this.txtListaFinancas.setModel(this.cbModelDespesa);
    }//GEN-LAST:event_bdespesaActionPerformed

    private void breceitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_breceitaActionPerformed
        this.txtListaFinancas.setModel(this.cbModelReceita);
    }//GEN-LAST:event_breceitaActionPerformed

    private void deletarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deletarMouseClicked
        
        if (this.jtabela.getSelectedRow() != -1) {
            // Remove financa do XML
            try {
                this.ContabilidadeInterface.remVoid(this.jtabela.getSelectedRow());
                // Atualiza o saldo na Interface
                this.txtfSaldo.setValue(this.ContabilidadeInterface.getSaldo());
            }
            catch (Exception e) {
                System.out.println("Excessao fatal reportada durante tentaiva de remocao de uma financa.");
            }
            
            // Remove financa da tabela da interface
            DefaultTableModel tabela = (DefaultTableModel) jtabela.getModel();
            tabela.removeRow(jtabela.getSelectedRow());
        }else {
            JOptionPane.showMessageDialog(null, "Selecione uma linha para deletar.");
        }
        
    }//GEN-LAST:event_deletarMouseClicked

    private void jPanel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel8MouseClicked
        
         DefaultTableModel tabela = (DefaultTableModel) jtabela.getModel();
         
        if (this.jtabela.getSelectedRow() != -1) {
            // Modifica financa no XML
            Financa tempFinanca;
            if (this.bdespesa.isSelected()) {
                Despesa tempDespesa = new Despesa(Calendar.getInstance(),
                    Double.parseDouble(this.txtValor.getText().replaceAll(",", ".")),
                    CategoriaDespesa.categorias.valueOf(this.txtListaFinancas.getSelectedItem().toString()).ordinal());
                    tempFinanca = tempDespesa;
            }else {
                Receita tempReceita = new Receita(Calendar.getInstance(),
                    Double.parseDouble(this.txtValor.getText().replaceAll(",", ".")),
                    CategoriaReceita.categorias.valueOf(this.txtListaFinancas.getSelectedItem().toString()).ordinal());
                    tempFinanca = tempReceita;
            }
            this.ContabilidadeInterface.setFinanca(this.jtabela.getSelectedRow(), tempFinanca);
            
            // Modifica financa na tabela da interface
            NumberFormat nf = NumberFormat.getCurrencyInstance();
            tabela.setValueAt(this.txtListaFinancas.getSelectedItem(), jtabela.getSelectedRow(), 0);
            tabela.setValueAt(nf.format(Double.parseDouble(this.txtValor.getText().replaceAll(",", "."))), jtabela.getSelectedRow(), 1);
            
            // Atualiza o saldo na Interface
            this.txtfSaldo.setValue(this.ContabilidadeInterface.getSaldo());
         } else {
            JOptionPane.showMessageDialog(null, "Selecione uma linha para editar.");
         }
    }//GEN-LAST:event_jPanel8MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interface().setVisible(true);
            }
        });
    }
    //funcao de geracao do grafico e adição aos panels correspondentes
    private void initFxComponents(){
    
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
          //gera o grafico de barras e coloca no jxfpanel
          GridPane grid = new GridPane();
          Scene scene = new Scene(grid, 400, 400);
          grid.add(gb.geraGrafico(), 0, 0);
          jxfPanelGB.setScene(scene);  
          
          //gera o grafico de pizza e coloca no jxfpanel
          grid = new GridPane();
          scene = new Scene(grid, 400, 400);
          grid.add(gp.geraGrafico(), 0, 0);
          jxfPanelGP.setScene(scene); 
      }
      });

  }
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton bdespesa;
    private javax.swing.JRadioButton breceita;
    private javax.swing.ButtonGroup btnGrpTipoFinanca;
    private javax.swing.JLabel deletar;
    private javax.swing.JLabel editar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanelGB;
    private javax.swing.JPanel jPanelGP;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTable jtabela;
    private javax.swing.JButton submit;
    private javax.swing.JTabbedPane tablegraficos;
    private javax.swing.JComboBox<String> txtListaFinancas;
    private javax.swing.JFormattedTextField txtValor;
    private javax.swing.JFormattedTextField txtfSaldo;
    // End of variables declaration//GEN-END:variables

    private void getText() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
