/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Estoque;
import UI.frmVenda;
import java.awt.Toolkit;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import persistencia.Conexao;

/**
 *
 * @author luizo
 */
public class EstController {
    
    Icon erro = new ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icones/Erro.png"))));
    Icon ok = new ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icones/certo_1.png"))));
    
    public boolean edita(Estoque est) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE produto SET estoque = ? where codigo = ?";
        PreparedStatement ps;
        ps = Conexao.getConexao().prepareStatement(sql);
        try {
            ps.setInt(1, Integer.parseInt(est.getEst_nv()));
            ps.setInt(2, Integer.parseInt(est.getCod()));
            ps.execute();
            JOptionPane.showMessageDialog(null, "Estoque do produto '" + est.getNome() + "' atualizado com sucesso!\n\n"+"O estoque atual gora é: "+est.getEst_nv(), "CM - Store 1.0 | Aviso - Gerencidor de Produtos", JOptionPane.INFORMATION_MESSAGE, ok);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EstController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro, atualização do cadastro não realizada! Verifique os dados informados.\n\n" + "Erro SQL:\n" + ex, "CM - Store 1.0 | Erro - Gerenciador de Produtos", JOptionPane.ERROR_MESSAGE, erro);
            return false;
        }
    }
    
        public boolean venda(Estoque est) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE produto SET estoque = ? where codigo = ?";
        PreparedStatement ps;
        ps = Conexao.getConexao().prepareStatement(sql);
        try {
            ps.setInt(1, est.getEst_venda());
            ps.setInt(2, Integer.parseInt(est.getCod()));
            ps.execute();
            JOptionPane.showMessageDialog(null, "Estoque do produto '" + est.getNome() + "' atualizado com sucesso!\n\n"+"O estoque atual gora é: "+est.getEst_nv(), "CM - Store 1.0 | Aviso - Gerencidor de Produtos", JOptionPane.INFORMATION_MESSAGE, ok);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EstController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro, atualização do cadastro não realizada! Verifique os dados informados.\n\n" + "Erro SQL:\n" + ex, "CM - Store 1.0 | Erro - Gerenciador de Produtos", JOptionPane.ERROR_MESSAGE, erro);
            return false;
        }
    }
}
