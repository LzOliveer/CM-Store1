/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import persistencia.Conexao;
import DAO.ProdController;
import DTO.Produto;
import Util.Convrt;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author luizo
 */
public class ifrmProduto extends javax.swing.JInternalFrame {

    Icon aviso = new ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Icones/Alerta.png"))));
    Icon erro = new ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Icones/Erro.png"))));

    String pmc;
    double pfab, lucr, pfi;

    /**
     * Creates new form ifrmProduto
     */
    public ifrmProduto() {
        initComponents();
        list_prod();
        list_fab();
    }

    public void setPosicao() {
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }

    public void list_prod() {
        String sql = "Select * from produto";
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            rs = ps.executeQuery();
            tab_prod.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (ClassNotFoundException | SQLException error) {
            JOptionPane.showMessageDialog(null, error, "CM - Store 1.0 | Erro - Gerenciador de Produtos", JOptionPane.ERROR_MESSAGE, erro);
        }
    }

    public void list_fab() {
        String sql = "Select codigo, nome from fabricante";
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            rs = ps.executeQuery();
            tab_fab.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (ClassNotFoundException | SQLException error) {
            JOptionPane.showMessageDialog(null, error, "CM - Store 1.0 | Erro - Gerenciador de Produtos", JOptionPane.ERROR_MESSAGE, erro);
        }
    }

