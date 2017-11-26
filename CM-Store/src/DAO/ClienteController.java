/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import persistencia.Conexao;
import DTO.Cliente;
import java.awt.Toolkit;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author luizo
 */
public class ClienteController {

    Icon erro = new ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icones/Erro.png"))));
    Icon ok = new ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icones/certo_1.png"))));

    public boolean cadastra(Cliente cli) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO cliente(cod_loja,cpf,rg,nome,endereco,telefone,celular,sexo,email) VALUES(?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps;
        ps = Conexao.getConexao().prepareStatement(sql);
        try {
            ps.setInt(1, cli.getCod_emp());
            ps.setString(2, cli.getCpf());
            ps.setString(3, cli.getRg());
            ps.setString(4, cli.getNome());
            ps.setString(5, cli.getEnd());
            ps.setString(6, cli.getTel());
            ps.setString(7, cli.getCel());
            ps.setString(8, cli.getSexo());
            ps.setString(9, cli.getEmail());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Cliente " + cli.getNome() + " cadastrado com sucesso!", "CM - Store 1.0 | Aviso - Gerencidor de Clientes", JOptionPane.INFORMATION_MESSAGE, ok);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro, cadastro não realizado! Verifique os dados informados.\n\n" + "Erro SQL:\n" + ex, "CM - Store 1.0 | Erro - Gerenciador de Clientes", JOptionPane.ERROR_MESSAGE, erro);
            return false;
        }

    }

    public boolean edita(Cliente cli) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE cliente SET cod_loja = ?,cpf = ?,rg = ?,nome = ?,endereco = ?,telefone = ?,celular = ?,sexo = ?,email = ? where codigo = ?";
        PreparedStatement ps;
        ps = Conexao.getConexao().prepareStatement(sql);
        try {
            ps.setInt(1, cli.getCod_emp());
            ps.setString(2, cli.getCpf());
            ps.setString(3, cli.getRg());
            ps.setString(4, cli.getNome());
            ps.setString(5, cli.getEnd());
            ps.setString(6, cli.getTel());
            ps.setString(7, cli.getCel());
            ps.setString(8, cli.getSexo());
            ps.setString(9, cli.getEmail());
            ps.setInt(10, cli.getCod());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Cadastro do cliente " + cli.getNome() + " atualizado com sucesso!", "CM - Store 1.0 | Aviso - Gerencidor de Clientes", JOptionPane.INFORMATION_MESSAGE, ok);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro, atualização do cadastro não realizada! Verifique os dados informados.\n\n" + "Erro SQL:\n" + ex, "CM - Store 1.0 | Erro - Gerenciador de Clientes", JOptionPane.ERROR_MESSAGE, erro);
            return false;
        }
    }

    public boolean exclui(Cliente cli) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM cliente where codigo=? or cpf=? or rg=?";
        PreparedStatement ps;
        ps = Conexao.getConexao().prepareStatement(sql);
        try {
            ps.setInt(1, cli.getCod());
            ps.setString(2, cli.getCpf());
            ps.setString(3, cli.getRg());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Exclusão do cadastro do cliente " + cli.getNome() + " realizado com sucesso!", "CM - Store 1.0 | Aviso - Gerencidor de Clientes", JOptionPane.INFORMATION_MESSAGE, ok);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro, exclusão do cadastro não realizada! Verifique os dados informados.\n\n" + "Erro SQL:\n" + ex, "CM - Store 1.0 | Erro - Gerenciador de Clientes", JOptionPane.ERROR_MESSAGE, erro);
            return false;
        }
    }
}
