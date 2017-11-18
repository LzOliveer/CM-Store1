/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Produto;
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
public class ProdController {
    Icon erro = new ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icones/Erro.png"))));
    Icon ok = new ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icones/certo_1.png"))));

    public boolean cadastra(Produto prod) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO produto(codbarras,nome,apresentacao,prc_fabrica,prc_lucro,prc_venda, estoque, cod_fabricante) VALUES(?,?,?,?,?,?,?,?)";
        PreparedStatement ps;
        ps = Conexao.getConexao().prepareStatement(sql);
        try {
            ps.setLong(1, prod.getCod_bar());
            ps.setString(2, prod.getNome());
            ps.setString(3, prod.getApr());
            ps.setFloat(4, prod.getPf());
            ps.setFloat(5, prod.getLucro());
            ps.setFloat(6, prod.getPv());
            ps.setInt(7, prod.getEst());
            ps.setInt(8, prod.getCod_fab());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Produto '" + prod.getNome() + "' cadastrado com sucesso!", "CM - Store 1.0 | Aviso - Gerencidor de Produtos", JOptionPane.INFORMATION_MESSAGE, ok);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro, cadastro não realizado! Verifique os dados informados.\n\n" + "Erro SQL:\n" + ex, "CM - Store 1.0 | Erro - Gerenciador de Produtos", JOptionPane.ERROR_MESSAGE, erro);
            return false;
        }

    }

    public boolean edita(Produto prod) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE produto SET codbarras = ?,nome = ?,apresentacao = ?,prc_fabrica = ?,prc_lucro = ?,prc_venda = ?, estoque = ?,cod_fabricante = ? where codigo = ?";
        PreparedStatement ps;
        ps = Conexao.getConexao().prepareStatement(sql);
        try {
            ps.setLong(1, prod.getCod_bar());
            ps.setString(2, prod.getNome());
            ps.setString(3, prod.getApr());
            ps.setFloat(4, prod.getPf());
            ps.setFloat(5, prod.getLucro());
            ps.setFloat(6, prod.getPv());
            ps.setInt(7, prod.getEst());
            ps.setInt(8, prod.getCod_fab());
            ps.setInt(9, prod.getCod());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Cadastro do produto '" + prod.getNome() + "' atualizado com sucesso!", "CM - Store 1.0 | Aviso - Gerencidor de Produtos", JOptionPane.INFORMATION_MESSAGE, ok);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro, atualização do cadastro não realizada! Verifique os dados informados.\n\n" + "Erro SQL:\n" + ex, "CM - Store 1.0 | Erro - Gerenciador de Produtos", JOptionPane.ERROR_MESSAGE, erro);
            return false;
        }
    }

    public boolean exclui(Produto prod) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM produto where codigo=? and cod_fabricante=?";
        PreparedStatement ps;
        ps = Conexao.getConexao().prepareStatement(sql);
        try {
            ps.setInt(1, prod.getCod());
            ps.setInt(2, prod.getCod_fab());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Exclusão do cadastro do produto '" + prod.getNome() + "' realizado com sucesso!", "CM - Store 1.0 | Aviso - Gerencidor de Produtos", JOptionPane.INFORMATION_MESSAGE, ok);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro, exclusão do cadastro não realizada! Verifique os dados informados.\n\n" + "Erro SQL:\n" + ex, "CM - Store 1.0 | Erro - Gerenciador de Produtos", JOptionPane.ERROR_MESSAGE, erro);
            return false;
        }
    }
}
