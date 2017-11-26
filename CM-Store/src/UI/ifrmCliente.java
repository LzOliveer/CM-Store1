/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import DAO.ClienteController;
import persistencia.Conexao;
import DTO.Cliente;
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
public class ifrmCliente extends javax.swing.JInternalFrame {

    Icon aviso = new ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Icones/Alerta.png"))));
    Icon erro = new ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Icones/Erro.png"))));

    /**
     * Creates new form ifrmCliente
     */
    public ifrmCliente() {
        initComponents();
        list_cli();
        list_emp();
    }

    public void setPosicao() {
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }

    public void list_cli() {
        String sql = "Select codigo, nome, endereco, telefone, celular, cpf, rg, sexo, cod_loja, email from cliente";
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            rs = ps.executeQuery();
            tab_cli.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (ClassNotFoundException | SQLException error) {
            JOptionPane.showMessageDialog(null, error, "CM - Store 1.0 | Erro - Gerenciador de Clientes", JOptionPane.ERROR_MESSAGE, erro);
        }
    }

    public void list_emp() {
        String sql = "Select codigo, nome from loja";
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            rs = ps.executeQuery();
            tab_emp.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (ClassNotFoundException | SQLException error) {
            JOptionPane.showMessageDialog(null, error, "CM - Store 1.0 | Erro - Gerenciador de Clientes", JOptionPane.ERROR_MESSAGE, erro);
        }
    }

