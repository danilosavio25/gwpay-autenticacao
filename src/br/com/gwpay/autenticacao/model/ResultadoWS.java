package br.com.gwpay.autenticacao.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ResultadoWS {
	
	private String codigoResposta;
    private String mensagemResposta;
    private String token;
	
    public ResultadoWS() {
	}
	
	public String getCodigoResposta() {
		return codigoResposta;
	}
	public void setCodigoResposta(String codigoResposta) {
		this.codigoResposta = codigoResposta;
	}
	public String getMensagemResposta() {
		return mensagemResposta;
	}
	public void setMensagemResposta(String mensagemResposta) {
		this.mensagemResposta = mensagemResposta;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String descricaoResposta) {
		this.token = descricaoResposta;
	}
	
}
