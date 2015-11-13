package br.com.gwpay.autenticacao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ConfigParamDao {
	
	
	public ConfigParamDao() {
	}

	
	public String getValor(String chave){
		
		try {
			
			ConnectionFactory connectionFactory = new ConnectionFactory();
			Connection conn = connectionFactory.getConnection();
			
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
