/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Fabricante;
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
public class FabController {
    Icon erro = new ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icones/Erro.png"))));
    Icon ok = new ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icones/certo_1.png"))));

    public boolean cadastra(Fabricante fab) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO fabricante(cnpj,nome,endereco,telefone) VALUES(?,?,?,?)";
        PreparedStatement ps;
        ps = Conexao.getConexao().prepareStatement(sql);
        try {
            ps.setString(1, fab.getCnpj());
            ps.setString(2, fab.getNome());
            ps.setString(3, fab.getEnd());
            ps.setString(4, fab.getTel());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Fabricante " + fab.getNome() + " cadastrado com sucesso!", "CM - Store 1.0 | Aviso - Gerencidor de Fabricantes", JOptionPane.INFORMATION_MESSAGE, ok);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro, cadastro não realizado! Verifique os dados informados.\n\n" + "Erro SQL:\n" + ex, "CM - Store 1.0 | Erro - Gerenciador de Fabricantes", JOptionPane.ERROR_MESSAGE, erro);
            return false;
        }

    }

    public boolean edita(Fabricante fab) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE fabricante SET cnpj = ?,nome = ?,endereco = ?,telefone = ? where codigo = ?";
        PreparedStatement ps;
        ps = Conexao.getConexao().prepareStatement(sql);
        try {
            ps.setString(1, fab.getCnpj());
            ps.setString(2, fab.getNome());
            ps.setString(3, fab.getEnd());
            ps.setString(4, fab.getTel());
            ps.setInt(5, fab.getCod());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Cadastro do fabricante " + fab.getNome() + " atualizado com sucesso!", "CM - Store 1.0 | Aviso - Gerencidor de Fabricantes", JOptionPane.INFORMATION_MESSAGE, ok);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro, atualização do cadastro não realizada! Verifique os dados informados.\n\n" + "Erro SQL:\n" + ex, "CM - Store 1.0 | Erro - Gerenciador de Fabricantes", JOptionPane.ERROR_MESSAGE, erro);
            return false;
        }
    }

    public boolean exclui(Fabricante fab) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM fabricante where codigo=?";
        PreparedStatement ps;
        ps = Conexao.getConexao().prepareStatement(sql);
        try {
            ps.setInt(1, fab.getCod());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Exclusão do cadastro do fabricante " + fab.getNome() + " realizado com sucesso!", "CM - Store 1.0 | Aviso - Gerencidor de Fabricantes", JOptionPane.INFORMATION_MESSAGE, ok);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro, exclusão do cadastro não realizada! Verifique os dados informados.\n\n" + "Erro SQL:\n" + ex, "CM - Store 1.0 | Erro - Gerenciador de Fabricantes", JOptionPane.ERROR_MESSAGE, erro);
            return false;
        }
    }
}
