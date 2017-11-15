/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import DAO.Conexao;
import DAO.EmpresaController;
import DTO.Empresa;
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
public class ifrmEmpresa extends javax.swing.JInternalFrame {

    Icon aviso = new ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Icones/Alerta.png"))));
    Icon erro = new ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Icones/Erro.png"))));

    /**
     * Creates new form ifrmEmpresa
     */
    public ifrmEmpresa() {
        initComponents();
        list_emp();
    }

    public void setPosicao() {
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }

    public void list_emp() {
        String sql = "Select * from loja";
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            rs = ps.executeQuery();
            tab_emp.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (ClassNotFoundException | SQLException error) {
            JOptionPane.showMessageDialog(null, error, "CM - Store 1.0 | Erro - Gerenciador de Empresas", JOptionPane.ERROR_MESSAGE, erro);
        }
    }

    public void psq_empresa() {
        String sql = "Select * from loja where nome like ?";
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, psq_emp.getText() + "%");
            rs = ps.executeQuery();
            tab_emp.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (ClassNotFoundException | SQLException error) {
            JOptionPane.showMessageDialog(null, error, "CM - Store 1.0 | Erro - Gerenciador de Empresas", JOptionPane.ERROR_MESSAGE, erro);
        }
    }

    public void comp_ifrm() {
        int seleciona = tab_emp.getSelectedRow();
        cod.setText(tab_emp.getModel().getValueAt(seleciona, 0).toString());
        cnpj.setText(tab_emp.getModel().getValueAt(seleciona, 1).toString());
        ie.setText(tab_emp.getModel().getValueAt(seleciona, 2).toString());
        razao.setText(tab_emp.getModel().getValueAt(seleciona, 3).toString());
        nome.setText(tab_emp.getModel().getValueAt(seleciona, 4).toString());
        end.setText(tab_emp.getModel().getValueAt(seleciona, 5).toString());
        tel.setText(tab_emp.getModel().getValueAt(seleciona, 6).toString());
        site.setText(tab_emp.getModel().getValueAt(seleciona, 7).toString());
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
        psq_emp = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tab_emp = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        cod = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        ie = new javax.swing.JTextField();
        cnpj = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        razao = new javax.swing.JTextField();
        nome = new javax.swing.JTextField();
        end = new javax.swing.JTextField();
        tel = new javax.swing.JFormattedTextField();
        site = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        cadastrar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("Gerenciador de Empresas | CM - Store 1.0");
        setToolTipText("");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/shop.png"))); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/009-search.png"))); // NOI18N
        jLabel1.setText("Nome Fantasia");

        psq_emp.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        psq_emp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                psq_empKeyReleased(evt);
            }
        });

        tab_emp.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tab_emp.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tab_emp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tab_emp.setGridColor(new java.awt.Color(14, 147, 255));
        tab_emp.setRowSelectionAllowed(false);
        tab_emp.setSelectionBackground(new java.awt.Color(73, 173, 255));
        tab_emp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab_empMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tab_emp);
        tab_emp.getAccessibleContext().setAccessibleDescription("");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("Código");

        cod.setEditable(false);
        cod.setBackground(new java.awt.Color(204, 204, 204));
        cod.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cod.setForeground(new java.awt.Color(255, 255, 255));
        cod.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        cod.setToolTipText("");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("CNPJ");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel4.setText("IE- Inscrição Estadual");

        ie.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        try {
            cnpj.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.###/####-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        cnpj.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setText("Razão Social");

        jLabel6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel6.setText("Nome Fantasia");

        jLabel7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel7.setText("Endereço");

        jLabel8.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel8.setText("Telefone");

        jLabel9.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel9.setText("Site");

        razao.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        razao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                razaoActionPerformed(evt);
            }
        });

        nome.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        end.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        end.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                endActionPerformed(evt);
            }
        });

        try {
            tel.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) ####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                telActionPerformed(evt);
            }
        });

        site.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 0, 0));
        jLabel10.setText("**FAVOR PREENCHER TODOS OS CAMPOS ACIMA**");
        jLabel10.setToolTipText("");

        cadastrar.setBackground(new java.awt.Color(14, 147, 255));
        cadastrar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cadastrar.setForeground(new java.awt.Color(255, 255, 255));
        cadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/025-add.png"))); // NOI18N
        cadastrar.setText("Cadastrar");
        cadastrar.setToolTipText("Adiciona uma nova empresa");
        cadastrar.setBorder(null);
        cadastrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cadastrar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                cadastrarMouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                cadastrarMouseMoved(evt);
            }
        });
        cadastrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cadastrarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cadastrarMouseEntered(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cadastrarMouseReleased(evt);
            }
        });
        cadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cadastrarActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(14, 147, 255));
        jButton2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/024-edit.png"))); // NOI18N
        jButton2.setText("Editar");
        jButton2.setToolTipText("Edita o cadastro da empresa");
        jButton2.setBorder(null);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(14, 147, 255));
        jButton3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/023-delete.png"))); // NOI18N
        jButton3.setText("Excluir");
        jButton3.setToolTipText("Exclui o cadastro da empresa");
        jButton3.setBorder(null);
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(14, 147, 255));
        jButton4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/Limpar.png"))); // NOI18N
        jButton4.setText("Limpar");
        jButton4.setToolTipText("Limpa o formulário");
        jButton4.setBorder(null);
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(14, 147, 255));
        jButton5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/exit.png"))); // NOI18N
        jButton5.setText("Sair");
        jButton5.setToolTipText("Fecha o formulário");
        jButton5.setBorder(null);
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(psq_emp))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(230, 230, 230)
                                .addComponent(jLabel10))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(cod, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cnpj, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ie, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(razao)
                                    .addComponent(nome)
                                    .addComponent(end)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(tel, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(site, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(11, 11, 11)
                                        .addComponent(cadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton2)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton3)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton4)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton5)))))
                        .addGap(0, 58, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cadastrar, jButton2, jButton3, jButton4, jButton5});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(psq_emp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(ie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cnpj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(razao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(end, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(tel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(site, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(cadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cadastrar, jButton2, jButton3, jButton4, jButton5});

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

    private void razaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_razaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_razaoActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Empresa emp = new Empresa();
        emp.setCNPJ(cnpj.getText());
        emp.setIE(ie.getText());
        emp.setNOME(nome.getText());
        emp.setCOD(Integer.parseInt(cod.getText()));

        if ((cod.getText().isEmpty()) || (cnpj.getText().isEmpty()) || (ie.getText().isEmpty()) || (nome.getText().isEmpty())) {
            JOptionPane.showMessageDialog(null, "Os campos CÓDIGO, CNPJ, IE não podem estar incompletos. Favor preencher todos os campos", "CM - Store 1.0 | Aviso - Gerencidor de Empresas", JOptionPane.INFORMATION_MESSAGE, aviso);
        } else {

            try {

                EmpresaController ec = new EmpresaController();
                ec.exclui(emp);
                psq_emp.setText("");
                cod.setText("");
                cnpj.setText("");
                ie.setText("");
                razao.setText("");
                nome.setText("");
                end.setText("");
                tel.setText("");
                site.setText("");
                list_emp();

            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ifrmEmpresa.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void cadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cadastrarActionPerformed
        Empresa emp = new Empresa();
        emp.setCNPJ(cnpj.getText());
        emp.setIE(ie.getText());
        emp.setRAZAO(razao.getText());
        emp.setNOME(nome.getText());
        emp.setEND(end.getText());
        emp.setFONE(tel.getText());
        emp.setSITE(site.getText());

        if ((cnpj.getText().isEmpty()) || (ie.getText().isEmpty()) || (razao.getText().isEmpty()) || (nome.getText().isEmpty()) || (end.getText().isEmpty()) || (tel.getText().isEmpty())) {
            JOptionPane.showMessageDialog(null, "Os campos não podem estar incompletos. Favor preencher todos os campos", "CM - Store 1.0 | Aviso - Gerencidor de Empresas", JOptionPane.INFORMATION_MESSAGE, aviso);
        } else {

            try {

                EmpresaController ec = new EmpresaController();
                ec.cadastra(emp);
                psq_emp.setText("");
                cod.setText("");
                cnpj.setText("");
                ie.setText("");
                razao.setText("");
                nome.setText("");
                end.setText("");
                tel.setText("");
                site.setText("");
                list_emp();

            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ifrmEmpresa.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_cadastrarActionPerformed

    private void endActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_endActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_endActionPerformed

    private void telActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_telActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_telActionPerformed

    private void cadastrarMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cadastrarMouseDragged

    }//GEN-LAST:event_cadastrarMouseDragged

    private void cadastrarMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cadastrarMouseMoved

    }//GEN-LAST:event_cadastrarMouseMoved

    private void cadastrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cadastrarMouseClicked

    }//GEN-LAST:event_cadastrarMouseClicked

    private void cadastrarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cadastrarMouseReleased

    }//GEN-LAST:event_cadastrarMouseReleased

    private void cadastrarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cadastrarMouseEntered

    }//GEN-LAST:event_cadastrarMouseEntered

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        psq_emp.setText("");
        cod.setText("");
        cnpj.setText("");
        ie.setText("");
        razao.setText("");
        nome.setText("");
        end.setText("");
        tel.setText("");
        site.setText("");
        list_emp();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void tab_empMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab_empMouseClicked
        comp_ifrm();
    }//GEN-LAST:event_tab_empMouseClicked

    private void psq_empKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_psq_empKeyReleased
        psq_empresa();
    }//GEN-LAST:event_psq_empKeyReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Empresa emp = new Empresa();
        emp.setCNPJ(cnpj.getText());
        emp.setIE(ie.getText());
        emp.setRAZAO(razao.getText());
        emp.setNOME(nome.getText());
        emp.setEND(end.getText());
        emp.setFONE(tel.getText());
        emp.setSITE(site.getText());
        emp.setCOD(Integer.parseInt(cod.getText()));

        if ((cod.getText().isEmpty()) || (cnpj.getText().isEmpty()) || (ie.getText().isEmpty()) || (razao.getText().isEmpty()) || (nome.getText().isEmpty()) || (end.getText().isEmpty()) || (tel.getText().isEmpty())) {
            JOptionPane.showMessageDialog(null, "Os campos não podem estar incompletos. Favor preencher todos os campos", "CM - Store 1.0 | Aviso - Gerencidor de Empresas", JOptionPane.INFORMATION_MESSAGE, aviso);
        } else {

            try {

                EmpresaController ec = new EmpresaController();
                ec.edita(emp);
                psq_emp.setText("");
                cod.setText("");
                cnpj.setText("");
                ie.setText("");
                razao.setText("");
                nome.setText("");
                end.setText("");
                tel.setText("");
                site.setText("");
                list_emp();

            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ifrmEmpresa.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cadastrar;
    private javax.swing.JFormattedTextField cnpj;
    private javax.swing.JTextField cod;
    private javax.swing.JTextField end;
    private javax.swing.JTextField ie;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nome;
    private javax.swing.JTextField psq_emp;
    private javax.swing.JTextField razao;
    private javax.swing.JTextField site;
    private javax.swing.JTable tab_emp;
    private javax.swing.JFormattedTextField tel;
    // End of variables declaration//GEN-END:variables
}