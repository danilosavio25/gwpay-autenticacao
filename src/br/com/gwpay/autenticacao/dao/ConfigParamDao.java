package br.com.gwpay.autenticacao.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.naming.InitialContext;
import javax.sql.DataSource;


public class ConfigParamDao {
	
	DataSource ds = null;
	InitialContext ic = null;
	
	
	public Connection getConnection(){
		

		Connection connection = null;

		try {
			System.out.println("Conectando com DataSource");
			 ic = new InitialContext();  
			 ds = (DataSource) ic.lookup("java:jboss/datasources/PostgreSQLDS");  
			 connection = ds.getConnection();
			 
			 
			/* String url = "jdbc:postgresql://localhost:5432/GWPayBD";  
			 String usuario = "GWPayAdminBD";  
			 String senha = "GWPayAdminBD00";
			 
			 Class.forName("org.postgresql.Driver").newInstance();  
		      connection = DriverManager.getConnection(url, usuario, senha);  */
		      

		} catch (Exception e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return null;
		}

		if (connection != null) {
			System.out.println("Conectado com sucesso!");
			return connection;
		} else {
			System.out.println("Conexao FAlhou!");
			return null;

		}

		
	}

	
	public String getValor(String chave){
		
		try {
			
			Connection conn = getConnection();
			System.out.println("after getconn");
			PreparedStatement pstmt;
			
			pstmt = conn.prepareStatement("SELECT VALOR FROM CONFIG_PARAM  WHERE CHAVE = ?");
										

			pstmt.setString(1, chave);
		

			ResultSet rs = pstmt.executeQuery();
				
			
			String valor = null;
			while (rs.next()) {
				valor =  rs.getString("valor");
			}
			rs.close();

			pstmt.close();
			conn.close();
			
			return valor;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
		
	}
	
}