    public void psq_produto() {
        String sql = "Select * from produto where codigo like ? or codbarras like ?or nome like ?";
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, psq_prod.getText() + "%");
            ps.setString(2, psq_prod.getText() + "%");
            ps.setString(3, psq_prod.getText() + "%");
            rs = ps.executeQuery();
            tab_prod.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (ClassNotFoundException | SQLException error) {
            JOptionPane.showMessageDialog(null, error, "CM - Store 1.0 | Erro - Gerenciador de Produtos", JOptionPane.ERROR_MESSAGE, erro);
        }
    }

    public void nm_fab() {
        String sql = "Select codigo,nome from loja where codigo like ?";
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, cod_fab.getText() + "%");
            rs = ps.executeQuery();
            tab_fab.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (ClassNotFoundException | SQLException error) {
            JOptionPane.showMessageDialog(null, error, "CM - Store 1.0 | Erro - Gerenciador de Produtos", JOptionPane.ERROR_MESSAGE, erro);
        }
    }

    public void psq_fabricante() {
        String sql = "Select codigo,nome from loja where nome like ? or codigo like = ?";
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, psq_fab.getText() + "%");
            ps.setString(2, psq_fab.getText() + "%");
            rs = ps.executeQuery();
            tab_fab.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (ClassNotFoundException | SQLException error) {
            JOptionPane.showMessageDialog(null, error, "CM - Store 1.0 | Erro - Gerenciador de Produtos", JOptionPane.ERROR_MESSAGE, erro);
        }
    }

    public void comp_ifrm() {
        int seleciona = tab_prod.getSelectedRow();
        cod.setText(tab_prod.getModel().getValueAt(seleciona, 0).toString());
        cod_bar.setText(tab_prod.getModel().getValueAt(seleciona, 1).toString());
        nome.setText(tab_prod.getModel().getValueAt(seleciona, 2).toString());
        apr.setText(tab_prod.getModel().getValueAt(seleciona, 3).toString());
        String pf1 = tab_prod.getModel().getValueAt(seleciona, 4).toString();
        double pf2 = Double.parseDouble(pf1);
        pf.setText(Convrt.ptov(pf2));
        String lcr1 = tab_prod.getModel().getValueAt(seleciona, 5).toString();
        double lcr2 = Double.parseDouble(lcr1);
        lucro.setText(Convrt.ptov(lcr2));
        String pv1 = tab_prod.getModel().getValueAt(seleciona, 6).toString();
        double pv2 = Double.parseDouble(pv1);
        pv.setText(Convrt.ptov(pv2));
        est.setText(tab_prod.getModel().getValueAt(seleciona, 7).toString());
        cod_fab.setText(tab_prod.getModel().getValueAt(seleciona, 8).toString());
        psq_fab.setText("");
        nm_fab();
    }

    public void comp_ifrm2() {
        int seleciona = tab_fab.getSelectedRow();
        cod_fab.setText(tab_fab.getModel().getValueAt(seleciona, 0).toString());
    }

    public void comp_pv() {
        double pfab = Convrt.vtop(pf.getText());
        double lcr = Convrt.vtop(lucro.getText());
        double pfi = pfab + ((pfab * lcr) / 100);
        String pmc = Convrt.ptov(pfi);
        pv.setText(pmc);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        psq_prod = new br.com.cyber.componente.KTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tab_prod = new br.com.cyber.componente.Ktable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        nome = new br.com.cyber.componente.KTextField();
        apr = new br.com.cyber.componente.KTextField();
        cod_bar = new br.com.cyber.componente.KTextField();
        cod = new br.com.cyber.componente.KTextField();
        jLabel6 = new javax.swing.JLabel();
        pf = new br.com.cyber.componente.KTextField();
        jLabel7 = new javax.swing.JLabel();
        lucro = new br.com.cyber.componente.KTextField();
        jLabel8 = new javax.swing.JLabel();
        pv = new br.com.cyber.componente.KTextField();
        jLabel9 = new javax.swing.JLabel();
        est = new br.com.cyber.componente.KTextField();
        jLabel10 = new javax.swing.JLabel();
        cod_fab = new br.com.cyber.componente.KTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        psq_fab = new br.com.cyber.componente.KTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tab_fab = new br.com.cyber.componente.Ktable();
        jLabel12 = new javax.swing.JLabel();
        kButton1 = new br.com.cyber.componente.KButton();
        kButton2 = new br.com.cyber.componente.KButton();
        kButton3 = new br.com.cyber.componente.KButton();
        kButton4 = new br.com.cyber.componente.KButton();
        kButton5 = new br.com.cyber.componente.KButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("Gerenciador de Produtos | CM - Store 1.0");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/001-open-box.png"))); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/007-delivery.png"))); // NOI18N
        jLabel1.setText("Pesquisa");

        psq_prod.setToolTipText("");
        psq_prod.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        psq_prod.setK_back_focus_gained(new java.awt.Color(254, 246, 189));
        psq_prod.setK_bord_focus_gained(new java.awt.Color(249, 182, 81));
        psq_prod.setK_placeholder_text("Código, cód barras ou nome");
        psq_prod.setPreferredSize(new java.awt.Dimension(100, 28));
        psq_prod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                psq_prodKeyReleased(evt);
            }
        });

        tab_prod.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tab_prod.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tab_prod.setHeadercolor(new java.awt.Color(140, 140, 140));
        tab_prod.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab_prodMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tab_prod);

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("Código");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("Cód de Barras");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel4.setText("Nome");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setText("Apresentação");

        nome.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        nome.setK_back_focus_gained(new java.awt.Color(254, 246, 189));
        nome.setK_bord_focus_gained(new java.awt.Color(249, 182, 81));
        nome.setPreferredSize(new java.awt.Dimension(100, 28));

        apr.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        apr.setK_back_focus_gained(new java.awt.Color(254, 246, 189));
        apr.setK_bord_focus_gained(new java.awt.Color(249, 182, 81));
        apr.setPreferredSize(new java.awt.Dimension(100, 28));

        cod_bar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cod_bar.setK_back_focus_gained(new java.awt.Color(254, 246, 189));
        cod_bar.setK_bord_focus_gained(new java.awt.Color(249, 182, 81));
        cod_bar.setPreferredSize(new java.awt.Dimension(100, 28));

        cod.setEditable(false);
        cod.setForeground(new java.awt.Color(255, 255, 255));
        cod.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        cod.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cod.setK_back_color(new java.awt.Color(175, 175, 175));
        cod.setK_back_focus_gained(new java.awt.Color(175, 175, 175));
        cod.setK_back_focus_lost(new java.awt.Color(175, 175, 175));
        cod.setK_bord_color_change_text(new java.awt.Color(175, 175, 175));
        cod.setK_bord_focus_gained(new java.awt.Color(175, 175, 175));
        cod.setPreferredSize(new java.awt.Dimension(100, 28));

        jLabel6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel6.setText("Preço de Fábrica");

        pf.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        pf.setK_back_focus_gained(new java.awt.Color(254, 246, 189));
        pf.setK_bord_focus_gained(new java.awt.Color(249, 182, 81));
        pf.setK_placeholder_text("Ex: 100.00");
        pf.setPreferredSize(new java.awt.Dimension(100, 28));
        pf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                pfKeyReleased(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel7.setText("Lucro");

        lucro.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lucro.setK_back_focus_gained(new java.awt.Color(254, 246, 189));
        lucro.setK_bord_focus_gained(new java.awt.Color(249, 182, 81));
        lucro.setK_placeholder_text("Ex: 50.00%");
        lucro.setPreferredSize(new java.awt.Dimension(100, 28));
        lucro.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                lucroFocusLost(evt);
            }
        });
        lucro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lucroKeyReleased(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel8.setText("Preço de Venda");

        pv.setEditable(false);
        pv.setForeground(new java.awt.Color(255, 255, 255));
        pv.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pv.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        pv.setK_back_color(new java.awt.Color(175, 175, 175));
        pv.setK_back_focus_gained(new java.awt.Color(175, 175, 175));
        pv.setK_back_focus_lost(new java.awt.Color(175, 175, 175));
        pv.setK_bord_color_change_text(new java.awt.Color(175, 175, 175));
        pv.setK_bord_focus_gained(new java.awt.Color(175, 175, 175));
        pv.setPreferredSize(new java.awt.Dimension(100, 28));

        jLabel9.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel9.setText("Estoque");

        est.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        est.setK_back_focus_gained(new java.awt.Color(254, 246, 189));
        est.setK_bord_focus_gained(new java.awt.Color(249, 182, 81));
        est.setPreferredSize(new java.awt.Dimension(100, 28));

        jLabel10.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel10.setText("Cód Fabricante");

        cod_fab.setEditable(false);
        cod_fab.setForeground(new java.awt.Color(255, 255, 255));
        cod_fab.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        cod_fab.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cod_fab.setK_back_color(new java.awt.Color(175, 175, 175));
        cod_fab.setK_back_focus_gained(new java.awt.Color(175, 175, 175));
        cod_fab.setK_back_focus_lost(new java.awt.Color(175, 175, 175));
        cod_fab.setK_bord_color_change_text(new java.awt.Color(175, 175, 175));
        cod_fab.setK_bord_focus_gained(new java.awt.Color(175, 175, 175));
        cod_fab.setPreferredSize(new java.awt.Dimension(100, 28));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Selecione um fabricante", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 12))); // NOI18N
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        jLabel11.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/009-search.png"))); // NOI18N

        psq_fab.setToolTipText("");
        psq_fab.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        psq_fab.setK_back_focus_gained(new java.awt.Color(254, 246, 189));
        psq_fab.setK_bord_focus_gained(new java.awt.Color(249, 182, 81));
        psq_fab.setK_placeholder_text("Código ou nome");
        psq_fab.setPreferredSize(new java.awt.Dimension(100, 28));
        psq_fab.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                psq_fabKeyReleased(evt);
            }
        });

        tab_fab.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tab_fab.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tab_fab.setHeadercolor(new java.awt.Color(140, 140, 140));
        tab_fab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab_fabMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tab_fab);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(psq_fab, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE))
            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(psq_fab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );

        jLabel12.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 0, 0));
        jLabel12.setText("PARA CADASTRAR UM PRODUTO SELECIONE UM FABRICANTE");
        jLabel12.setToolTipText("");

        kButton1.setBackground(new java.awt.Color(140, 140, 140));
        kButton1.setForeground(new java.awt.Color(255, 255, 255));
        kButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/006-shipping-1.png"))); // NOI18N
        kButton1.setText("Cadastrar");
        kButton1.setToolTipText("Cadastra um novo produto");
        kButton1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        kButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButton1ActionPerformed(evt);
            }
        });

        kButton2.setBackground(new java.awt.Color(140, 140, 140));
        kButton2.setForeground(new java.awt.Color(255, 255, 255));
        kButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/002-delivery-2.png"))); // NOI18N
        kButton2.setText("Editar");
        kButton2.setToolTipText("Atualiza o cadastro de acordo com os dados informados");
        kButton2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        kButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButton2ActionPerformed(evt);
            }
        });

        kButton3.setBackground(new java.awt.Color(140, 140, 140));
        kButton3.setForeground(new java.awt.Color(255, 255, 255));
        kButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/007-shipping-1.png"))); // NOI18N
        kButton3.setText("Excluir");
        kButton3.setToolTipText("Exclui o cadastro do produto selecionado");
        kButton3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        kButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButton3ActionPerformed(evt);
            }
        });

        kButton4.setBackground(new java.awt.Color(140, 140, 140));
        kButton4.setForeground(new java.awt.Color(255, 255, 255));
        kButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/Limpar.png"))); // NOI18N
        kButton4.setText("Limpar");
        kButton4.setToolTipText("Limpa os campos de texto");
        kButton4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        kButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButton4ActionPerformed(evt);
            }
        });

        kButton5.setBackground(new java.awt.Color(140, 140, 140));
        kButton5.setForeground(new java.awt.Color(255, 255, 255));
        kButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/exit.png"))); // NOI18N
        kButton5.setText("Sair");
        kButton5.setToolTipText("Fecha o formulário");
        kButton5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        kButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 962, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(psq_prod, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel9)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGap(13, 13, 13)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel4)
                                                .addComponent(jLabel2)
                                                .addComponent(jLabel5)))
                                        .addComponent(jLabel6)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(est, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cod_fab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(pf, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lucro, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(pv, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(nome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(cod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cod_bar, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(apr, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel12)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(kButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(kButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(kButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(kButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(kButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {kButton1, kButton2, kButton3, kButton4, kButton5});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(psq_prod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(cod_bar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(apr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(pf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(lucro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(est, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(cod_fab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(kButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(kButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(kButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(kButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(kButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {kButton1, kButton2, kButton3, kButton4, kButton5});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void psq_prodKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_psq_prodKeyReleased
        psq_produto();
    }//GEN-LAST:event_psq_prodKeyReleased

    private void tab_prodMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab_prodMouseClicked
        comp_ifrm();
    }//GEN-LAST:event_tab_prodMouseClicked

    private void psq_fabKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_psq_fabKeyReleased
        psq_fabricante();
    }//GEN-LAST:event_psq_fabKeyReleased

    private void tab_fabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab_fabMouseClicked
        comp_ifrm2();
    }//GEN-LAST:event_tab_fabMouseClicked

    private void kButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButton5ActionPerformed
        dispose();
    }//GEN-LAST:event_kButton5ActionPerformed

    private void kButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButton4ActionPerformed
        psq_prod.setText("");
        cod.setText("");
        cod_bar.setText("");
        nome.setText("");
        apr.setText("");
        pf.setText("");
        lucro.setText("");
        pv.setText("");
        est.setText("");
        cod_fab.setText("");
        list_prod();
        list_fab();
    }//GEN-LAST:event_kButton4ActionPerformed

    private void kButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButton3ActionPerformed
        Produto prod = new Produto();
        prod.setNome(nome.getText());
        prod.setCod_fab(Integer.parseInt(cod_fab.getText()));
        prod.setCod(Integer.parseInt(cod.getText()));

        if ((cod.getText().isEmpty()) || (nome.getText().isEmpty()) || (cod_fab.getText().isEmpty())) {
            JOptionPane.showMessageDialog(null, "Favor selecione um cadastro existente, só após exclua o mesmo", "CM - Store 1.0 | Aviso - Gerencidor de Produtos", JOptionPane.INFORMATION_MESSAGE, aviso);
        } else {
            try {
                ProdController pc = new ProdController();
                pc.exclui(prod);
                psq_prod.setText("");
                cod.setText("");
                cod_bar.setText("");
                nome.setText("");
                apr.setText("");
                pf.setText("");
                lucro.setText("");
                pv.setText("");
                est.setText("");
                cod_fab.setText("");
                list_prod();
                list_fab();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ifrmCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_kButton3ActionPerformed

    private void kButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButton2ActionPerformed
        Produto prod = new Produto();
        prod.setCod_bar(cod_bar.getText());
        prod.setNome(nome.getText());
        prod.setApr(apr.getText());
        prod.setCod_fab(Integer.parseInt(cod_fab.getText()));
        prod.setEst(Integer.parseInt(est.getText()));
        prod.setLucro(Convrt.vtop(lucro.getText()));
        prod.setPf(Convrt.vtop(pf.getText()));
        prod.setPv(Convrt.vtop(pv.getText()));
        prod.setCod(Integer.parseInt(cod.getText()));

        if ((cod.getText().isEmpty()) || (cod_bar.getText().isEmpty()) || (est.getText().isEmpty()) || (pf.getText().isEmpty()) || (nome.getText().isEmpty()) || (pv.getText().isEmpty()) || (lucro.getText().isEmpty()) || (cod_fab.getText().isEmpty()) || (apr.getText().isEmpty())) {
            JOptionPane.showMessageDialog(null, "Favor selecione um cadastro existente, só após edite os dados do mesmo", "CM - Store 1.0 | Aviso - Gerencidor de Produtos", JOptionPane.INFORMATION_MESSAGE, aviso);
        } else {
            try {
                ProdController pc = new ProdController();
                pc.edita(prod);
                psq_prod.setText("");
                cod.setText("");
                cod_bar.setText("");
                nome.setText("");
                apr.setText("");
                pf.setText("");
                lucro.setText("");
                pv.setText("");
                est.setText("");
                cod_fab.setText("");
                list_prod();
                list_fab();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ifrmCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_kButton2ActionPerformed

    private void kButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButton1ActionPerformed
        Produto prod = new Produto();
        prod.setCod_bar(cod_bar.getText());
        prod.setNome(nome.getText());
        prod.setApr(apr.getText());
        prod.setCod_fab(Integer.parseInt(cod_fab.getText()));
        prod.setEst(Integer.parseInt(est.getText()));
        prod.setLucro(Convrt.vtop(lucro.getText()));
        prod.setPf(Convrt.vtop(pf.getText()));
        prod.setPv(Convrt.vtop(pv.getText()));

        if ((cod_bar.getText().isEmpty()) || (est.getText().isEmpty()) || (pf.getText().isEmpty()) || (nome.getText().isEmpty()) || (pv.getText().isEmpty()) || (lucro.getText().isEmpty()) || (cod_fab.getText().isEmpty()) || (apr.getText().isEmpty())) {
            JOptionPane.showMessageDialog(null, "Os campos não podem estar incompletos. Favor preencher todos os campos", "CM - Store 1.0 | Aviso - Gerencidor de Produtos", JOptionPane.INFORMATION_MESSAGE, aviso);
        } else {
            try {
                ProdController pc = new ProdController();
                pc.cadastra(prod);
                psq_prod.setText("");
                cod.setText("");
                cod_bar.setText("");
                nome.setText("");
                apr.setText("");
                pf.setText("");
                lucro.setText("");
                pv.setText("");
                est.setText("");
                cod_fab.setText("");
                list_prod();
                list_fab();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ifrmCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_kButton1ActionPerformed

    private void pfKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pfKeyReleased
        comp_pv();

    }//GEN-LAST:event_pfKeyReleased

    private void lucroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lucroKeyReleased
        comp_pv();

    }//GEN-LAST:event_lucroKeyReleased

    private void lucroFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_lucroFocusLost

    }//GEN-LAST:event_lucroFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private br.com.cyber.componente.KTextField apr;
    private br.com.cyber.componente.KTextField cod;
    private br.com.cyber.componente.KTextField cod_bar;
    private br.com.cyber.componente.KTextField cod_fab;
    private br.com.cyber.componente.KTextField est;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private br.com.cyber.componente.KButton kButton1;
    private br.com.cyber.componente.KButton kButton2;
    private br.com.cyber.componente.KButton kButton3;
    private br.com.cyber.componente.KButton kButton4;
    private br.com.cyber.componente.KButton kButton5;
    private br.com.cyber.componente.KTextField lucro;
    private br.com.cyber.componente.KTextField nome;
    private br.com.cyber.componente.KTextField pf;
    private br.com.cyber.componente.KTextField psq_fab;
    private br.com.cyber.componente.KTextField psq_prod;
    private br.com.cyber.componente.KTextField pv;
    private br.com.cyber.componente.Ktable tab_fab;
    private br.com.cyber.componente.Ktable tab_prod;
    // End of variables declaration//GEN-END:variables
}
