package model.dao;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.bean.Categoria;

public class CategoriaDAO {
   
    private Connection con = null;

    /* Metodo que insere um registro no banco de dados na tabela categoria */
    public void inserir(Categoria categoria){
        
        con = Conexao.getConexao();
        
        PreparedStatement stmt = null;
        
        String sql = "INSERT INTO categoria (descricao) VALUES (?)";
                
        try {
            stmt = con.prepareStatement(sql);
            /* passando valores para os campos que serão preenchidos na query */
            stmt.setString(1,categoria.getDescricao());
            /* executando a query */
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            System.err.println("[INSERT ERROR]: " + ex.getMessage());
        } finally {
            Conexao.fecharConexao(con, stmt);
        }
    }
    
     /* Metodo que retorna uma lista de objetos com todos os registros existentes em categoria  */
    public List<Categoria> selectCategoria(){
        
        con = Conexao.getConexao();
        
        List<Categoria> lstCat = new ArrayList<Categoria>();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        String sql = "SELECT * FROM categoria";
        
        try {
            
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            /* executa enquanto houver linhas na tabela */
            while(rs.next()){
                
                Categoria categoria = new Categoria();
                /* passa o valor da coluna da tabela para o atributo do objeto */
                categoria.setDescricao(rs.getString("descricao"));
                
                lstCat.add(categoria);
                
            }//fim while
            
            
        } catch (SQLException ex) {
            System.err.println("[SELECT ERROR]: " + ex.getMessage());
        } finally {
            Conexao.fecharConexao(con, stmt, rs);
        }
        
        return lstCat;
    }
    
    /* Método para atualizar um registro da tabela no Banco de Dados */
     public void atualizar(Categoria categoria){
         
         con = Conexao.getConexao();
        
        PreparedStatement stmt = null;
        
        String sql = "UPDATE categoria SET descricao=? WHERE id=?";
                
        try {
            stmt = con.prepareStatement(sql);
            /* passando valores para os campos que serão preenchidos na query */
            stmt.setString(1,categoria.getDescricao());
            stmt.setInt(2, categoria.getId());
            /* executando a query */
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            System.err.println("[UPDATE ERROR]: " + ex.getMessage());
        } finally {
            Conexao.fecharConexao(con, stmt);
        }
    }
    
     /* Método para deletar um registro da tabela no Banco de Dados */
     public void excluir(Categoria categoria){
         
         con = Conexao.getConexao();
        
        PreparedStatement stmt = null;
        
        String sql = "DELETE FROM categoria WHERE id=?";
                
        try {
            stmt = con.prepareStatement(sql);
            
            stmt.setInt(1, categoria.getId());
            /* executando a query */
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            System.err.println("[DELETE ERROR]: " + ex.getMessage());
        } finally {
            Conexao.fecharConexao(con, stmt);
        }
    }
     
}
