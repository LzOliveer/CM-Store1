/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import DAO.VendaController;
import DTO.Venda;
import Util.Convrt;
import java.awt.Toolkit;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import persistencia.Conexao;

/**
 *
 * @author luizo
 */
public class frmVenda extends javax.swing.JFrame {

    Icon aviso = new ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Icones/Alerta.png"))));
    Icon erro = new ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Icones/Erro.png"))));
    Icon ok = new ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Icones/certo_1.png"))));

    double tot = 0;
    double f;
    int cv;
    /**
     * Creates new form frmVenda
     */
    public frmVenda() {
        initComponents();
        setIcon();
        list_cli();
        list_prod();
    }

    public void list_cli() {
        String sql = "Select codigo, nome from cliente";
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            rs = ps.executeQuery();
            tab_cli.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (ClassNotFoundException | SQLException error) {
            JOptionPane.showMessageDialog(null, error, "CM - Store 1.0 | Erro - Vendas", JOptionPane.ERROR_MESSAGE, erro);
        }
    }

    public void list_prod() {
        String sql = "Select codigo, nome, prc_venda from produto";
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            rs = ps.executeQuery();
            tab_prod.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (ClassNotFoundException | SQLException error) {
            JOptionPane.showMessageDialog(null, error, "CM - Store 1.0 | Erro - Vendas", JOptionPane.ERROR_MESSAGE, erro);
        }
    }
    
    public void list_item() {
        String sql = "Select * from venda_produto";
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            rs = ps.executeQuery();
            tab_itens.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (ClassNotFoundException | SQLException error) {
            JOptionPane.showMessageDialog(null, error, "CM - Store 1.0 | Erro - Vendas", JOptionPane.ERROR_MESSAGE, erro);
        }
    }

