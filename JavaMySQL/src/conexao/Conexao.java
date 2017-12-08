package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.bean.Categoria;
import model.dao.CategoriaDAO;

public class Conexao {
    
    /* carregar o Driver.class dentro da biblioteca JDBC */
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/java_exemplo";
    private static final String USUARIO = "root";
    private static final String SENHA = "";
    
    public static Connection getConexao(){
        
        try{
            
            Class.forName(DRIVER);
            
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("Erro na conexão", ex);
        }
        
    } // fim getConexao
    
    public static void fecharConexao(Connection con){
        
        if(con != null){
            try {
                con.close();
            }catch (SQLException ex){
                System.err.println("ERRO!");
            }  
        }
    }
    
    public static void fecharConexao(Connection con, PreparedStatement stmt){
        
        if(stmt != null){
            try {
                con.close();
            }catch (SQLException ex){
                System.err.println("ERRO!");
            } 
        }
        fecharConexao(con);
    }
    
     public static void fecharConexao(Connection con, PreparedStatement stmt, ResultSet rs){
        
        if(rs != null){
            try {
                con.close();
            }catch (SQLException ex){
                System.err.println("ERRO!");
            } 
        }
        fecharConexao(con, stmt);
    }
    
}