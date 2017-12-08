package model.dao;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.bean.Categoria;
import model.bean.Item;

public class ItemDAO {
    
    private Connection con = null;
    
    public void inserir(Item item){
        
        con = Conexao.getConexao();
        
        PreparedStatement stmt = null;
        
        String sql = "INSERT INTO item (descricao, qtd, valor, categoria) VALUES (?, ?, ?, ?)";
        
        try{
            
            stmt = con.prepareStatement(sql);
            
            stmt.setString(1, item.getDescricao());
            stmt.setInt(2, item.getQtd());
            stmt.setDouble(3, item.getValor());
            stmt.setInt(4, item.getCategoria().getId());
            
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            
            System.err.println("[INSERT ERROR] : " + ex.getMessage());
            
        } finally {
            Conexao.fecharConexao(con, stmt);
        }
        
    }
    
    public List<Item> selectItem(){
        
        con = Conexao.getConexao();
        
        List<Item> lstItem = new ArrayList<Item>();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        String sql = "SELECT item.id as idItem ,item.descricao as descItem, item.qtd, item.valor, ";
        sql += "categoria.id as idCat,categoria.descricao as categoria FROM item ";
        sql += "INNER JOIN categoria ON categoria.id = item.categoria";
        
        try {
            
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                
                Categoria cat = new Categoria(rs.getString("categoria"));
                cat.setId(rs.getInt("idCat"));
                
                Item item = new Item();
                item.setId(rs.getInt("idItem"));
                item.setDescricao(rs.getString("descItem"));
                item.setQtd(rs.getInt("qtd"));
                item.setValor(rs.getDouble("valor"));
                item.setCategoria(cat);
                
                lstItem.add(item);
                
            }
            
        } catch (SQLException ex) {
            
            System.err.println("[SELECT ERROR] : " + ex.getMessage());
            
        } finally {
            Conexao.fecharConexao(con, stmt, rs);
        }
       
        return lstItem;
    }
    
    public void atualizar(Item item){
        
        con = Conexao.getConexao();
        
        PreparedStatement stmt = null;
        
        String sql = "UPDATE item SET descricao = ?, qtd = ?, valor = ?, categoria = ? WHERE id = ?";
        
        try {
            
            stmt = con.prepareStatement(sql);
            
            stmt.setString(1, item.getDescricao());
            stmt.setInt(2, item.getQtd());
            stmt.setDouble(3, item.getValor());
            stmt.setInt(4, item.getCategoria().getId());
            stmt.setInt(5, item.getId());
            
            stmt.executeUpdate();
            
        } catch (SQLException ex){
            
            System.err.println("[UPDATE ERROR] : " + ex.getMessage());
            
        } finally {
            Conexao.fecharConexao(con, stmt);
        }
        
    }
    
    public void excluir(Item item){
        
        con = Conexao.getConexao();
        
        PreparedStatement stmt = null;
        
        String sql = "DELETE FROM item WHERE id = ?";
        
        try{
            
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, item.getId());
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            
            System.err.println("[DELETE ERROR] : " + ex.getMessage());
            
        } finally {
            Conexao.fecharConexao(con, stmt);
        }
    }
    
}
