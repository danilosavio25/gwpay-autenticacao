package br.com.gwpay.autenticacao.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;
import javax.xml.bind.annotation.XmlElement;

import br.com.gwpay.autenticacao.dao.UsuarioDao;
import br.com.gwpay.autenticacao.exception.GWPayException;
import br.com.gwpay.autenticacao.model.ResultadoWS;
import br.com.gwpay.autenticacao.model.Usuario;
import br.com.gwpay.autenticacao.service.AutenticacaoService;


@WebService
@SOAPBinding(style=Style.DOCUMENT, use=Use.LITERAL, parameterStyle=ParameterStyle.WRAPPED)
public class AutenticacaoWS {
		
	@WebMethod(operationName="autenticacao")
	@WebResult(name="resultadoWS")
	public ResultadoWS autenticar(@XmlElement(required=true) @WebParam(name="Usuario") Usuario usuario) throws GWPayException{
		//### Autenticação ###
		UsuarioDao uDao = new UsuarioDao();
		int usuarioId = uDao.autenticar(usuario.getLogin(), usuario.getSenha());
		if(usuarioId == 0 ){
			GWPayException exception = new GWPayException("Login inválido.");
			exception.setInfoFault("GW04", "Login Inválido" , "Usuário e/ou senha inválidos." , "Favor verificar seu usuário e senha.");
			throw exception;
		}
		AutenticacaoService service = new AutenticacaoService();
		return service.gerarSessao(usuario);
	}
	
}