    public void psq_cliente() {
        String sql = "Select codigo, nome, endereco, telefone, celular, cpf, rg, sexo, cod_loja, email from cliente where nome like ?";
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, psq_cli.getText() + "%");
            rs = ps.executeQuery();
            tab_cli.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (ClassNotFoundException | SQLException error) {
            JOptionPane.showMessageDialog(null, error, "CM - Store 1.0 | Erro - Gerenciador de Clientes", JOptionPane.ERROR_MESSAGE, erro);
        }
    }

    public void nm_emp() {
        String sql = "Select codigo,nome from loja where codigo like ?";
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, cod_emp.getText() + "%");
            rs = ps.executeQuery();
            tab_emp.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (ClassNotFoundException | SQLException error) {
            JOptionPane.showMessageDialog(null, error, "CM - Store 1.0 | Erro - Gerenciador de Clientes", JOptionPane.ERROR_MESSAGE, erro);
        }
    }

    public void psq_empresa() {
        String sql = "Select codigo,nome from loja where nome like ?";
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, psq_emp.getText() + "%");
            rs = ps.executeQuery();
            tab_emp.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (ClassNotFoundException | SQLException error) {
            JOptionPane.showMessageDialog(null, error, "CM - Store 1.0 | Erro - Gerenciador de Clientes", JOptionPane.ERROR_MESSAGE, erro);
        }
    }

    public void comp_ifrm() {
        int seleciona = tab_cli.getSelectedRow();
        cod.setText(tab_cli.getModel().getValueAt(seleciona, 0).toString());
        nome.setText(tab_cli.getModel().getValueAt(seleciona, 1).toString());
        sexo.setSelectedItem(tab_cli.getModel().getValueAt(seleciona, 7).toString());
        cpf.setText(tab_cli.getModel().getValueAt(seleciona, 5).toString());
        rg.setText(tab_cli.getModel().getValueAt(seleciona, 6).toString());
        end.setText(tab_cli.getModel().getValueAt(seleciona, 2).toString());
        tel.setText(tab_cli.getModel().getValueAt(seleciona, 3).toString());
        email.setText(tab_cli.getModel().getValueAt(seleciona, 9).toString());
        cod_emp.setText(tab_cli.getModel().getValueAt(seleciona, 8).toString());
        cel.setText(tab_cli.getModel().getValueAt(seleciona, 4).toString());
        psq_emp.setText("");
        nm_emp();
    }

    public void comp_ifrm2() {
        int seleciona = tab_emp.getSelectedRow();
        cod_emp.setText(tab_emp.getModel().getValueAt(seleciona, 0).toString());
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
        psq_cli = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tab_cli = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        cod = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cpf = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        rg = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        nome = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        end = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        sexo = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        tel = new javax.swing.JFormattedTextField();
        jLabel10 = new javax.swing.JLabel();
        cel = new javax.swing.JFormattedTextField();
        jLabel11 = new javax.swing.JLabel();
        cod_emp = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        psq_emp = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tab_emp = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setTitle("Gerenciador de Clientes | CM - Store 1.0");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/037-user-8.png"))); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/039-user-6.png"))); // NOI18N
        jLabel1.setText("Nome");

        psq_cli.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        psq_cli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                psq_cliKeyReleased(evt);
            }
        });

        tab_cli.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tab_cli.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
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
        tab_cli.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab_cliMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tab_cli);

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("Código");

        cod.setEditable(false);
        cod.setBackground(new java.awt.Color(204, 204, 204));
        cod.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cod.setForeground(new java.awt.Color(255, 255, 255));
        cod.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("CPF");

        try {
            cpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        cpf.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel4.setText("RG");

        try {
            rg.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-#")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        rg.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setText("Nome");

        nome.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel6.setText("Endereço");

        end.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel7.setText("E-mail");

        email.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel8.setText("Sexo");

        sexo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        sexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "M", "F" }));
        sexo.setToolTipText("");

        jLabel9.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel9.setText("Telefone");

        try {
            tel.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) ####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel10.setText("Celular");

        try {
            cel.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        cel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel11.setText("Cód. Empresa");

        cod_emp.setEditable(false);
        cod_emp.setBackground(new java.awt.Color(204, 204, 204));
        cod_emp.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cod_emp.setForeground(new java.awt.Color(255, 255, 255));
        cod_emp.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Selecione uma Empresa", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 12))); // NOI18N

        jLabel12.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/009-search.png"))); // NOI18N
        jLabel12.setText("Nome");

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
        tab_emp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab_empMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tab_emp);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(psq_emp, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(psq_emp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton1.setBackground(new java.awt.Color(47, 147, 255));
        jButton1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/044-user-1.png"))); // NOI18N
        jButton1.setText("Cadastrar");
        jButton1.setToolTipText("Cadastra um novo cliente de acordo com os dados informado acima");
        jButton1.setBorder(null);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(47, 147, 255));
        jButton2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/043-user-2.png"))); // NOI18N
        jButton2.setText("Atualizar");
        jButton2.setToolTipText("Atualiza o cadastro do cliente selecionado de acordo com os dados informados acima");
        jButton2.setBorder(null);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(47, 147, 255));
        jButton3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/041-user-4.png"))); // NOI18N
        jButton3.setText("Excluir");
        jButton3.setToolTipText("Exclui o cadastro do cliente selecionado");
        jButton3.setBorder(null);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(47, 147, 255));
        jButton4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/Limpar.png"))); // NOI18N
        jButton4.setText("Limpar");
        jButton4.setToolTipText("Limpa o formulário");
        jButton4.setBorder(null);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(47, 147, 255));
        jButton5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/exit.png"))); // NOI18N
        jButton5.setText("Sair");
        jButton5.setToolTipText("Fecha o gerenciador de clientes");
        jButton5.setBorder(null);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel13.setText("PARA CADASTRAR UM CLIENTE, FAVOR SELECIONE UMA EMPRESA E APÓS PREENCHA OS CAMPOS ABAIXO");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(psq_cli)))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(end, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nome, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(sexo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jLabel9)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(tel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jLabel10)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(cel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(cod, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jLabel3)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(cpf, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jLabel4)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(rg, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jLabel11)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(cod_emp, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(email))
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(54, 54, 54))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 29, Short.MAX_VALUE))))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton2, jButton3, jButton4, jButton5});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(psq_cli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(cod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(cpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(rg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)
                            .addComponent(cod_emp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(end, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(sexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(tel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(cel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton1, jButton2, jButton3, jButton4, jButton5});

        jButton1.getAccessibleContext().setAccessibleDescription("");
        jButton2.getAccessibleContext().setAccessibleDescription("");
        jButton3.getAccessibleContext().setAccessibleDescription("");
        jButton4.getAccessibleContext().setAccessibleDescription("");
        jButton5.getAccessibleContext().setAccessibleDescription("");

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

    private void psq_cliKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_psq_cliKeyReleased
        psq_cliente();
    }//GEN-LAST:event_psq_cliKeyReleased

    private void psq_empKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_psq_empKeyReleased
        psq_empresa();
    }//GEN-LAST:event_psq_empKeyReleased

    private void tab_cliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab_cliMouseClicked
        comp_ifrm();
    }//GEN-LAST:event_tab_cliMouseClicked

    private void tab_empMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab_empMouseClicked
        comp_ifrm2();
    }//GEN-LAST:event_tab_empMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Cliente cli = new Cliente();
        cli.setCod_emp(Integer.parseInt(cod_emp.getText()));
        cli.setCpf(cpf.getText());
        cli.setRg(rg.getText());
        cli.setNome(nome.getText());
        cli.setEnd(end.getText());
        cli.setSexo(sexo.getSelectedItem().toString());
        cli.setTel(tel.getText());
        cli.setCel(cel.getText());
        cli.setEmail(email.getText());

        if ((cod_emp.getText().isEmpty()) || (cpf.getText().isEmpty()) || (rg.getText().isEmpty()) || (nome.getText().isEmpty()) || (end.getText().isEmpty()) || (tel.getText().isEmpty()) || (cel.getText().isEmpty()) || ("Selecione".equals(sexo.getSelectedItem()))) {
            JOptionPane.showMessageDialog(null, "Os campos não podem estar incompletos. Favor preencher todos os campos", "CM - Store 1.0 | Aviso - Gerencidor de Clientes", JOptionPane.INFORMATION_MESSAGE, aviso);
        } else {
            try {
                ClienteController cc = new ClienteController();
                cc.cadastra(cli);
                cod.setText("");
                cpf.setText("");
                rg.setText("");
                cod_emp.setText("");
                nome.setText("");
                end.setText("");
                tel.setText("");
                cel.setText("");
                email.setText("");
                sexo.setSelectedItem("Selecione");
                list_cli();
                list_emp();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ifrmCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        cod.setText("");
        cpf.setText("");
        rg.setText("");
        cod_emp.setText("");
        nome.setText("");
        end.setText("");
        tel.setText("");
        cel.setText("");
        email.setText("");
        sexo.setSelectedItem("Selecione");
        list_cli();
        list_emp();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Cliente cli = new Cliente();
        cli.setCod_emp(Integer.parseInt(cod_emp.getText()));
        cli.setCpf(cpf.getText());
        cli.setRg(rg.getText());
        cli.setNome(nome.getText());
        cli.setEnd(end.getText());
        cli.setSexo(sexo.getSelectedItem().toString());
        cli.setTel(tel.getText());
        cli.setCel(cel.getText());
        cli.setEmail(email.getText());
        cli.setCod(Integer.parseInt(cod.getText()));

        if ((cod.getText().isEmpty()) || (cod_emp.getText().isEmpty()) || (cpf.getText().isEmpty()) || (rg.getText().isEmpty()) || (nome.getText().isEmpty()) || (end.getText().isEmpty()) || (tel.getText().isEmpty()) || (cel.getText().isEmpty()) || ("Selecione".equals(sexo.getSelectedItem()))) {
            JOptionPane.showMessageDialog(null, "Favor selecione um cadastro existente, só após edite os dados do mesmo", "CM - Store 1.0 | Aviso - Gerencidor de Clientes", JOptionPane.INFORMATION_MESSAGE, aviso);
        } else {
            try {
                ClienteController cc = new ClienteController();
                cc.edita(cli);
                cod.setText("");
                cpf.setText("");
                rg.setText("");
                cod_emp.setText("");
                nome.setText("");
                end.setText("");
                tel.setText("");
                cel.setText("");
                email.setText("");
                sexo.setSelectedItem("Selecione");
                list_cli();
                list_emp();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ifrmCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Cliente cli = new Cliente();
        cli.setCpf(cpf.getText());
        cli.setRg(rg.getText());
        cli.setNome(nome.getText());
        cli.setCod(Integer.parseInt(cod.getText()));

        if ((cod.getText().isEmpty()) || (cpf.getText().isEmpty()) || (rg.getText().isEmpty()) || (nome.getText().isEmpty())) {
            JOptionPane.showMessageDialog(null, "Favor selecione um cadastro existente, só após exclua o mesmo", "CM - Store 1.0 | Aviso - Gerencidor de Clientes", JOptionPane.INFORMATION_MESSAGE, aviso);
        } else {
            try {
                ClienteController cc = new ClienteController();
                cc.exclui(cli);
                cod.setText("");
                cpf.setText("");
                rg.setText("");
                cod_emp.setText("");
                nome.setText("");
                end.setText("");
                tel.setText("");
                cel.setText("");
                email.setText("");
                sexo.setSelectedItem("Selecione");
                list_cli();
                list_emp();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ifrmCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField cel;
    private javax.swing.JTextField cod;
    private javax.swing.JTextField cod_emp;
    private javax.swing.JFormattedTextField cpf;
    private javax.swing.JTextField email;
    private javax.swing.JTextField end;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField nome;
    private javax.swing.JTextField psq_cli;
    private javax.swing.JTextField psq_emp;
    private javax.swing.JFormattedTextField rg;
    private javax.swing.JComboBox<String> sexo;
    private javax.swing.JTable tab_cli;
    private javax.swing.JTable tab_emp;
    private javax.swing.JFormattedTextField tel;
    // End of variables declaration//GEN-END:variables
}
