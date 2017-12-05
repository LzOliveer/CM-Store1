/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import persistencia.Conexao;
import DAO.SalarioController;
import DTO.Salario;
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
public class ifrmSalario extends javax.swing.JInternalFrame {

    Icon aviso = new ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Icones/Alerta.png"))));
    Icon erro = new ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Icones/Erro.png"))));

    /**
     * Creates new form ifrmSalario
     */
    public ifrmSalario() {
        initComponents();
        list_fun();
        list_sal();
    }

    public void setPosicao() {
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }

    public void list_fun() {
        String sql = "Select codigo,nome from funcionario";
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            rs = ps.executeQuery();
            tab_fun.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, error, "CM - Store 1.0 | Erro - Gerenciador de Salários", JOptionPane.ERROR_MESSAGE, erro);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ifrmSalario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void list_sal() {
        String sql = "Select * from salario";
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            rs = ps.executeQuery();
            tab_sal.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, error, "CM - Store 1.0 | Erro - Gerenciador de Salários", JOptionPane.ERROR_MESSAGE, erro);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ifrmSalario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void psq_fun() {
        String sql = "Select codigo,nome from funcionario where nome like ? or codigo like ?";
        PreparedStatement ps;
        ResultSet rs;
        String sql2 = "Select s.codigo, s.datainicial, s.valor, s.datafinal, s.cod_funcionario from salario s JOIN funcionario f on s.cod_funcionario = f.codigo WHERE f.nome like ? or f.codigo like ?";
        PreparedStatement ps2;
        ResultSet rs2;
        int codfunc;
        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, psq_fun.getText() + "%");
            ps.setString(2, psq_fun.getText() + "%");
            rs = ps.executeQuery();
            tab_fun.setModel(DbUtils.resultSetToTableModel(rs));
            try {
                ps2 = Conexao.getConexao().prepareStatement(sql2);
                ps2.setString(1, psq_fun.getText() + "%");
                ps2.setString(2, psq_fun.getText() + "%");
                rs2 = ps2.executeQuery();
                tab_sal.setModel(DbUtils.resultSetToTableModel(rs2));
            } catch (SQLException error) {
                JOptionPane.showMessageDialog(null, error, "CM - Store 1.0 | Erro - Gerenciador de Salários", JOptionPane.ERROR_MESSAGE, erro);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ifrmSalario.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, error, "CM - Store 1.0 | Erro - Gerenciador de Salários", JOptionPane.ERROR_MESSAGE, erro);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ifrmSalario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setNome() {
        String sql = "Select codigo,nome from funcionario where codigo like ?";
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, cod_fun.getText() + "%");
            rs = ps.executeQuery();
            tab_fun.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, error, "CM - Store 1.0 | Erro - Gerenciador de Salários", JOptionPane.ERROR_MESSAGE, erro);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ifrmSalario.class.getName()).log(Level.SEVERE, null, ex);
        }
        int select1 = tab_fun.getSelectedRow();
        psq_fun.setText(tab_fun.getModel().getValueAt(select1, 1).toString());
    }

    public void comp_ifrm() {
        int select2 = tab_sal.getSelectedRow();
        cod.setText(tab_sal.getModel().getValueAt(select2, 4).toString());
        cod_fun.setText(tab_sal.getModel().getValueAt(select2, 3).toString());
        dt_inicial.setText(tab_sal.getModel().getValueAt(select2, 0).toString());
        dt_final.setText(tab_sal.getModel().getValueAt(select2, 1).toString());
        vlr.setText(tab_sal.getModel().getValueAt(select2, 2).toString());
        setNome();
    }

    public void comp_ifrm2() {
        int select1 = tab_fun.getSelectedRow();
        cod_fun.setText(tab_fun.getModel().getValueAt(select1, 0).toString());
        psq_fun.setText(tab_fun.getModel().getValueAt(select1, 1).toString());
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
        jLabel2 = new javax.swing.JLabel();
        cod = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cod_fun = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        dt_inicial = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        dt_final = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        vlr = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        psq_fun = new br.com.cyber.componente.KTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tab_fun = new br.com.cyber.componente.Ktable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tab_sal = new br.com.cyber.componente.Ktable();
        kButton1 = new br.com.cyber.componente.KButton();
        kButton2 = new br.com.cyber.componente.KButton();
        kButton3 = new br.com.cyber.componente.KButton();
        kButton4 = new br.com.cyber.componente.KButton();
        kButton5 = new br.com.cyber.componente.KButton();
        jLabel9 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setTitle("Gerenciado de Salários | CM - Store 1.0");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/026-cash.png"))); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/039-user-6.png"))); // NOI18N
        jLabel1.setText("Pesquisa");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("Código");

        cod.setEditable(false);
        cod.setBackground(new java.awt.Color(204, 204, 204));
        cod.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cod.setForeground(new java.awt.Color(255, 255, 255));
        cod.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        cod.setToolTipText("");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("Cód. Funcionário");

        cod_fun.setEditable(false);
        cod_fun.setBackground(new java.awt.Color(204, 204, 204));
        cod_fun.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cod_fun.setForeground(new java.awt.Color(255, 255, 255));
        cod_fun.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        cod_fun.setToolTipText("");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel4.setText("Data Inicial");

        try {
            dt_inicial.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-##-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        dt_inicial.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        dt_inicial.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setText("Data Final");

        try {
            dt_final.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-##-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        dt_final.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        dt_final.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel6.setText("Valor");

        vlr.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 0, 0));
        jLabel7.setText("PARA CADASTRAR UMA NOVA ALTERAÇÃO NO SALARIO, SELECIONE UM FUNCIONARIO.");

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 0, 0));
        jLabel8.setText("PARA EDITAR UMA ALTERAÇÃO SALARIAL SELECIONE A MESMA.");

        psq_fun.setToolTipText("");
        psq_fun.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        psq_fun.setK_back_focus_gained(new java.awt.Color(254, 246, 189));
        psq_fun.setK_bord_focus_gained(new java.awt.Color(249, 182, 81));
        psq_fun.setK_placeholder_text("Código ou nome");
        psq_fun.setPreferredSize(new java.awt.Dimension(100, 28));
        psq_fun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                psq_funActionPerformed(evt);
            }
        });
        psq_fun.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                psq_funKeyReleased(evt);
            }
        });

        tab_fun.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tab_fun.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tab_fun.setHeadercolor(new java.awt.Color(140, 140, 140));
        tab_fun.setUpdateSelectionOnSort(false);
        tab_fun.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab_funMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tab_fun);

        tab_sal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tab_sal.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tab_sal.setHeadercolor(new java.awt.Color(140, 140, 140));
        tab_sal.setUpdateSelectionOnSort(false);
        tab_sal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab_salMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tab_sal);

        kButton1.setBackground(new java.awt.Color(140, 140, 140));
        kButton1.setForeground(new java.awt.Color(255, 255, 255));
        kButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/Limpar.png"))); // NOI18N
        kButton1.setText("Limpar");
        kButton1.setToolTipText("Limpa o texto do formulário");
        kButton1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        kButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButton1ActionPerformed(evt);
            }
        });

        kButton2.setBackground(new java.awt.Color(140, 140, 140));
        kButton2.setForeground(new java.awt.Color(255, 255, 255));
        kButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/exit.png"))); // NOI18N
        kButton2.setText("Sair");
        kButton2.setToolTipText("Fecha o gerenciador de salários");
        kButton2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        kButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButton2ActionPerformed(evt);
            }
        });

        kButton3.setBackground(new java.awt.Color(140, 140, 140));
        kButton3.setForeground(new java.awt.Color(255, 255, 255));
        kButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/023-delete.png"))); // NOI18N
        kButton3.setText("Excluir");
        kButton3.setToolTipText("Exclui o cadastro do salário selecionado");
        kButton3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        kButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButton3ActionPerformed(evt);
            }
        });

        kButton4.setBackground(new java.awt.Color(140, 140, 140));
        kButton4.setForeground(new java.awt.Color(255, 255, 255));
        kButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/024-edit.png"))); // NOI18N
        kButton4.setText("Atualizar");
        kButton4.setToolTipText("Atualiza o cadastro do salário selecionado de acordo com os dados informados acima");
        kButton4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        kButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButton4ActionPerformed(evt);
            }
        });

        kButton5.setBackground(new java.awt.Color(140, 140, 140));
        kButton5.setForeground(new java.awt.Color(255, 255, 255));
        kButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/025-add.png"))); // NOI18N
        kButton5.setText("Cadastrar");
        kButton5.setToolTipText("Cadastra um novo salário de acordo com os dados informados acima");
        kButton5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        kButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButton5ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 0, 0));
        jLabel9.setText("Formato: aaaa-MM-dd");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(psq_fun, javax.swing.GroupLayout.DEFAULT_SIZE, 664, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cod)
                                    .addComponent(dt_inicial)
                                    .addComponent(vlr, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cod_fun)
                                    .addComponent(dt_final, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addComponent(jLabel8)))))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(kButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(kButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(kButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(kButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(kButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(psq_fun, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cod, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(cod_fun, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(dt_inicial, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(dt_final, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(vlr, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void psq_funKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_psq_funKeyReleased
        psq_fun();
    }//GEN-LAST:event_psq_funKeyReleased

    private void tab_funMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab_funMouseClicked
        comp_ifrm2();
    }//GEN-LAST:event_tab_funMouseClicked

    private void tab_salMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab_salMouseClicked
        comp_ifrm();
    }//GEN-LAST:event_tab_salMouseClicked

    private void psq_funActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_psq_funActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_psq_funActionPerformed

    private void kButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButton2ActionPerformed
        dispose();
    }//GEN-LAST:event_kButton2ActionPerformed

    private void kButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButton1ActionPerformed
        cod.setText("");
        cod_fun.setText("");
        dt_inicial.setText("");
        dt_final.setText("");
        vlr.setText("");
        psq_fun.setText("");
        list_fun();
        list_sal();
    }//GEN-LAST:event_kButton1ActionPerformed

    private void kButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButton3ActionPerformed
        Salario sal = new Salario();
        sal.setCod_fun(Integer.parseInt(cod_fun.getText()));
        sal.setCod(Integer.parseInt(cod.getText()));
        sal.setNome(psq_fun.getText());

        if ((cod.getText().isEmpty()) || (cod_fun.getText().isEmpty()) || (psq_fun.getText().isEmpty())) {
            JOptionPane.showMessageDialog(null, "Os campos não podem estar incompletos. Favor preencher todos os campos solicitados e selecionar uma alteração salarial e um funcionário", "CM - Store 1.0 | Aviso - Gerencidor de Salários", JOptionPane.INFORMATION_MESSAGE, aviso);
        } else {

            try {

                SalarioController sc = new SalarioController();
                sc.exclui(sal);
                cod.setText("");
                cod_fun.setText("");
                dt_inicial.setText("");
                dt_final.setText("");
                vlr.setText("");
                psq_fun.setText("");
                list_fun();
                list_sal();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ifrmFuncionario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_kButton3ActionPerformed

    private void kButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButton4ActionPerformed
        Salario sal = new Salario();
        sal.setCod_fun(Integer.parseInt(cod_fun.getText()));
        sal.setDt_inicial(dt_inicial.getText());
        sal.setDt_final(dt_final.getText());
        sal.setVlr(Convrt.vtop(vlr.getText()));
        sal.setNome(psq_fun.getText());
        sal.setCod(Integer.parseInt(cod.getText()));

        if ((cod.getText().isEmpty()) || (cod_fun.getText().isEmpty()) || (psq_fun.getText().isEmpty()) || (dt_inicial.getText().isEmpty()) || (dt_final.getText().isEmpty()) || (vlr.getText().isEmpty())) {
            JOptionPane.showMessageDialog(null, "Os campos não podem estar incompletos. Favor preencher todos os campos solicitados e selecionar uma alteração salarial e um funcionário", "CM - Store 1.0 | Aviso - Gerencidor de Salários", JOptionPane.INFORMATION_MESSAGE, aviso);
        } else {

            try {

                SalarioController sc = new SalarioController();
                sc.edita(sal);
                cod.setText("");
                cod_fun.setText("");
                dt_inicial.setText("");
                dt_final.setText("");
                vlr.setText("");
                psq_fun.setText("");
                list_fun();
                list_sal();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ifrmFuncionario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_kButton4ActionPerformed

    private void kButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButton5ActionPerformed
        Salario sal = new Salario();
        sal.setCod_fun(Integer.parseInt(cod_fun.getText()));
        sal.setDt_inicial(dt_inicial.getText());
        sal.setDt_final(dt_final.getText());
        sal.setVlr(Convrt.vtop(vlr.getText()));
        sal.setNome(psq_fun.getText());

        if ((cod_fun.getText().isEmpty()) || (psq_fun.getText().isEmpty()) || (dt_inicial.getText().isEmpty()) || (dt_final.getText().isEmpty()) || (vlr.getText().isEmpty())) {
            JOptionPane.showMessageDialog(null, "Os campos não podem estar incompletos. Favor preencher todos os campos solicitados e selecionar um funcionário", "CM - Store 1.0 | Aviso - Gerencidor de Salários", JOptionPane.INFORMATION_MESSAGE, aviso);
        } else {

            try {

                SalarioController sc = new SalarioController();
                sc.cadastra(sal);
                cod.setText("");
                cod_fun.setText("");
                dt_inicial.setText("");
                dt_final.setText("");
                vlr.setText("");
                psq_fun.setText("");
                list_fun();
                list_sal();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ifrmFuncionario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_kButton5ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField cod;
    private javax.swing.JTextField cod_fun;
    private javax.swing.JFormattedTextField dt_final;
    private javax.swing.JFormattedTextField dt_inicial;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private br.com.cyber.componente.KButton kButton1;
    private br.com.cyber.componente.KButton kButton2;
    private br.com.cyber.componente.KButton kButton3;
    private br.com.cyber.componente.KButton kButton4;
    private br.com.cyber.componente.KButton kButton5;
    private br.com.cyber.componente.KTextField psq_fun;
    private br.com.cyber.componente.Ktable tab_fun;
    private br.com.cyber.componente.Ktable tab_sal;
    private javax.swing.JTextField vlr;
    // End of variables declaration//GEN-END:variables
}
