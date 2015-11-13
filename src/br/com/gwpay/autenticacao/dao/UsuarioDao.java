package br.com.gwpay.autenticacao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UsuarioDao {
	
	 Connection conn;
		
		public UsuarioDao() {
			ConnectionFactory connectionFactory = new ConnectionFactory();
			conn = connectionFactory.getConnection();
		}
	
	public int autenticar(String login, String senha){
		
		try {
			
			System.out.println("after getconn");
			PreparedStatement pstmt;
			
			pstmt = conn.prepareStatement("SELECT ID FROM USUARIO  WHERE LOGIN = ? AND SENHA = ?");
										

			pstmt.setString(1, login);
			pstmt.setString(2, senha);

			ResultSet rs = pstmt.executeQuery();
				
			
			int id = 0;
			while (rs.next()) {
				id =  rs.getInt("id");
			}
			rs.close();

			pstmt.close();
			conn.close();
			
			return id;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
		
	}
	
	
	public int getClienteId(String login){
		
		try {
			
			System.out.println("after getconn");
			PreparedStatement pstmt;
			
			pstmt = conn.prepareStatement("SELECT CLIENTE_ID FROM USUARIO  WHERE LOGIN = ?");
										

			pstmt.setString(1, login);

			ResultSet rs = pstmt.executeQuery();
				
			
			int id = 0;
			while (rs.next()) {
				id =  rs.getInt("cliente_id");
			}
			rs.close();

			pstmt.close();
			conn.close();
			
			return id;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
		
	}
	
}
