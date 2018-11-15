package br.uniriotec.bsi.tp2.JogoTrivia_SERVER.service;

import javax.ws.rs.core.Response.Status;

public class MensagemErro {
	int status;
	int codigoErro;
	String mensagem;

	public MensagemErro(int status, int codigoErro, String mensagem) {
		this.status = status;
		this.codigoErro = codigoErro;
		this.mensagem = mensagem;
	}

	public MensagemErro(int status, CodigoErro codigoErro, String mensagem) {
		this.status = status;
		this.codigoErro = codigoErro.CODIGO;
		this.mensagem = mensagem;
	}

	public MensagemErro(Status status, int codigoErro, String mensagem) {
		this.status = status.getStatusCode();
		this.codigoErro = codigoErro;
		this.mensagem = mensagem;
	}

	public MensagemErro(Status status, CodigoErro codigoErro, String mensagem) {
		this.status = status.getStatusCode();
		this.codigoErro = codigoErro.CODIGO;
		this.mensagem = mensagem;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getCodigoErro() {
		return codigoErro;
	}

	public void setCodigoErro(int codigoErro) {
		this.codigoErro = codigoErro;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
