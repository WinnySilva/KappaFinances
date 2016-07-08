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
import Relatorios.Relatorio;
import XMLHandler.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author fabio
 */
public class Interface extends javax.swing.JFrame {
    private Contabilidade ContabilidadeInterface = new Contabilidade();

    //variaveis para mostrar os graficos na na GUI
    private JFXPanel jxfPanelGB_despesa;
    private JFXPanel jxfPanelGP_despesa;
    private GraficoBarra gb_despesa = new GraficoBarra(ContabilidadeInterface,Relatorio.DESPESA);
    private GraficoPizza gp_despesa = new GraficoPizza(ContabilidadeInterface,Relatorio.DESPESA);
    
    private JFXPanel jxfPanelGB_receita;
    private JFXPanel jxfPanelGP_receita;
    private GraficoBarra gb_receita = new GraficoBarra(ContabilidadeInterface,Relatorio.RECEITA);
    private GraficoPizza gp_receita = new GraficoPizza(ContabilidadeInterface,Relatorio.RECEITA);
    
    //----------
    
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
        jxfPanelGB_despesa = new JFXPanel();
        jxfPanelGP_despesa = new JFXPanel();
        jxfPanelGB_receita = new JFXPanel();
        jxfPanelGP_receita = new JFXPanel();
       
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
        jComboBox1.setSelectedIndex( Calendar.getInstance().get(Calendar.MONTH));
        // Le do XML as financas e adiciona elas a tabela da interface
        FileHandler fh;
        ArrayList<Financa> al;
        try {
            fh = new FileHandler();
            al = fh.loadMonth(this.intToMonth(Calendar.getInstance().get(Calendar.MONTH)),
                    Calendar.getInstance().get(Calendar.YEAR));
            carregaTabela(al);
//            NumberFormat nf = NumberFormat.getCurrencyInstance();
//            for (Financa f: al) {
//                DefaultTableModel tabela = (DefaultTableModel) jtabela.getModel();
//                if (f instanceof Despesa) {
//                    Object[] dados = {CategoriaDespesa.categorias.values()[f.getCategoria()], nf.format(f.getValue())};
//                    tabela.addRow(dados);
//                    
//                }
//                else {
//                    Object[] dados = {CategoriaReceita.categorias.values()[f.getCategoria()], nf.format(f.getValue())};
//                    tabela.addRow(dados);
//                }
//            }
        /*} catch (IOException ex) {
            Logger.getLogger(Contabilidade.class.getName()).log(Level.SEVERE, null, ex);*/
        } catch (Exception e) {
            System.out.println("EXCESSAO FATAL DETECTADA");
        }
        this.txtfSaldo.setValue(this.ContabilidadeInterface.getSaldo());
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        this.jtext_mes.setText(sdf.format(new Date()));
    }

    
    void carregaTabela(ArrayList<Financa> al){
        NumberFormat nf = NumberFormat.getCurrencyInstance();
            for (Financa f: al) {
                DefaultTableModel tabela = (DefaultTableModel) jtabela.getModel();
                if (f instanceof Despesa) {
                    Object[] dados = {CategoriaDespesa.categorias.values()[f.getCategoria()], nf.format(f.getValue())};
                    tabela.addRow(dados);
                    
                }
                else {
                    Object[] dados = {CategoriaReceita.categorias.values()[f.getCategoria()], nf.format(f.getValue())};
                    tabela.addRow(dados);
                }
            }
    }
    
    /**
     * 
     */
    private void carregarGraficos(){
        // isso funciona
        JPanel graficoPanel;
        graficoPanel = new JPanel(new BorderLayout() );
        graficoPanel.add(jxfPanelGB_despesa, BorderLayout.CENTER) ;
        graficoPanel.setSize( jPanelGB_despesa.getWidth() , jPanelGB_despesa.getHeight());
        jPanelGB_despesa.add(graficoPanel);
        jPanelGB_despesa.updateUI();
        
        graficoPanel = new JPanel(new BorderLayout() );
        graficoPanel.add(jxfPanelGP_despesa, BorderLayout.CENTER) ;
        graficoPanel.setSize( jPanelGP_despesa.getWidth() , jPanelGP_despesa.getHeight());
        jPanelGP_despesa.add(graficoPanel);
        jPanelGP_despesa.updateUI();
        
        
        graficoPanel = new JPanel(new BorderLayout() );
        graficoPanel.add(jxfPanelGP_receita, BorderLayout.CENTER) ;
        graficoPanel.setSize( jPanelGP_receita1.getWidth() , jPanelGP_receita1.getHeight());
        jPanelGP_receita1.add(graficoPanel);
        jPanelGP_receita1.updateUI();
        
        
        graficoPanel = new JPanel(new BorderLayout() );
        graficoPanel.add(jxfPanelGB_receita, BorderLayout.CENTER) ;
        graficoPanel.setSize( jPanelGB_receita.getWidth() , jPanelGB_receita.getHeight());
        jPanelGB_receita.add(graficoPanel);
        jPanelGB_receita.updateUI();
        
        
        
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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                                                    
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
        jButton1 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox();
        jComboBox2 = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        bdespesa = new javax.swing.JRadioButton();
        breceita = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtListaFinancas = new javax.swing.JComboBox<String>();
        submit = new javax.swing.JButton();
        txtValor = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        jtext_mes = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        tablegraficos = new javax.swing.JTabbedPane();
        jPanelGP_despesa = new javax.swing.JPanel();
        jPanelGB_despesa = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanelGP_receita = new javax.swing.JTabbedPane();
        jPanelGB_receita = new javax.swing.JPanel();
        jPanelGP_receita1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Kappa Finances");

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Informações"));
        jPanel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel8MouseClicked(evt);
            }
        });
        jtabela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabela_clicada(evt);
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
                .addComponent(txtfSaldo)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtfSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        editar.setDisplayedMnemonic('e');
        editar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/application_edit.png"))); // NOI18N
        editar.setToolTipText("editar");
        editar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editarMouseClicked(evt);
            }
        });

        deletar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/cross.png"))); // NOI18N
        deletar.setToolTipText("excluir");
        deletar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deletarMouseClicked(evt);
            }
        });

        

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Janeiro","Fevereiro","Março",
        "Abril","Maio","Junho","Julho","Agosto","Setembro","Outubro","Novembro","Dezembro"}));
        jComboBox1.setSelectedIndex(4);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2016" }));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(2, 2, 2))))
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(editar, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deletar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(editar)
                    .addComponent(deletar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        jButton1.setText("Carregar mês");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt){ 
            jButton1ActionPerformed(evt);
            }
        } );
