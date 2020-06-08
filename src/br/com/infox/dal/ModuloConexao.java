package br.com.infox.dal;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;

public class ModuloConexao {
    //Metodo responsável por estabelecer a conexão com o banco.
    public static Connection conector(){
    java.sql.Connection conexao = null;
    // A linha abaixo chama o Driver responsável pela conexão ao banco de dados.
    String driver ="org.postgresql.Driver";
    //Armazenando Informações referentes ao banco.
    String url = "jdbc:postgresql://127.0.0.1:5432/ProjetoIntegrador";
    String user = "postgres";
    String password = "@ti#10";
    //Estabelecendo a conexão com o banco de dados.
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user,password);
            return conexao;
        } catch (Exception e) {
            //A linha abaixo serve de apoio para esclarecer o erro ao banco.
            //System.out.println(e);
            return null;
        }
    
    }
}