    public void psq_cliente() {
        String sql = "Select codigo, nome from cliente where nome like ? or codigo like ?";
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, psq_cli.getText() + "%");
            ps.setString(2, psq_cli.getText() + "%");
            rs = ps.executeQuery();
            tab_cli.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (ClassNotFoundException | SQLException error) {
            JOptionPane.showMessageDialog(null, error, "CM - Store 1.0 | Erro - Vendas", JOptionPane.ERROR_MESSAGE, erro);
        }
    }

    public void psq_produto() {
        String sql = "Select codigo, nome, prc_venda from produto where codigo like ? or codbarras like ? or nome like ?";
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
            JOptionPane.showMessageDialog(null, error, "CM - Store 1.0 | Erro - Vendas", JOptionPane.ERROR_MESSAGE, erro);
        }
    }

    public void comp_ifrm() {
        int seleciona = tab_cli.getSelectedRow();
        nome_cli.setText(tab_cli.getModel().getValueAt(seleciona, 1).toString());
        cod_cli.setText(tab_cli.getModel().getValueAt(seleciona, 0).toString());
    }

    public void comp_ifrm2() {
        int seleciona = tab_prod.getSelectedRow();
        prod.setText(tab_prod.getModel().getValueAt(seleciona, 1).toString());
        cod_prod.setText(tab_prod.getModel().getValueAt(seleciona, 0).toString());
        String pv1 = tab_prod.getModel().getValueAt(seleciona, 2).toString();
        double pv2 = Double.parseDouble(pv1);
        vlr_unit.setText(Convrt.ptov(pv2));
        qtd.setText("1");
    }
    
    public void comp_cod_prod(){
        int seleciona = tab_prod.getSelectedRow();
        prod.setText(tab_prod.getModel().getValueAt(seleciona, 1).toString());
        cod_prod.setText(tab_prod.getModel().getValueAt(seleciona, 0).toString());
        String pv1 = tab_prod.getModel().getValueAt(seleciona, 2).toString();
        double pv2 = Double.parseDouble(pv1);
        vlr_unit.setText(Convrt.ptov(pv2));
    }
    
    public void comp_ifrm3(){
        int seleciona = tab_itens.getSelectedRow();
        qtd.setText(tab_itens.getModel().getValueAt(seleciona, 0).toString());
        psq_prod.setText(tab_itens.getModel().getValueAt(seleciona, 1).toString());
        comp_cod_prod();
    }

    public void calc_tot_item() {
        int q = Integer.parseInt(qtd.getText());
        double vu = Convrt.vtop(vlr_unit.getText());
        f = vu * q;
    }

    public boolean AbreOrdem() {
        String sql = "INSERT INTO venda(total) VALUES(?)";
        PreparedStatement ps;
        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setDouble(1, 0);
            ps.execute();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(frmVenda.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro, cadastro não realizado! Verifique os dados informados.\n\n" + "Erro SQL:\n" + ex, "CM - Store 1.0 | Erro - Gerenciador de Clientes", JOptionPane.ERROR_MESSAGE, erro);
            return false;
        }
    }

    private void getCodVenda() throws ClassNotFoundException {
        String sql = "select codigo from venda where total = 0.00";
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                int cod = (rs.getInt("codigo"));
                Venda v = new Venda();
                cv = cod;
                v.setCod_venda(cod);
                JOptionPane.showMessageDialog(null, "Ordem de venda " + v.getCod_venda() + " aberta com sucesso", "Aviso | CM - Store 1.0", JOptionPane.INFORMATION_MESSAGE, ok);
            } else {
                JOptionPane.showMessageDialog(null, "Erro na abertura de ordem de venda", "Erro | CM - Store 1.0", JOptionPane.ERROR_MESSAGE, erro);
            }
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Erro:\n\n" + error, "Login | CM - Store 1.0", JOptionPane.ERROR_MESSAGE, erro);
        }
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
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        prod = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        qtd = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        vlr_unit = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tab_itens = new br.com.cyber.componente.Ktable();
        jLabel9 = new javax.swing.JLabel();
        vlr_tot = new javax.swing.JTextField();
        kButton3 = new br.com.cyber.componente.KButton();
        kButton4 = new br.com.cyber.componente.KButton();
        jLabel4 = new javax.swing.JLabel();
        data = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();
        nome_cli = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        kButton5 = new br.com.cyber.componente.KButton();
        kButton6 = new br.com.cyber.componente.KButton();
        jLabel12 = new javax.swing.JLabel();
        pgt = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        cod_cli = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        cod_prod = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        psq_cli = new br.com.cyber.componente.KTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        tab_cli = new br.com.cyber.componente.Ktable();
        jPanel4 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        psq_prod = new br.com.cyber.componente.KTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        tab_prod = new br.com.cyber.componente.Ktable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Frente de Caixa - Vendas | CM - Store 1.0");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(240, 240, 240), 1, true));

        jLabel6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel6.setText("Produto");

        prod.setEditable(false);
        prod.setBackground(new java.awt.Color(140, 140, 140));
        prod.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        prod.setForeground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel7.setText("Quantidade");

        qtd.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        qtd.setToolTipText("");
        qtd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                qtdKeyReleased(evt);
            }
        });

        jLabel8.setText("Valor Unitário");

        vlr_unit.setEditable(false);
        vlr_unit.setBackground(new java.awt.Color(140, 140, 140));
        vlr_unit.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        vlr_unit.setForeground(new java.awt.Color(255, 255, 255));
        vlr_unit.setToolTipText("");

        tab_itens.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tab_itens.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tab_itens.setHeadercolor(new java.awt.Color(140, 140, 140));
        tab_itens.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab_itensMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tab_itens);

        jLabel9.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel9.setText("Total");

        vlr_tot.setEditable(false);
        vlr_tot.setBackground(new java.awt.Color(140, 140, 140));
        vlr_tot.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        vlr_tot.setForeground(new java.awt.Color(255, 255, 255));
        vlr_tot.setToolTipText("");

        kButton3.setBackground(new java.awt.Color(140, 140, 140));
        kButton3.setForeground(new java.awt.Color(255, 255, 255));
        kButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/032-cart-2.png"))); // NOI18N
        kButton3.setText("Cancelar");
        kButton3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        kButton4.setBackground(new java.awt.Color(140, 140, 140));
        kButton4.setForeground(new java.awt.Color(255, 255, 255));
        kButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/033-cart-1.png"))); // NOI18N
        kButton4.setText("Concluir");
        kButton4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        kButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButton4ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel4.setText("Data");

        data.setEditable(false);
        data.setBackground(new java.awt.Color(140, 140, 140));
        data.setForeground(new java.awt.Color(255, 255, 255));
        try {
            data.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-##-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        data.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("Cliente");

        nome_cli.setEditable(false);
        nome_cli.setBackground(new java.awt.Color(140, 140, 140));
        nome_cli.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        nome_cli.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("DADOS DA VENDA");

        kButton5.setBackground(new java.awt.Color(140, 140, 140));
        kButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/031-cart-3.png"))); // NOI18N
        kButton5.setToolTipText("Adicionar item a venda");
        kButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButton5ActionPerformed(evt);
            }
        });

        kButton6.setBackground(new java.awt.Color(140, 140, 140));
        kButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/030-cart-4.png"))); // NOI18N
        kButton6.setToolTipText("Excluir item da venda");
        kButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButton6ActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel12.setText("Pagamento");

        pgt.setBackground(new java.awt.Color(240, 240, 240));
        pgt.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        pgt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "À Vista", "Boleto", "Cartão" }));

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setText("Cód Cliente");

        cod_cli.setEditable(false);
        cod_cli.setBackground(new java.awt.Color(140, 140, 140));
        cod_cli.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cod_cli.setForeground(new java.awt.Color(255, 255, 255));
        cod_cli.setToolTipText("");

        jLabel13.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel13.setText("Cód Produto");

        cod_prod.setEditable(false);
        cod_prod.setBackground(new java.awt.Color(140, 140, 140));
        cod_prod.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cod_prod.setForeground(new java.awt.Color(255, 255, 255));
        cod_prod.setToolTipText("");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nome_cli, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cod_cli, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(data, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pgt, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(prod, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cod_prod, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(qtd, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(vlr_unit, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(23, 23, 23)
                                .addComponent(kButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(kButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(vlr_tot, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(kButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(kButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nome_cli, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(data, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(pgt, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(cod_cli, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(kButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel13)
                                .addComponent(cod_prod, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(qtd, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8)
                                .addComponent(vlr_unit, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6)
                                .addComponent(prod, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(vlr_tot, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(kButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(kButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(kButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(240, 240, 240), 1, true));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/039-user-6.png"))); // NOI18N

        psq_cli.setToolTipText("");
        psq_cli.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        psq_cli.setK_back_focus_gained(new java.awt.Color(254, 246, 189));
        psq_cli.setK_bord_focus_gained(new java.awt.Color(249, 182, 81));
        psq_cli.setK_placeholder_text("Código ou nome do cliente");
        psq_cli.setPreferredSize(new java.awt.Dimension(100, 28));
        psq_cli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                psq_cliKeyReleased(evt);
            }
        });

        tab_cli.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tab_cli.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tab_cli.setHeadercolor(new java.awt.Color(140, 140, 140));
        tab_cli.setUpdateSelectionOnSort(false);
        tab_cli.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab_cliMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tab_cli);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(psq_cli, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(psq_cli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(240, 240, 240), 1, true));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/007-delivery.png"))); // NOI18N

        psq_prod.setToolTipText("");
        psq_prod.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        psq_prod.setK_back_focus_gained(new java.awt.Color(254, 246, 189));
        psq_prod.setK_bord_focus_gained(new java.awt.Color(249, 182, 81));
        psq_prod.setK_placeholder_text("Código, cód de barras ou nome do produto");
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
        tab_prod.setUpdateSelectionOnSort(false);
        tab_prod.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab_prodMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tab_prod);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(psq_prod, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(psq_prod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tab_itensMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab_itensMouseClicked
        comp_ifrm3();
    }//GEN-LAST:event_tab_itensMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        Date dt = new Date();
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        data.setText(fmt.format(dt));
        
        AbreOrdem();
        try {
            getCodVenda();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(frmVenda.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formWindowOpened

    private void psq_cliKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_psq_cliKeyReleased
        psq_cliente();
    }//GEN-LAST:event_psq_cliKeyReleased

    private void tab_cliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab_cliMouseClicked
        comp_ifrm();
    }//GEN-LAST:event_tab_cliMouseClicked

    private void psq_prodKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_psq_prodKeyReleased
        psq_produto();
    }//GEN-LAST:event_psq_prodKeyReleased

    private void tab_prodMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab_prodMouseClicked
        comp_ifrm2();
    }//GEN-LAST:event_tab_prodMouseClicked

    private void kButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButton5ActionPerformed
        Venda v = new Venda();
        v.setCod_prod(Integer.parseInt(cod_prod.getText()));
        v.setQtd(Integer.parseInt(qtd.getText()));
        v.setValor(f);
        v.setCod_venda(cv);
        tot = tot+f;
        vlr_tot.setText(Convrt.ptov(tot));
        
        VendaController vc = new VendaController();
        try {
            vc.addItem(v);
            prod.setText("");
            cod_prod.setText("");
            qtd.setText("");
            vlr_unit.setText("");
            list_item();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(frmVenda.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_kButton5ActionPerformed

    private void kButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButton6ActionPerformed
        Venda v = new Venda();
        v.setCod_prod(Integer.parseInt(cod_prod.getText()));
        v.setCod_venda(cv);
         tot = tot - f;
        vlr_tot.setText(Convrt.ptov(tot));
        
        VendaController vc = new VendaController();
        try {
            vc.delItem(v);
            prod.setText("");
            cod_prod.setText("");
            qtd.setText("");
            vlr_unit.setText("");
            list_item();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(frmVenda.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_kButton6ActionPerformed

    private void kButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButton4ActionPerformed
        Venda v = new Venda();
        v.setCod_cli(Integer.parseInt(cod_cli.getText()));
        v.setDt(data.getText());
        v.setForma_pgt(pgt.getSelectedItem().toString());
        v.setTotal(Convrt.vtop(vlr_tot.getText()));
        v.setCod_venda(cv);
        
        if((cod_cli.getText().isEmpty()) || ("Selecione".equals(pgt.getSelectedItem()))){
            JOptionPane.showMessageDialog(null, "Favor selecionar um cliente da tabela e um meio de pagamento", "CM - Store 1.0 | Aviso - Vendas", JOptionPane.INFORMATION_MESSAGE, aviso);
        }
        
        VendaController vc = new VendaController();
        try {
            vc.finaliza(v);
            list_item();
            System.exit(0);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(frmVenda.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_kButton4ActionPerformed

    private void qtdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_qtdKeyReleased
        calc_tot_item();
    }//GEN-LAST:event_qtdKeyReleased

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
            java.util.logging.Logger.getLogger(frmVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new frmVenda().setVisible(true);
        });
    }

    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Icones/028-commerce.png")));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField cod_cli;
    private javax.swing.JTextField cod_prod;
    private javax.swing.JFormattedTextField data;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private br.com.cyber.componente.KButton kButton3;
    private br.com.cyber.componente.KButton kButton4;
    private br.com.cyber.componente.KButton kButton5;
    private br.com.cyber.componente.KButton kButton6;
    private javax.swing.JTextField nome_cli;
    private javax.swing.JComboBox<String> pgt;
    private javax.swing.JTextField prod;
    private br.com.cyber.componente.KTextField psq_cli;
    private br.com.cyber.componente.KTextField psq_prod;
    private javax.swing.JTextField qtd;
    private br.com.cyber.componente.Ktable tab_cli;
    private br.com.cyber.componente.Ktable tab_itens;
    private br.com.cyber.componente.Ktable tab_prod;
    private javax.swing.JTextField vlr_tot;
    private javax.swing.JTextField vlr_unit;
    // End of variables declaration//GEN-END:variables
}
