package br.com.gwpay.autenticacao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import br.com.gwpay.autenticacao.model.Sessao;



public class SessaoDao {
	
		
		public SessaoDao() {
		}
	
	
	public boolean inserirSessao(Sessao sessao){
		
		try {
			
				ConnectionFactory connectionFactory = new ConnectionFactory();
				Connection conn = connectionFactory.getConnection();
				System.out.println("after getconn");
				PreparedStatement pstmt;
				
				String sql = "INSERT INTO sessao(terminal_id, token, login, dat_expiracao, dat_acesso, cliente_id) VALUES (?, ?, ?, ?, ?, ?)";
				
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, null);
				pstmt.setString(2, sessao.getToken());
				pstmt.setString(3, sessao.getLogin());
				pstmt.setTimestamp(4, sessao.getDataExpiracao());
				pstmt.setTimestamp(5, sessao.getDataAcesso());
				pstmt.setInt(6, sessao.getClienteId());
				
				pstmt.executeUpdate();
	
				pstmt.close();
				conn.close();
				
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		
	}
	
	public static void main(String[] args) {
		Sessao s = new Sessao();
		s.setToken("xxxxxxx-xxxxxxx-xxxxxx-xxxxxxxxx");
		s.setLogin("danilo.savio");
		
		Date data =  new Date();
		Timestamp hoje = new Timestamp(data.getTime());
		Timestamp expiracao = new Timestamp(data.getTime() + 12 * 60 * 60 * 1000);
		
		
		s.setDataAcesso(hoje);
		s.setDataExpiracao(expiracao);
		s.setClienteId(1);
		
		
		SessaoDao h = new SessaoDao();
		//h.inserirSessao(s);
		
	}


	
	
	
	
	
}