//        jButton1.addActionListener( new java.awt.event.ActionListener(){
//            @Override
//            public void actionPerformed(ActionEvent e) {
//            jButton1ActionPerformed(e);
//            }
//        });
//        
        
        jLabel6.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel6.setText("Lista");

        jLabel7.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel7.setText("R$");

        txtListaFinancas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        submit.setText("Submeter");
        submit.setToolTipText("submeter");
        submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitActionPerformed(evt);
            }
        });
        
       
        txtValor.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtValor.setInheritsPopupMenu(true);
        txtValor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextField1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Data Atual");

        jtext_mes.setEditable(false);
        jtext_mes.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtext_mes.setToolTipText("");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(txtListaFinancas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(txtValor))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(submit))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(bdespesa)
                        .addGap(18, 18, 18)
                        .addComponent(breceita)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtext_mes, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel6, jLabel7});

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
                .addGap(32, 32, 32)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addComponent(submit)
                .addGap(46, 46, 46)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtext_mes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel6, jLabel7});

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/kappa.jpg"))); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Relatórios"));

        tablegraficos.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

        javax.swing.GroupLayout jPanelGP_despesaLayout = new javax.swing.GroupLayout(jPanelGP_despesa);
        jPanelGP_despesa.setLayout(jPanelGP_despesaLayout);
        jPanelGP_despesaLayout.setHorizontalGroup(
            jPanelGP_despesaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 376, Short.MAX_VALUE)
        );
        jPanelGP_despesaLayout.setVerticalGroup(
            jPanelGP_despesaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 511, Short.MAX_VALUE)
        );

        tablegraficos.addTab("Gráfico Pizza", jPanelGP_despesa);

        javax.swing.GroupLayout jPanelGB_despesaLayout = new javax.swing.GroupLayout(jPanelGB_despesa);
        jPanelGB_despesa.setLayout(jPanelGB_despesaLayout);
        jPanelGB_despesaLayout.setHorizontalGroup(
            jPanelGB_despesaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 376, Short.MAX_VALUE)
        );
        jPanelGB_despesaLayout.setVerticalGroup(
            jPanelGB_despesaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 511, Short.MAX_VALUE)
        );

        tablegraficos.addTab("Gráfico Barras", jPanelGB_despesa);

        jTabbedPane1.addTab("Despesas", tablegraficos);

        jPanelGP_receita.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);
        jPanelGP_receita.setToolTipText("");

        javax.swing.GroupLayout jPanelGB_receitaLayout = new javax.swing.GroupLayout(jPanelGB_receita);
        jPanelGB_receita.setLayout(jPanelGB_receitaLayout);
        jPanelGB_receitaLayout.setHorizontalGroup(
            jPanelGB_receitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 376, Short.MAX_VALUE)
        );
        jPanelGB_receitaLayout.setVerticalGroup(
            jPanelGB_receitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 511, Short.MAX_VALUE)
        );

        jPanelGP_receita.addTab("Gráfico Barras", jPanelGB_receita);

        javax.swing.GroupLayout jPanelGP_receita1Layout = new javax.swing.GroupLayout(jPanelGP_receita1);
        jPanelGP_receita1.setLayout(jPanelGP_receita1Layout);
        jPanelGP_receita1Layout.setHorizontalGroup(
            jPanelGP_receita1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 376, Short.MAX_VALUE)
        );
        jPanelGP_receita1Layout.setVerticalGroup(
            jPanelGP_receita1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 511, Short.MAX_VALUE)
        );

        jPanelGP_receita.addTab("Gráfico Pizza", jPanelGP_receita1);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelGP_receita)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelGP_receita)
        );

        jTabbedPane1.addTab("Receitas", jPanel5);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
    }// </editor-fold>                                               

    private void jFormattedTextField1ActionPerformed(java.awt.event.ActionEvent evt) {                                                     
        // TODO add your handling code here:
    }                                                    

    private void submitActionPerformed(java.awt.event.ActionEvent evt) {                                       
        System.out.println(this.txtValor.getText());
        if((this.txtValor.getText().isEmpty()) ||(!this.txtValor.getText().matches("^([,.\\d]+)([,.]\\d{2})$")  ))   {
            JOptionPane.showMessageDialog(null, "Insira um valor válido.");
            return;
        }
        
               
        DefaultTableModel tabela = (DefaultTableModel) jtabela.getModel();
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        
        
        if (!this.txtValor.getText().isEmpty()) {
        
            
            // Insere a financa no XML
            Financa tempFinanca;
            if (this.bdespesa.isSelected()) {
                Despesa tempDespesa = new Despesa(Calendar.getInstance(),
                    Double.parseDouble(this.txtValor.getText().replaceAll(",", ".")),
                    CategoriaDespesa.categorias.valueOf(this.txtListaFinancas.getSelectedItem().toString()).ordinal());
            
                tempFinanca = tempDespesa;
            
                // Insere a financa na tabela interface grafica
                Object[] dados = {
                this.txtListaFinancas.getSelectedItem(),
                nf.format(-Double.parseDouble(this.txtValor.getText().replaceAll(",", ".")))
                };
                tabela.addRow(dados);
                
            
            }else {
                Receita tempReceita = new Receita(Calendar.getInstance(),
                    Double.parseDouble(this.txtValor.getText().replaceAll(",", ".")),
                    CategoriaReceita.categorias.valueOf(this.txtListaFinancas.getSelectedItem().toString()).ordinal());
                tempFinanca = tempReceita;
                
                Object[] dados = {
                this.txtListaFinancas.getSelectedItem(),
                nf.format(Double.parseDouble(this.txtValor.getText().replaceAll(",", ".")))
                };
                tabela.addRow(dados);
                
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
    }                                      

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {                                           

    }                                          

    private void breceitaActionPerformed(java.awt.event.ActionEvent evt) {                                         
        this.txtListaFinancas.setModel(this.cbModelReceita);
    }                                        

    private void bdespesaActionPerformed(java.awt.event.ActionEvent evt) {                                         
        this.txtListaFinancas.setModel(this.cbModelDespesa);
    }                                        

    private void jPanel8MouseClicked(java.awt.event.MouseEvent evt) {                                     

    }                                    
    private void deletarMouseClicked(java.awt.event.MouseEvent evt) {                                     

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
    }
    private void tabela_clicada(java.awt.event.MouseEvent evt){
        System.out.println("TABELA CLICADA "+jtabela.getSelectedRow() );
        String valor = ""+jtabela.getValueAt(jtabela.getSelectedRow(),1);
        valor = valor.replace("-R$ " , "");
        valor = valor.replace("." , "");
        this.txtValor.setText(valor);
        //this.txtListaFinancas.getSelectedItem()
        this.txtListaFinancas.setSelectedItem( jtabela.getValueAt(jtabela.getSelectedRow(),0 ) );
    }

    private void editarMouseClicked(java.awt.event.MouseEvent evt) {                                    
        if((this.txtValor.getText().isEmpty()) ||(!this.txtValor.getText().matches("^([,.\\d]+)([,.]\\d{2})$")  ))   {
            JOptionPane.showMessageDialog(null, "Insira um valor válido.");
            return;
        }
        
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
            if(this.bdespesa.isSelected()){
                tabela.setValueAt(nf.format(-Double.parseDouble(this.txtValor.getText().replaceAll(",", "."))), jtabela.getSelectedRow(), 1);
            }else{
                tabela.setValueAt(nf.format(Double.parseDouble(this.txtValor.getText().replaceAll(",", "."))), jtabela.getSelectedRow(), 1);  
            }
            // Atualiza o saldo na Interface
            this.txtfSaldo.setValue(this.ContabilidadeInterface.getSaldo());
        } else {
            JOptionPane.showMessageDialog(null, "Selecione uma linha para editar.");
        }
    }                                   

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
          //gera o grafico de barras de despesas e coloca no jxfpanel
          GridPane grid = new GridPane();
          Scene scene = new Scene(grid, 400, 400);
          grid.add(gb_despesa.geraGrafico(), 0, 0);
          jxfPanelGB_despesa.setScene(scene);  
          
          //gera o grafico de barra de receitas e coloca no jxfpanel
          grid = new GridPane();
          scene = new Scene(grid, 400, 400);
          grid.add(gb_receita.geraGrafico(), 0, 0);
          jxfPanelGB_receita.setScene(scene);
          
          //gera o grafico de pizza de despesas e coloca no jxfpanel
          grid = new GridPane();
          scene = new Scene(grid, 400, 400);
          grid.add(gp_despesa.geraGrafico(), 0, 0);
          jxfPanelGP_despesa.setScene(scene);          
          
          //gera o grafico de pizza de receitas e coloca no jxfpanel
          grid = new GridPane();
          scene = new Scene(grid, 400, 400);
          grid.add(gp_receita.geraGrafico(), 0, 0);
          jxfPanelGP_receita.setScene(scene);
          
      }
      });

  }
    
    // Variables declaration - do not modify                                          
    private javax.swing.JRadioButton bdespesa;
    private javax.swing.JRadioButton breceita;
    private javax.swing.ButtonGroup btnGrpTipoFinanca;
    private javax.swing.JLabel deletar;
    private javax.swing.JLabel editar;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanelGB_despesa;
    private javax.swing.JPanel jPanelGB_receita;
    private javax.swing.JPanel jPanelGP_despesa;
    private javax.swing.JTabbedPane jPanelGP_receita;
    private javax.swing.JPanel jPanelGP_receita1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jtabela;
    private javax.swing.JTextField jtext_mes;
    private javax.swing.JButton submit;
    private javax.swing.JTabbedPane tablegraficos;
    private javax.swing.JComboBox<String> txtListaFinancas;
    private javax.swing.JFormattedTextField txtValor;
    private javax.swing.JFormattedTextField txtfSaldo;
    // End of variables declaration                                     

    private void jButton1ActionPerformed(java.awt.event.MouseEvent evt) {
        int index = this.jComboBox1.getSelectedIndex();
       
        //jComboBox1;
   // private javax.swing.JComboBox jComboBox2;
        FileHandler fh = new FileHandler();
        ArrayList<Financa> al = fh.loadMonth(
                this.intToMonth(index),
                    2016);
        if((al.isEmpty())){
            JOptionPane.showMessageDialog(null, "Não há dados registrados neste mês");
            jComboBox1.setSelectedIndex( Calendar.getInstance().get(Calendar.MONTH));
            return;
        }
        
        DefaultTableModel tabela = (DefaultTableModel) jtabela.getModel();
        System.out.println(tabela.getRowCount());
        int rowCount = tabela.getRowCount();
        for(int i=0;i<rowCount;i++){
            tabela.removeRow(0);
        }
        this.carregaTabela(al);
        this.ContabilidadeInterface.setFinancas(al);
        if(index != Calendar.getInstance().get(Calendar.MONTH) ){
            this.submit.setEnabled(false);
            this.editar.setEnabled(false);
            this.deletar.setEnabled(false);
        }
        else{
            this.submit.setEnabled(true);
            this.editar.setEnabled(true);
            this.deletar.setEnabled(true);
        }
        
        
    }
}
