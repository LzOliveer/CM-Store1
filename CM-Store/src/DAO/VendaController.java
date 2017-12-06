/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Venda;
import Util.Convrt;
import java.awt.Toolkit;
import java.sql.Date;
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
public class VendaController {

    Icon erro = new ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icones/Erro.png"))));
    Icon ok = new ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icones/certo_1.png"))));

   public boolean finaliza(Venda v) throws SQLException, ClassNotFoundException {
        String sql = "Update venda set total = ?, cod_cliente = ?, dia = ?, forma_pgt = ? where codigo = ?";
        PreparedStatement ps;
        ps = Conexao.getConexao().prepareStatement(sql);
        try {
            ps.setDouble(1, v.getTotal());
            ps.setInt(2, v.getCod_cli());
            ps.setDate(3, Date.valueOf(v.getDt()));
            ps.setString(4, v.getForma_pgt());
            ps.setInt(5, v.getCod_venda());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Venda " + v.getCod_venda() + " finalizada com sucesso!\n\n"+"Código da Venda: "+v.getCod_venda()+"\n\n"+"Forma de Pagamento: "+v.getForma_pgt()+"\n\n"+"Total: R$ "+ Convrt.ptov(v.getTotal()), "CM - Store 1.0 | Aviso - Vendas", JOptionPane.INFORMATION_MESSAGE, ok);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro, venda não finalizada!\n\n" + "Erro SQL:\n" + ex, "CM - Store 1.0 | Erro - Vendas", JOptionPane.ERROR_MESSAGE, erro);
            return false;
        }

    }
   
    public boolean canc_prod(Venda v) throws SQLException, ClassNotFoundException {
        String sql = "Delete from venda_produto where cod_venda = ?";
        PreparedStatement ps;
        ps = Conexao.getConexao().prepareStatement(sql);
        try {
            ps.setInt(1, v.getCod_venda());
            ps.execute();            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro, venda não cancela!\n\n" + "Erro SQL:\n" + ex, "CM - Store 1.0 | Erro - Vendas", JOptionPane.ERROR_MESSAGE, erro);
            return false;
        }

    }
    
        public boolean cancela(Venda v) throws SQLException, ClassNotFoundException {
        String sql = "Delete from venda where codigo = ?";
        PreparedStatement ps;
        ps = Conexao.getConexao().prepareStatement(sql);
        try {
            canc_prod(v);
            ps.setInt(1, v.getCod_venda());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Venda " + v.getCod_venda() + " cancelada com sucesso!", "CM - Store 1.0 | Aviso - Vendas", JOptionPane.INFORMATION_MESSAGE, ok);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro, venda não cancela!\n\n" + "Erro SQL:\n" + ex, "CM - Store 1.0 | Erro - Vendas", JOptionPane.ERROR_MESSAGE, erro);
            return false;
        }

    }

    public boolean addItem(Venda v) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO venda_produto (quantidade, cod_produto, cod_venda, valor) values(?,?,?,?)";
        PreparedStatement ps;
        ps = Conexao.getConexao().prepareStatement(sql);
        try {
            ps.setInt(1, v.getQtd());
            ps.setInt(2, v.getCod_prod());
            ps.setInt(3, v.getCod_venda());
            ps.setDouble(4, v.getValor());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro, produto não inserido na venda!\n\n" + "Erro SQL:\n" + ex, "CM - Store 1.0 | Erro - Vendas", JOptionPane.ERROR_MESSAGE, erro);
            return false;
        }
    }

    public boolean delItem(Venda v) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM venda_produto where cod_venda=? and cod_produto=?";
        PreparedStatement ps;
        ps = Conexao.getConexao().prepareStatement(sql);
        try {
            ps.setInt(1, v.getCod_venda());
            ps.setInt(2, v.getCod_prod());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro, exclusão do produto na venda "+v.getCod_venda()+" não realizada!\n\n" + "Erro SQL:\n" + ex, "CM - Store 1.0 | Erro - Vendas", JOptionPane.ERROR_MESSAGE, erro);
            return false;
        }
    }
    
    public boolean gerEstoque(Venda v) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE produto SET estoque = ? where codigo = ?";
        PreparedStatement ps;
        ps = Conexao.getConexao().prepareStatement(sql);
        try {
            ps.setInt(1, v.getEst());
            ps.setInt(2, v.getCod_prod());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EstController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro, baixa no estoque não realizada!\n\n" + "Erro SQL:\n" + ex, "CM - Store 1.0 | Erro - Vendas", JOptionPane.ERROR_MESSAGE, erro);
            return false;
        }
    }
}
