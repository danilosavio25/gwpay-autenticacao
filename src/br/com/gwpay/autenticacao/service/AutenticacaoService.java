package br.com.gwpay.autenticacao.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

import br.com.gwpay.autenticacao.dao.ConfigParamDao;
import br.com.gwpay.autenticacao.dao.SessaoDao;
import br.com.gwpay.autenticacao.dao.UsuarioDao;
import br.com.gwpay.autenticacao.exception.GWPayException;
import br.com.gwpay.autenticacao.model.ResultadoWS;
import br.com.gwpay.autenticacao.model.Sessao;
import br.com.gwpay.autenticacao.model.Usuario;

public class AutenticacaoService {
	
	
	public ResultadoWS gerarSessao(Usuario usuario) throws GWPayException{
		//### Declaracao de variaveis ###
		int clienteId;
		int horasExpiracaoToken = 12;
		String token = "";
		
		try {
			// ### Gera o token ###
		    token = gerarToken();

			// ### Busca clienteId do usuario ###
			UsuarioDao uDao = new UsuarioDao();
			clienteId = uDao.getClienteId(usuario.getLogin());

			// ### Busca horas expiracao na tabela de config ###
			ConfigParamDao cDao = new ConfigParamDao();
			horasExpiracaoToken = Integer.parseInt(cDao.getValor("horasExpiracaoToken"));
			
			// ### Gera as datas de sessão ###
			Date data =  new Date();
			Timestamp dataAcesso = new Timestamp(data.getTime());
			Timestamp dataExpiracao = new Timestamp(data.getTime() + horasExpiracaoToken * 60 * 60 * 1000);
			
			// ### Gera uma sessão ###
			Sessao sessao = new Sessao();
			sessao.setToken(token);
			sessao.setLogin(usuario.getLogin());
			sessao.setDataAcesso(dataAcesso);
			sessao.setDataExpiracao(dataExpiracao);
			sessao.setClienteId(clienteId);
			
			// ### Grava a sessão no banco de dados###
			SessaoDao sDao = new SessaoDao();
			sDao.inserirSessao(sessao);
			
		} catch (Exception e) {
			GWPayException exception = new GWPayException("Login inválido.");
			exception.setInfoFault("GW00", "Erro" , "Sistema temporariamente indisponível" , "Favor verificar realizar chamada novamente. Se erro persistir entre em contato com a GWPay");
			throw exception;
		
		}

		// ### Gera o ResultadoWS e retorna###
		ResultadoWS resultadoWS = new ResultadoWS();
		resultadoWS.setCodigoResposta("00");
		resultadoWS.setMensagemResposta("Autenticação realizada com sucesso.");
		resultadoWS.setToken(token);
		
		return resultadoWS;
	}
	
	public static void main(String[] args) {
		
		System.out.println("uuid = " + UUID.randomUUID().toString());
		System.out.println("uuid = " + UUID.randomUUID().toString());
		System.out.println("uuid = " + UUID.randomUUID().toString());
	}
	
	public String gerarToken(){
		return UUID.randomUUID().toString();
	}
	
	
}
